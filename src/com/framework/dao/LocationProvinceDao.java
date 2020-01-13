package com.framework.dao;

import java.util.List;

import com.framework.entity.LocationProvinceEntity;
import com.framework.vo.ProvinceCityVo;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-23 17:10:41
 */
public interface LocationProvinceDao extends BaseDao<LocationProvinceEntity> {
	
	List<LocationProvinceEntity> queryAllList();

	List<ProvinceCityVo> queryAllProviceCity();
}
