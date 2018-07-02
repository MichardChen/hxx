package com.framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.framework.dao.TFinanceDao;
import com.framework.entity.TFinanceEntity;
import com.framework.service.TFinanceService;
import com.framework.utils.DateUtil;
import com.framework.utils.ShiroUtils;



@Service("tFinanceService")
public class TFinanceServiceImpl implements TFinanceService {
	@Autowired
	private TFinanceDao tFinanceDao;
	
	@Override
	public TFinanceEntity queryObject(Integer id){
		return tFinanceDao.queryObject(id);
	}
	
	@Override
	public List<TFinanceEntity> queryList(Map<String, Object> map){
		return tFinanceDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tFinanceDao.queryTotal(map);
	}
	
	@Override
	public void save(TFinanceEntity tFinance){
		tFinanceDao.save(tFinance);
	}
	
	@Override
	public void update(TFinanceEntity tFinance){
		tFinanceDao.update(tFinance);
	}
	
	@Override
	public void delete(Integer id){
		tFinanceDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tFinanceDao.deleteBatch(ids);
	}
	
}
