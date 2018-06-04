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

import com.framework.entity.TCartParam2Entity;
import com.framework.service.TCartParam2Service;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-06-04 15:55:55
 */
@Controller
@RequestMapping("tcartparam2")
public class TCartParam2Controller {
	@Autowired
	private TCartParam2Service tCartParam2Service;
	
	@RequestMapping("/tcartparam2.html")
	public String list(){
		return "tcartparam2/tcartparam2.html";
	}
	
	@RequestMapping("/tcartparam2_add.html")
	public String add(){
		return "tcartparam2/tcartparam2_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tcartparam2:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<TCartParam2Entity> tCartParam2List = tCartParam2Service.queryList(map);
		int total = tCartParam2Service.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(tCartParam2List, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tcartparam2:info")
	public R info(@PathVariable("id") Integer id){
		TCartParam2Entity tCartParam2 = tCartParam2Service.queryObject(id);
		
		return R.ok().put("tCartParam2", tCartParam2);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tcartparam2:save")
	public R save(@RequestBody TCartParam2Entity tCartParam2){
		tCartParam2Service.save(tCartParam2);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tcartparam2:update")
	public R update(@RequestBody TCartParam2Entity tCartParam2){
		tCartParam2Service.update(tCartParam2);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tcartparam2:delete")
	public R delete(@RequestBody Integer[] ids){
		tCartParam2Service.deleteBatch(ids);
		
		return R.ok();
	}
	
}
