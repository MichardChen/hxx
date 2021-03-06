package com.framework.service;

import com.framework.entity.TCarImportEntity;
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
public interface TCarImportService {

	TCarImportEntity queryObject(Integer id);

	List<TCarImportEntity> queryList(Map<String, Object> map);

	List<TCarImportEntity> queryImportCartList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);

	void save(TCarImportEntity tCarImport);

	void update(TCarImportEntity tCarImport);
	
	int updateHot(int cartId);

	void delete(Integer id);

	void deleteBatch(Integer[] ids);

	List<TCarImportEntity> queryPCTerminalList(Map<String, Object> map);

	int queryPCTerminalTotal(Map<String, Object> map);
}
