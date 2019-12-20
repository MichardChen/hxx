package com.framework.service;

import com.framework.dto.FishDTO;
import com.framework.utils.ReturnData;

/**
 * @author ChenDang
 * @date 2019/12/17 0017
 */
public interface RestfulService {

    public ReturnData index(FishDTO dto);

    public ReturnData getNewsList(FishDTO dto);

    public ReturnData getPersonData(FishDTO dto);

    public ReturnData getCheckCode(FishDTO dto);

    public ReturnData register(FishDTO dto);

    public ReturnData login(FishDTO dto);

    public ReturnData logout(FishDTO dto);

    public ReturnData modifyPwd(FishDTO dto);

    public ReturnData saveForgetPwd(FishDTO dto);
}
