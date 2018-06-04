package com.framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.framework.dao.TCartParamsDao;
import com.framework.entity.TCartParamsEntity;
import com.framework.service.TCartParamsService;



@Service("tCartParamsService")
public class TCartParamsServiceImpl implements TCartParamsService {
	@Autowired
	private TCartParamsDao tCartParamsDao;
	
	@Override
	public TCartParamsEntity queryObject(Integer id){
		return tCartParamsDao.queryObject(id);
	}
	
	@Override
	public List<TCartParamsEntity> queryList(Map<String, Object> map){
		return tCartParamsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tCartParamsDao.queryTotal(map);
	}
	
	@Override
	public void save(TCartParamsEntity tCartParams){
		tCartParamsDao.save(tCartParams);
	}
	
	@Override
	public void update(TCartParamsEntity tCartParams){
		tCartParamsDao.update(tCartParams);
	}
	
	@Override
	public void delete(Integer id){
		tCartParamsDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tCartParamsDao.deleteBatch(ids);
	}

	@Override
	public TCartParamsEntity queryObjectByCartId(Integer cartId) {
		return tCartParamsDao.queryObjectByCartId(cartId);
	}
}
