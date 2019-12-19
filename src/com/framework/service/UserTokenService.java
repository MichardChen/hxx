package com.framework.service;

import com.framework.entity.UserToken;

/**
 * @author ChenDang
 * @date 2019/12/16 0016
 */
public interface UserTokenService {

    UserToken queryPlatToken(Integer userId,String userTypeCd,String platform,String token);

    UserToken queryToken(Integer userId,String userTypeCd,String platform);

    int save(UserToken entity);

    int update(UserToken entity);

}
