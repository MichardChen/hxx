package com.framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.framework.dao.TSalecartDao;
import com.framework.entity.TSalecartEntity;
import com.framework.service.TSalecartService;



@Service("tSalecartService")
public class TSalecartServiceImpl implements TSalecartService {
	@Autowired
	private TSalecartDao tSalecartDao;
	
	@Override
	public TSalecartEntity queryObject(Integer id){
		return tSalecartDao.queryObject(id);
	}
	
	@Override
	public List<TSalecartEntity> queryList(Map<String, Object> map){
		return tSalecartDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tSalecartDao.queryTotal(map);
	}
	
	@Override
	public void save(TSalecartEntity tSalecart){
		tSalecartDao.save(tSalecart);
	}
	
	@Override
	public void update(TSalecartEntity tSalecart){
		tSalecartDao.update(tSalecart);
	}
	
	@Override
	public void delete(Integer id){
		tSalecartDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tSalecartDao.deleteBatch(ids);
	}
	
}
