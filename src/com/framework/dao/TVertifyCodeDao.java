package com.framework.dao;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Param;

import com.framework.entity.TVertifyCodeEntity;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-30 10:21:33
 */
public interface TVertifyCodeDao extends BaseDao<TVertifyCodeEntity> {
	
	public TVertifyCodeEntity queryCodeByMobile(@Param("mobile")String mobile,@Param("typeCd")String typeCd);
	
	int updateExpireCode(@Param("id")int id,@Param("nowTime")Timestamp nowTime);
}
