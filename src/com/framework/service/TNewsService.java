package com.framework.service;

import com.framework.entity.TNewsEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-03-16 17:13:03
 */
public interface TNewsService {
	
	TNewsEntity queryObject(Integer id);
	
	List<TNewsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TNewsEntity tNews);
	
	void update(TNewsEntity tNews);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
