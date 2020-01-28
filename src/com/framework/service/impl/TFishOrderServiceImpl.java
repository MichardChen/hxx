package com.framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.framework.dao.TFishOrderDao;
import com.framework.entity.TFishOrderEntity;
import com.framework.service.TFishOrderService;



@Service("tFishOrderService")
public class TFishOrderServiceImpl implements TFishOrderService {
	@Autowired
	private TFishOrderDao tFishOrderDao;
	
	@Override
	public TFishOrderEntity queryObject(Integer id){
		return tFishOrderDao.queryObject(id);
	}
	
	@Override
	public List<TFishOrderEntity> queryList(Map<String, Object> map){
		return tFishOrderDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tFishOrderDao.queryTotal(map);
	}
	
	@Override
	public void save(TFishOrderEntity tFishOrder){
		tFishOrderDao.save(tFishOrder);
	}
	
	@Override
	public void update(TFishOrderEntity tFishOrder){
		tFishOrderDao.update(tFishOrder);
	}
	
	@Override
	public void delete(Integer id){
		tFishOrderDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tFishOrderDao.deleteBatch(ids);
	}
	
}
