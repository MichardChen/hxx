package com.framework.restful;

import com.alibaba.fastjson.JSONObject;
import com.framework.constants.Constants;
import com.framework.dto.FishDTO;
import com.framework.dto.ParamsDTO;
import com.framework.entity.Member;
import com.framework.entity.TQuestionEntity;
import com.framework.service.FileService;
import com.framework.service.MemberService;
import com.framework.service.RestfulService;
import com.framework.utils.BeanUtils;
import com.framework.utils.DateUtil;
import com.framework.utils.ReturnData;
import com.framework.utils.StringUtil;
import com.framework.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 需要token验证的controller
 * @author ChenDang
 * @date 2019/12/16 0016
 */
@Controller
@RequestMapping("/apprest/auth")
public class AppRestAuthController extends RestfulController{

    @Autowired
    RestfulService restfulService;
    @Autowired
    MemberService memberService;

    //在线咨询
    @RequestMapping("/index")
    public void index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ParamsDTO dto = ParamsDTO.getInstance(request);
        TQuestionEntity entity = new TQuestionEntity();
        ReturnData data = new ReturnData();
        data.setCode(Constants.STATUS_CODE.SUCCESS);
        data.setMessage("谢谢您的提交，我们会尽快给您反馈");
        renderJson(data, response);
        return;
    }

    //查询个人数据接口
    @PostMapping("/getPersonData")
    public void getPersonData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.getPersonData(FishDTO.getInstance(request)), response);
        return;
    }

    /**
     * 退出
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.logout(FishDTO.getInstance(request)), response);
        return;
    }

    /**
     * 修改密码
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/modifyPwd")
    public void modifyPwd(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.modifyPwd(FishDTO.getInstance(request)), response);
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
        userVo.setStatus(member.getStatus());
        member.setUpdateTime(DateUtil.getNowTimestamp());
        member.setMobile(dto.getMobile());

        //更新用户数据
        memberService.update(member);
        ReturnData data = new ReturnData();
        JSONObject map = new JSONObject();
        map.put("userVo",userVo);
        data.setData(map);
        data.setMessage("恭喜您，上传成功，平台将在1-3个工作日审核，请耐心等待");
        data.setCode(Constants.STATUS_CODE.SUCCESS);
        renderJson(data,response);
        return;
    }

    /**
     * 获取积分商城首页数据
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/getMallIndex")
    public void getMallIndex(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.getMallIndex(FishDTO.getInstance(request)), response);
        return;
    }

    /**
     * 查看商品详情
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/getMallProductDetail")
    public void getMallProductDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.getMallProductDetail(FishDTO.getInstance(request)), response);
        return;
    }

    /**
     * 兑换
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/exchangePoints")
    public void exchangePoints(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.exchangePoints(FishDTO.getInstance(request)), response);
        return;
    }

    /**
     * 兑换记录
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/getExchangeRecords")
    public void getExchangeRecords(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.exchangePoints(FishDTO.getInstance(request)), response);
        return;
    }
}
