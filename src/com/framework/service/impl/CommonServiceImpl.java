package com.framework.service.impl;

import com.framework.dao.TFishOrderstatusDao;
import com.framework.entity.TFishOrderstatusEntity;
import com.framework.service.CommonService;
import com.framework.utils.DateUtil;
import com.framework.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ChenDang
 * @date 2020/1/11 0011
 */
@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    TFishOrderstatusDao orderstatusDao;

    @Override
    public int saveOrderStatus(int adminId
                              ,String orderNo
                              ,String typeCd
                              ,String status
                              ,String mark
                              ,String params) {

        TFishOrderstatusEntity orderStatus = new TFishOrderstatusEntity();
        orderStatus.setCreateTime(DateUtil.getNowTimestamp());

        orderStatus.setUpdateBy(adminId);
        orderStatus.setUpdateTime(DateUtil.getNowTimestamp());
        orderStatus.setMark(mark);
        orderStatus.setParams(params);
        orderStatus.setStatus(status);
        orderStatus.setOrderTypeCd(typeCd);
        orderStatus.setOrderNo(orderNo);
        return orderstatusDao.save(orderStatus);
    }
}
