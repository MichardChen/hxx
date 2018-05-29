package com.framework.service;

import com.framework.entity.TSalecartEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-29 22:50:01
 */
public interface TSalecartService {
	
	TSalecartEntity queryObject(Integer id);
	
	List<TSalecartEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TSalecartEntity tSalecart);
	
	void update(TSalecartEntity tSalecart);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
