package com.framework.service;

import com.framework.entity.TFinanceCommitEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-06-02 16:18:21
 */
public interface TFinanceCommitService {
	
	TFinanceCommitEntity queryObject(Integer id);
	
	List<TFinanceCommitEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	int save(TFinanceCommitEntity tFinanceCommit);
	
	void update(TFinanceCommitEntity tFinanceCommit);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
