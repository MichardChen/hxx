package com.framework.service.impl;

import com.framework.dao.UserTokenDao;
import com.framework.entity.UserToken;
import com.framework.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ChenDang
 * @date 2019/12/16 0016
 */
@Service("userTokenService")
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    UserTokenDao userTokenDao;

    @Override
    public UserToken queryPlatToken(Integer userId, String userTypeCd,String platform,String token) {
        return userTokenDao.queryPlatToken(userId,userTypeCd,platform,token);
    }

    @Override
    public UserToken queryToken(Integer userId, String userTypeCd, String platform) {
        return userTokenDao.queryToken(userId,userTypeCd,platform);
    }

    @Override
    public int save(UserToken entity) {
        return userTokenDao.save(entity);
    }

    @Override
    public int update(UserToken entity) {
        return userTokenDao.update(entity);
    }
}
