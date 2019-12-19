package com.framework.dao;

import com.framework.entity.UserToken;
import org.apache.ibatis.annotations.Param;

/**
 * @author ChenDang
 * @date 2019/12/16 0016
 */
public interface UserTokenDao extends BaseDao<UserToken> {

    UserToken queryPlatToken(@Param("userId")Integer userId,@Param("userTypeCd")String userTypeCd,@Param("platform")String platform,@Param("token")String token);

    UserToken queryToken(@Param("userId")Integer userId,@Param("userTypeCd")String userTypeCd,@Param("platform")String platform);


}
