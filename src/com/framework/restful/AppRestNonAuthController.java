package com.framework.restful;

import com.framework.dto.FishDTO;
import com.framework.service.MemberService;
import com.framework.service.RestfulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    /**
     * 获取app用版本控制的数据
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/getAppConstantDatas")
    public void getAppConstantDatas(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.getAppConstantDatas(FishDTO.getInstance(request)), response);
        return;
    }

}
