package com.framework.service;

import com.framework.entity.TCartParam2Entity;
import com.framework.entity.TCartParamsEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-06-04 15:55:55
 */
public interface TCartParam2Service {
	
	TCartParam2Entity queryObject(Integer id);
	
	List<TCartParam2Entity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TCartParam2Entity tCartParam2);
	
	void update(TCartParam2Entity tCartParam2);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
	TCartParam2Entity queryObjectByCartId(Integer cartId);
	
	TCartParam2Entity queryObjectByCartIdType(Integer cartId,String cartTypeCd);
	
	void updateByCartId(TCartParam2Entity tCartParam2);
}
