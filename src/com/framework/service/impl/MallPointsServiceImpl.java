package com.framework.service.impl;

import com.framework.dao.MallPointsRecordDao;
import com.framework.entity.MallPointsRecord;
import com.framework.service.MallPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service("mallPointsService")
public class MallPointsServiceImpl implements MallPointsService {

	@Autowired
	private MallPointsRecordDao mallPointsDao;

	/** 当前时间**/
	Timestamp cueerntTime = new Timestamp(System.currentTimeMillis());
	
	@Override
	public MallPointsRecord queryObject(Long id) {
		return mallPointsDao.queryObject(id);
	}

	@Override
	public List<MallPointsRecord> queryList(Map<String, Object> map) {
		return mallPointsDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return mallPointsDao.queryTotal(map);
	}

	/**
	 * 新增
	 * @param record
	 */
	@Override
	public void save(MallPointsRecord record) {
		//设置创建时间和更新时间
		record.setCreateTime(cueerntTime);
		record.setUpdateTime(cueerntTime);
		mallPointsDao.save(record);
	}

	/**
	 * 修改
	 * @param record
	 */
	@Override
	public void update(MallPointsRecord record) {
		record.setUpdateTime(cueerntTime);
		mallPointsDao.update(record);
	}

	/**
	 * 批量删除
	 * @param recordIds
	 */
	@Override
	public void deleteBatch(Long[] recordIds) {
		mallPointsDao.deleteBatch(recordIds);
	}
}
