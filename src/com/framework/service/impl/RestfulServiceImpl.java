package com.framework.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.framework.constants.Constants;
import com.framework.dao.*;
import com.framework.dto.FishDTO;
import com.framework.entity.*;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        ReturnData data = new ReturnData();
        data.setCode(Constants.STATUS_CODE.SUCCESS);
        data.setMessage("查询成功");
        JSONObject jsonObject = new JSONObject();
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
        userVo.setStatus(member.getStatus());
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
        int msgCount = userDeviceTokenDao.queryToken(dto.getDeviceToken(),new Timestamp(System.currentTimeMillis()-10*60*1000),new Timestamp(System.currentTimeMillis()));
        TCodemstEntity codemstEntity = tCodemstDao.queryByCode(Constants.STATIC_PARAMS.SHOWMSG_COUNT);
        int limit = 10;
        if(codemstEntity != null){
            limit = codemstEntity.getData1();
        }
        if(msgCount >= limit){
            //超出发送条数限制
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，您获取短信太频繁，请稍后重试");
            return data;
        }
        //判断有没有
        //String code = VertifyUtil.getVertifyCode();
        String code = "8396";
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
        entity.setExpireTime(new Timestamp(System.currentTimeMillis()+10*60*1000));
        TVertifyCodeEntity vCode = vertifyCodeDao.queryCodeByMobile(mobile, dto.getShortMsgTypeCd());
        if (vCode == null) {
            vertifyCodeDao.save(entity);
        } else {
            entity.setId(vc.getId());
            vertifyCodeDao.update(entity);
        }
        //发送短信
        String shortMsg = "您的验证码是：" + code + "，10分钟内有效，请不要把验证码泄露给其他人。";
        //发送短信,0-成功
        String ret = "0";
        try {
            ret = StringUtil.toString(ShortMessageUtil.sendsms(mobile, shortMsg));
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
        TVertifyCodeEntity vCode = vertifyCodeDao.queryCodeByMobile(mobile,Constants.SHORT_MSG_TYPE.REGISTER);
        Timestamp expireTime = vCode == null ? null : vCode.getExpireTime();
        Timestamp now = DateUtil.getNowTimestamp();
        Member member = memberDao.queryByMobile(mobile);
        if(member != null){
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，您的手机号码已经注册了");
            return data;
        }

        if(expireTime == null){
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，您还没有获取验证码");
            return data;
        }

        if((expireTime != null)&&(now.after(expireTime))){
            //true，就是过期了
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("验证码过期了，请重新获取");
            return data;
        }

        if((expireTime != null) && (expireTime.after(now))){
            //没有过期，获取数据库验证码
            String dcode = vCode.getCode();
            if(!StringUtil.equals(code, dcode)){
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

        int id = memberDao.save(registerMember);
        if(id != 0){
            //注册成功
            Member m = memberDao.queryByMobile(mobile);
            JSONObject map = new JSONObject();
            map.put("token", token);
            vertifyCodeDao.updateExpireCode(vCode.getId(),DateUtil.getNowTimestamp());
            //保存token
            UserToken at = userTokenDao.queryToken(m.getId(),dto.getUserTypeCd(),dto.getPlatform());
            int tokensave = 0;
            if(at == null){
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
                if(tokensave != 0){
                    data.setCode(Constants.STATUS_CODE.SUCCESS);
                    data.setMessage("注册成功");
                    data.setData(map);
                    return data;
                }else{
                    data.setCode(Constants.STATUS_CODE.FAIL);
                    data.setMessage("注册失败");
                    return data;
                }
            }else{
                at.setToken(token);
                at.setUpdateTime(DateUtil.getNowTimestamp());
                //一个月后失效
                at.setExpireTime(new Timestamp(DateUtil.getNowTimestamp().getTime() + 30 * 24 * 60 * 60 * 1000L));
                int ret = userTokenDao.update(at);
                if(ret != 0){
                    data.setCode(Constants.STATUS_CODE.SUCCESS);
                    data.setMessage("注册成功");
                    data.setData(map);
                }else{
                    data.setCode(Constants.STATUS_CODE.FAIL);
                    data.setMessage("注册失败");
                }
                return data;
            }
        }else{
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("注册失败");
            return data;
        }
    }

    @Override
    public ReturnData login(FishDTO dto) {
        ReturnData data = new ReturnData();
        Member member = memberDao.queryByMobile(dto.getMobile());
        if(member == null){
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，您的账号尚未注册");
            return data;
        }
        if(!StringUtil.equals(member.getUserPwd(), dto.getUserPwd())){
            data.setMessage("对不起，密码错误");
            data.setCode(Constants.STATUS_CODE.FAIL);
            return data;
        }

        //保存token
        int userId = member.getId();
        String platForm = dto.getPlatform();
        UserToken at = userTokenDao.queryToken(userId, dto.getUserTypeCd(),platForm);
        int tokensave = 0;
        String token = TextUtil.generateUUID();
        UserToken userToken = new UserToken();
        userToken.setUserId(userId);
        userToken.setUserTypeCd(dto.getUserTypeCd());
        userToken.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        //更新token
        if(StringUtil.equals(platForm, Constants.PLATFORM.ANDROID)){
            //如果是安卓登录，则把ios的token置空
            userToken.setToken("");
            userToken.setExpireTime(new Timestamp(System.currentTimeMillis()));
            userToken.setPlatform( Constants.PLATFORM.IOS);
            userTokenDao.update(userToken);
        }
        if(StringUtil.equals(platForm, Constants.PLATFORM.IOS)){
            //如果是ios，则把安卓token置空
            userToken.setToken("");
            userToken.setExpireTime(new Timestamp(System.currentTimeMillis()));
            userToken.setPlatform( Constants.PLATFORM.ANDROID);
            userTokenDao.update(userToken);
        }
        if(at == null){
            userToken.setToken(token);
            userToken.setExpireTime(new Timestamp(System.currentTimeMillis()+30*24*60*60*1000L));
            userToken.setPlatform(platForm);
            userToken.setCreateTime(new Timestamp(System.currentTimeMillis()));
            tokensave = userTokenDao.save(userToken);
            if(tokensave != 0){
                JSONObject map = new JSONObject();
                map.put("token", token);
                data.setData(map);
                data.setCode(Constants.STATUS_CODE.SUCCESS);
                data.setMessage("登录成功");
            }else{
                data.setCode(Constants.STATUS_CODE.FAIL);
                data.setMessage("登录失败");
            }
        }else{
            userToken.setToken(token);
            userToken.setExpireTime(new Timestamp(System.currentTimeMillis()+30*24*60*60*1000L));
            userToken.setPlatform(platForm);
            int ret = userTokenDao.update(userToken);
            if(ret != 0){
                JSONObject map = new JSONObject();
                map.put("token", token);
                data.setData(map);
                data.setCode(Constants.STATUS_CODE.SUCCESS);
                data.setMessage("登录成功");
                return data;
            }else{
                data.setCode(Constants.STATUS_CODE.FAIL);
                data.setMessage("登录失败");
                return data;
            }

        }
        return data;
    }

    /**
     * 退出功能
     * @param dto
     * @return
     */
    @Override
    public ReturnData logout(FishDTO dto) {
        ReturnData data = new ReturnData();
        Member member = memberDao.queryByMobile(dto.getMobile());
		if(member == null){
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
        if(ret != 0){
            data.setCode(Constants.STATUS_CODE.SUCCESS);
            data.setMessage("退出成功");
        }else{
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("退出失败");
        }
        return data;
    }

    /**
     * 修改密码
     * @param dto
     * @return
     */
    @Override
    public ReturnData modifyPwd(FishDTO dto) {
        ReturnData data = new ReturnData();
        Member member = memberDao.queryByMobile(dto.getMobile());
        if(member == null){
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，用户不存在");
            return data;
        }
        if(!StringUtil.equals(member.getUserPwd(), dto.getOldPwd())){
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，旧密码错误");
            return data;
        }
        //保存密码
        Map<String,Object> map = new HashMap<>();
        map.put("id",member.getId());
        map.put("userPwd",dto.getUserPwd());
        int ret = memberDao.updatePassword(map);
        if(ret != 0){
            data.setCode(Constants.STATUS_CODE.SUCCESS);
            data.setMessage("密码修改成功");
        }else{
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("密码修改失败");
        }
        return data;
    }

    /**
     * 保存忘记密码
     * @param dto
     * @return
     */
    @Override
    public ReturnData saveForgetPwd(FishDTO dto) {
        ReturnData data = new ReturnData();
        Member member = memberDao.queryByMobile(dto.getMobile());
        if(member == null){
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，用户不存在");
            return data;
        }
        TVertifyCodeEntity vCode = vertifyCodeDao.queryCodeByMobile(dto.getMobile(),Constants.SHORT_MSG_TYPE.FORGET_PASSWORD);
        if(vCode == null){
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("请重新获取验证码");
            return data;
        }
        if(!StringUtil.equals(dto.getCode(), vCode.getCode())){
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("请输入正确的验证码");
            return data;
        }

        //判断验证码是不是过期
        Timestamp expireTime = (Timestamp)vCode.getExpireTime();
        Timestamp now = DateUtil.getNowTimestamp();
        if((expireTime != null)&&(now.after(expireTime))){
            //true，就是过期了
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("验证码过期了，请重新获取");
            return data;
        }else{
            //把验证码设置为过期
            vertifyCodeDao.updateExpireCode(vCode.getId(),now);
            //保存密码
            Map<String,Object> map = new HashMap<>();
            map.put("id",member.getId());
            map.put("userPwd",dto.getUserPwd());
            int ret = memberDao.updatePassword(map);
            if(ret != 0){
                data.setCode(Constants.STATUS_CODE.SUCCESS);
                data.setMessage("密码保存成功");
            }else{
                data.setCode(Constants.STATUS_CODE.FAIL);
                data.setMessage("密码修改失败");
            }
            return data;
        }
    }

    /**
     * 积分商城首页
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
        List<MallProduct> list = mallProductDao.queryList(map);
        MallProductVo vo = null;
        List<MallProductVo> mallProductList = new ArrayList<>();
        for(MallProduct product : list){
            vo = new MallProductVo();
            vo.setProductId(product.getId());
            vo.setProductLogo(product.getLogos());
            vo.setPoints(product.getNeedPoints());
            vo.setProductName(product.getProductTitle());
            mallProductList.add(vo);
        }
        Member member = memberDao.queryByMobile(dto.getMobile());
        if(member == null){
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("查询失败，用户不存在");
            return data;
        }
        JSONObject object = new JSONObject();
        object.put("mallProductList",mallProductList);
        object.put("points",member.getPoints());
        data.setCode(Constants.STATUS_CODE.SUCCESS);
        data.setMessage("查询成功");
        data.setData(object);
        return data;
    }

    /**
     * 商品详情
     * @param dto
     * @return
     */
    @Override
    public ReturnData getMallProductDetail(FishDTO dto) {

        ReturnData data = new ReturnData();
        int productId = dto.getProductId();
        if(productId == 0){
            data.setMessage("对不起，商品不存在");
            data.setCode(Constants.STATUS_CODE.FAIL);
            return data;
        }
        MallProduct product = mallProductDao.queryObject(productId);
        JSONObject object = new JSONObject();
        object.put("productDetail",product);
        //获取发货时间及快递相关
        object.put("expressRelateUrl","https://www.sf-express.com/cn/sc/");
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
        if(product == null){
            data.setMessage("对不起，商品不存在");
            data.setCode(Constants.STATUS_CODE.FAIL);
            return data;
        }
        int needPoints = product.getNeedPoints();
        int allNeedPoints = needPoints * productNum;
        Member member = memberDao.queryByMobile(dto.getMobile());
        if(member == null){
            data.setMessage("对不起，您的账号不存在");
            data.setCode(Constants.STATUS_CODE.FAIL);
            return data;
        }
        if(!Constants.MEMBER_STATUS.REVIEW_PASS.equals(member.getStatus())){
            data.setMessage("对不起，您的账号还未认证通过，暂时不能兑换");
            data.setCode(Constants.STATUS_CODE.FAIL);
            return data;
        }
        int userPoint = member.getPoints();
        if(userPoint < allNeedPoints){
            data.setMessage("对不起，您的积分不足");
            data.setCode(Constants.STATUS_CODE.FAIL);
            return data;
        }
        if(productNum > product.getQuality()){
            data.setMessage("对不起，商品库存不足");
            data.setCode(Constants.STATUS_CODE.FAIL);
            return data;
        }
        //积分够，扣积分，扣商品数量
        int pointsRet = memberDao.updatePoints(allNeedPoints,member.getId());
        if(pointsRet != 0){
            int qualityRet = mallProductDao.updateQuality(productId,productNum);
            if(qualityRet != 0){
                //添加积分记录
                MallPointsRecord record = new MallPointsRecord();
                record.setCreateTime(DateUtil.getNowTimestamp());
                record.setUpdateTime(DateUtil.getNowTimestamp());
                record.setMark("兑换商品ID："+productId+"，数量："+productNum+"，消耗积分："+allNeedPoints);
                record.setPoint("-"+allNeedPoints);
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
                if(recordRet != 0 && exchangeRecordRet != 0){
                    data.setMessage("恭喜您，兑换成功，待平台处理");
                    data.setCode(Constants.STATUS_CODE.SUCCESS);
                    return data;
                }else{
                    data.setMessage("对不起，兑换失败");
                    data.setCode(Constants.STATUS_CODE.FAIL);
                    return data;
                }
            }else{
                data.setMessage("对不起，兑换失败");
                data.setCode(Constants.STATUS_CODE.FAIL);
                return data;
            }
        }else{
            data.setMessage("对不起，兑换失败");
            data.setCode(Constants.STATUS_CODE.FAIL);
            return data;
        }
    }

    /**
     * 获取兑换记录
     * @param dto
     * @return
     */
    @Override
    public ReturnData getExchangeRecords(FishDTO dto) {

        String mobile = dto.getMobile();
        ReturnData data = new ReturnData();
        Member member = memberDao.queryByMobile(mobile);
        if(member == null){
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，您的账号不存在");
            return data;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("offset", (dto.getPageNum() - 1) * dto.getPageSize());
        map.put("limit", dto.getPageSize());
        map.put("userId",member.getId());
        List<MallPointsExchangeRecord> list = exchangeRecordDao.queryList(map);
        MallPointsExchangeRecordVo vo = null;
        List<MallPointsExchangeRecordVo> voList = new ArrayList<>();
        for(MallPointsExchangeRecord record : list){
            vo = new MallPointsExchangeRecordVo();
            vo.setExchangeId(record.getId());
            MallProduct product = mallProductDao.queryObject(record.getProductId());
            if(product != null){
                vo.setTitle(product.getProductTitle());
            }
            vo.setCreateTime(DateUtil.formatTimestampForDate(record.getCreateTime()));
            vo.setPoints(record.getPoints());
            voList.add(vo);
        }
        JSONObject object = new JSONObject();
        object.put("exchageList",voList);
        data.setData(object);
        data.setCode(Constants.STATUS_CODE.SUCCESS);
        data.setMessage("查询成功");
        return data;
    }

    /**
     * 获取兑换详情
     * @param dto
     * @return
     */
    @Override
    public ReturnData getExchangeDetail(FishDTO dto) {

        ReturnData data = new ReturnData();
        int exchangeId = dto.getKey();
        MallPointsExchangeRecord exchangeRecord = exchangeRecordDao.queryObject(exchangeId);
        if(exchangeRecord == null){
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，兑换记录不存在");
            return data;
        }

        MallProduct product = mallProductDao.queryObject(exchangeRecord.getProductId());
        MallPointsExchangeRecordVo vo = new MallPointsExchangeRecordVo();
        if(product != null){
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

        JSONObject object = new JSONObject();
        object.put("exchageDetail",vo);
        data.setData(object);
        data.setCode(Constants.STATUS_CODE.SUCCESS);
        data.setMessage("查询成功");
        return data;
    }

    /**
     * 修改昵称
     * @param dto
     * @return
     */
    @Override
    public ReturnData modifyNickName(FishDTO dto) {

        ReturnData data = new ReturnData();
        if(StringUtil.isBlank(dto.getNickName())){
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，昵称不能为空");
            return data;
        }
        Member member = memberDao.queryByMobile(dto.getMobile());
        if(member == null){
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("对不起，您的账号不存在");
            return data;
        }
        int ret = memberDao.updateNickName(dto.getNickName(),member.getId());
        if(ret != 0){
            data.setCode(Constants.STATUS_CODE.SUCCESS);
            data.setMessage("修改成功");
            return data;
        }else{
            data.setCode(Constants.STATUS_CODE.FAIL);
            data.setMessage("修改失败");
            return data;
        }
    }
}
