package com.framework.dao;

import java.util.List;
import java.util.Map;

import com.framework.entity.TNewsEntity;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-03-16 17:13:03
 */
public interface TNewsDao extends BaseDao<TNewsEntity> {
	
	List<TNewsEntity> queryListData(Map<String, Object> map);
	
	int queryTotalData(Map<String, Object> map);
}
