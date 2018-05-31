package com.framework.dao;

import java.util.List;
import java.util.Map;

import com.framework.entity.TCarSecondhandEntity;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-22 21:25:47
 */
public interface TCarSecondhandDao extends BaseDao<TCarSecondhandEntity> {
	
	List<TCarSecondhandEntity> queryMobileTerminalList(Map<String, Object> map);
}
