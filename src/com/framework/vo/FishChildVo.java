package com.framework.vo;

import java.io.Serializable;

/**
 * @author ChenDang
 * @date 2020/1/15 0015
 */
public class FishChildVo implements Serializable {

    private int childId;
    private String childName;
    private String logo;

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
