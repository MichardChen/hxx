package com.framework.service;

import com.framework.entity.TCarLeaseEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-22 21:25:48
 */
public interface TCarLeaseService {
	
	TCarLeaseEntity queryObject(Integer id);
	
	List<TCarLeaseEntity> queryList(Map<String, Object> map);
	
	List<TCarLeaseEntity> queryMobileTerminalList(Map<String, Object> map);
	
	List<TCarLeaseEntity> queryPCTerminalList(Map<String, Object> map);
	
	int queryPCTerminalTotal(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TCarLeaseEntity tCarLease);
	
	void update(TCarLeaseEntity tCarLease);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
