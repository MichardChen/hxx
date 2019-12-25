package com.framework.dto;

import com.framework.utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class FishDTO implements Serializable {

    private static FishDTO dto;
    private int pageSize;
    private int pageNum;
    private String mark;
    private String mobile;
    private String name;
    private String code;
    private int key;
    private String color;
    private int provinceId;
    private int cityId;
    private int age;
    private int sex;
    private String userTypeCd;
    private String platform;
    private String memberGradeCd;
    private String md5Mobile;
    private String shortMsgTypeCd;
    private String userPwd;
    private String token;
    private String oldPwd;
    private String deviceToken;
    private int productId;
    private int productNum;
    private String receivePerson;
    private String receiveMobile;
    private String address;
    private String nickName;



    public static FishDTO getInstance(HttpServletRequest request) throws Exception{

        request.setCharacterEncoding("UTF-8");
        dto = new FishDTO();
        dto.setProvinceId(StringUtil.toInteger(request.getParameter("provinceId")));
        dto.setCityId(StringUtil.toInteger(request.getParameter("cityId")));
        dto.setSex(StringUtil.toInteger(request.getParameter("sex")));
        dto.setAge(StringUtil.toInteger(request.getParameter("age")));
        dto.setPageNum(StringUtil.toInteger(request.getParameter("pageNum")));
        dto.setPageSize(StringUtil.toInteger(request.getParameter("pageSize")));
        dto.setMark(request.getParameter("mark"));
        dto.setMobile(request.getParameter("mobile"));
        dto.setName(request.getParameter("name"));
        dto.setCode(request.getParameter("code"));
        dto.setColor(request.getParameter("color"));
        dto.setKey(StringUtil.toInteger(request.getParameter("key")));
        dto.setUserTypeCd(request.getParameter("userTypeCd"));
        dto.setPlatform(request.getParameter("platform"));
        dto.setMemberGradeCd(request.getParameter("memberGradeCd"));
        dto.setMd5Mobile(request.getParameter("md5Mobile"));
        dto.setShortMsgTypeCd(request.getParameter("shortMsgTypeCd"));
        dto.setUserPwd(request.getParameter("userPwd"));
        dto.setOldPwd(request.getParameter("oldPwd"));
        dto.setDeviceToken(request.getParameter("deviceToken"));
        dto.setProductId(StringUtil.toInteger(request.getParameter("productId")));
        dto.setProductNum(StringUtil.toInteger(request.getParameter("productNum")));
        if(StringUtil.isNoneBlank(request.getParameter("receivePerson"))){
            dto.setReceivePerson(new String(request.getParameter("receivePerson").getBytes("iso-8859-1"), "utf-8"));
        }
        if(StringUtil.isNoneBlank(request.getParameter("receiveMobile"))){
            dto.setReceiveMobile(new String(request.getParameter("receiveMobile").getBytes("iso-8859-1"), "utf-8"));
        }
        if(StringUtil.isNoneBlank(request.getParameter("address"))){
            dto.setAddress(new String(request.getParameter("address").getBytes("iso-8859-1"), "utf-8"));
        }
        if(StringUtil.isNoneBlank(request.getParameter("nickName"))){
            dto.setNickName(new String(request.getParameter("nickName").getBytes("iso-8859-1"), "utf-8"));
        }
         return dto;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getReceivePerson() {
        return receivePerson;
    }

    public void setReceivePerson(String receivePerson) {
        this.receivePerson = receivePerson;
    }

    public String getReceiveMobile() {
        return receiveMobile;
    }

    public void setReceiveMobile(String receiveMobile) {
        this.receiveMobile = receiveMobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getShortMsgTypeCd() {
        return shortMsgTypeCd;
    }

    public void setShortMsgTypeCd(String shortMsgTypeCd) {
        this.shortMsgTypeCd = shortMsgTypeCd;
    }

    public String getMd5Mobile() {
        return md5Mobile;
    }

    public void setMd5Mobile(String md5Mobile) {
        this.md5Mobile = md5Mobile;
    }

    public String getMemberGradeCd() {
        return memberGradeCd;
    }

    public void setMemberGradeCd(String memberGradeCd) {
        this.memberGradeCd = memberGradeCd;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getUserTypeCd() {
        return userTypeCd;
    }

    public void setUserTypeCd(String userTypeCd) {
        this.userTypeCd = userTypeCd;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
