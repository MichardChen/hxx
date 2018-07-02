package com.framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.framework.dao.TDocumentDao;
import com.framework.entity.TDocumentEntity;
import com.framework.service.TDocumentService;



@Service("tDocumentService")
public class TDocumentServiceImpl implements TDocumentService {
	@Autowired
	private TDocumentDao tDocumentDao;
	
	@Override
	public TDocumentEntity queryObject(Integer id){
		return tDocumentDao.queryObject(id);
	}
	
	@Override
	public List<TDocumentEntity> queryList(Map<String, Object> map){
		return tDocumentDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tDocumentDao.queryTotal(map);
	}
	
	@Override
	public void save(TDocumentEntity tDocument){
		tDocumentDao.save(tDocument);
	}
	
	@Override
	public void update(TDocumentEntity tDocument){
		tDocumentDao.update(tDocument);
	}
	
	@Override
	public void delete(Integer id){
		tDocumentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tDocumentDao.deleteBatch(ids);
	}

	@Override
	public List<TDocumentEntity> queryListByTypeCd(String[] code) {
		return tDocumentDao.queryDocumentByTypeCd(code);
	}
	
}
