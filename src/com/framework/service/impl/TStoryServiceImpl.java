package com.framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.dao.TStoryDao;
import com.framework.entity.TStoryEntity;
import com.framework.service.TStoryService;



@Service("tStoryService")
public class TStoryServiceImpl implements TStoryService {
	@Autowired
	private TStoryDao tStoryDao;
	
	@Override
	public TStoryEntity queryObject(Integer id){
		return tStoryDao.queryObject(id);
	}
	
	@Override
	public List<TStoryEntity> queryList(Map<String, Object> map){
		return tStoryDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tStoryDao.queryTotal(map);
	}
	
	@Override
	public void save(TStoryEntity tStory){
		tStoryDao.save(tStory);
	}
	
	@Override
	public void update(TStoryEntity tStory){
		tStoryDao.update(tStory);
	}
	
	@Override
	public void delete(Integer id){
		tStoryDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids,Timestamp updateTime,int updateBy){
		Map<String, Object> map = new HashMap<>();
		map.put("updateTime", updateTime);
		map.put("updateBy", updateBy);
		map.put("ids", ids);
		tStoryDao.deleteBatchs(map);
	}

	@Override
	public List<TStoryEntity> queryListData(Map<String, Object> map) {
		return tStoryDao.queryListData(map);
	}

	@Override
	public int queryTotalData(Map<String, Object> map) {
		return tStoryDao.queryTotalData(map);
	}
	
}
