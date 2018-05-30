package com.framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.framework.dao.TVertifyCodeDao;
import com.framework.entity.TVertifyCodeEntity;
import com.framework.service.TVertifyCodeService;



@Service("tVertifyCodeService")
public class TVertifyCodeServiceImpl implements TVertifyCodeService {
	@Autowired
	private TVertifyCodeDao tVertifyCodeDao;
	
	@Override
	public TVertifyCodeEntity queryObject(Integer id){
		return tVertifyCodeDao.queryObject(id);
	}
	
	@Override
	public List<TVertifyCodeEntity> queryList(Map<String, Object> map){
		return tVertifyCodeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tVertifyCodeDao.queryTotal(map);
	}
	
	@Override
	public int save(TVertifyCodeEntity tVertifyCode){
		return tVertifyCodeDao.save(tVertifyCode);
	}
	
	@Override
	public int update(TVertifyCodeEntity tVertifyCode){
		return tVertifyCodeDao.update(tVertifyCode);
	}
	
	@Override
	public void delete(Integer id){
		tVertifyCodeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tVertifyCodeDao.deleteBatch(ids);
	}

	@Override
	public TVertifyCodeEntity queryObjectByMobile(String mobile,String typeCd) {
		return tVertifyCodeDao.queryCodeByMobile(mobile, typeCd);
	}

	@Override
	public int updateExpireCode(int id,Timestamp nowTime){
		return tVertifyCodeDao.updateExpireCode(id,nowTime);
	}
}
