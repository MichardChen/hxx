package com.framework.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.framework.entity.TCarouselEntity;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-16 22:46:34
 */
public interface TCarouselDao extends BaseDao<TCarouselEntity> {

	public List<TCarouselEntity> queryListByTypeCd(@Param("typeCd")String typeCd);
	
	public TCarouselEntity queryCarouselByTypeCd(@Param("typeCd")String typeCd);
}
