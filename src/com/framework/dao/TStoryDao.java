package com.framework.dao;

import java.util.List;
import java.util.Map;

import com.framework.entity.TStoryEntity;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-14 11:40:45
 */
public interface TStoryDao extends BaseDao<TStoryEntity> {
	
	int deleteBatchs(Map<String, Object> map);
	
	List<TStoryEntity> queryListData(Map<String, Object> map);
	
	int queryTotalData(Map<String, Object> map);
}
