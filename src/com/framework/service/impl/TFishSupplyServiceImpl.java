package com.framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.framework.dao.TFishSupplyDao;
import com.framework.entity.TFishSupplyEntity;
import com.framework.service.TFishSupplyService;



@Service("tFishSupplyService")
public class TFishSupplyServiceImpl implements TFishSupplyService {
	@Autowired
	private TFishSupplyDao tFishSupplyDao;
	
	@Override
	public TFishSupplyEntity queryObject(Integer id){
		return tFishSupplyDao.queryObject(id);
	}
	
	@Override
	public List<TFishSupplyEntity> queryList(Map<String, Object> map){
		return tFishSupplyDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tFishSupplyDao.queryTotal(map);
	}
	
	@Override
	public void save(TFishSupplyEntity tFishSupply){
		tFishSupplyDao.save(tFishSupply);
	}
	
	@Override
	public void update(TFishSupplyEntity tFishSupply){
		tFishSupplyDao.update(tFishSupply);
	}
	
	@Override
	public void delete(Integer id){
		tFishSupplyDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tFishSupplyDao.deleteBatch(ids);
	}
	
}
