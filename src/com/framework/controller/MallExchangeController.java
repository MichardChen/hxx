package com.framework.controller;

import com.framework.entity.MallPointsExchangeRecord;
import com.framework.service.MallExchangeService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;
import com.framework.vo.MallExchangeRecordVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public R list(Integer page, Integer limit,String productTitle,String buyTypeCd, String userName, String status) {
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("buyTypeCd", buyTypeCd);
		map.put("productTitle", productTitle);
		map.put("userName", userName);
		map.put("status", status);

		// 查询列表数据
		List<MallExchangeRecordVo> menuList = mallExchangeService.queryAllList(map);
		int total = mallExchangeService.queryAllTotal(map);
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
