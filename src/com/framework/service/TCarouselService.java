package com.framework.service;

import com.framework.entity.TCarouselEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-16 22:46:34
 */
public interface TCarouselService {
	
	TCarouselEntity queryObject(Integer id);
	
	List<TCarouselEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TCarouselEntity tCarousel);
	
	void update(TCarouselEntity tCarousel);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
	List<TCarouselEntity> queryListByTypeCd(String typeCd);
	
	TCarouselEntity queryByTypeCd(String typeCd);
}
