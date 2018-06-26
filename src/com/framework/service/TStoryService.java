package com.framework.service;

import com.framework.entity.TStoryEntity;
import com.framework.model.StoreAddUpdateModel;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-14 11:40:45
 */
public interface TStoryService {
	
	TStoryEntity queryObject(Integer id);
	
	List<TStoryEntity> queryList(Map<String, Object> map);
	
	List<TStoryEntity> queryListData(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	int queryTotalData(Map<String, Object> map);
	
	void save(TStoryEntity tStory);
	
	void update(TStoryEntity tStory);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids,Timestamp updateTime,int updateBy);
}
