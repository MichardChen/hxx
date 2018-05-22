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

import com.framework.entity.TCarLeaseEntity;
import com.framework.service.TCarLeaseService;
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
@RequestMapping("tcarlease")
public class TCarLeaseController {
	@Autowired
	private TCarLeaseService tCarLeaseService;
	
	@RequestMapping("/tcarlease.html")
	public String list(){
		return "tcarlease/tcarlease.html";
	}
	
	@RequestMapping("/tcarlease_add.html")
	public String add(){
		return "tcarlease/tcarlease_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tcarlease:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<TCarLeaseEntity> tCarLeaseList = tCarLeaseService.queryList(map);
		int total = tCarLeaseService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(tCarLeaseList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tcarlease:info")
	public R info(@PathVariable("id") Integer id){
		TCarLeaseEntity tCarLease = tCarLeaseService.queryObject(id);
		
		return R.ok().put("tCarLease", tCarLease);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tcarlease:save")
	public R save(@RequestBody TCarLeaseEntity tCarLease){
		tCarLeaseService.save(tCarLease);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tcarlease:update")
	public R update(@RequestBody TCarLeaseEntity tCarLease){
		tCarLeaseService.update(tCarLease);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tcarlease:delete")
	public R delete(@RequestBody Integer[] ids){
		tCarLeaseService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
