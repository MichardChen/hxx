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

import com.framework.entity.TSalecartEntity;
import com.framework.service.TSalecartService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-29 22:50:01
 */
@Controller
@RequestMapping("tsalecart")
public class TSalecartController {
	@Autowired
	private TSalecartService tSalecartService;
	
	@RequestMapping("/tsalecart.html")
	public String list(){
		return "tsalecart/tsalecart.html";
	}
	
	@RequestMapping("/tsalecart_add.html")
	public String add(){
		return "tsalecart/tsalecart_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tsalecart:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<TSalecartEntity> tSalecartList = tSalecartService.queryList(map);
		int total = tSalecartService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(tSalecartList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tsalecart:info")
	public R info(@PathVariable("id") Integer id){
		TSalecartEntity tSalecart = tSalecartService.queryObject(id);
		
		return R.ok().put("tSalecart", tSalecart);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tsalecart:save")
	public R save(@RequestBody TSalecartEntity tSalecart){
		tSalecartService.save(tSalecart);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tsalecart:update")
	public R update(@RequestBody TSalecartEntity tSalecart){
		tSalecartService.update(tSalecart);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tsalecart:delete")
	public R delete(@RequestBody Integer[] ids){
		tSalecartService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
