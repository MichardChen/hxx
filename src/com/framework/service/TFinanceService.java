package com.framework.service;

import com.framework.entity.TFinanceEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-16 22:28:57
 */
public interface TFinanceService {
	
	TFinanceEntity queryObject(Integer id);
	
	List<TFinanceEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TFinanceEntity tFinance);
	
	void update(TFinanceEntity tFinance);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
