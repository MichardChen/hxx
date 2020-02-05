package com.framework.vo;

import java.io.Serializable;

/**
 * @author ChenDang
 * @date 2020/2/5 0005
 */
public class PointRecordListVo implements Serializable {

    private String mark;
    private String points;
    private String createTime;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
