package com.framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.framework.dao.TCarSecondhandDao;
import com.framework.entity.TCarSecondhandEntity;
import com.framework.service.TCarSecondhandService;



@Service("tCarSecondhandService")
public class TCarSecondhandServiceImpl implements TCarSecondhandService {
	@Autowired
	private TCarSecondhandDao tCarSecondhandDao;
	
	@Override
	public TCarSecondhandEntity queryObject(Integer id){
		return tCarSecondhandDao.queryObject(id);
	}
	
	@Override
	public List<TCarSecondhandEntity> queryList(Map<String, Object> map){
		return tCarSecondhandDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tCarSecondhandDao.queryTotal(map);
	}
	
	@Override
	public void save(TCarSecondhandEntity tCarSecondhand){
		tCarSecondhandDao.save(tCarSecondhand);
	}
	
	@Override
	public void update(TCarSecondhandEntity tCarSecondhand){
		tCarSecondhandDao.update(tCarSecondhand);
	}
	
	@Override
	public void delete(Integer id){
		tCarSecondhandDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tCarSecondhandDao.deleteBatch(ids);
	}
	
}
