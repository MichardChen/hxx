package com.framework.dao;

import com.framework.entity.TFishOrderEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2020-01-14 21:01:28
 */
public interface TFishOrderDao extends BaseDao<TFishOrderEntity> {

    public TFishOrderEntity queryOrderByOrderNo(String orderNo);

    int updateOrderStatus(@Param("orderNo")String orderNo,@Param("status")String status);

    int updatePayType(@Param("orderNo")String orderNo,@Param("flg")String flg,@Param("payType")String payType,@Param("status")String status);
}
