package com.framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.framework.dao.TCartParam2Dao;
import com.framework.entity.TCartParam2Entity;
import com.framework.service.TCartParam2Service;



@Service("tCartParam2Service")
public class TCartParam2ServiceImpl implements TCartParam2Service {
	@Autowired
	private TCartParam2Dao tCartParam2Dao;
	
	@Override
	public TCartParam2Entity queryObject(Integer id){
		return tCartParam2Dao.queryObject(id);
	}
	
	@Override
	public List<TCartParam2Entity> queryList(Map<String, Object> map){
		return tCartParam2Dao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tCartParam2Dao.queryTotal(map);
	}
	
	@Override
	public void save(TCartParam2Entity tCartParam2){
		tCartParam2Dao.save(tCartParam2);
	}
	
	@Override
	public void update(TCartParam2Entity tCartParam2){
		tCartParam2Dao.update(tCartParam2);
	}
	
	@Override
	public void delete(Integer id){
		tCartParam2Dao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tCartParam2Dao.deleteBatch(ids);
	}

	@Override
	public TCartParam2Entity queryObjectByCartId(Integer cartId) {
		return tCartParam2Dao.queryObjectByCartId(cartId);
	}
	
}
