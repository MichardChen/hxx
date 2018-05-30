package com.framework.service;

import com.framework.entity.TVertifyCodeEntity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-30 10:21:33
 */
public interface TVertifyCodeService {
	
	TVertifyCodeEntity queryObject(Integer id);
	
	TVertifyCodeEntity queryObjectByMobile(String mobile,String typeCd);
	
	int updateExpireCode(int id,Timestamp nowTime);
	
	List<TVertifyCodeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	int save(TVertifyCodeEntity tVertifyCode);
	
	int update(TVertifyCodeEntity tVertifyCode);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
