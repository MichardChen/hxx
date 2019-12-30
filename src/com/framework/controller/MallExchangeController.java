package com.framework.controller;

import com.alibaba.fastjson.JSON;
import com.framework.constants.Constants;
import com.framework.entity.MallPointsExchangeRecord;
import com.framework.entity.MallProduct;
import com.framework.service.MallExchangeService;
import com.framework.service.MallProductService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;
import com.framework.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 商城兑换/购买记录
 */
@RestController
@RequestMapping("/mall/exchange/")
public class MallExchangeController extends AbstractController {

	@Autowired
	private MallExchangeService mallExchangeService;

	/**
	 * 所有用户列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("mall:exchange:list")
	public R list(Integer page, Integer limit,String productTitle,String price, String status) {
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);

		// 查询列表数据
		List<MallPointsExchangeRecord> menuList = mallExchangeService.queryList(map);
		int total = mallExchangeService.queryTotal(map);
		PageUtils pageUtil = new PageUtils(menuList, total, limit, page);
		return R.ok().put("page", pageUtil);
	}

	/**
	 * 详情信息
	 */
	@RequestMapping("/info/{productId}")
	@RequiresPermissions("mall:exchange:info")
	public R info(@PathVariable("productId") Long productId) {
		MallPointsExchangeRecord record = mallExchangeService.queryObject(productId);
		return R.ok().put("record", record);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("mall:exchange:save")
	public R save(@RequestBody MallPointsExchangeRecord record) {
		mallExchangeService.save(record);
		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("mall:exchange:update")
	public R update(@RequestBody MallPointsExchangeRecord record) {
		mallExchangeService.update(record);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("mall:exchange:delete")
	public R delete(@RequestBody Long[] recordIds) {
		if(recordIds == null || recordIds.length<1){
			return R.error("ID不能为空");
		}
		mallExchangeService.deleteBatch(recordIds);
		return R.ok();
	}
}
