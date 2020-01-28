package com.framework.service;

import com.framework.entity.TFishOrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author R & D
 * @email adang369@126.com
 * @date 2020-01-23 09:07:29
 */
public interface TFishOrderService {
	
	TFishOrderEntity queryObject(Integer id);
	
	List<TFishOrderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TFishOrderEntity tFishOrder);
	
	void update(TFishOrderEntity tFishOrder);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
