package com.framework.vo;

import com.framework.entity.MallPointsRecord;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Chenwx
 * @date 2020/01/02
 */
@Data
@Setter
@Getter
public class MallPointsListVo extends MallPointsRecord {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
