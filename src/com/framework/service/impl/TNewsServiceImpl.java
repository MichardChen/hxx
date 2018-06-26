package com.framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.framework.dao.TNewsDao;
import com.framework.entity.TNewsEntity;
import com.framework.service.TNewsService;



@Service("tNewsService")
public class TNewsServiceImpl implements TNewsService {
	@Autowired
	private TNewsDao tNewsDao;
	
	@Override
	public TNewsEntity queryObject(Integer id){
		return tNewsDao.queryObject(id);
	}
	
	@Override
	public List<TNewsEntity> queryList(Map<String, Object> map){
		return tNewsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tNewsDao.queryTotal(map);
	}
	
	@Override
	public void save(TNewsEntity tNews){
		tNewsDao.save(tNews);
	}
	
	@Override
	public void update(TNewsEntity tNews){
		tNewsDao.update(tNews);
	}
	
	@Override
	public void delete(Integer id){
		tNewsDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tNewsDao.deleteBatch(ids);
	}

	@Override
	public List<TNewsEntity> queryListData(Map<String, Object> map) {
		return tNewsDao.queryListData(map);
	}

	@Override
	public int queryTotalData(Map<String, Object> map) {
		return tNewsDao.queryTotalData(map);
	}
	
}
