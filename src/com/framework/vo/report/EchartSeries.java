package com.framework.vo.report;

/**
 * @author Chenwx
 * @date
 */
public class EchartSeries {
    /**
     * 线名称
     */
    private String name;
    /**
     * 展示类型
     */
    private String type;
    /**
     * 线值
     */
    private String[] data;

    public EchartSeries(String name, String type, String[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
