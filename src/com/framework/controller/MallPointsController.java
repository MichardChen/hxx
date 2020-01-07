package com.framework.controller;

import com.framework.entity.MallPointsRecord;
import com.framework.service.MallPointsService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;
import com.framework.vo.MallPointsListVo;
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
	public R list(Integer page, Integer limit,String userName) {
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("userName", userName);

		// 查询列表数据
		List<MallPointsListVo> menuList = mallPointsService.queryAllList(map);
		int total = mallPointsService.queryAllTotal(map);
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
