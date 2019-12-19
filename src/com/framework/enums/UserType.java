package com.framework.enums;

/**
 * @author ChenDang
 * @date 2019/12/16 0016
 */
public enum UserType {

    NORMAL("1001","普通用户"),
    HIGH("1002","认证用户"),;
    private String code;
    private String name;

    UserType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getNameByCode(String code){
        for(UserType type : UserType.values()){
            if(code.equals(type.code)){
                return type.name;
            }
        }
        return null;
    }
}
