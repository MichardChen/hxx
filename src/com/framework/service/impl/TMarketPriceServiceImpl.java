package com.framework.service.impl;

import com.framework.utils.DateUtil;
import com.framework.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.framework.dao.TMarketPriceDao;
import com.framework.entity.TMarketPriceEntity;
import com.framework.service.TMarketPriceService;



@Service("tMarketPriceService")
public class TMarketPriceServiceImpl implements TMarketPriceService {
	@Autowired
	private TMarketPriceDao tMarketPriceDao;
	
	@Override
	public TMarketPriceEntity queryObject(Integer id){
		return tMarketPriceDao.queryObject(id);
	}
	
	@Override
	public List<TMarketPriceEntity> queryList(Map<String, Object> map){
		return tMarketPriceDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tMarketPriceDao.queryTotal(map);
	}
	
	@Override
	public void save(TMarketPriceEntity tMarketPrice){
		tMarketPriceDao.save(tMarketPrice);
	}
	
	@Override
	public void update(TMarketPriceEntity tMarketPrice){
		tMarketPriceDao.update(tMarketPrice);
	}
	
	@Override
	public void delete(Integer id){
		tMarketPriceDao.updateFlg(0, DateUtil.getNowTimestamp(), ShiroUtils.getUserId().intValue(),id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids,int flg, Date updateTime, int updateBy){
		tMarketPriceDao.updateFlgBatch(0,DateUtil.getNowTimestamp(),ShiroUtils.getUserId().intValue(),ids);
	}
	
}
