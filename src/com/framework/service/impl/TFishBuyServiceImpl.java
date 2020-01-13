package com.framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.framework.dao.TFishBuyDao;
import com.framework.entity.TFishBuyEntity;
import com.framework.service.TFishBuyService;



@Service("tFishBuyService")
public class TFishBuyServiceImpl implements TFishBuyService {
	@Autowired
	private TFishBuyDao tFishBuyDao;
	
	@Override
	public TFishBuyEntity queryObject(Integer id){
		return tFishBuyDao.queryObject(id);
	}
	
	@Override
	public List<TFishBuyEntity> queryList(Map<String, Object> map){
		return tFishBuyDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tFishBuyDao.queryTotal(map);
	}
	
	@Override
	public void save(TFishBuyEntity tFishBuy){
		tFishBuyDao.save(tFishBuy);
	}
	
	@Override
	public void update(TFishBuyEntity tFishBuy){
		tFishBuyDao.update(tFishBuy);
	}
	
	@Override
	public void delete(Integer id){
		tFishBuyDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tFishBuyDao.deleteBatch(ids);
	}
	
}
