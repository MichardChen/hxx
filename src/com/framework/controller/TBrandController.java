package com.framework.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.entity.TBrandEntity;
import com.framework.service.TBrandService;
import com.framework.utils.DateUtil;
import com.framework.utils.PageUtils;
import com.framework.utils.R;
import com.framework.utils.ShiroUtils;


/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-16 23:00:02
 */
@Controller
@RequestMapping("tbrand")
public class TBrandController {
	@Autowired
	private TBrandService tBrandService;
	
	@RequestMapping("/tbrand.html")
	public String list(){
		return "tbrand/tbrand.html";
	}
	
	@RequestMapping("/tbrand_add.html")
	public String add(){
		return "tbrand/tbrand_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tbrand:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<TBrandEntity> tBrandList = tBrandService.queryList(map);
		int total = tBrandService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(tBrandList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	@ResponseBody
	@RequestMapping("/queryAllBrand")
	@RequiresPermissions("tbrand:list")
	public R queryAllBrand(){
		//查询列表数据
		List<TBrandEntity> tBrandList = tBrandService.queryAllList();
		return R.ok().put("tBrandList", tBrandList);
	}
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tbrand:info")
	public R info(@PathVariable("id") Integer id){
		TBrandEntity tBrand = tBrandService.queryObject(id);
		
		return R.ok().put("tBrand", tBrand);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tbrand:save")
	public R save(@RequestBody TBrandEntity tBrand){
		
		int userid = ShiroUtils.getUserId().intValue();
		tBrand.setCreateBy(userid);
		tBrand.setCreateTime(DateUtil.getNowTimestamp());
		tBrand.setUpdateBy(userid);
		tBrand.setUpdateTime(DateUtil.getNowTimestamp());
		tBrandService.save(tBrand);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tbrand:update")
	public R update(@RequestBody TBrandEntity tBrand){
		tBrandService.update(tBrand);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tbrand:delete")
	public R delete(@RequestBody Integer[] ids){
		tBrandService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
