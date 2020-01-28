package com.framework.restful;

import com.alibaba.fastjson.JSONObject;
import com.framework.constants.Constants;
import com.framework.dao.MemberDao;
import com.framework.dto.FishDTO;
import com.framework.dto.ParamsDTO;
import com.framework.entity.Member;
import com.framework.entity.TQuestionEntity;
import com.framework.service.FileService;
import com.framework.service.MemberService;
import com.framework.service.RestfulService;
import com.framework.utils.*;
import com.framework.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    MemberService memberService;
    @Autowired
    MemberDao memberDao;

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

        ReturnData data = new ReturnData();
        FishDTO dto = FishDTO.getInstance(request);

        Member m = memberDao.queryByMobile(dto.getMobile());
        if(m == null){
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，用户不存在");
            renderJson(data,response);
            return;
        }
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
        member.setMemberGradeCd(memberGradeCd);
        userVo.setMemberGradeCd(dto.getMemberGradeCd());

        //更新用户数据
        memberService.update(member);
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
        renderJson(restfulService.getExchangeRecords(FishDTO.getInstance(request)), response);
        return;
    }

    /**
     * 兑换记录详情
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/getExchangeDetail")
    public void getExchangeDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.getExchangeDetail(FishDTO.getInstance(request)), response);
        return;
    }

    /**
     * 修改昵称
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/modifyNickName")
    public void modifyNickName(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.modifyNickName(FishDTO.getInstance(request)), response);
        return;
    }

    /**
     * 修改头像
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/modifyIcon")
    public void modifyIcon(@RequestParam(value = "iconFile", required = false) MultipartFile iconFile
                            ,HttpServletRequest request, HttpServletResponse response) throws Exception {

        FishDTO dto = FishDTO.getInstance(request);
        ReturnData data = new ReturnData();
        Member m = memberDao.queryByMobile(dto.getMobile());
        if(m == null){
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，用户不存在");
            renderJson(data,response);
            return;
        }

        FileService fs = new FileService();
        String url = fs.upload(iconFile, Constants.FILE_HOST.ICON, Constants.HOST.ICON);
        if (StringUtil.isNoneBlank(url)) {
            int ret = memberDao.updateIcon(url,m.getId());
            if(ret != 0){
                data.setCode(Constants.STATUS_CODE.SUCCESS);
                data.setMessage("更新成功");
                renderJson(data,response);
                return;
            }else{
                data.setCode(Constants.STATUS_CODE.FAIL);
                data.setMessage("更新失败");
                renderJson(data,response);
                return;
            }
        }else{
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("更新失败");
            renderJson(data,response);
            return;
        }
    }

    /**
     * 留言板
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/saveFeedback")
    public void saveFeedback(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.saveFeedback(FishDTO.getInstance(request)), response);
        return;
    }

    /**
     * 金融列表
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/getFinanceList")
    public void getFinanceList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.getFinanceList(FishDTO.getInstance(request)), response);
        return;
    }

    /**
     * 金融产品详情
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/getFinanceDetail")
    public void getFinanceDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.getFinanceDetail(FishDTO.getInstance(request)), response);
        return;
    }

    /**
     * 审核金融产品
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/applyFinance")
    public void applyFinance(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.applyFinance(FishDTO.getInstance(request)), response);
        return;
    }

    /**
     * 保险列表
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/getInsuranceList")
    public void getInsuranceList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.getInsuranceList(FishDTO.getInstance(request)), response);
        return;
    }

    /**
     * 保险产品详情
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/getInsuranceDetail")
    public void getInsuranceDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.getInsuranceDetail(FishDTO.getInstance(request)), response);
        return;
    }

    /**
     * 申请保险产品
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/applyInsurance")
    public void applyInsurance(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.applyInsurance(FishDTO.getInstance(request)), response);
        return;
    }

    /**
     * 发布供应
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/saveSupplyInfo")
    public void saveSupplyInfo(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value = "imgFiles", required = false) MultipartFile[] imgFiles) throws Exception {

        FishDTO dto = FishDTO.getInstance(request);
        // 生成html
        FileService fs = new FileService();
        String logo = "";
        //多文件上传
        if(imgFiles != null){
            for(MultipartFile file : imgFiles){
                String url = fs.upload(file, Constants.FILE_HOST.IMG, Constants.HOST.IMG);
                logo = logo + "," + url;
            }
        }


        if (StringUtil.isNoneBlank(logo)) {
            dto.setUrl(logo);
        }
        renderJson(restfulService.saveSupplyInfo(dto), response);
        return;
    }

    /**
     * 供应大厅/供应列表
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/querySupplyList")
    public void querySupplyList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.querySupplyList(FishDTO.getInstance(request)), response);
        return;
    }

    /**
     * 供应详情
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/querySupplyDetail")
    public void querySupplyDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.querySupplyDetail(FishDTO.getInstance(request)), response);
        return;
    }

    /**
     * 下单
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/saveSupplyOrder")
    public void saveSupplyOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.saveSupplyOrder(FishDTO.getInstance(request)), response);
        return;
    }

    /**
     * 发布求购
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/saveBuyInfo")
    public void saveBuyInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        FishDTO dto = FishDTO.getInstance(request);
        renderJson(restfulService.saveBuyInfo(dto), response);
        return;
    }

    /**
     * 求购列表
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/queryBuyList")
    public void queryBuyList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.queryBuyList(FishDTO.getInstance(request)), response);
        return;
    }

    /**
     * 求购详情
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/queryBuyDetail")
    public void queryBuyDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.queryBuyDetail(FishDTO.getInstance(request)), response);
        return;
    }

    /**
     *我的供应
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/queryMySupplyList")
    public void queryMySupplyList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.queryMySupplyList(FishDTO.getInstance(request)), response);
        return;
    }

    /**
     * 我的求购
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/queryMyBuyList")
    public void queryMyBuyList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.queryMyBuyList(FishDTO.getInstance(request)), response);
        return;
    }

    /**
     * 买卖行情
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/queryMarketPriceList")
    public void queryMarketPriceList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.queryMarketPriceList(FishDTO.getInstance(request)), response);
        return;
    }

    /**
     * 获取供应订单
     * @param request
     * @param response
     * @throws Exception
     */
    @PostMapping("/getSupplyOrderList")
    public void getSupplyOrderList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        renderJson(restfulService.getSupplyOrderList(FishDTO.getInstance(request)), response);
        return;
    }
}
