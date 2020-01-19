package com.framework.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author ChenDang
 * @date 2020/1/15 0015
 */
public class FishParentVo implements Serializable {

    private int parentId;
    private String parentName;
    private List<FishChildVo> childs;

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<FishChildVo> getChilds() {
        return childs;
    }

    public void setChilds(List<FishChildVo> childs) {
        this.childs = childs;
    }
}
