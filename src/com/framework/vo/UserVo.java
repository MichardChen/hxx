package com.framework.vo;

/**
 * @author ChenDang
 * @date 2019/12/18 0018
 */
public class UserVo {

    private String idCardImgUrl;
    private String idCardReverseImgUrl;
    private String handIdCardImgUrl;
    private String businessImgUrl;
    private String headerImgUrl;
    private String nickName;
    private String status;
    private String memberGradeCd;
    private String statusName;
    private int point;

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getIdCardImgUrl() {
        return idCardImgUrl;
    }

    public void setIdCardImgUrl(String idCardImgUrl) {
        this.idCardImgUrl = idCardImgUrl;
    }

    public String getIdCardReverseImgUrl() {
        return idCardReverseImgUrl;
    }

    public void setIdCardReverseImgUrl(String idCardReverseImgUrl) {
        this.idCardReverseImgUrl = idCardReverseImgUrl;
    }

    public String getHandIdCardImgUrl() {
        return handIdCardImgUrl;
    }

    public void setHandIdCardImgUrl(String handIdCardImgUrl) {
        this.handIdCardImgUrl = handIdCardImgUrl;
    }

    public String getBusinessImgUrl() {
        return businessImgUrl;
    }

    public void setBusinessImgUrl(String businessImgUrl) {
        this.businessImgUrl = businessImgUrl;
    }

    public String getHeaderImgUrl() {
        return headerImgUrl;
    }

    public void setHeaderImgUrl(String headerImgUrl) {
        this.headerImgUrl = headerImgUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMemberGradeCd() {
        return memberGradeCd;
    }

    public void setMemberGradeCd(String memberGradeCd) {
        this.memberGradeCd = memberGradeCd;
    }
}
