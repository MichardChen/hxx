package com.framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.framework.dao.TBrandDao;
import com.framework.entity.TBrandEntity;
import com.framework.service.TBrandService;



@Service("tBrandService")
public class TBrandServiceImpl implements TBrandService {
	@Autowired
	private TBrandDao tBrandDao;
	
	@Override
	public TBrandEntity queryObject(Integer id){
		return tBrandDao.queryObject(id);
	}
	
	@Override
	public List<TBrandEntity> queryList(Map<String, Object> map){
		return tBrandDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tBrandDao.queryTotal(map);
	}
	
	@Override
	public void save(TBrandEntity tBrand){
		tBrandDao.save(tBrand);
	}
	
	@Override
	public void update(TBrandEntity tBrand){
		tBrandDao.update(tBrand);
	}
	
	@Override
	public void delete(Integer id){
		tBrandDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tBrandDao.deleteBatch(ids);
	}

	@Override
	public List<TBrandEntity> queryAllList(Integer flg) {
		return tBrandDao.queryAllList(flg);
	}
	
}
