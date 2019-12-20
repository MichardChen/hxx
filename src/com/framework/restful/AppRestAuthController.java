package com.framework.restful;

import com.framework.constants.Constants;
import com.framework.dto.FishDTO;
import com.framework.dto.ParamsDTO;
import com.framework.entity.TQuestionEntity;
import com.framework.service.RestfulService;
import com.framework.utils.ReturnData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
}
