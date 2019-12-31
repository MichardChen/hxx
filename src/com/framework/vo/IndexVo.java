package com.framework.vo;

import java.io.Serializable;

/**
 * @author ChenDang
 * @date 2019/12/17 0017
 */
public class IndexVo implements Serializable {

    private String androidVersion;
    private String iosVersion;
    private String isoUrl;
    private String androidUrl;

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public String getIosVersion() {
        return iosVersion;
    }

    public void setIosVersion(String iosVersion) {
        this.iosVersion = iosVersion;
    }

    public String getIsoUrl() {
        return isoUrl;
    }

    public void setIsoUrl(String isoUrl) {
        this.isoUrl = isoUrl;
    }

    public String getAndroidUrl() {
        return androidUrl;
    }

    public void setAndroidUrl(String androidUrl) {
        this.androidUrl = androidUrl;
    }
}
