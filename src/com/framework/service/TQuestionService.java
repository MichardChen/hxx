package com.framework.service;

import com.framework.entity.TQuestionEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-16 22:14:11
 */
public interface TQuestionService {
	
	TQuestionEntity queryObject(Integer id);
	
	List<TQuestionEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TQuestionEntity tQuestion);
	
	void update(TQuestionEntity tQuestion);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
