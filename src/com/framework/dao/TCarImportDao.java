package com.framework.dao;

import java.util.List;
import java.util.Map;

import com.framework.entity.TCarImportEntity;
import com.framework.entity.TCarLeaseEntity;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-22 21:25:48
 */
public interface TCarImportDao extends BaseDao<TCarImportEntity> {
	
	List<TCarImportEntity> queryImportCartList(Map<String, Object> map);
	
	public List<TCarImportEntity> queryPCTerminalList(Map<String, Object> map);
	
	int queryImportCarTotal(Map<String, Object> map);
}
