package com.framework.dao;

import org.apache.ibatis.annotations.Param;

import com.framework.entity.TCartParam2Entity;
import com.framework.entity.TCartParamsEntity;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-06-04 15:55:55
 */
public interface TCartParam2Dao extends BaseDao<TCartParam2Entity> {

	TCartParam2Entity queryObjectByCartId(@Param("cartId")int cartId);
	
	TCartParam2Entity queryObjectByCartIdType(@Param("cartId")Integer cartId,@Param("cartTypeCd")String cartTypeCd);
	
	void updateByCartId(TCartParam2Entity tCartParam2);
}
