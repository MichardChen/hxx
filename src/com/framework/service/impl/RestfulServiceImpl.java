package com.framework.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.framework.constants.Constants;
import com.framework.dao.*;
import com.framework.dto.FishDTO;
import com.framework.entity.*;
import com.framework.restful.BrandSeriesVo;
import com.framework.service.CommonService;
import com.framework.service.RestfulService;
import com.framework.utils.*;
import com.framework.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ChenDang
 * @date 2019/12/17 0017
 */
@Service("restfulService")
public class RestfulServiceImpl implements RestfulService {

    private final static Logger logger = LoggerFactory.getLogger(RestfulServiceImpl.class);

    @Autowired
    TCodemstDao tCodemstDao;
    @Autowired
    TNewsDao tNewsDao;
    @Autowired
    TCarouselDao tCarouselDao;
    @Autowired
    MemberDao memberDao;
    @Autowired
    TVertifyCodeDao vertifyCodeDao;
    @Autowired
    UserTokenDao userTokenDao;
    @Autowired
    UserDeviceTokenDao userDeviceTokenDao;
    @Autowired
    MallProductDao mallProductDao;
    @Autowired
    MallPointsRecordDao mallPointsRecordDao;
    @Autowired
    MallPointsExchangeRecordDao exchangeRecordDao;
    @Autowired
    TQuestionDao tQuestionDao;
    @Autowired
    TFinanceDao tFinanceDao;
    @Autowired
    TFinanceCommitDao tFinanceCommitDao;
    @Autowired
    TInsuranceDao tInsuranceDao;
    @Autowired
    TInsuranceCommitDao tInsuranceCommitDao;
    @Autowired
    LocationProvinceDao provinceDao;
    @Autowired
    TBrandDao tBrandDao;
    @Autowired
    TFishSupplyDao supplyDao;
    @Autowired
    TFishBuyDao buyDao;
    @Autowired
    CommonService commonService;
    @Autowired
    TBrandSeriesDao brandSeriesDao;
    @Autowired
    TFishOrderDao tFishOrderDao;
    @Autowired
    TAdvertisementDao tAdvertisementDao;
    @Autowired
    TMarketPriceDao tMarketPriceDao;
    @Autowired
    LocationCityDao cityDao;
    @Autowired
    TFishOrderDao orderDao;
    @Autowired
    TFishOrderstatusDao orderstatusDao;
    @Autowired
    TDocumentDao tDocumentDao;

    /**
     * 首页接口
     *
     * @param dto
     * @return
     */
    @Override
    public ReturnData index(FishDTO dto) {
        IndexVo vo = new IndexVo();
        //获取安卓最新版本
        TCodemstEntity codemstEntity = tCodemstDao.queryByCode(Constants.APP_VERSION.ANDROID);
        if (codemstEntity != null) {
            vo.setAndroidVersion(codemstEntity.getData2());
            vo.setAndroidUrl(codemstEntity.getData3());
        }
        //获取苹果最新版本
        TCodemstEntity iosEntity = tCodemstDao.queryByCode(Constants.APP_VERSION.IOS);
        if (iosEntity != null) {
            vo.setIosVersion(iosEntity.getData2());
            vo.setIsoUrl(iosEntity.getData3());
        }
        //获取资讯列表
        Map<String, Object> newsParams = new HashMap<>();
        newsParams.put("offset", 0);
        newsParams.put("limit", 6);
        List<TNewsEntity> list = tNewsDao.queryListData(newsParams);
        NewsListVo listVo = null;
        List<NewsListVo> newsList = new ArrayList<>();
        for (TNewsEntity entity : list) {
            listVo = new NewsListVo();
            listVo.setNewsLogo(entity.getNewsLogo());
            listVo.setNewsTitle(entity.getNewsTitle());
            listVo.setUrl(entity.getContentUrl());
            listVo.setCreateTime(DateUtil.formatTimestampForDate(entity.getCreateTime()));
            newsList.add(listVo);
        }

        //查看轮播图
        List<TCarouselEntity> carouselEntities = tCarouselDao.queryListByTypeCd(Constants.CAROUSEL_TYPE.INDEX_CAROUSEL);
        List<CarouselVo> carouselVos = new ArrayList<>();
        CarouselVo carouselVo = null;
        for (TCarouselEntity entity : carouselEntities) {
            carouselVo = new CarouselVo();
            carouselVo.setImgUrl(entity.getImgUrl());
            carouselVo.setLinkUrl(entity.getRealUrl());
            carouselVos.add(carouselVo);
        }

        JSONObject jsonObject = new JSONObject();

        //查询文档
        String[] code1 = new String[]{Constants.DOCUMENT_ABOUTUS.ABOUTUS};
        String[] code2 = new String[]{Constants.DOCUMENT_ABOUTUS.HELP_CENTER};
        String[] userabout = new String[]{Constants.DOCUMENT_ABOUTUS.USER_ABOUT};

        List<TDocumentEntity> aboutUs = tDocumentDao.queryDocumentByTypeCd(code1);
        List<TDocumentEntity> helpcenter = tDocumentDao.queryDocumentByTypeCd(code2);
        List<TDocumentEntity> useraboutList = tDocumentDao.queryDocumentByTypeCd(userabout);

        if(aboutUs.size() != 0){
            TDocumentEntity about = aboutUs.get(0);
            jsonObject.put("aboutUsUrl",about.getDescUrl());
        }else{
            jsonObject.put("aboutUsUrl","");
        }
        if(helpcenter.size() != 0){
            TDocumentEntity help = helpcenter.get(0);
            jsonObject.put("helpCenterUrl",help.getDescUrl());
        }else{
            jsonObject.put("helpCenterUrl","");
        }

        if(useraboutList.size() != 0){
            TDocumentEntity entity = useraboutList.get(0);
            jsonObject.put("useraboutUrl",entity.getDescUrl());
        }else{
            jsonObject.put("useraboutUrl","");
        }


        ReturnData data = new ReturnData();
        data.setCode(Constants.STATUS_CODE.SUCCESS);
        data.setMessage("查询成功");
        jsonObject.put("appVerson", vo);
        jsonObject.put("newsList", newsList);
        jsonObject.put("carouselList", carouselVos);
        data.setData(jsonObject);
        return data;
    }

    @Override
    public ReturnData getNewsList(FishDTO dto) {
        //获取资讯列表
        Map<String, Object> map = new HashMap<>();
        map.put("offset", (dto.getPageNum() - 1) * dto.getPageSize());
        map.put("limit", dto.getPageSize());
        List<TNewsEntity> list = tNewsDao.queryListData(map);
        NewsListVo listVo = null;
        List<NewsListVo> newsList = new ArrayList<>();
        for (TNewsEntity entity : list) {
            listVo = new NewsListVo();
            listVo.setNewsLogo(entity.getNewsLogo());
            listVo.setNewsTitle(entity.getNewsTitle());
            listVo.setUrl(entity.getContentUrl());
            listVo.setCreateTime(DateUtil.formatTimestampForDate(entity.getCreateTime()));
            newsList.add(listVo);
        }

        ReturnData data = new ReturnData();
        data.setCode(Constants.STATUS_CODE.SUCCESS);
        data.setMessage("查询成功");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("newsList", newsList);
        data.setData(jsonObject);
        return data;
    }

    @Override
    public ReturnData getPersonData(FishDTO dto) {

        ReturnData data = new ReturnData();
        Member member = memberDao.queryByMobile(dto.getMobile());
        if (member == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("查询失败，您的账号不存在");
            return data;
        }
        UserVo userVo = new UserVo();
        userVo.setNickName(member.getNickName());
        userVo.setIdCardImgUrl(member.getIdCardImg());
        userVo.setIdCardReverseImgUrl(member.getIdCardReverseImg());
        userVo.setHandIdCardImgUrl(member.getHandIdcardImg());
        userVo.setBusinessImgUrl(member.getBusinessImg());
        userVo.setHeaderImgUrl(member.getIcon());
        userVo.setMemberGradeCd(member.getMemberGradeCd());
        userVo.setStatus(member.getStatus());
        TCodemstEntity status = tCodemstDao.queryByCode(member.getStatus());
        if(status != null){
            userVo.setStatusName(status.getName());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userData", userVo);
        data.setData(jsonObject);
        data.setCode(Constants.STATUS_CODE.SUCCESS);
        data.setMessage("查询成功");

        return data;
    }

    /**
     * 短信发送手机号码需要做混淆再做加密
     * 18250752939->md5加盐加密j8!@9ACIj##dk
     *
     * @param dto
     * @return
     */
    @Override
    public ReturnData getCheckCode(FishDTO dto) {
        ReturnData data = new ReturnData();
        String mobile = dto.getMobile();
        StringBuffer sb = new StringBuffer();
        sb.append(mobile).append("kdk$$%&_UIs873");
        String encryMobile = MD5Utils.getStrMD5(sb.toString());
        //System.out.println("获取验证码参数：md5Mobile："+dto.getMd5Mobile().toLowerCase()+",encryMobile："+encryMobile+",shortMsgTypeCd："+dto.getShortMsgTypeCd());
        if (!encryMobile.equals(dto.getMd5Mobile()) || dto.getShortMsgTypeCd() == null) {
            //非法发送短信
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("发送失败");
            return data;
        }

        Member member = memberDao.queryByMobile(mobile);
        if (Constants.SHORT_MSG_TYPE.REGISTER.equals(dto.getShortMsgTypeCd())) {
            //注册短信，判断是否注册过
            if (member != null) {
                data.setCode(Constants.STATUS_CODE.FAIL);
                data.setMessage("对不起，您的手机号已经注册");
                return data;
            }
        }

        if (Constants.SHORT_MSG_TYPE.FORGET_PASSWORD.equals(dto.getShortMsgTypeCd())) {
            //忘记密码短信，判断是否注册过
            if (member == null) {
                data.setCode(Constants.STATUS_CODE.FAIL);
                data.setMessage("对不起，您的手机号未注册");
                return data;
            }
        }

        //判断此设备是否太频繁获取验证码,最近十分钟之内，最多获取5次
        int msgCount = userDeviceTokenDao.queryToken(dto.getDeviceToken(), new Timestamp(System.currentTimeMillis() - 10 * 60 * 1000), new Timestamp(System.currentTimeMillis()));
        TCodemstEntity codemstEntity = tCodemstDao.queryByCode(Constants.STATIC_PARAMS.SHOWMSG_COUNT);
        int limit = 10;
        if (codemstEntity != null) {
            limit = codemstEntity.getData1();
        }
        if (msgCount >= limit) {
            //超出发送条数限制
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，您获取短信太频繁，请稍后重试");
            return data;
        }
        //判断有没有
        String code = VertifyUtil.getVertifyCode();
        //String code = "8396";
        TVertifyCodeEntity vc = vertifyCodeDao.queryCodeByMobile(mobile, dto.getShortMsgTypeCd());
        if (vc != null) {
            Timestamp expireTime = vc.getExpireTime();
            Timestamp nowTime = DateUtil.getNowTimestamp();
            if (expireTime.after(nowTime)) {
                data.setCode(Constants.STATUS_CODE.FAIL);
                data.setMessage("验证码已发送，请稍后重试");
                return data;
            }
        }

        TVertifyCodeEntity entity = new TVertifyCodeEntity();
        entity.setMobile(mobile);
        entity.setUserTypeCd(dto.getUserTypeCd());
        entity.setCode(code);
        entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        entity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        entity.setCodeTypeCd(dto.getShortMsgTypeCd());
        entity.setExpireTime(new Timestamp(System.currentTimeMillis() + 10 * 60 * 1000));
        TVertifyCodeEntity vCode = vertifyCodeDao.queryCodeByMobile(mobile, dto.getShortMsgTypeCd());
        if (vCode == null) {
            vertifyCodeDao.save(entity);
        } else {
            entity.setId(vc.getId());
            vertifyCodeDao.update(entity);
        }
        //发送短信
        //String shortMsg = "您的验证码是：" + code + "，10分钟内有效，请不要把验证码泄露给其他人。";
        //发送短信,0-成功
        String ret = "0";
        try {
            ret = SmsSendUtil.sendsms(mobile, code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtil.equals(ret, "1")) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("验证码发送失败，请重新获取");
        } else {
            //插入设备token
            UserDeviceToken deviceToken = new UserDeviceToken();
            deviceToken.setCode(code);
            deviceToken.setMobile(mobile);
            deviceToken.setCreateTime(DateUtil.getNowTimestamp());
            deviceToken.setUpdateTime(DateUtil.getNowTimestamp());
            deviceToken.setDeviceToken(dto.getDeviceToken());
            userDeviceTokenDao.save(deviceToken);

            data.setCode(Constants.STATUS_CODE.SUCCESS);
            data.setMessage("获取验证码成功，十分钟内有效");
        }
        return data;
    }

    @Override
    public ReturnData register(FishDTO dto) {
        ReturnData data = new ReturnData();
        String mobile = dto.getMobile();
        String userPwd = dto.getUserPwd();
        String code = dto.getCode();
        String token = TextUtil.generateUUID();
        //获取验证码有效时间
        TVertifyCodeEntity vCode = vertifyCodeDao.queryCodeByMobile(mobile, Constants.SHORT_MSG_TYPE.REGISTER);
        Timestamp expireTime = vCode == null ? null : vCode.getExpireTime();
        Timestamp now = DateUtil.getNowTimestamp();
        Member member = memberDao.queryByMobile(mobile);
        if (member != null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，您的手机号码已经注册了");
            return data;
        }

        if (expireTime == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，您还没有获取验证码");
            return data;
        }

        if ((expireTime != null) && (now.after(expireTime))) {
            //true，就是过期了
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("验证码过期了，请重新获取");
            return data;
        }

        if ((expireTime != null) && (expireTime.after(now))) {
            //没有过期，获取数据库验证码
            String dcode = vCode.getCode();
            if (!StringUtil.equals(code, dcode)) {
                //验证码错误
                data.setCode(Constants.STATUS_CODE.FAIL);
                data.setMessage("请输入正确的验证码");
                return data;
            }
        }

        Member registerMember = new Member();
        registerMember.setUpdateTime(DateUtil.getNowTimestamp());
        registerMember.setCreateTime(DateUtil.getNowTimestamp());
        registerMember.setUserPwd(userPwd);
        registerMember.setMobile(mobile);
        registerMember.setStatus(Constants.MEMBER_STATUS.NOT_REVIEW);
        registerMember.setUserTypeCd(dto.getUserTypeCd());
        TCarouselEntity defaultIcon = tCarouselDao.queryCarouselByTypeCd(Constants.CAROUSEL_TYPE.DEFAULT_ICON);
        if(defaultIcon != null){
            registerMember.setIcon(defaultIcon.getImgUrl());
        }
        registerMember.setPoints(10);
        int id = memberDao.save(registerMember);
        if (id != 0) {
            Member m = memberDao.queryByMobile(mobile);
            //插入积分记录
            MallPointsRecord record = new MallPointsRecord();
            record.setCreateTime(DateUtil.getNowTimestamp());
            record.setUpdateTime(DateUtil.getNowTimestamp());
            record.setUserTypeCd(dto.getUserTypeCd());
            record.setUserId(m.getId());
            record.setOperateTypeCd(Constants.OPERATE_TYPE.REGISTER);
            record.setMark("注册，获得积分+10");
            record.setPoint("+10");
            mallPointsRecordDao.save(record);
            //注册成功
            JSONObject map = new JSONObject();
            map.put("token", token);
            vertifyCodeDao.updateExpireCode(vCode.getId(), DateUtil.getNowTimestamp());
            //保存token
            UserToken at = userTokenDao.queryToken(m.getId(), dto.getUserTypeCd(), dto.getPlatform());
            int tokensave = 0;
            if (at == null) {
                //没有token，需要新建
                UserToken userToken = new UserToken();
                userToken.setCreateTime(DateUtil.getNowTimestamp());
                userToken.setUpdateTime(DateUtil.getNowTimestamp());
                //一个月后失效,这里使用加的方法，会导致超出int的范围，导致溢出。所以要加L
                userToken.setExpireTime(new Timestamp(DateUtil.getNowTimestamp().getTime() + 30 * 24 * 60 * 60 * 1000L));
                userToken.setPlatform(dto.getPlatform());
                userToken.setUserId(m.getId());
                userToken.setToken(token);
                userToken.setUserTypeCd(dto.getUserTypeCd());
                tokensave = userTokenDao.save(userToken);
                if (tokensave != 0) {
                    data.setCode(Constants.STATUS_CODE.SUCCESS);
                    data.setMessage("注册成功");
                    data.setData(map);
                    return data;
                } else {
                    data.setCode(Constants.STATUS_CODE.FAIL);
                    data.setMessage("注册失败");
                    return data;
                }
            } else {
                at.setToken(token);
                at.setUpdateTime(DateUtil.getNowTimestamp());
                //一个月后失效
                at.setExpireTime(new Timestamp(DateUtil.getNowTimestamp().getTime() + 30 * 24 * 60 * 60 * 1000L));
                int ret = userTokenDao.update(at);
                if (ret != 0) {
                    data.setCode(Constants.STATUS_CODE.SUCCESS);
                    data.setMessage("注册成功");
                    data.setData(map);
                } else {
                    data.setCode(Constants.STATUS_CODE.FAIL);
                    data.setMessage("注册失败");
                }
                return data;
            }
        } else {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("注册失败");
            return data;
        }
    }

    @Override
    public ReturnData login(FishDTO dto) {
        ReturnData data = new ReturnData();
        Member member = memberDao.queryByMobile(dto.getMobile());
        if (member == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，您的账号尚未注册");
            return data;
        }
        if (!StringUtil.equals(member.getUserPwd(), dto.getUserPwd())) {
            data.setMessage("对不起，密码错误");
            data.setCode(Constants.STATUS_CODE.FAIL);
            return data;
        }

        //保存token
        int userId = member.getId();
        String platForm = dto.getPlatform();
        UserToken at = userTokenDao.queryToken(userId, dto.getUserTypeCd(), platForm);
        int tokensave = 0;
        String token = TextUtil.generateUUID();
        UserToken userToken = new UserToken();
        userToken.setUserId(userId);
        userToken.setUserTypeCd(dto.getUserTypeCd());
        userToken.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        //更新token
        if (StringUtil.equals(platForm, Constants.PLATFORM.ANDROID)) {
            //如果是安卓登录，则把ios的token置空
            userToken.setToken("");
            userToken.setExpireTime(new Timestamp(System.currentTimeMillis()));
            userToken.setPlatform(Constants.PLATFORM.IOS);
            userTokenDao.update(userToken);
        }
        if (StringUtil.equals(platForm, Constants.PLATFORM.IOS)) {
            //如果是ios，则把安卓token置空
            userToken.setToken("");
            userToken.setExpireTime(new Timestamp(System.currentTimeMillis()));
            userToken.setPlatform(Constants.PLATFORM.ANDROID);
            userTokenDao.update(userToken);
        }
        if (at == null) {
            userToken.setToken(token);
            userToken.setExpireTime(new Timestamp(System.currentTimeMillis() + 30 * 24 * 60 * 60 * 1000L));
            userToken.setPlatform(platForm);
            userToken.setCreateTime(new Timestamp(System.currentTimeMillis()));
            tokensave = userTokenDao.save(userToken);
            if (tokensave != 0) {
                JSONObject map = new JSONObject();
                map.put("token", token);
                data.setData(map);
                data.setCode(Constants.STATUS_CODE.SUCCESS);
                data.setMessage("登录成功");
            } else {
                data.setCode(Constants.STATUS_CODE.FAIL);
                data.setMessage("登录失败");
            }
        } else {
            userToken.setToken(token);
            userToken.setExpireTime(new Timestamp(System.currentTimeMillis() + 30 * 24 * 60 * 60 * 1000L));
            userToken.setPlatform(platForm);
            int ret = userTokenDao.update(userToken);
            if (ret != 0) {
                JSONObject map = new JSONObject();
                map.put("token", token);
                data.setData(map);
                data.setCode(Constants.STATUS_CODE.SUCCESS);
                data.setMessage("登录成功");
                return data;
            } else {
                data.setCode(Constants.STATUS_CODE.FAIL);
                data.setMessage("登录失败");
                return data;
            }

        }
        return data;
    }

    /**
     * 退出功能
     *
     * @param dto
     * @return
     */
    @Override
    public ReturnData logout(FishDTO dto) {
        ReturnData data = new ReturnData();
        Member member = memberDao.queryByMobile(dto.getMobile());
        if (member == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，用户不存在");
            return data;
        }

        UserToken updateToken = new UserToken();
        updateToken.setUserId(member.getId());
        updateToken.setToken("");
        updateToken.setPlatform(dto.getPlatform());
        updateToken.setUpdateTime(DateUtil.getNowTimestamp());
        updateToken.setExpireTime(DateUtil.getNowTimestamp());
        updateToken.setUserTypeCd(dto.getUserTypeCd());
        int ret = userTokenDao.update(updateToken);
        if (ret != 0) {
            data.setCode(Constants.STATUS_CODE.SUCCESS);
            data.setMessage("退出成功");
        } else {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("退出失败");
        }
        return data;
    }

    /**
     * 修改密码
     *
     * @param dto
     * @return
     */
    @Override
    public ReturnData modifyPwd(FishDTO dto) {
        ReturnData data = new ReturnData();
        Member member = memberDao.queryByMobile(dto.getMobile());
        if (member == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，用户不存在");
            return data;
        }
        if (!StringUtil.equals(member.getUserPwd(), dto.getOldPwd())) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，旧密码错误");
            return data;
        }
        //保存密码
        Map<String, Object> map = new HashMap<>();
        map.put("id", member.getId());
        map.put("userPwd", dto.getUserPwd());
        int ret = memberDao.updatePassword(map);
        if (ret != 0) {
            data.setCode(Constants.STATUS_CODE.SUCCESS);
            data.setMessage("密码修改成功");
        } else {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("密码修改失败");
        }
        return data;
    }

    /**
     * 保存忘记密码
     *
     * @param dto
     * @return
     */
    @Override
    public ReturnData saveForgetPwd(FishDTO dto) {
        ReturnData data = new ReturnData();
        Member member = memberDao.queryByMobile(dto.getMobile());
        if (member == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，用户不存在");
            return data;
        }
        TVertifyCodeEntity vCode = vertifyCodeDao.queryCodeByMobile(dto.getMobile(), Constants.SHORT_MSG_TYPE.FORGET_PASSWORD);
        if (vCode == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("请重新获取验证码");
            return data;
        }
        if (!StringUtil.equals(dto.getCode(), vCode.getCode())) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("请输入正确的验证码");
            return data;
        }

        //判断验证码是不是过期
        Timestamp expireTime = (Timestamp) vCode.getExpireTime();
        Timestamp now = DateUtil.getNowTimestamp();
        if ((expireTime != null) && (now.after(expireTime))) {
            //true，就是过期了
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("验证码过期了，请重新获取");
            return data;
        } else {
            //把验证码设置为过期
            vertifyCodeDao.updateExpireCode(vCode.getId(), now);
            //保存密码
            Map<String, Object> map = new HashMap<>();
            map.put("id", member.getId());
            map.put("userPwd", dto.getUserPwd());
            int ret = memberDao.updatePassword(map);
            if (ret != 0) {
                data.setCode(Constants.STATUS_CODE.SUCCESS);
                data.setMessage("密码保存成功");
            } else {
                data.setCode(Constants.STATUS_CODE.FAIL);
                data.setMessage("密码修改失败");
            }
            return data;
        }
    }

    /**
     * 积分商城首页
     *
     * @param dto
     * @return
     */
    @Override
    public ReturnData getMallIndex(FishDTO dto) {

        System.out.println("===============================ceshi==============");
        ReturnData data = new ReturnData();
        Map<String, Object> map = new HashMap<>();
        map.put("offset", (dto.getPageNum() - 1) * dto.getPageSize());
        map.put("limit", dto.getPageSize());
        map.put("status",Constants.MALL_STATUS.NORMAL);
        List<MallProduct> list = mallProductDao.queryList(map);
        MallProductVo vo = null;
        List<MallProductVo> mallProductList = new ArrayList<>();
        for (MallProduct product : list) {
            vo = new MallProductVo();
            vo.setProductId(product.getId());
            vo.setProductLogo(product.getLogos());
            vo.setPoints(product.getNeedPoints());
            vo.setProductName(product.getProductTitle());
            mallProductList.add(vo);
        }
        Member member = memberDao.queryByMobile(dto.getMobile());
        if (member == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("查询失败，用户不存在");
            return data;
        }
        JSONObject object = new JSONObject();
        object.put("mallProductList", mallProductList);
        object.put("points", member.getPoints());
        data.setCode(Constants.STATUS_CODE.SUCCESS);
        data.setMessage("查询成功");
        data.setData(object);
        return data;
    }

    /**
     * 商品详情
     *
     * @param dto
     * @return
     */
    @Override
    public ReturnData getMallProductDetail(FishDTO dto) {

        ReturnData data = new ReturnData();
        int productId = dto.getProductId();
        if (productId == 0) {
            data.setMessage("对不起，商品不存在");
            data.setCode(Constants.STATUS_CODE.FAIL);
            return data;
        }
        MallProduct product = mallProductDao.queryObject(productId);
        JSONObject object = new JSONObject();
        object.put("productDetail", product);
        //获取发货时间及快递相关
        String[] express = new String[]{Constants.DOCUMENT_ABOUTUS.EXPRESS};
        List<TDocumentEntity> expressList = tDocumentDao.queryDocumentByTypeCd(express);
        if(expressList.size() != 0){
            TDocumentEntity entity = expressList.get(0);
            object.put("expressRelateUrl", entity.getDescUrl());
        }else{
            object.put("expressRelateUrl", "");
        }

        data.setData(object);
        data.setCode(Constants.STATUS_CODE.SUCCESS);
        data.setMessage("查询成功");
        return data;
    }

    @Transactional
    @Override
    public ReturnData exchangePoints(FishDTO dto) {
        ReturnData data = new ReturnData();
        int productId = dto.getProductId();
        int productNum = dto.getProductNum();
        MallProduct product = mallProductDao.queryObject(productId);
        if (product == null) {
            data.setMessage("对不起，商品不存在");
            data.setCode(Constants.STATUS_CODE.FAIL);
            return data;
        }
        int needPoints = product.getNeedPoints();
        int allNeedPoints = needPoints * productNum;
        Member member = memberDao.queryByMobile(dto.getMobile());
        if (member == null) {
            data.setMessage("对不起，您的账号不存在");
            data.setCode(Constants.STATUS_CODE.FAIL);
            return data;
        }
        if (!Constants.MEMBER_STATUS.REVIEW_PASS.equals(member.getStatus())) {
            data.setMessage("对不起，您的账号还未认证通过，暂时不能兑换");
            data.setCode(Constants.STATUS_CODE.FAIL);
            return data;
        }
        int userPoint = member.getPoints();
        if (userPoint < allNeedPoints) {
            data.setMessage("对不起，您的积分不足");
            data.setCode(Constants.STATUS_CODE.FAIL);
            return data;
        }
        if (productNum > product.getQuality()) {
            data.setMessage("对不起，商品库存不足");
            data.setCode(Constants.STATUS_CODE.FAIL);
            return data;
        }
        //积分够，扣积分，扣商品数量
        int pointsRet = memberDao.updatePoints(allNeedPoints, member.getId());
        if (pointsRet != 0) {
            int qualityRet = mallProductDao.updateQuality(productId, productNum);
            if (qualityRet != 0) {
                //添加积分记录
                MallPointsRecord record = new MallPointsRecord();
                record.setCreateTime(DateUtil.getNowTimestamp());
                record.setUpdateTime(DateUtil.getNowTimestamp());
                record.setMark("兑换商品ID：" + productId + "，数量：" + productNum + "，消耗积分：" + allNeedPoints);
                record.setPoint("-" + allNeedPoints);
                record.setUserId(member.getId());
                record.setUserTypeCd(Constants.USER_TYPE.USER_TYPE_CLIENT);
                record.setOperateTypeCd(Constants.OPERATE_TYPE.EXCHANGE_PRODUCT);
                int recordRet = mallPointsRecordDao.save(record);
                //插入兑换记录
                MallPointsExchangeRecord exchangeRecord = new MallPointsExchangeRecord();
                exchangeRecord.setBuyTypeCd(Constants.BUY_TYPE.POINTS);
                exchangeRecord.setCreateTime(DateUtil.getNowTimestamp());
                exchangeRecord.setUpdateTime(DateUtil.getNowTimestamp());
                exchangeRecord.setMark("积分兑换");
                exchangeRecord.setMoneys(new BigDecimal("0"));
                exchangeRecord.setPoints(allNeedPoints);
                exchangeRecord.setProductId(productId);
                exchangeRecord.setQuality(productNum);
                exchangeRecord.setUserId(member.getId());
                exchangeRecord.setStatus(Constants.EXCHANGE_STATUS.STAY_REVIEW);
                exchangeRecord.setUserTypeCd(dto.getUserTypeCd());
                exchangeRecord.setAddress(dto.getAddress());
                exchangeRecord.setReceiveMobile(dto.getReceiveMobile());
                exchangeRecord.setReceiveName(dto.getReceivePerson());
                int exchangeRecordRet = exchangeRecordDao.save(exchangeRecord);
                if (recordRet != 0 && exchangeRecordRet != 0) {
                    data.setMessage("恭喜您，兑换成功，待平台处理");
                    data.setCode(Constants.STATUS_CODE.SUCCESS);
                    return data;
                } else {
                    data.setMessage("对不起，兑换失败");
                    data.setCode(Constants.STATUS_CODE.FAIL);
                    return data;
                }
            } else {
                data.setMessage("对不起，兑换失败");
                data.setCode(Constants.STATUS_CODE.FAIL);
                return data;
            }
        } else {
            data.setMessage("对不起，兑换失败");
            data.setCode(Constants.STATUS_CODE.FAIL);
            return data;
        }
    }

    /**
     * 获取兑换记录
     *
     * @param dto
     * @return
     */
    @Override
    public ReturnData getExchangeRecords(FishDTO dto) {

        String mobile = dto.getMobile();
        ReturnData data = new ReturnData();
        Member member = memberDao.queryByMobile(mobile);
        if (member == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，您的账号不存在");
            return data;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("offset", (dto.getPageNum() - 1) * dto.getPageSize());
        map.put("limit", dto.getPageSize());
        map.put("userId", member.getId());
        List<MallPointsExchangeRecord> list = exchangeRecordDao.queryList(map);
        MallPointsExchangeRecordVo vo = null;
        List<MallPointsExchangeRecordVo> voList = new ArrayList<>();
        for (MallPointsExchangeRecord record : list) {
            vo = new MallPointsExchangeRecordVo();
            vo.setExchangeId(record.getId());
            MallProduct product = mallProductDao.queryObject(record.getProductId());
            if (product != null) {
                vo.setTitle(product.getProductTitle());
            }
            vo.setCreateTime(DateUtil.formatTimestampForDate(record.getCreateTime()));
            vo.setPoints(record.getPoints());
            voList.add(vo);
        }
        JSONObject object = new JSONObject();
        object.put("exchageList", voList);
        data.setData(object);
        data.setCode(Constants.STATUS_CODE.SUCCESS);
        data.setMessage("查询成功");
        return data;
    }

    /**
     * 积分记录
     * @param dto
     * @return
     */
    @Override
    public ReturnData getPointRecords(FishDTO dto) {
        String mobile = dto.getMobile();
        ReturnData data = new ReturnData();
        Member member = memberDao.queryByMobile(mobile);
        if (member == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，您的账号不存在");
            return data;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("offset", (dto.getPageNum() - 1) * dto.getPageSize());
        map.put("limit", dto.getPageSize());
        map.put("userId", member.getId());
        List<MallPointsRecord> list = mallPointsRecordDao.queryList(map);
        PointRecordListVo vo = null;
        List<PointRecordListVo> voList = new ArrayList<>();
        for (MallPointsRecord record : list) {
            vo = new PointRecordListVo();
            vo.setCreateTime(DateUtil.formatTimestampForDate(record.getCreateTime()));
            vo.setPoints(record.getPoint());
            TCodemstEntity mark = tCodemstDao.queryByCode(record.getOperateTypeCd());
            if(mark != null){
                vo.setMark(mark.getName());
            }
            voList.add(vo);
        }
        JSONObject object = new JSONObject();
        object.put("pointRecordList", voList);
        data.setData(object);
        data.setCode(Constants.STATUS_CODE.SUCCESS);
        data.setMessage("查询成功");
        return data;
    }

    /**
     * 获取兑换详情
     *
     * @param dto
     * @return
     */
    @Override
    public ReturnData getExchangeDetail(FishDTO dto) {

        ReturnData data = new ReturnData();
        int exchangeId = dto.getKey();
        MallPointsExchangeRecord exchangeRecord = exchangeRecordDao.queryObject(exchangeId);
        if (exchangeRecord == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，兑换记录不存在");
            return data;
        }

        MallProduct product = mallProductDao.queryObject(exchangeRecord.getProductId());
        MallPointsExchangeRecordVo vo = new MallPointsExchangeRecordVo();
        if (product != null) {
            vo.setTitle(product.getProductTitle());
        }
        vo.setCreateTime(DateUtil.formatTimestampForDate(exchangeRecord.getCreateTime()));
        vo.setPoints(exchangeRecord.getPoints());
        vo.setAddress(exchangeRecord.getAddress());
        vo.setLogistic(exchangeRecord.getLogistic());
        vo.setMark(exchangeRecord.getMark());
        vo.setMoneys(StringUtil.toString(exchangeRecord.getMoneys()));
        vo.setStatus(exchangeRecord.getStatus());
        vo.setReceiveMobile(exchangeRecord.getReceiveMobile());
        vo.setReceivePerson(exchangeRecord.getReceiveName());
        vo.setExpressRelateUrl("https://www.sf-express.com/cn/sc/");
        vo.setExchageTime(DateUtil.formatTimestampForDate(exchangeRecord.getCreateTime()));
        vo.setQuality(exchangeRecord.getQuality());

        JSONObject object = new JSONObject();
        object.put("exchageDetail", vo);
        data.setData(object);
        data.setCode(Constants.STATUS_CODE.SUCCESS);
        data.setMessage("查询成功");
        return data;
    }

    /**
     * 修改昵称
     *
     * @param dto
     * @return
     */
    @Override
    public ReturnData modifyNickName(FishDTO dto) {

        ReturnData data = new ReturnData();
        if (StringUtil.isBlank(dto.getNickName())) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，昵称不能为空");
            return data;
        }
        Member member = memberDao.queryByMobile(dto.getMobile());
        if (member == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，您的账号不存在");
            return data;
        }
        int ret = memberDao.updateNickName(dto.getNickName(), member.getId());
        if (ret != 0) {
            data.setCode(Constants.STATUS_CODE.SUCCESS);
            data.setMessage("修改成功");
            return data;
        } else {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("修改失败");
            return data;
        }
    }

    @Override
    public ReturnData saveFeedback(FishDTO dto) {
        ReturnData data = new ReturnData();
        if (StringUtil.isBlank(dto.getValue())) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，留言内容不能为空");
            return data;
        }
        Member member = memberDao.queryByMobile(dto.getMobile());
        if (member == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，您的账号不存在");
            return data;
        }
        TQuestionEntity question = new TQuestionEntity();
        question.setQuestion(dto.getValue());
        question.setLinkMan(member.getNickName());
        question.setMobile(dto.getMobile());
        question.setCreateTime(DateUtil.getNowTimestamp());
        question.setStatus(Constants.FEEDBACK_STATUS.STAY_HANDLE);
        int ret = tQuestionDao.save(question);
        if (ret != 0) {
            data.setCode(Constants.STATUS_CODE.SUCCESS);
            data.setMessage("提交成功，感谢您的宝贵意见，我们将尽快处理");
            return data;
        } else {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("提交失败");
            return data;
        }
    }

    @Override
    public ReturnData getFinanceList(FishDTO dto) {
        ReturnData data = new ReturnData();
        Map<String, Object> map = new HashMap<>();
        map.put("offset", (dto.getPageNum() - 1) * dto.getPageSize());
        map.put("limit", dto.getPageSize());
        map.put("typeCd", dto.getTypeCd());
        map.put("status", 1);
        List<TFinanceEntity> list = tFinanceDao.queryList(map);
        List<FinanceVo> voList = new ArrayList<>();
        FinanceVo vo = null;
        for (TFinanceEntity entity : list) {
            vo = new FinanceVo();
            vo.setId(entity.getId());
            vo.setFinanceName(entity.getName());
            vo.setLogo(entity.getIcon());
            vo.setRate(entity.getLowRate());
            vo.setRefund(entity.getLowRefund());
            vo.setTimeDistance(entity.getTimeDistance());
            voList.add(vo);
        }
        data.setMessage("查询成功");
        data.setCode(Constants.STATUS_CODE.SUCCESS);
        JSONObject dataMap = new JSONObject();
        dataMap.put("financeList", voList);
        data.setData(dataMap);
        return data;
    }

    @Override
    public ReturnData getFinanceDetail(FishDTO dto) {
        ReturnData data = new ReturnData();
        TFinanceEntity entity = tFinanceDao.queryObject(dto.getKey());
        FinanceDetailVo detailVo = new FinanceDetailVo();
        if (entity != null) {
            detailVo.setId(entity.getId());
            detailVo.setFinanceName(entity.getName());
            detailVo.setLogo(entity.getIcon());
            detailVo.setRate(entity.getLowRate());
            detailVo.setRefund(entity.getLowRefund());
            detailVo.setTimeDistance(entity.getTimeDistance());
            detailVo.setContentUrl(entity.getDescUrl());
        }
        //查找客服电话
        JSONObject json = new JSONObject();
        TCodemstEntity phoneEntity = tCodemstDao.queryByCode(Constants.TEL_TYPE.KEFU);
        if (phoneEntity != null) {
            json.put("phone", phoneEntity.getData2());
        } else {
            json.put("phone", "");
        }

        List<String> labelList = new ArrayList<>();
        String labels = entity.getLabels();
        if (StringUtil.isNoneBlank(labels)) {
            String[] labelArray = labels.split(",");
            for (String label : labelArray) {
                labelList.add(label);
            }
        }

        json.put("labels", labelList);
        json.put("financeDetail", detailVo);
        data.setData(json);
        data.setCode(Constants.STATUS_CODE.SUCCESS);
        data.setMessage("查询成功");
        return data;
    }

    @Override
    public ReturnData applyFinance(FishDTO dto) {

        ReturnData data = new ReturnData();
        TFinanceEntity entity = tFinanceDao.queryObject(dto.getKey());
        if (entity == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，此产品已下架");
            return data;
        }
        Member member = memberDao.queryByMobile(dto.getMobile());
        if (member == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，此用户不存在");
            return data;
        }
        if (!Constants.MEMBER_STATUS.REVIEW_PASS.equals(member.getStatus())) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，您还未进行认证，请先认证资料");
            return data;
        }
        TFinanceCommitEntity commitEntity = new TFinanceCommitEntity();
        commitEntity.setFinanceId(entity.getId());
        commitEntity.setCreateTime(DateUtil.getNowTimestamp());
        commitEntity.setUpdateTime(DateUtil.getNowTimestamp());
        commitEntity.setStatus(Constants.FEEDBACK_STATUS.STAY_HANDLE);
        commitEntity.setMark("金融产品申请");
        commitEntity.setMobile(dto.getMobile());
        commitEntity.setIdcardNo(member.getIdCardNo());
        commitEntity.setName(member.getNickName());
        int ret = tFinanceCommitDao.save(commitEntity);
        if (ret == 0) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，申请失败");
        } else {
            data.setCode(Constants.STATUS_CODE.SUCCESS);
            data.setMessage("恭喜您申请成功，待平台工作人员联系您");
        }
        return data;
    }

    @Override
    public ReturnData getInsuranceList(FishDTO dto) {
        ReturnData data = new ReturnData();
        Map<String, Object> map = new HashMap<>();
        map.put("offset", (dto.getPageNum() - 1) * dto.getPageSize());
        map.put("limit", dto.getPageSize());
        map.put("typeCd", dto.getTypeCd());
        map.put("status", 1);
        List<TInsuranceEntity> list = tInsuranceDao.queryList(map);
        List<InsuranceVo> voList = new ArrayList<>();
        InsuranceVo vo = null;
        for (TInsuranceEntity entity : list) {
            vo = new InsuranceVo();
            vo.setId(entity.getId());
            vo.setInsuranceName(entity.getName());
            vo.setLogo(entity.getIcon());
            vo.setTimeDistance(entity.getTimeDistance());
            vo.setRefund(entity.getLowRefund());
            voList.add(vo);
        }
        data.setMessage("查询成功");
        data.setCode(Constants.STATUS_CODE.SUCCESS);
        JSONObject dataMap = new JSONObject();
        dataMap.put("insuranceList", voList);
        data.setData(dataMap);
        return data;
    }

    @Override
    public ReturnData getInsuranceDetail(FishDTO dto) {
        ReturnData data = new ReturnData();
        TInsuranceEntity entity = tInsuranceDao.queryObject(dto.getKey());
        InsuranceDetailVo detailVo = new InsuranceDetailVo();
        if (entity != null) {
            detailVo.setId(entity.getId());
            detailVo.setInsuranceName(entity.getName());
            detailVo.setLogo(entity.getIcon());
            detailVo.setTimeDistance(entity.getTimeDistance());
            detailVo.setRefund(entity.getLowRefund());
            detailVo.setContentUrl(entity.getDescUrl());
        }
        //查找客服电话
        JSONObject json = new JSONObject();
        TCodemstEntity phoneEntity = tCodemstDao.queryByCode(Constants.TEL_TYPE.KEFU);
        if (phoneEntity != null) {
            json.put("phone", phoneEntity.getData2());
        } else {
            json.put("phone", "");
        }

        List<String> labelList = new ArrayList<>();
        String labels = entity.getLabels();
        if (StringUtil.isNoneBlank(labels)) {
            String[] labelArray = labels.split(",");
            for (String label : labelArray) {
                labelList.add(label);
            }
        }

        json.put("labels", labelList);
        json.put("insuranceDetail", detailVo);
        data.setData(json);
        data.setCode(Constants.STATUS_CODE.SUCCESS);
        data.setMessage("查询成功");
        return data;
    }

    @Override
    public ReturnData applyInsurance(FishDTO dto) {
        ReturnData data = new ReturnData();
        TInsuranceEntity entity = tInsuranceDao.queryObject(dto.getKey());
        if (entity == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，此产品已下架");
            return data;
        }
        Member member = memberDao.queryByMobile(dto.getMobile());
        if (member == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，此用户不存在");
            return data;
        }
        if (!Constants.MEMBER_STATUS.REVIEW_PASS.equals(member.getStatus())) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，您还未进行认证，请先认证资料");
            return data;
        }
        TInsuranceCommitEntity commitEntity = new TInsuranceCommitEntity();
        commitEntity.setFinanceId(entity.getId());
        commitEntity.setCreateTime(DateUtil.getNowTimestamp());
        commitEntity.setUpdateTime(DateUtil.getNowTimestamp());
        commitEntity.setStatus(Constants.FEEDBACK_STATUS.STAY_HANDLE);
        commitEntity.setMark("保险产品申请");
        commitEntity.setMobile(dto.getMobile());
        commitEntity.setIdcardNo(member.getIdCardNo());
        commitEntity.setName(member.getNickName());
        int ret = tInsuranceCommitDao.save(commitEntity);
        if (ret == 0) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，申请失败");
        } else {
            data.setCode(Constants.STATUS_CODE.SUCCESS);
            data.setMessage("恭喜您申请成功，待平台工作人员联系您");
        }
        return data;
    }

    @Override
    public ReturnData getAppConstantDatas(FishDTO dto) {

        //Map<Integer,List<ProvinceCityVo>> cityMap = new HashMap<>();
        //Map<Integer,List<BrandSeriesVo>> brandMap = new HashMap<>();
        List<ProvinceCityVo> list = provinceDao.queryAllProviceCity();
        List<BrandSeriesVo> brandSeriesVos = tBrandDao.queryAllBrandSeries();
        List<String> fishTypeList = tCodemstDao.queryNamesByPcode("260000");
        /*for(ProvinceCityVo vo : list){
            if(cityMap.containsKey(vo.getProvinceId())){
                //map集合包含key
                List<ProvinceCityVo> cityList = cityMap.get(vo.getProvinceId());
                cityList.add(vo);
            }else{
                //map集合不包含key
                List<ProvinceCityVo> cityList = new ArrayList<>();
                cityList.add(vo);
                cityMap.put(vo.getProvinceId(),cityList);
            }
        }

        for(BrandSeriesVo vo : brandSeriesVos){
            if(brandMap.containsKey(vo.getBrandId())){
                //map集合包含key
                List<BrandSeriesVo> brandList = brandMap.get(vo.getBrandId());
                brandList.add(vo);
            }else{
                //map集合不包含key
                List<BrandSeriesVo> brandList = new ArrayList<>();
                brandList.add(vo);
                brandMap.put(vo.getBrandId(),brandList);
            }
        }*/

        ReturnData data = new ReturnData();
        JSONObject json = new JSONObject();
       /* if(Constants.PLATFORM.ANDROID.equals(dto.getPlatform())){
            json.put("cityList", list);
            json.put("fishList", brandSeriesVos);
            json.put("fishTypeList", fishTypeList);
        }else{*/
        //苹果
        //省市数据
        List<ParentVo> cityList = new ArrayList<>();
        Map<Integer, ParentVo> cityMap = new HashMap<>();
        for (ProvinceCityVo vo : list) {
            if (cityMap.containsKey(vo.getProvinceId())) {
                //如果包含
                ParentVo parentVo = cityMap.get(vo.getProvinceId());
                List<ChildVo> childVos = parentVo.getChilds();
                ChildVo childVo = new ChildVo();
                childVo.setChildId(vo.getCityId());
                childVo.setChildName(vo.getCityName());
                childVos.add(childVo);
            } else {
                //不包含
                ParentVo parentVo = new ParentVo();
                parentVo.setParentId(vo.getProvinceId());
                parentVo.setParentName(vo.getProvinceName());
                List<ChildVo> childVos = new ArrayList<>();
                ChildVo childVo = new ChildVo();
                childVo.setChildId(vo.getCityId());
                childVo.setChildName(vo.getCityName());
                childVos.add(childVo);
                parentVo.setChilds(childVos);
                cityMap.put(vo.getProvinceId(), parentVo);
            }
        }
        for (Map.Entry<Integer, ParentVo> entry : cityMap.entrySet()) {
            cityList.add(entry.getValue());
        }
        //鱼类种类
        List<FishParentVo> fishList = new ArrayList<>();
        Map<Integer, FishParentVo> fishMap = new HashMap<>();
        for (BrandSeriesVo vo : brandSeriesVos) {
            if (fishMap.containsKey(vo.getBrandId())) {
                //如果包含
                FishParentVo parentVo = fishMap.get(vo.getBrandId());
                List<FishChildVo> childVos = parentVo.getChilds();
                FishChildVo childVo = new FishChildVo();
                childVo.setChildId(vo.getSeriesId());
                childVo.setChildName(vo.getSeriesName());
                childVo.setLogo(vo.getLogo());
                childVos.add(childVo);
            } else {
                //不包含
                FishParentVo parentVo = new FishParentVo();
                parentVo.setParentId(vo.getBrandId());
                parentVo.setParentName(vo.getBrandName());
                List<FishChildVo> childVos = new ArrayList<>();
                FishChildVo childVo = new FishChildVo();
                childVo.setChildId(vo.getSeriesId());
                childVo.setChildName(vo.getSeriesName());
                childVo.setLogo(vo.getLogo());
                childVos.add(childVo);
                parentVo.setChilds(childVos);
                fishMap.put(vo.getBrandId(), parentVo);
            }
        }

        for (Map.Entry<Integer, FishParentVo> entry : fishMap.entrySet()) {
            fishList.add(entry.getValue());
        }

        json.put("cityList", cityList);
        json.put("fishList", fishList);
        json.put("fishTypeList", fishTypeList);
        //}

        TCodemstEntity dataVersion = tCodemstDao.queryByCode("180002");
        if (dataVersion != null) {
            json.put("dataVersion", dataVersion.getData1());
        } else {
            json.put("dataVersion", "");
        }
        data.setData(json);
        data.setCode(Constants.STATUS_CODE.SUCCESS);
        data.setMessage("查询成功");
        return data;
    }

    @Transactional
    @Override
    public ReturnData saveSupplyInfo(FishDTO dto) {

        ReturnData data = new ReturnData();
        Member member = memberDao.queryByMobile(dto.getMobile());
        if (member == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，此用户不存在");
            return data;
        }
        if (!Constants.MEMBER_STATUS.REVIEW_PASS.equals(member.getStatus())) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，您还未进行认证，请先认证资料");
            return data;
        }
        TFishSupplyEntity entity = new TFishSupplyEntity();
        entity.setOrderNo("G" + getOrderIdByTime());
        entity.setProductType(dto.getTypeCd());
        entity.setMainType(dto.getMainType());
        entity.setImg(dto.getUrl());
        entity.setSize(dto.getSize());
        entity.setUnit(dto.getUnit());
        entity.setPrice(dto.getPrice());
        entity.setWeight(dto.getSupply());
        entity.setCreateTime(DateUtil.getNowTimestamp());
        entity.setUpdateTime(DateUtil.getNowTimestamp());
        entity.setProvice(dto.getProvince());
        entity.setCity(dto.getCity());
        entity.setMark(dto.getMark());
        entity.setMemberId(member.getId());
        entity.setLatestTime(dto.getLatestTime());
        entity.setStatus(Constants.ORDER_STUTUS.STAY_REVIEW);
        int ret = supplyDao.save(entity);
        if (ret != 0) {
            commonService.saveOrderStatus(0, entity.getOrderNo()
                    , Constants.ORDER_TYPE.SUPPLY
                    , Constants.ORDER_STUTUS.STAY_REVIEW, "", entity.toString());

            data.setCode(Constants.STATUS_CODE.SUCCESS);
            data.setMessage("发布成功，待平台审核");
            return data;
        } else {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("发布失败，请重新发布");
            return data;
        }
    }


    public String getOrderIdByTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        String result = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            result += random.nextInt(10);
        }
        return newDate + result;
    }

    @Override
    public ReturnData querySupplyList(FishDTO dto) {

        ReturnData data = new ReturnData();
        Map<String, Object> map = new HashMap<>();
        map.put("offset", (dto.getPageNum() - 1) * dto.getPageSize());
        map.put("limit", dto.getPageSize());
        map.put("mark", dto.getMark());
        map.put("productType", dto.getTypeCd());
        map.put("province", dto.getProvince());
        map.put("city", dto.getCity());
        map.put("mainType", dto.getMainType());
        map.put("status", Constants.ORDER_STUTUS.PASS);
        List<TFishSupplyEntity> supplyEntities = supplyDao.querySupplyList(map);
        List<SupplyListVo> supplyList = new ArrayList<>();

        for (TFishSupplyEntity entity : supplyEntities) {
            SupplyListVo vo = new SupplyListVo();
            vo.setId(entity.getId());
            vo.setCity(entity.getCity());
            TBrandSeriesEntity seriesEntity = brandSeriesDao.queryObjectByName(entity.getProductType());
            if (seriesEntity != null) {
                vo.setImg(seriesEntity.getSeriesIcon());
            }
            vo.setCreateDate(DateUtil.format(entity.getCreateTime(), "yyyy-MM-dd"));
            vo.setTitle(entity.getProductType() + " " + entity.getMainType());
            vo.setSize(entity.getSize());
            vo.setPrice(entity.getPrice() + entity.getUnit());
            TBrandSeriesEntity brandSeriesEntity = brandSeriesDao.queryObjectByName(entity.getMainType());
            if (brandSeriesEntity != null) {
                vo.setImg(brandSeriesEntity.getSeriesIcon());
            }
            String labels = entity.getLabels();
            List<String> images = new ArrayList<>();
            if (StringUtil.isNoneBlank(labels)) {
                String[] imgList = labels.split(",");
                for (String str : imgList) {
                    images.add(str);
                }
            }
            vo.setLabels(images);
            supplyList.add(vo);
        }

        data.setCode(Constants.STATUS_CODE.SUCCESS);
        data.setMessage("查询成功");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("supplyList", supplyList);
        data.setData(jsonObject);
        return data;
    }

    @Override
    public ReturnData querySupplyDetail(FishDTO dto) {
        ReturnData data = new ReturnData();
        TFishSupplyEntity entity = supplyDao.queryObject(dto.getKey());
        if (entity == null) {
            data.setMessage("对不起，该供应信息不存在");
            data.setCode(Constants.STATUS_CODE.FAIL);
            return data;
        }
        SupplyDetailVo vo = new SupplyDetailVo();
        vo.setId(entity.getId());
        vo.setCity(entity.getCity());
        vo.setCreateDate(DateUtil.format(entity.getCreateTime(), "yyyy-MM-dd"));
        vo.setLatestTime(entity.getLatestTime());
        vo.setTitle(entity.getProductType() + " " + entity.getMainType());
        vo.setSize(entity.getSize());
        vo.setWeight(entity.getWeight());
        vo.setPrice(entity.getPrice() + entity.getUnit());
        TBrandSeriesEntity brandSeriesEntity = brandSeriesDao.queryObjectByName(entity.getProductType());
        if (brandSeriesEntity != null) {
            vo.setImg(brandSeriesEntity.getSeriesIcon());
        }
        String labels = entity.getLabels();
        List<String> images = new ArrayList<>();
        if (StringUtil.isNoneBlank(labels)) {
            String[] imgList = labels.split(",");
            for (String str : imgList) {
                images.add(str);
            }
        }
        vo.setLabels(images);
        vo.setMark(entity.getMark());
        TCodemstEntity kf = tCodemstDao.queryByCode("130001");
        if (kf != null) {
            vo.setKfMobile(kf.getData2());
            vo.setKfQQ(kf.getData3());
        }
        String entityImgs = entity.getImg();
        List<String> imgs = new ArrayList<>();
        if (StringUtil.isNoneBlank(entityImgs)) {
            String[] imgList = entityImgs.split(",");
            for (String str : imgList) {
                imgs.add(str);
            }
        }
        vo.setImgs(imgs);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("detailModel", vo);
        data.setData(jsonObject);
        data.setCode(Constants.STATUS_CODE.SUCCESS);
        data.setMessage("查询成功");
        return data;
    }

    @Override
    public ReturnData saveSupplyOrder(FishDTO dto) {

        ReturnData data = new ReturnData();
        Member member = memberDao.queryByMobile(dto.getMobile());
        if (member == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，此用户不存在");
            return data;
        }
        if (!Constants.MEMBER_STATUS.REVIEW_PASS.equals(member.getStatus())) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，您还未进行认证，请先认证资料");
            return data;
        }
        TFishSupplyEntity supplyEntity = supplyDao.queryObject(dto.getKey());
        if (supplyEntity == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，此供应信息不存在");
            return data;
        }
        if(supplyEntity.getMemberId() == member.getId()){
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，这是您发布的供应，不能下单");
            return data;
        }
        TFishOrderEntity orderEntity = new TFishOrderEntity();
        orderEntity.setCreateTime(DateUtil.getNowTimestamp());
        orderEntity.setUpdateTime(DateUtil.getNowTimestamp());
        orderEntity.setStatus(Constants.ORDER_STUTUS.STAY_REVIEW);
        orderEntity.setMark(dto.getMark());
        orderEntity.setInfoId(dto.getKey());
        orderEntity.setOrderNo("T" + getOrderIdByTime());
        orderEntity.setToUserId(member.getId());
        orderEntity.setFromUserId(supplyEntity.getMemberId());
        orderEntity.setNeeds(dto.getNeeds());
        orderEntity.setOrderTypeCd(Constants.ORDER_TYPE.SUPPLY);
        orderEntity.setReceiveAddress(dto.getAddress());
        orderEntity.setReceiveMobile(dto.getReceiveMobile());
        orderEntity.setReceivePerson(dto.getReceivePerson());
        int ret = tFishOrderDao.save(orderEntity);
        if (ret == 0) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("下单失败，请联系客服处理");
            return data;
        } else {
            commonService.saveOrderStatus(0, orderEntity.getOrderNo()
                    , Constants.ORDER_TYPE.SUPPLY
                    , Constants.ORDER_STUTUS.STAY_REVIEW, "供应下单", orderEntity.toString());
            data.setCode(Constants.STATUS_CODE.SUCCESS);
            data.setMessage("下单成功，请等待客服联系您");
            return data;
        }
    }

    @Override
    public ReturnData saveBuyInfo(FishDTO dto) {
        ReturnData data = new ReturnData();
        Member member = memberDao.queryByMobile(dto.getMobile());
        if (member == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，此用户不存在");
            return data;
        }
        if (!Constants.MEMBER_STATUS.REVIEW_PASS.equals(member.getStatus())) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，您还未进行认证，请先认证资料");
            return data;
        }
        TFishBuyEntity entity = new TFishBuyEntity();
        entity.setOrderNo("QG" + getOrderIdByTime());
        entity.setProductType(dto.getTypeCd());
        entity.setMainType(dto.getMainType());
        entity.setImg(dto.getUrl());
        entity.setSize(dto.getSize());
        entity.setUnit(dto.getUnit());
        entity.setPrice(dto.getPrice());
        entity.setWeight(dto.getBuy());
        entity.setCreateTime(DateUtil.getNowTimestamp());
        entity.setUpdateTime(DateUtil.getNowTimestamp());
        entity.setProvice(dto.getProvince());
        entity.setCity(dto.getCity());
        entity.setMark(dto.getMark());
        entity.setMemberId(member.getId());
        entity.setLatestTime(dto.getLatestTime());
        entity.setStatus(Constants.ORDER_STUTUS.STAY_REVIEW);
        int ret = buyDao.save(entity);
        if (ret != 0) {
            commonService.saveOrderStatus(0, entity.getOrderNo()
                    , Constants.ORDER_TYPE.BUY
                    , Constants.ORDER_STUTUS.STAY_REVIEW, "", entity.toString());

            data.setCode(Constants.STATUS_CODE.SUCCESS);
            data.setMessage("发布成功，待平台审核");
            return data;
        } else {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("发布失败，请重新发布");
            return data;
        }
    }

    /**
     * 求购列表
     *
     * @param dto
     * @return
     */
    @Override
    public ReturnData queryBuyList(FishDTO dto) {
        ReturnData data = new ReturnData();
        Map<String, Object> map = new HashMap<>();
        map.put("offset", (dto.getPageNum() - 1) * dto.getPageSize());
        map.put("limit", dto.getPageSize());
        map.put("mark", dto.getMark());
        map.put("productType", dto.getTypeCd());
        map.put("province", dto.getProvince());
        map.put("city", dto.getCity());
        map.put("mainType", dto.getMainType());
        map.put("status", Constants.ORDER_STUTUS.PASS);
        List<TFishBuyEntity> buyEntities = buyDao.queryBuyList(map);
        List<BuyListVo> buyList = new ArrayList<>();

        for (TFishBuyEntity entity : buyEntities) {
            BuyListVo vo = new BuyListVo();
            vo.setId(entity.getId());
            vo.setCity(entity.getCity());
            TBrandSeriesEntity seriesEntity = brandSeriesDao.queryObjectByName(entity.getProductType());
            if (seriesEntity != null) {
                vo.setImg(seriesEntity.getSeriesIcon());
            }
            vo.setCreateDate(DateUtil.format(entity.getCreateTime(), "yyyy-MM-dd"));
            vo.setTitle(entity.getProductType() + " " + entity.getMainType());
            vo.setSize(entity.getSize());
            vo.setPrice(entity.getPrice() + entity.getUnit());
            TBrandSeriesEntity brandSeriesEntity = brandSeriesDao.queryObjectByName(entity.getMainType());
            if (brandSeriesEntity != null) {
                vo.setImg(brandSeriesEntity.getSeriesIcon());
            }
            List<String> labelList = new ArrayList<>();
            labelList.add(entity.getSize());
            labelList.add(entity.getWeight());
            vo.setLabels(labelList);
            buyList.add(vo);
        }

        data.setCode(Constants.STATUS_CODE.SUCCESS);
        data.setMessage("查询成功");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("buyList", buyList);
        data.setData(jsonObject);
        return data;
    }

    @Override
    public ReturnData queryBuyDetail(FishDTO dto) {
        ReturnData data = new ReturnData();
        TFishBuyEntity entity = buyDao.queryObject(dto.getKey());
        if (entity == null) {
            data.setMessage("对不起，该求购信息不存在");
            data.setCode(Constants.STATUS_CODE.FAIL);
            return data;
        }
        BuyDetailVo vo = new BuyDetailVo();
        vo.setId(entity.getId());
        vo.setCity(entity.getCity());
        vo.setCreateDate(DateUtil.format(entity.getCreateTime(), "yyyy-MM-dd"));
        vo.setTitle(entity.getProductType() + " " + entity.getMainType());
        vo.setSize(entity.getSize());
        vo.setWeight(entity.getWeight());
        vo.setPrice(entity.getPrice() + entity.getUnit());
        TBrandSeriesEntity brandSeriesEntity = brandSeriesDao.queryObjectByName(entity.getProductType());
        if (brandSeriesEntity != null) {
            vo.setImg(brandSeriesEntity.getSeriesIcon());
        }
        String labels = entity.getLabels();
        List<String> labelList = new ArrayList<>();
        labelList.add(entity.getSize());
        labelList.add(entity.getWeight());
        vo.setLabels(labelList);
        vo.setMark(entity.getMark());
        TCodemstEntity kf = tCodemstDao.queryByCode("130001");
        if (kf != null) {
            vo.setKfMobile(kf.getData2());
            vo.setKfQQ(kf.getData3());
        }
        //获取广告位
        List<TAdvertisementEntity> adList = tAdvertisementDao.queryListByTypeCd(Constants.ADVERTISEMENT_TYPE.BUY_DETAIL);
        List<CarouselVo> imgs = new ArrayList<>();
        for (TAdvertisementEntity advertisement : adList) {
            CarouselVo carouselVo = new CarouselVo();
            carouselVo.setImgUrl(advertisement.getLogo());
            carouselVo.setLinkUrl(advertisement.getRealUrl());
            imgs.add(carouselVo);
        }
        vo.setImgs(imgs);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("detailModel", vo);
        data.setData(jsonObject);
        data.setCode(Constants.STATUS_CODE.SUCCESS);
        data.setMessage("查询成功");
        return data;
    }

    @Override
    public ReturnData queryMySupplyList(FishDTO dto) {
        ReturnData data = new ReturnData();
        Member member = memberDao.queryByMobile(dto.getMobile());
        if (member == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，此用户不存在");
            return data;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("offset", (dto.getPageNum() - 1) * dto.getPageSize());
        map.put("limit", dto.getPageSize());
        map.put("mark", dto.getMark());
        map.put("productType", dto.getTypeCd());
        map.put("province", dto.getProvince());
        map.put("city", dto.getCity());
        map.put("mainType", dto.getMainType());
        map.put("memberId", member.getId());
        List<TFishSupplyEntity> supplyEntities = supplyDao.querySupplyList(map);
        List<MySupplyListVo> supplyList = new ArrayList<>();

        for (TFishSupplyEntity entity : supplyEntities) {
            MySupplyListVo vo = new MySupplyListVo();
            vo.setId(entity.getId());
            vo.setCity(entity.getCity());
            TBrandSeriesEntity seriesEntity = brandSeriesDao.queryObjectByName(entity.getProductType());
            if (seriesEntity != null) {
                vo.setImg(seriesEntity.getSeriesIcon());
            }
            vo.setCreateDate(DateUtil.format(entity.getCreateTime(), "yyyy-MM-dd"));
            vo.setTitle(entity.getProductType() + " " + entity.getMainType());
            vo.setSize(entity.getSize());
            vo.setPrice(entity.getPrice() + entity.getUnit());
            TBrandSeriesEntity brandSeriesEntity = brandSeriesDao.queryObjectByName(entity.getMainType());
            if (brandSeriesEntity != null) {
                vo.setImg(brandSeriesEntity.getSeriesIcon());
            }
            String labels = entity.getLabels();
            List<String> images = new ArrayList<>();
            if (StringUtil.isNoneBlank(labels)) {
                String[] imgList = labels.split(",");
                for (String str : imgList) {
                    images.add(str);
                }
            }
            vo.setLabels(images);
            vo.setStatusCd(entity.getStatus());
            if (StringUtil.isNoneBlank(entity.getStatus())) {
                TCodemstEntity status = tCodemstDao.queryByCode(entity.getStatus());
                if (status != null) {
                    vo.setStatusName(status.getName());
                }
            }

            supplyList.add(vo);
        }

        data.setCode(Constants.STATUS_CODE.SUCCESS);
        data.setMessage("查询成功");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("supplyList", supplyList);
        data.setData(jsonObject);
        return data;
    }

    @Override
    public ReturnData queryMyBuyList(FishDTO dto) {
        ReturnData data = new ReturnData();
        Member member = memberDao.queryByMobile(dto.getMobile());
        if (member == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，此用户不存在");
            return data;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("offset", (dto.getPageNum() - 1) * dto.getPageSize());
        map.put("limit", dto.getPageSize());
        map.put("mark", dto.getMark());
        map.put("productType", dto.getTypeCd());
        map.put("province", dto.getProvince());
        map.put("city", dto.getCity());
        map.put("mainType", dto.getMainType());
        map.put("memberId", member.getId());
        List<TFishBuyEntity> buyEntities = buyDao.queryBuyList(map);
        List<MyBuyListVo> buyList = new ArrayList<>();

        for (TFishBuyEntity entity : buyEntities) {
            MyBuyListVo vo = new MyBuyListVo();
            vo.setId(entity.getId());
            vo.setCity(entity.getCity());
            TBrandSeriesEntity seriesEntity = brandSeriesDao.queryObjectByName(entity.getProductType());
            if (seriesEntity != null) {
                vo.setImg(seriesEntity.getSeriesIcon());
            }
            vo.setCreateDate(DateUtil.format(entity.getCreateTime(), "yyyy-MM-dd"));
            vo.setTitle(entity.getProductType() + " " + entity.getMainType());
            vo.setSize(entity.getSize());
            vo.setPrice(entity.getPrice() + entity.getUnit());
            TBrandSeriesEntity brandSeriesEntity = brandSeriesDao.queryObjectByName(entity.getMainType());
            if (brandSeriesEntity != null) {
                vo.setImg(brandSeriesEntity.getSeriesIcon());
            }
            List<String> labelList = new ArrayList<>();
            labelList.add(entity.getSize());
            labelList.add(entity.getWeight());
            vo.setLabels(labelList);
            vo.setStatusCd(entity.getStatus());
            if (StringUtil.isNoneBlank(entity.getStatus())) {
                TCodemstEntity status = tCodemstDao.queryByCode(entity.getStatus());
                if (status != null) {
                    vo.setStatusName(status.getName());
                }
            }
            buyList.add(vo);
        }

        data.setCode(Constants.STATUS_CODE.SUCCESS);
        data.setMessage("查询成功");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("buyList", buyList);
        data.setData(jsonObject);
        return data;
    }

    @Override
    public ReturnData queryMarketPriceList(FishDTO dto) {
        ReturnData data = new ReturnData();
        Map<String, Object> map = new HashMap<>();
        map.put("offset", (dto.getPageNum() - 1) * dto.getPageSize());
        map.put("limit", dto.getPageSize());
        map.put("productType", dto.getTypeCd());
        map.put("priceDate", dto.getLatestTime());
        List<MarketPriceVo> list = tMarketPriceDao.queryPriceList(map);
        for (MarketPriceVo vo : list) {
            LocationCityEntity cityEntity = cityDao.queryObject(StringUtil.toInteger(vo.getCity()));
            if (cityEntity != null) {
                vo.setCity(cityEntity.getName());
            }
            //获取图片
            TBrandSeriesEntity seriesEntity = brandSeriesDao.queryObjectByName(vo.getBrandSeries());
            if (seriesEntity != null) {
                vo.setImg(seriesEntity.getSeriesIcon());
            }
        }
        //获取广告位
        List<TAdvertisementEntity> adList = tAdvertisementDao.queryListByTypeCd(Constants.ADVERTISEMENT_TYPE.MARKET_PRICE);
        List<CarouselVo> imgs = new ArrayList<>();
        for (TAdvertisementEntity advertisement : adList) {
            CarouselVo vo = new CarouselVo();
            vo.setImgUrl(advertisement.getLogo());
            vo.setLinkUrl(advertisement.getRealUrl());
            imgs.add(vo);
        }

        data.setMessage("查询成功");
        data.setCode(Constants.STATUS_CODE.SUCCESS);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("marketPriceList", list);
        jsonObject.put("carouselList", imgs);
        //查询客服
        TCodemstEntity kf = tCodemstDao.queryByCode("130001");
        if (kf != null) {
            jsonObject.put("kfMobile", kf.getData2());
        }
        data.setData(jsonObject);
        return data;
    }

    @Override
    public ReturnData getSupplyOrderList(FishDTO dto) {
        ReturnData data = new ReturnData();
        Member member = memberDao.queryByMobile(dto.getMobile());
        if (member == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，此用户不存在");
            return data;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("offset", (dto.getPageNum() - 1) * dto.getPageSize());
        map.put("limit", dto.getPageSize());
        map.put("status", dto.getStatus());
        map.put("toUserId", member.getId());

        List<TFishOrderEntity> list = orderDao.queryList(map);
        List<SupplyOrderListVo> orderList = new ArrayList<>();
        for (TFishOrderEntity orderEntity : list) {
            SupplyOrderListVo vo = new SupplyOrderListVo();
            vo.setOrderNo(orderEntity.getOrderNo());
            vo.setOrderTime(DateUtil.format(orderEntity.getCreateTime(), "yyyy-MM-dd"));
            vo.setMark(orderEntity.getMark());
            if (Constants.ORDER_TYPE.SUPPLY.equals(orderEntity.getOrderTypeCd())) {
                //供应订单
                TFishSupplyEntity supplyEntity = supplyDao.queryObject(orderEntity.getInfoId());
                if (supplyEntity != null) {
                    vo.setWeight(supplyEntity.getWeight());
                    vo.setSize(supplyEntity.getSize());
                    vo.setPrice(supplyEntity.getPrice() + supplyEntity.getUnit());
                    TBrandSeriesEntity brandSeriesEntity = brandSeriesDao.queryObjectByName(supplyEntity.getProductType());
                    if (brandSeriesEntity != null) {
                        vo.setTitle(brandSeriesEntity.getCarSerial());
                        vo.setImg(brandSeriesEntity.getSeriesIcon());
                    }
                }
            }

            orderList.add(vo);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderList", orderList);
        data.setData(jsonObject);
        data.setCode(Constants.STATUS_CODE.SUCCESS);
        data.setMessage("查询成功");
        return data;
    }

    @Override
    public ReturnData getSupplyOrderDetail(FishDTO dto) {

        ReturnData data = new ReturnData();
        Member member = memberDao.queryByMobile(dto.getMobile());
        if (member == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，此用户不存在");
            return data;
        }
        String orderNo = dto.getOrderNo();
        if (StringUtil.isBlank(orderNo)) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，订单号不能为空");
            return data;
        }
        TFishOrderEntity orderEntity = orderDao.queryOrderByOrderNo(orderNo);
        if (orderEntity == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，订单不存在");
            return data;
        }

        TFishSupplyEntity supplyEntity = supplyDao.queryObject(orderEntity.getInfoId());
        if (supplyEntity == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，供应信息不存在");
            return data;
        }
        TBrandSeriesEntity brandSeriesEntity = brandSeriesDao.queryObjectByName(supplyEntity.getProductType());
        if (brandSeriesEntity == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，产品不存在");
            return data;
        }
        SupplyInfo supplyInfo = new SupplyInfo();
        OrderInfo orderInfo = new OrderInfo();
        LogisticInfo logisticInfo = new LogisticInfo();

        supplyInfo.setImg(brandSeriesEntity.getSeriesIcon());
        supplyInfo.setTitle(brandSeriesEntity.getCarSerial());
        supplyInfo.setAddress(supplyEntity.getProvice() + supplyEntity.getCity());
        supplyInfo.setEndTime(supplyEntity.getLatestTime());
        supplyInfo.setMark(supplyEntity.getMark());
        supplyInfo.setOrderNo(orderEntity.getOrderNo());
        supplyInfo.setWeight(supplyEntity.getWeight());
        supplyInfo.setSize(supplyEntity.getSize());
        supplyInfo.setPrice(supplyEntity.getPrice() + supplyEntity.getUnit());
        supplyInfo.setOrderTime(DateUtil.format(orderEntity.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));

        TCodemstEntity codemstEntity = tCodemstDao.queryByCode(orderEntity.getStatus());
        if (codemstEntity != null) {
            orderInfo.setStatus(codemstEntity.getName());
            orderInfo.setStatusCd(orderEntity.getStatus());
        }
        orderInfo.setFailReason(orderEntity.getReason());
        orderInfo.setFirstPay(StringUtil.toString(orderEntity.getFirstPay()) + "元");
        orderInfo.setSecondPay(StringUtil.toString(orderEntity.getSecondPay()) + "元");
        BigDecimal all = new BigDecimal("0");
        if (orderEntity.getFirstPay() != null) {
            all = all.add(orderEntity.getFirstPay());
        }
        if (orderEntity.getSecondPay() != null) {
            all = all.add(orderEntity.getSecondPay());
        }
        orderInfo.setAllPay(StringUtil.toString(all) + "元");
        TFishOrderstatusEntity stayShip = orderstatusDao.queryByOrderNo(orderNo, Constants.ORDER_STUTUS.STAY_SHIP);
        //首付款后,就是待发货,此时查看第一次付款时间
        if (stayShip != null) {
            String payTime = DateUtil.format(stayShip.getCreateTime(), "yyyy-MM-dd hh:mm");
            orderInfo.setPayTime(payTime);
        }
        TFishOrderstatusEntity complete = orderstatusDao.queryByOrderNo(orderNo, Constants.ORDER_STUTUS.COMPLETE);
        //付尾款后,就是待发货,此时查看第二次付款时间
        if (complete != null) {
            String completeTime = DateUtil.format(complete.getCreateTime(), "yyyy-MM-dd hh:mm");
            orderInfo.setPayTime(completeTime);
        }

        TFishOrderstatusEntity receive = orderstatusDao.queryByOrderNo(orderNo, Constants.ORDER_STUTUS.RECEIVED);
        if (receive != null) {
            logisticInfo.setReceiveTime(DateUtil.format(receive.getCreateTime(), "yyyy-MM-dd hh:mm"));
        }

        logisticInfo.setReceiveAddress(orderEntity.getReceiveAddress());
        logisticInfo.setReceivePerson(orderEntity.getReceivePerson());
        logisticInfo.setMobile(orderEntity.getReceiveMobile());
        logisticInfo.setInfomation(orderEntity.getLogisticsInfo());
        logisticInfo.setLogisticNo(orderEntity.getLogisticsNo());


        data.setMessage("查询成功");
        data.setCode(Constants.STATUS_CODE.SUCCESS);
        JSONObject jsonObject = new JSONObject();
        //查询客服
        TCodemstEntity kf = tCodemstDao.queryByCode("130001");
        if (kf != null) {
            jsonObject.put("kfMobile", kf.getData2());
        }
        jsonObject.put("supplyInfo", supplyInfo);
        jsonObject.put("orderInfo", orderInfo);
        jsonObject.put("logisticInfo", logisticInfo);
        data.setData(jsonObject);
        return data;
    }

    @Override
    public ReturnData cancleOrder(FishDTO dto) {

        ReturnData data = new ReturnData();
        Member member = memberDao.queryByMobile(dto.getMobile());
        if (member == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，此用户不存在");
            return data;
        }
        String orderNo = dto.getOrderNo();
        if (StringUtil.isBlank(orderNo)) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，订单号不能为空");
            return data;
        }
        TFishOrderEntity orderEntity = orderDao.queryOrderByOrderNo(orderNo);
        if (orderEntity == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，订单不存在");
            return data;
        }

        if ((!Constants.ORDER_STUTUS.STAY_REVIEW.equals(orderEntity.getStatus())) &&
                (!Constants.ORDER_STUTUS.STAY_PAY.equals(orderEntity.getStatus()))) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，订单状态未待审核或待支付，才可取消订单");
            return data;
        }

        if (orderEntity.getToUserId() != member.getId()) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，该订单不是您的订单");
            return data;
        }

        //更新订单状态
        int ret = orderDao.updateOrderStatus(orderNo, Constants.ORDER_STUTUS.CANCLED);
        if (ret != 0) {
            commonService.saveOrderStatus(0, orderNo, Constants.ORDER_TYPE.SUPPLY
                    , Constants.ORDER_STUTUS.CANCLED, "", orderEntity.toString());
            data.setCode(Constants.STATUS_CODE.SUCCESS);
            data.setMessage("取消成功");
        } else {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("取消失败");
        }
        return data;
    }

    /**
     * 设置线下支付
     *
     * @param dto
     * @return
     */
    @Override
    public ReturnData setOutlinePay(FishDTO dto) {

        ReturnData data = new ReturnData();
        Member member = memberDao.queryByMobile(dto.getMobile());
        if (member == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，此用户不存在");
            return data;
        }
        String orderNo = dto.getOrderNo();
        if (StringUtil.isBlank(orderNo)) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，订单号不能为空");
            return data;
        }
        TFishOrderEntity orderEntity = orderDao.queryOrderByOrderNo(orderNo);
        if (orderEntity == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，订单不存在");
            return data;
        }

        if (orderEntity.getToUserId() != member.getId()) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，该订单不是您的订单");
            return data;
        }

        /**
         * 待付款和已收货的状态下，才能付款
         */
        if ((!Constants.ORDER_STUTUS.STAY_PAY.equals(orderEntity.getStatus())) &&
                (!Constants.ORDER_STUTUS.RECEIVED.equals(orderEntity.getStatus()))) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，订单状态未待支付或已收货，才需要付款");
            return data;
        }

        //更新支付方式
        String flg = dto.getTypeCd();
        //预付款使用线下支付
        if((!"0".equals(flg)) && (!"1".equals(flg))){
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，数据错误");
            return data;
        }

        if (orderEntity.getToUserId() != member.getId()) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，该订单不是您的订单");
            return data;
        }


        if("0".equals(flg)){
            if(orderEntity.getFirstPay() == null){
                data.setCode(Constants.STATUS_CODE.SUCCESS);
                data.setMessage("操作失败，待平台设置预付款再操作，谢谢");
                return data;
            }
            int ret = orderDao.updatePayType(orderNo, flg, Constants.PAY_TYPE.OUTLINE_PAY, Constants.ORDER_STUTUS.STAY_SHIP);
            if (ret != 0) {
                //更新为待发货
                commonService.saveOrderStatus(0, orderNo, Constants.ORDER_TYPE.SUPPLY
                        , Constants.ORDER_STUTUS.STAY_SHIP, "设置已线下支付", orderEntity.toString());

                data.setCode(Constants.STATUS_CODE.SUCCESS);
                data.setMessage("操作成功");
                return data;
            }else{
                data.setCode(Constants.STATUS_CODE.FAIL);
                data.setMessage("对不起，操作失败");
                return data;
            }
        }

        if("1".equals(flg)){
            if(orderEntity.getSecondPay() == null){
                data.setCode(Constants.STATUS_CODE.SUCCESS);
                data.setMessage("操作失败，待平台设置尾款再操作，谢谢");
                return data;
            }
            int ret = orderDao.updatePayType(orderNo, flg, Constants.PAY_TYPE.OUTLINE_PAY, Constants.ORDER_STUTUS.COMPLETE);
            if (ret != 0) {
                //更新为待发货
                commonService.saveOrderStatus(0, orderNo, Constants.ORDER_TYPE.SUPPLY
                        , Constants.ORDER_STUTUS.COMPLETE, "设置已线下支付", orderEntity.toString());

                data.setCode(Constants.STATUS_CODE.SUCCESS);
                data.setMessage("操作成功");
                return data;
            }else{
                data.setCode(Constants.STATUS_CODE.FAIL);
                data.setMessage("对不起，操作失败");
                return data;
            }
        }
        data.setCode(Constants.STATUS_CODE.FAIL);
        data.setMessage("对不起，操作失败");
        return data;
    }

    @Override
    public ReturnData confirmReceived(FishDTO dto) {
        ReturnData data = new ReturnData();
        Member member = memberDao.queryByMobile(dto.getMobile());
        if (member == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，此用户不存在");
            return data;
        }
        String orderNo = dto.getOrderNo();
        if (StringUtil.isBlank(orderNo)) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，订单号不能为空");
            return data;
        }
        TFishOrderEntity orderEntity = orderDao.queryOrderByOrderNo(orderNo);
        if (orderEntity == null) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，订单不存在");
            return data;
        }

        if (!Constants.ORDER_STUTUS.SHIPPED.equals(orderEntity.getStatus())) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，订单状态已发货，才可确认收货");
            return data;
        }

        if (orderEntity.getToUserId() != member.getId()) {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，该订单不是您的订单");
            return data;
        }

        //更新订单状态
        int ret = orderDao.updateOrderStatus(orderNo, Constants.ORDER_STUTUS.RECEIVED);
        if (ret != 0) {
            commonService.saveOrderStatus(0, orderNo, Constants.ORDER_TYPE.SUPPLY
                    , Constants.ORDER_STUTUS.RECEIVED, "", orderEntity.toString());
            data.setCode(Constants.STATUS_CODE.SUCCESS);
            data.setMessage("确认成功");
        } else {
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("确认失败");
        }
        return data;
    }
}
