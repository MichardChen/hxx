package com.framework.service;

import com.framework.entity.TMarketPriceEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2020-01-17 17:10:04
 */
public interface TMarketPriceService {
	
	TMarketPriceEntity queryObject(Integer id);
	
	List<TMarketPriceEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TMarketPriceEntity tMarketPrice);
	
	void update(TMarketPriceEntity tMarketPrice);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids, int flg, Date updateTime, int updateBy);
}
