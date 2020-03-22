package com.framework.dao;

import com.framework.entity.BankCardEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author R & D
 * @email adang369@126.com
 * @date 2020-03-15 21:27:53
 */
public interface BankCardDao extends BaseDao<BankCardEntity> {

    BankCardEntity queryAvailableCard(@Param("flg")int flg);

    /**
     * 根据对象ID数组批量启用银行卡
     */
    int enable(Object[] id);

    /**
     * 根据对象ID数组批量禁用银行卡
     */
    int disable(Object[] id);
}
