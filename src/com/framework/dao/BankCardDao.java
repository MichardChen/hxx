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
}
