package com.framework.service;

import com.framework.entity.TFishOrderEvaluationEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author R & D
 * @email adang369@126.com
 * @date 2020-03-22 14:48:12
 */
public interface TFishOrderEvaluationService {
	
	TFishOrderEvaluationEntity queryObject(Integer id);
	
	List<TFishOrderEvaluationEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TFishOrderEvaluationEntity tFishOrderEvaluation);
	
	void update(TFishOrderEvaluationEntity tFishOrderEvaluation);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
