package com.framework.dao;

import java.util.List;

import com.framework.entity.TBrandEntity;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-16 23:00:02
 */
public interface TBrandDao extends BaseDao<TBrandEntity> {
	
	List<TBrandEntity> queryAllList();
}
