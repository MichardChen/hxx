package com.framework.service;

import com.framework.entity.TCarSecondhandEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-22 21:25:47
 */
public interface TCarSecondhandService {
	
	TCarSecondhandEntity queryObject(Integer id);
	
	List<TCarSecondhandEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TCarSecondhandEntity tCarSecondhand);
	
	void update(TCarSecondhandEntity tCarSecondhand);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
