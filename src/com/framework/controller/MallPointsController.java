package com.framework.controller;

import com.alibaba.fastjson.JSON;
import com.framework.constants.Constants;
import com.framework.entity.MallPointsRecord;
import com.framework.entity.MallPointsRecord;
import com.framework.service.MallPointsService;
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
 * 商城积分记录
 */
@RestController
@RequestMapping("/mall/points/")
public class MallPointsController extends AbstractController {

	@Autowired
	private MallPointsService mallPointsService;

	/**
	 * 所有列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("mall:points:list")
	public R list(Integer page, Integer limit,String productTitle,String price, String status) {
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);

		// 查询列表数据
		List<MallPointsRecord> menuList = mallPointsService.queryList(map);
		int total = mallPointsService.queryTotal(map);
		PageUtils pageUtil = new PageUtils(menuList, total, limit, page);
		return R.ok().put("page", pageUtil);
	}

	/**
	 * 详情信息
	 */
	@RequestMapping("/info/{productId}")
	@RequiresPermissions("mall:points:info")
	public R info(@PathVariable("productId") Long productId) {
		MallPointsRecord record = mallPointsService.queryObject(productId);
		return R.ok().put("record", record);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("mall:points:save")
	public R save(@RequestBody MallPointsRecord record) {
		mallPointsService.save(record);
		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("mall:points:update")
	public R update(@RequestBody MallPointsRecord reord) {
		mallPointsService.update(reord);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("mall:points:delete")
	public R delete(@RequestBody Long[] recordIds) {
		if(recordIds == null || recordIds.length<1){
			return R.error("ID不能为空");
		}
		mallPointsService.deleteBatch(recordIds);
		return R.ok();
	}
}
