package com.framework.service;

import com.framework.entity.TFishBuyEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2020-01-05 12:40:44
 */
public interface TFishBuyService {
	
	TFishBuyEntity queryObject(Integer id);
	
	List<TFishBuyEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TFishBuyEntity tFishBuy);
	
	void update(TFishBuyEntity tFishBuy);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
