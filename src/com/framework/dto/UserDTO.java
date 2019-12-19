package com.framework.dto;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @author ChenDang
 * @date 2019/12/16 0016
 */
public class UserDTO implements Serializable {

    private String mobile;
    private String userTypeCd;
    private String platform;
    private static UserDTO dto;

    public static UserDTO getInstance(HttpServletRequest request) {

        dto = new UserDTO();
        dto.setMobile(request.getParameter("mobile"));
        dto.setUserTypeCd(request.getParameter("userTypeCd"));
        dto.setPlatform(request.getParameter("platform"));
        return dto;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserTypeCd() {
        return userTypeCd;
    }

    public void setUserTypeCd(String userTypeCd) {
        this.userTypeCd = userTypeCd;
    }
}
