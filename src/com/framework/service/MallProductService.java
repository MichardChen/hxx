package com.framework.service;

import com.framework.entity.MallProduct;

import java.util.List;
import java.util.Map;

public interface MallProductService {

	MallProduct queryObject(Long id);

	List<MallProduct> queryList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);

	/**
	 * 新增
	 * @param mallProduct
	 */
	void save(MallProduct mallProduct);

	/**
	 * 修改
	 * @param mallProduct
	 */
	void update(MallProduct mallProduct);

	/**
	 *批量删除
	 * @param mallProductIds
	 */
	void deleteBatch(Long[] mallProductIds);

	/**
	 * 下架商品
	 * @param productId
	 */
	void offLoading(int productId);

}
