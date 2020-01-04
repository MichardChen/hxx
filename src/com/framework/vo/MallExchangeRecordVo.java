package com.framework.vo;

import com.framework.entity.MallPointsExchangeRecord;
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
public class MallExchangeRecordVo extends MallPointsExchangeRecord {

    private String userName;
    private String productTitle;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }
}
