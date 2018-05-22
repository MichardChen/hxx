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

import com.framework.entity.TCarImportEntity;
import com.framework.service.TCarImportService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-22 21:25:48
 */
@Controller
@RequestMapping("tcarimport")
public class TCarImportController {
	@Autowired
	private TCarImportService tCarImportService;
	
	@RequestMapping("/tcarimport.html")
	public String list(){
		return "tcarimport/tcarimport.html";
	}
	
	@RequestMapping("/tcarimport_add.html")
	public String add(){
		return "tcarimport/tcarimport_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tcarimport:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<TCarImportEntity> tCarImportList = tCarImportService.queryList(map);
		int total = tCarImportService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(tCarImportList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tcarimport:info")
	public R info(@PathVariable("id") Integer id){
		TCarImportEntity tCarImport = tCarImportService.queryObject(id);
		
		return R.ok().put("tCarImport", tCarImport);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tcarimport:save")
	public R save(@RequestBody TCarImportEntity tCarImport){
		tCarImportService.save(tCarImport);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tcarimport:update")
	public R update(@RequestBody TCarImportEntity tCarImport){
		tCarImportService.update(tCarImport);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tcarimport:delete")
	public R delete(@RequestBody Integer[] ids){
		tCarImportService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
