package com.framework.service;

import com.framework.entity.BankCardEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author R & D
 * @email adang369@126.com
 * @date 2020-03-22 14:48:12
 */
public interface BankCardService {
	
	BankCardEntity queryObject(Integer id);
	
	List<BankCardEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BankCardEntity bankCard);
	
	void update(BankCardEntity bankCard);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	void enable(Integer[] ids);

	void disable(Integer[] ids);
}
