package com.framework.service.impl;

import com.framework.service.TInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.framework.dao.TInsuranceDao;
import com.framework.entity.TInsuranceEntity;
import com.framework.service.TFinanceService;
import com.framework.utils.DateUtil;
import com.framework.utils.ShiroUtils;



@Service("tInsuranceService")
public class TInsuranceServiceImpl implements TInsuranceService {
	@Autowired
	private TInsuranceDao TInsuranceDao;
	
	@Override
	public TInsuranceEntity queryObject(Integer id){
		return TInsuranceDao.queryObject(id);
	}
	
	@Override
	public List<TInsuranceEntity> queryList(Map<String, Object> map){
		return TInsuranceDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return TInsuranceDao.queryTotal(map);
	}
	
	@Override
	public void save(TInsuranceEntity tFinance){
		TInsuranceDao.save(tFinance);
	}
	
	@Override
	public void update(TInsuranceEntity tFinance){
		TInsuranceDao.update(tFinance);
	}
	
	@Override
	public void delete(Integer id){
		TInsuranceDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		TInsuranceDao.deleteBatch(ids);
	}
	
}
