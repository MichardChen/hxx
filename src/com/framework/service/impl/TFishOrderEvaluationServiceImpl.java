package com.framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.framework.dao.TFishOrderEvaluationDao;
import com.framework.entity.TFishOrderEvaluationEntity;
import com.framework.service.TFishOrderEvaluationService;



@Service("tFishOrderEvaluationService")
public class TFishOrderEvaluationServiceImpl implements TFishOrderEvaluationService {
	@Autowired
	private TFishOrderEvaluationDao tFishOrderEvaluationDao;
	
	@Override
	public TFishOrderEvaluationEntity queryObject(Integer id){
		return tFishOrderEvaluationDao.queryObject(id);
	}
	
	@Override
	public List<TFishOrderEvaluationEntity> queryList(Map<String, Object> map){
		return tFishOrderEvaluationDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tFishOrderEvaluationDao.queryTotal(map);
	}
	
	@Override
	public void save(TFishOrderEvaluationEntity tFishOrderEvaluation){
		tFishOrderEvaluationDao.save(tFishOrderEvaluation);
	}
	
	@Override
	public void update(TFishOrderEvaluationEntity tFishOrderEvaluation){
		tFishOrderEvaluationDao.update(tFishOrderEvaluation);
	}
	
	@Override
	public void delete(Integer id){
		tFishOrderEvaluationDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tFishOrderEvaluationDao.deleteBatch(ids);
	}
	
}
