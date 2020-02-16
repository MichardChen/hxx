package com.framework.service;

import com.framework.entity.TFishOrderEntity;
import com.framework.vo.report.OrderReportVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author R & D
 * @email adang369@126.com
 * @date 2020-01-23 09:07:29
 */
public interface TFishOrderService {
	
	TFishOrderEntity queryObject(Integer id);
	
	List<TFishOrderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TFishOrderEntity tFishOrder);
	
	void update(TFishOrderEntity tFishOrder);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	/**
	 * 根据product_type分类统计有多少订单量
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<OrderReportVo> getOrderCountByType(String startDate, String endDate);

	/**
	 * 根据product_type分类统计订单总金额(预付款+尾款)
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<OrderReportVo> getOrderAmountByType(String startDate, String endDate);

	/**
	 * 查询各个订单状态下的订单数量
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<OrderReportVo> getOrderCountByStatus(String startDate, String endDate);
}
