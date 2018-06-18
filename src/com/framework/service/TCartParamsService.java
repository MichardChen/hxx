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
 * @date 2018-06-04 15:55:58
 */
public interface TCartParamsService {
	
	TCartParamsEntity queryObject(Integer id);
	
	List<TCartParamsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TCartParamsEntity tCartParams);
	
	void update(TCartParamsEntity tCartParams);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
	TCartParamsEntity queryObjectByCartId(Integer cartId,String cartTypeCd);
	
	void updateByCartId(TCartParamsEntity tCartParams);
}
