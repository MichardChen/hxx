package com.framework.service.impl;

import com.framework.constants.Constants;
import com.framework.dao.MallProductDao;
import com.framework.entity.MallProduct;
import com.framework.entity.Member;
import com.framework.service.MallProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("mallProductService")
public class MallProductServiceImpl implements MallProductService {

	@Autowired
	private MallProductDao mallProductDao;
	/** 当前时间**/
	Timestamp cueerntTime = new Timestamp(System.currentTimeMillis());
	
	@Override
	public MallProduct queryObject(Long id) {
		return mallProductDao.queryObject(id);
	}

	@Override
	public List<MallProduct> queryList(Map<String, Object> map) {
		return mallProductDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return mallProductDao.queryTotal(map);
	}

	/**
	 * 新增
	 * @param mallProduct
	 */
	@Override
	public void save(MallProduct mallProduct) {
		//设置创建时间和更新时间
		mallProduct.setCreateTime(cueerntTime);
		mallProduct.setUpdateTime(cueerntTime);
		mallProductDao.save(mallProduct);
	}

	/**
	 * 修改
	 * @param mallProduct
	 */
	@Override
	public void update(MallProduct mallProduct) {
		mallProduct.setUpdateTime(cueerntTime);
		mallProductDao.update(mallProduct);
	}

	/**
	 * 批量删除
	 * @param mallProductIds
	 */
	@Override
	public void deleteBatch(Long[] mallProductIds) {
		mallProductDao.deleteBatch(mallProductIds);
	}

	/**
	 * 商品下架
	 * @param productId
	 */
	@Override
	public void offLoading(int productId) {
		mallProductDao.updateStatus(productId, Constants.MALL_STATUS.OFF_LOADING);
	}

}
