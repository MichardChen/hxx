package com.framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.framework.dao.BankCardDao;
import com.framework.entity.BankCardEntity;
import com.framework.service.BankCardService;



@Service("bankCardService")
public class BankCardServiceImpl implements BankCardService {
	@Autowired
	private BankCardDao bankCardDao;
	
	@Override
	public BankCardEntity queryObject(Integer id){
		return bankCardDao.queryObject(id);
	}
	
	@Override
	public List<BankCardEntity> queryList(Map<String, Object> map){
		return bankCardDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return bankCardDao.queryTotal(map);
	}
	
	@Override
	public void save(BankCardEntity bankCard){
		bankCardDao.save(bankCard);
	}
	
	@Override
	public void update(BankCardEntity bankCard){
		bankCardDao.update(bankCard);
	}
	
	@Override
	public void delete(Integer id){
		bankCardDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		bankCardDao.deleteBatch(ids);
	}

	@Override
	public void enable(Integer[] ids){
		bankCardDao.enable(ids);
	}

	@Override
	public void disable(Integer[] ids){
		bankCardDao.disable(ids);
	}


}
