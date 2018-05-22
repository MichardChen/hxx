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

import com.framework.entity.TCarSecondhandEntity;
import com.framework.service.TCarSecondhandService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-22 21:25:47
 */
@Controller
@RequestMapping("tcarsecondhand")
public class TCarSecondhandController {
	@Autowired
	private TCarSecondhandService tCarSecondhandService;
	
	@RequestMapping("/tcarsecondhand.html")
	public String list(){
		return "tcarsecondhand/tcarsecondhand.html";
	}
	
	@RequestMapping("/tcarsecondhand_add.html")
	public String add(){
		return "tcarsecondhand/tcarsecondhand_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tcarsecondhand:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<TCarSecondhandEntity> tCarSecondhandList = tCarSecondhandService.queryList(map);
		int total = tCarSecondhandService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(tCarSecondhandList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tcarsecondhand:info")
	public R info(@PathVariable("id") Integer id){
		TCarSecondhandEntity tCarSecondhand = tCarSecondhandService.queryObject(id);
		
		return R.ok().put("tCarSecondhand", tCarSecondhand);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tcarsecondhand:save")
	public R save(@RequestBody TCarSecondhandEntity tCarSecondhand){
		tCarSecondhandService.save(tCarSecondhand);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tcarsecondhand:update")
	public R update(@RequestBody TCarSecondhandEntity tCarSecondhand){
		tCarSecondhandService.update(tCarSecondhand);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tcarsecondhand:delete")
	public R delete(@RequestBody Integer[] ids){
		tCarSecondhandService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
