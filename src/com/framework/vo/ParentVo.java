package com.framework.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author ChenDang
 * @date 2020/1/9 0009
 */
public class ParentVo implements Serializable {

    private int parentId;
    private String parentName;
    private List<ChildVo> childs;

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

    public List<ChildVo> getChilds() {
        return childs;
    }

    public void setChilds(List<ChildVo> childs) {
        this.childs = childs;
    }
}
