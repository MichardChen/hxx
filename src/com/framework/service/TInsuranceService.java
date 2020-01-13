package com.framework.service;

import com.framework.entity.TInsuranceEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-16 22:28:57
 */
public interface TInsuranceService {
	
	TInsuranceEntity queryObject(Integer id);
	
	List<TInsuranceEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TInsuranceEntity tFinance);
	
	void update(TInsuranceEntity tFinance);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
