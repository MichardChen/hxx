package com.framework.service.impl;

import com.framework.dao.MallPointsExchangeRecordDao;
import com.framework.entity.MallPointsExchangeRecord;
import com.framework.service.MallExchangeService;
import com.framework.vo.MallExchangeRecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service("mallExchangeService")
public class MallExchangeServiceImpl implements MallExchangeService {

	@Autowired
	private MallPointsExchangeRecordDao mallExchangeDao;
	/** 当前时间**/
	Timestamp cueerntTime = new Timestamp(System.currentTimeMillis());
	
	@Override
	public MallPointsExchangeRecord queryObject(Long id) {
		return mallExchangeDao.queryObject(id);
	}

	@Override
	public MallExchangeRecordVo queryVoObject(Long id) {
		return mallExchangeDao.queryVoObject(id);
	}

	@Override
	public List<MallPointsExchangeRecord> queryList(Map<String, Object> map) {
		return mallExchangeDao.queryList(map);
	}

	@Override
	public List<MallExchangeRecordVo> queryAllList(Map<String, Object> map) {
		return mallExchangeDao.queryAllList(map);
	}

	@Override
	public int queryAllTotal(Map<String, Object> map) {
		return mallExchangeDao.queryAllTotal(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return mallExchangeDao.queryTotal(map);
	}

	/**
	 * 新增
	 * @param record
	 */
	@Override
	public void save(MallPointsExchangeRecord record) {
		//设置创建时间和更新时间
		record.setCreateTime(cueerntTime);
		record.setUpdateTime(cueerntTime);
		mallExchangeDao.save(record);
	}

	@Override
	public void updateReceiveInfo(MallPointsExchangeRecord record) {
		mallExchangeDao.updateReceiveInfo(record);
	}

	/**
	 * 修改
	 * @param record
	 */
	@Override
	public void update(MallPointsExchangeRecord record) {
		record.setUpdateTime(cueerntTime);
		mallExchangeDao.update(record);
	}

	/**
	 * 批量删除
	 * @param recordIds
	 */
	@Override
	public void deleteBatch(Long[] recordIds) {
		mallExchangeDao.deleteBatch(recordIds);
	}

}
