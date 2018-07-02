package com.framework.service;

import com.framework.entity.TDocumentEntity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-06-12 17:37:37
 */
public interface TDocumentService {
	
	TDocumentEntity queryObject(Integer id);
	
	List<TDocumentEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TDocumentEntity tDocument);
	
	void update(TDocumentEntity tDocument);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
	List<TDocumentEntity> queryListByTypeCd(String[] code);
}
