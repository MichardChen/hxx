package com.framework.controller;

import com.framework.entity.MallProduct;
import com.framework.entity.SysMenuEntity;
import com.framework.service.MallProductService;
import com.framework.service.SysMenuService;
import com.framework.service.SysUserService;
import com.framework.utils.Constant.MenuType;
import com.framework.utils.PageUtils;
import com.framework.utils.R;
import com.framework.utils.RRException;
import org.apache.commons.lang.StringUtils;
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
 * 商城商品
 */
@RestController
@RequestMapping("/mall")
public class MallProductController extends AbstractController {

	@Autowired
	private MallProductService mallProductService;

	/**
	 * 所有用户列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("mall:list")
	public R list(Integer page, Integer limit,String productTitle,String price) {
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("productTitle", productTitle);

		// 查询列表数据
		List<MallProduct> menuList = mallProductService.queryList(map);
		int total = mallProductService.queryTotal(map);

		PageUtils pageUtil = new PageUtils(menuList, total, limit, page);

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 商品信息
	 */
	@RequestMapping("/info/{MallProductId}")
	@RequiresPermissions("mall:info")
	public R info(@PathVariable("MallProductId") Long MallProductId) {
		MallProduct MallProduct = mallProductService.queryObject(MallProductId);
		return R.ok().put("MallProduct", MallProduct);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("mall:save")
	public R save(@RequestBody MallProduct MallProduct) {
		mallProductService.save(MallProduct);
		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("mall:update")
	public R update(@RequestBody MallProduct MallProduct) {
		mallProductService.update(MallProduct);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("mall:delete")
	public R delete(@RequestBody Long[] MallProductIds) {
		for (Long MallProductId : MallProductIds) {
			if (MallProductId.longValue() <= 28) {
				return R.error("系统菜单，不能删除");
			}
		}
		mallProductService.deleteBatch(MallProductIds);
		return R.ok();
	}

	/**
	 * 下架
	 */
	@RequestMapping("/off")
	@RequiresPermissions("mall:off:loading")
	public R offLoading(@PathVariable("productId") Integer productId) {
		if(productId == null){
			return R.error("ID不能为空");
		}
		mallProductService.offLoading(productId);
		return R.ok();
	}
}
