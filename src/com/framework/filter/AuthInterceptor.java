package com.framework.filter;

import com.framework.constants.Constants;
import com.framework.dto.FishDTO;
import com.framework.dto.UserDTO;
import com.framework.entity.Member;
import com.framework.entity.UserToken;
import com.framework.service.MemberService;
import com.framework.service.UserTokenService;
import com.framework.utils.DateUtil;
import com.framework.utils.ReturnData;
import com.framework.utils.SendMsgUtil;
import com.framework.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

/**
 * token校验拦截器
 * @author ChenDang
 * @date 2019/12/16 0016
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    UserTokenService userTokenService;
    @Autowired
    MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        boolean handleResult = true;
        String token = request.getHeader("token");
        String platForm = request.getParameter("platform");
        FishDTO fishDTO = FishDTO.getInstance(request);
        fishDTO.setToken(token);

        UserDTO userDTO = UserDTO.getInstance(request);
        ReturnData data = new ReturnData();
        data.setCode(Constants.STATUS_CODE.SUCCESS);

        Member member = memberService.queryByMobile(userDTO.getMobile());
        if(member == null){
            //用户不存在
            data.setMessage("对不起，您的账号未注册");
            data.setCode(Constants.STATUS_CODE.FAIL);
            SendMsgUtil.sendJsonMessage(response,data);
            return false;
        }

        if(handleResult){
            Integer userId = member.getId();
            UserToken at = userTokenService.queryPlatToken(userId
                                                            ,userDTO.getUserTypeCd()
                                                            ,platForm
                                                            ,token);

            UserToken androidToken = userTokenService.queryToken(userId
                                                                ,userDTO.getUserTypeCd()
                                                                ,Constants.PLATFORM.ANDROID);

            UserToken iosToken = userTokenService.queryToken(userId
                                                            ,userDTO.getUserTypeCd()
                                                            ,Constants.PLATFORM.IOS);

            if((androidToken == null)&&(iosToken == null)){
                data.setCode(Constants.STATUS_CODE.FAIL);
                data.setMessage("对不起，您还没有登录");
                SendMsgUtil.sendJsonMessage(response,data);
                return false;
            }

            if(((StringUtil.equals(Constants.PLATFORM.ANDROID, platForm))
                    &&(iosToken != null)
                    &&(StringUtil.isNoneBlank(iosToken.getToken())))||
                    ((StringUtil.equals(Constants.PLATFORM.IOS, platForm))
                            &&(androidToken != null)
                            &&StringUtil.isNoneBlank(androidToken.getToken()))){
                data.setCode(Constants.LOGIN_STATUS_CODE.LOGIN_ANOTHER_PLACE);
                data.setMessage("对不起，您的账号在另一个地点登录，您被迫下线了，请重新登录");
                SendMsgUtil.sendJsonMessage(response,data);
                return false;
            }

            if(at==null || !StringUtil.equals(token, at.getToken())){
                //不相等，您的账号在另一个地点登录，您被迫下线了，请重新登录
                data.setCode(Constants.LOGIN_STATUS_CODE.LOGIN_ANOTHER_PLACE);
                data.setMessage("对不起，您的账号在另一个地点登录，您被迫下线了，请重新登录");
                SendMsgUtil.sendJsonMessage(response,data);
                return false;
            }else {
                //判断是不是token过期了
                Timestamp now = DateUtil.getNowTimestamp();
                if (at.getExpireTime() == null || now.after(at.getExpireTime())) {
                    //过期了
                    data.setCode(Constants.LOGIN_STATUS_CODE.LOGIN_ANOTHER_PLACE);
                    data.setMessage("对不起，登录账号过期了，请重新登录");
                    SendMsgUtil.sendJsonMessage(response,data);
                    return false;
                }
            }
        }
        return handleResult;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }


}
