package com.framework.service;

import com.framework.entity.MallPointsExchangeRecord;
import com.framework.vo.MallExchangeRecordVo;

import java.util.List;
import java.util.Map;

public interface MallExchangeService {

	MallPointsExchangeRecord queryObject(Long id);

	MallExchangeRecordVo queryVoObject(Long id);

	List<MallPointsExchangeRecord> queryList(Map<String, Object> map);

	/**
	 * 获取列表数据 - 后台
	 * @param map
	 * @return
	 */
	List<MallExchangeRecordVo> queryAllList(Map<String, Object> map);

	int queryAllTotal(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);

	/**
	 * 新增
	 * @param record
	 */
	void save(MallPointsExchangeRecord record);

	/**
	 * 更新物流及收货人信息
	 * @param record
	 */
	void updateReceiveInfo(MallPointsExchangeRecord record);

	/**
	 * 修改
	 * @param record
	 */
	void update(MallPointsExchangeRecord record);

	/**
	 *批量删除
	 * @param recordIds
	 */
	void deleteBatch(Long[] recordIds);

}
