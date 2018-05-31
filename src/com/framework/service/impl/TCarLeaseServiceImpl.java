package com.framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.framework.dao.TCarLeaseDao;
import com.framework.entity.TCarLeaseEntity;
import com.framework.service.TCarLeaseService;



@Service("tCarLeaseService")
public class TCarLeaseServiceImpl implements TCarLeaseService {
	@Autowired
	private TCarLeaseDao tCarLeaseDao;
	
	@Override
	public TCarLeaseEntity queryObject(Integer id){
		return tCarLeaseDao.queryObject(id);
	}
	
	@Override
	public List<TCarLeaseEntity> queryList(Map<String, Object> map){
		return tCarLeaseDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tCarLeaseDao.queryTotal(map);
	}
	
	@Override
	public void save(TCarLeaseEntity tCarLease){
		tCarLeaseDao.save(tCarLease);
	}
	
	@Override
	public void update(TCarLeaseEntity tCarLease){
		tCarLeaseDao.update(tCarLease);
	}
	
	@Override
	public void delete(Integer id){
		tCarLeaseDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tCarLeaseDao.deleteBatch(ids);
	}

	@Override
	public List<TCarLeaseEntity> queryMobileTerminalList(Map<String, Object> map) {
		return tCarLeaseDao.queryMobileTerminalList(map);
	}
	
}
