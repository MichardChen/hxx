package com.framework.service;

import com.framework.entity.TraceSourceEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2020-01-09 20:42:50
 */
public interface TraceSourceService {
	
	TraceSourceEntity queryObject(Integer id);
	
	List<TraceSourceEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TraceSourceEntity tTraceSource);
	
	void update(TraceSourceEntity tTraceSource);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
