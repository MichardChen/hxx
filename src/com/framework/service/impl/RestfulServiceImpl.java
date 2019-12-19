package com.framework.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.framework.constants.Constants;
import com.framework.dao.*;
import com.framework.dto.FishDTO;
import com.framework.entity.*;
import com.framework.service.RestfulService;
import com.framework.utils.*;
import com.framework.vo.CarouselVo;
import com.framework.vo.IndexVo;
import com.framework.vo.NewsListVo;
import com.framework.vo.UserVo;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        String salt = "j8!@9ACIj##dk";
        String encryMobile = Md5Crypt.md5Crypt(mobile.getBytes(), salt);
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

        //判断有没有
        String code = VertifyUtil.getVertifyCode();
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
        TVertifyCodeEntity vCode = vertifyCodeDao.queryCodeByMobile(mobile,Constants.SHORT_MESSAGE_TYPE.REGISTER);
        Timestamp expireTime = vCode == null ? null : (Timestamp)vCode.getExpireTime();
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
                //一个月后失效
                userToken.setExpireTime(new Timestamp(System.currentTimeMillis()+30*24*60*60*1000));
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
                at.setExpireTime(new Timestamp(System.currentTimeMillis()+30*24*60*60*1000));
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
            userToken.setExpireTime(new Timestamp(System.currentTimeMillis()+30*24*60*60*1000));
            userToken.setPlatform(platForm);
            userToken.setCreateTime(new Timestamp(System.currentTimeMillis()));
            tokensave = userTokenDao.save(userToken);
            if(tokensave != 0){
                data.setCode(Constants.STATUS_CODE.SUCCESS);
                data.setMessage("登录成功");
            }else{
                data.setCode(Constants.STATUS_CODE.FAIL);
                data.setMessage("登录失败");
            }
        }else{
            userToken.setToken(token);
            userToken.setExpireTime(new Timestamp(System.currentTimeMillis()+30*24*60*60*1000));
            userToken.setPlatform(platForm);
            int ret = userTokenDao.update(userToken);
            if(ret != 0){
                data.setCode(Constants.STATUS_CODE.SUCCESS);
                data.setMessage("登录成功");
                return data;
            }else{
                data.setCode(Constants.STATUS_CODE.FAIL);
                data.setMessage("登录失败");
                return data;
            }

        }
        JSONObject map = new JSONObject();
        map.put("token", token);
        data.setData(map);
        return data;
    }
}
