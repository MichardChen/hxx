package com.framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.framework.dao.TCarouselDao;
import com.framework.entity.TCarouselEntity;
import com.framework.service.TCarouselService;



@Service("tCarouselService")
public class TCarouselServiceImpl implements TCarouselService {
	@Autowired
	private TCarouselDao tCarouselDao;
	
	@Override
	public TCarouselEntity queryObject(Integer id){
		return tCarouselDao.queryObject(id);
	}
	
	@Override
	public List<TCarouselEntity> queryList(Map<String, Object> map){
		return tCarouselDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tCarouselDao.queryTotal(map);
	}
	
	@Override
	public void save(TCarouselEntity tCarousel){
		tCarouselDao.save(tCarousel);
	}
	
	@Override
	public void update(TCarouselEntity tCarousel){
		tCarouselDao.update(tCarousel);
	}
	
	@Override
	public void delete(Integer id){
		tCarouselDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tCarouselDao.deleteBatch(ids);
	}

	@Override
	public List<TCarouselEntity> queryListByTypeCd(String typeCd) {
		return tCarouselDao.queryListByTypeCd(typeCd);
	}

	@Override
	public TCarouselEntity queryByTypeCd(String typeCd) {
		return tCarouselDao.queryCarouselByTypeCd(typeCd);
	}
	
}
