package com.framework.constants;

/**
 * 报表导出常量
 * 
 * @author Chenwx
 * @date
 */
public interface ReportConstants {

	/**
	 * 订单报表导出
	 */
	public static interface ORDER_EXPORT_TYPE {
		/** 订单分类数量统计图 */
		public static final String GROUP_BY_TYPE_COUNT = "ORDER_TYPE_COUNT";
		/** 订单分类总金额统计图 */
		public static final String GROUP_BY_TYPE_AMOUNT = "ORDER_TYPE_AMOUNT";
		/** 订单各状态数量统计图 */
		public static final String GROUP_BY_STATUS_COUNT = "ORDER_STATUS_COUNT";
	}

}
