package com.framework.service;

import com.framework.entity.TInsuranceCommitEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-06-02 16:18:21
 */
public interface TInsuranceCommitService {
	
	TInsuranceCommitEntity queryObject(Integer id);
	
	List<TInsuranceCommitEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	int save(TInsuranceCommitEntity tFinanceCommit);
	
	void update(TInsuranceCommitEntity tFinanceCommit);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
