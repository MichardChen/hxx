package com.framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.framework.dao.TQuestionDao;
import com.framework.entity.TQuestionEntity;
import com.framework.service.TQuestionService;



@Service("tQuestionService")
public class TQuestionServiceImpl implements TQuestionService {
	@Autowired
	private TQuestionDao tQuestionDao;
	
	@Override
	public TQuestionEntity queryObject(Integer id){
		return tQuestionDao.queryObject(id);
	}
	
	@Override
	public List<TQuestionEntity> queryList(Map<String, Object> map){
		return tQuestionDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tQuestionDao.queryTotal(map);
	}
	
	@Override
	public int save(TQuestionEntity tQuestion){
		return tQuestionDao.save(tQuestion);
	}
	
	@Override
	public void update(TQuestionEntity tQuestion){
		tQuestionDao.update(tQuestion);
	}
	
	@Override
	public void delete(Integer id){
		tQuestionDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tQuestionDao.deleteBatch(ids);
	}
	
}
