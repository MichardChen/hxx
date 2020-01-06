package com.framework.service;

import com.framework.entity.MallPointsRecord;
import com.framework.vo.MallPointsListVo;

import java.util.List;
import java.util.Map;

public interface MallPointsService {

	MallPointsRecord queryObject(Long id);

	List<MallPointsRecord> queryList(Map<String, Object> map);

	/**
	 * 获取后台列表数据
	 * @param map
	 * @return
	 */
	List<MallPointsListVo> queryAllList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);

	/**
	 * 获取后台列表查询总数
	 * @param map
	 * @return
	 */
	int queryAllTotal(Map<String, Object> map);

	/**
	 * 新增
	 * @param record
	 */
	void save(MallPointsRecord record);

	/**
	 * 修改
	 * @param record
	 */
	void update(MallPointsRecord record);

	/**
	 *批量删除
	 * @param recordIds
	 */
	void deleteBatch(Long[] recordIds);

}
