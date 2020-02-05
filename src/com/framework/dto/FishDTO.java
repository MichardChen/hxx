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
    private String value;
    private String typeCd;
    private String mainType;
    private String size;
    private String unit;
    private String price;
    private String supply;
    private String province;
    private String city;
    private String latestTime;
    private String url;
    private int adminId;
    private String needs;
    private String buy;
    private String status;
    private String orderNo;


    public static FishDTO getInstance(HttpServletRequest request) throws Exception{

        request.setCharacterEncoding("UTF-8");
        dto = new FishDTO();
        dto.setProvinceId(StringUtil.toInteger(request.getParameter("provinceId")));
        dto.setCityId(StringUtil.toInteger(request.getParameter("cityId")));
        dto.setSex(StringUtil.toInteger(request.getParameter("sex")));
        dto.setAge(StringUtil.toInteger(request.getParameter("age")));
        dto.setPageNum(StringUtil.toInteger(request.getParameter("pageNum")));
        dto.setPageSize(StringUtil.toInteger(request.getParameter("pageSize")));

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
            dto.setReceivePerson(new String(request.getParameter("receivePerson").getBytes("utf-8"), "utf-8"));
        }
        if(StringUtil.isNoneBlank(request.getParameter("receiveMobile"))){
            dto.setReceiveMobile(new String(request.getParameter("receiveMobile").getBytes("utf-8"), "utf-8"));
        }
        if(StringUtil.isNoneBlank(request.getParameter("address"))){
            dto.setAddress(new String(request.getParameter("address").getBytes("utf-8"), "utf-8"));
        }
        if(StringUtil.isNoneBlank(request.getParameter("nickName"))){
            dto.setNickName(new String(request.getParameter("nickName").getBytes("utf-8"), "utf-8"));
        }
        if(StringUtil.isNoneBlank(request.getParameter("value"))){
            dto.setValue(new String(request.getParameter("value").getBytes("utf-8"), "utf-8"));
        }
        //发布供应
        if(StringUtil.isNoneBlank(request.getParameter("mainType"))){
            dto.setMainType(new String(request.getParameter("mainType").getBytes("utf-8"), "utf-8"));
        }
        if(StringUtil.isNoneBlank(request.getParameter("size"))){
            dto.setSize(new String(request.getParameter("size").getBytes("utf-8"), "utf-8"));
        }
        if(StringUtil.isNoneBlank(request.getParameter("unit"))){
            dto.setUnit(new String(request.getParameter("unit").getBytes("utf-8"), "utf-8"));
        }
        if(StringUtil.isNoneBlank(request.getParameter("price"))){
            dto.setPrice(new String(request.getParameter("price").getBytes("utf-8"), "utf-8"));
        }
        if(StringUtil.isNoneBlank(request.getParameter("supply"))){
            dto.setSupply(new String(request.getParameter("supply").getBytes("utf-8"), "utf-8"));
        }
        if(StringUtil.isNoneBlank(request.getParameter("province"))){
            dto.setProvince(new String(request.getParameter("province").getBytes("utf-8"), "utf-8"));
        }
        if(StringUtil.isNoneBlank(request.getParameter("city"))){
            dto.setCity(new String(request.getParameter("city").getBytes("utf-8"), "utf-8"));
        }
        if(StringUtil.isNoneBlank(request.getParameter("mark"))){
            dto.setMark(new String(request.getParameter("mark").getBytes("utf-8"), "utf-8"));
        }
        if(StringUtil.isNoneBlank(request.getParameter("needs"))){
            dto.setNeeds(new String(request.getParameter("needs").getBytes("utf-8"), "utf-8"));
        }
        if(StringUtil.isNoneBlank(request.getParameter("buy"))){
            dto.setBuy(new String(request.getParameter("buy").getBytes("utf-8"), "utf-8"));
        }
        dto.setTypeCd(request.getParameter("typeCd"));
        dto.setLatestTime(request.getParameter("latestTime"));
        dto.setStatus(request.getParameter("status"));
        dto.setOrderNo(request.getParameter("orderNo"));
         return dto;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getNeeds() {
        return needs;
    }

    public void setNeeds(String needs) {
        this.needs = needs;
    }

    public String getUrl() {
        return url;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLatestTime() {
        return latestTime;
    }

    public void setLatestTime(String latestTime) {
        this.latestTime = latestTime;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMainType() {
        return mainType;
    }

    public void setMainType(String mainType) {
        this.mainType = mainType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSupply() {
        return supply;
    }

    public void setSupply(String supply) {
        this.supply = supply;
    }

    public String getTypeCd() {
        return typeCd;
    }

    public void setTypeCd(String typeCd) {
        this.typeCd = typeCd;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
