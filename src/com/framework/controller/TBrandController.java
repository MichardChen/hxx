package com.framework.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.framework.entity.TBrandEntity;
import com.framework.service.TBrandService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


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
