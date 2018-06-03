package com.framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.framework.dao.TFinanceCommitDao;
import com.framework.entity.TFinanceCommitEntity;
import com.framework.service.TFinanceCommitService;



@Service("tFinanceCommitService")
public class TFinanceCommitServiceImpl implements TFinanceCommitService {
	@Autowired
	private TFinanceCommitDao tFinanceCommitDao;
	
	@Override
	public TFinanceCommitEntity queryObject(Integer id){
		return tFinanceCommitDao.queryObject(id);
	}
	
	@Override
	public List<TFinanceCommitEntity> queryList(Map<String, Object> map){
		return tFinanceCommitDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tFinanceCommitDao.queryTotal(map);
	}
	
	@Override
	public int save(TFinanceCommitEntity tFinanceCommit){
		return tFinanceCommitDao.save(tFinanceCommit);
	}
	
	@Override
	public void update(TFinanceCommitEntity tFinanceCommit){
		tFinanceCommitDao.update(tFinanceCommit);
	}
	
	@Override
	public void delete(Integer id){
		tFinanceCommitDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tFinanceCommitDao.deleteBatch(ids);
	}
	
}
