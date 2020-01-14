package com.framework.service.impl;

import com.framework.dao.TInsuranceCommitDao;
import com.framework.entity.TInsuranceCommitEntity;
import com.framework.service.TInsuranceCommitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service("tInsuranceCommitServiceImpl")
public class TInsuranceCommitServiceImpl implements TInsuranceCommitService {
	@Autowired
	private TInsuranceCommitDao tInsuranceCommitDao;
	
	@Override
	public TInsuranceCommitEntity queryObject(Integer id){
		return tInsuranceCommitDao.queryObject(id);
	}
	
	@Override
	public List<TInsuranceCommitEntity> queryList(Map<String, Object> map){
		return tInsuranceCommitDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tInsuranceCommitDao.queryTotal(map);
	}
	
	@Override
	public int save(TInsuranceCommitEntity tFinanceCommit){
		return tInsuranceCommitDao.save(tFinanceCommit);
	}
	
	@Override
	public void update(TInsuranceCommitEntity tFinanceCommit){
		tInsuranceCommitDao.update(tFinanceCommit);
	}
	
	@Override
	public void delete(Integer id){
		tInsuranceCommitDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tInsuranceCommitDao.deleteBatch(ids);
	}
	
}
