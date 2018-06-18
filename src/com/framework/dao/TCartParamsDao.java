package com.framework.dao;

import org.apache.ibatis.annotations.Param;

import com.framework.entity.TCartParamsEntity;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-06-04 15:55:58
 */
public interface TCartParamsDao extends BaseDao<TCartParamsEntity> {
	
	TCartParamsEntity queryObjectByCartId(@Param("cartId")int cartId,@Param("cartTypeCd")String cartTypeCd);
	
	void updateByCartId(TCartParamsEntity tCartParams);
}
