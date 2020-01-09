package com.framework.service.impl;

import com.framework.dao.TraceSourceDao;
import com.framework.entity.TraceSourceEntity;
import com.framework.service.TraceSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("tTraceSourceService")
public class TraceSourceServiceImpl implements TraceSourceService {
	@Autowired
	private TraceSourceDao traceSourceDao;
	
	@Override
	public TraceSourceEntity queryObject(Integer id){
		return traceSourceDao.queryObject(id);
	}
	
	@Override
	public List<TraceSourceEntity> queryList(Map<String, Object> map){
		return traceSourceDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return traceSourceDao.queryTotal(map);
	}
	
	@Override
	public void save(TraceSourceEntity tTraceSource){
		traceSourceDao.save(tTraceSource);
	}
	
	@Override
	public void update(TraceSourceEntity tTraceSource){
		traceSourceDao.update(tTraceSource);
	}
	
	@Override
	public void delete(Integer id){
		traceSourceDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		traceSourceDao.deleteBatch(ids);
	}
	
}
