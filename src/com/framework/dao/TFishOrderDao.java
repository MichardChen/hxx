package com.framework.dao;

import com.framework.entity.TFishOrderEntity;
import com.framework.vo.report.OrderReportVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 根据product_type分类统计有多少订单量
     * @param startDate
     * @param endDate
     * @return
     */
    List<OrderReportVo> getOrderCountByType(@Param("startDate")String startDate, @Param("endDate")String endDate);

    /**
     * 根据product_type分类统计订单总金额(预付款+尾款)
     * @param startDate
     * @param endDate
     * @return
     */
    List<OrderReportVo> getOrderAmountByType(@Param("startDate")String startDate, @Param("endDate")String endDate);

    /**
     * 查询各个订单状态下的订单数量
     * @param startDate
     * @param endDate
     * @return
     */
    List<OrderReportVo> getOrderCountByStatus(@Param("startDate")String startDate, @Param("endDate")String endDate);
}
