package com.framework.service;

import com.framework.entity.TBrandEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-16 23:00:02
 */
public interface TBrandService {
	
	TBrandEntity queryObject(Integer id);
	
	List<TBrandEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TBrandEntity tBrand);
	
	void update(TBrandEntity tBrand);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
