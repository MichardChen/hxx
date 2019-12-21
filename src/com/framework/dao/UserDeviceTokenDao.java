package com.framework.dao;

import com.framework.entity.UserDeviceToken;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;

/**
 * @author ChenDang
 * @date 2019/12/21 0021
 */
public interface UserDeviceTokenDao extends BaseDao<UserDeviceToken>{

    int queryToken(@Param("token")String token, @Param("fromTime") Timestamp fromTime, @Param("toTime")Timestamp toTime);
}
