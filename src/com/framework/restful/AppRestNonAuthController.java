package com.framework.restful;

import com.alibaba.fastjson.JSONObject;
import com.framework.constants.Constants;
import com.framework.dto.FishDTO;
import com.framework.entity.Member;
import com.framework.entity.TBrandEntity;
import com.framework.service.FileService;
import com.framework.service.MemberService;
import com.framework.service.RestfulService;
import com.framework.utils.*;
import com.framework.vo.UserVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 不需要token验证的controller
 *
 * @author ChenDang
 * @date 2019/12/16 0016
 */
@Controller
@RequestMapping("/apprest/nonAuth")
public class AppRestNonAuthController extends RestfulController {

    @Autowired
    RestfulService restfulService;
    @Autowired
    MemberService memberService;

    //首页
    @PostMapping("/index")
    public void index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.index(FishDTO.getInstance(request)), response);
        return;
    }

    //资讯列表
    @PostMapping("/getNewsList")
    public void getNewsList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.getNewsList(FishDTO.getInstance(request)), response);
        return;
    }

    /**
     * 保存认证资料
     * authFile1 身份证正面
     * authFile2 身份证反面
     * authFile3 手持身份证/营业执照
     * @throws Exception
     */
    @PostMapping("/saveAuthenticationFile")
    public void saveAuthenticationFile(@RequestParam(value = "authFile1", required = false) MultipartFile authFile1
                                      ,@RequestParam(value = "authFile2", required = false) MultipartFile authFile2
                                      ,@RequestParam(value = "authFile3", required = false) MultipartFile authFile3
                                      ,HttpServletRequest request
                                      ,HttpServletResponse response) throws Exception{

        FishDTO dto = FishDTO.getInstance(request);
        //个人还是企业
        String memberGradeCd = dto.getMemberGradeCd();
        UserVo userVo = new UserVo();

        // 生成html
        FileService fs = new FileService();
        //身份证正面
        String idCardImgUrl = fs.upload(authFile1, Constants.FILE_HOST.IMG, Constants.HOST.IMG);
        if (StringUtil.isNoneBlank(idCardImgUrl)) {
            userVo.setIdCardImgUrl(idCardImgUrl);
        }

        //身份证反面
        String idCardReverseImgUrl = fs.upload(authFile2, Constants.FILE_HOST.IMG, Constants.HOST.IMG);
        if (StringUtil.isNoneBlank(idCardReverseImgUrl)) {
            userVo.setIdCardReverseImgUrl(idCardReverseImgUrl);
        }

        //手持身份证或者企业营业执照
        String imgUrl = fs.upload(authFile3, Constants.FILE_HOST.IMG, Constants.HOST.IMG);
        if (StringUtil.isNoneBlank(imgUrl)) {
            if(Constants.MEMBER_GRADE.PERSON.equals(memberGradeCd)){
                userVo.setHandIdCardImgUrl(imgUrl);
            }else if(Constants.MEMBER_GRADE.COMPANY.equals(memberGradeCd)){
                userVo.setBusinessImgUrl(imgUrl);
            }
        }

        userVo.setMemberGradeCd(memberGradeCd);
        Member member = BeanUtils.getMemberByUserVo(userVo);
        member.setStatus(Constants.MEMBER_STATUS.STAY_REVIRE);
        member.setUpdateTime(DateUtil.getNowTimestamp());
        //更新用户数据
        memberService.update(member);
        ReturnData data = new ReturnData();
        data.setMessage("恭喜您，上传成功，平台将在1-3个工作日审核，请耐心等待");
        data.setCode(Constants.STATUS_CODE.SUCCESS);
        renderJson(data,response);
        return;
    }

    /**
     * 获取验证码
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/getCheckCode")
    public void getCheckCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.getCheckCode(FishDTO.getInstance(request)), response);
        return;
    }

    /**
     * 登录
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.login(FishDTO.getInstance(request)), response);
        return;
    }

    /**
     * 注册
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/register")
    public void register(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.register(FishDTO.getInstance(request)), response);
        return;
    }

    /**
     * 保存忘记密码
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/saveForgetPwd")
    public void saveForgetPwd(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.saveForgetPwd(FishDTO.getInstance(request)), response);
        return;
    }

}
