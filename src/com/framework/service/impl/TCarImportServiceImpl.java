package com.framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.framework.dao.TCarImportDao;
import com.framework.entity.TCarImportEntity;
import com.framework.service.TCarImportService;



@Service("tCarImportService")
public class TCarImportServiceImpl implements TCarImportService {
	@Autowired
	private TCarImportDao tCarImportDao;
	
	@Override
	public TCarImportEntity queryObject(Integer id){
		return tCarImportDao.queryObject(id);
	}
	
	@Override
	public List<TCarImportEntity> queryList(Map<String, Object> map){
		return tCarImportDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tCarImportDao.queryTotal(map);
	}
	
	@Override
	public void save(TCarImportEntity tCarImport){
		tCarImportDao.save(tCarImport);
	}
	
	@Override
	public void update(TCarImportEntity tCarImport){
		tCarImportDao.update(tCarImport);
	}
	
	@Override
	public void delete(Integer id){
		tCarImportDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tCarImportDao.deleteBatch(ids);
	}

	@Override
	public List<TCarImportEntity> queryImportCartList(Map<String, Object> map) {
		return tCarImportDao.queryImportCartList(map);
	}

	@Override
	public List<TCarImportEntity> queryPCTerminalList(Map<String, Object> map) {
		return tCarImportDao.queryPCTerminalList(map);
	}

	@Override
	public int queryPCTerminalTotal(Map<String, Object> map) {
		return tCarImportDao.queryImportCarTotal(map);
	}
	
}
