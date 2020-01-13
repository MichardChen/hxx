package com.framework.service;

import com.framework.entity.TFishSupplyEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2020-01-05 14:41:53
 */
public interface TFishSupplyService {
	
	TFishSupplyEntity queryObject(Integer id);
	
	List<TFishSupplyEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TFishSupplyEntity tFishSupply);
	
	void update(TFishSupplyEntity tFishSupply);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
