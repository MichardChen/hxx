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
import com.framework.entity.TCartParamsEntity;
import com.framework.service.TCartParam2Service;
import com.framework.service.TCartParamsService;
import com.framework.utils.DateUtil;
import com.framework.utils.PageUtils;
import com.framework.utils.R;
import com.framework.utils.ShiroUtils;


/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-06-04 15:55:58
 */
@Controller
@RequestMapping("tcartparams")
public class TCartParamsController {
	@Autowired
	private TCartParamsService tCartParamsService;
	@Autowired
	private TCartParam2Service tCartParam2Service;
	
	@RequestMapping("/tcartparams.html")
	public String list(){
		return "tcartparams/tcartparams.html";
	}
	
	@RequestMapping("/tcartparams_add.html")
	public String add(){
		return "tcartparams/tcartparams_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tcartparams:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<TCartParamsEntity> tCartParamsList = tCartParamsService.queryList(map);
		int total = tCartParamsService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(tCartParamsList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/allInfo/{id}/{typeCd}")
	//@RequiresPermissions("tcartparams:info")
	public R info(@PathVariable("id") Integer id,@PathVariable("typeCd")String typeCd){
		TCartParamsEntity tCartParams = tCartParamsService.queryObjectByCartId(id, typeCd);
		TCartParam2Entity tCartParams2 = tCartParam2Service.queryObjectByCartIdType(id, typeCd);
		return R.ok().put("tCartParams", tCartParams).put("tCartParams2", tCartParams2);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tcartparams:save")
	public R save(@RequestBody TCartParamsEntity tCartParams){
		tCartParamsService.save(tCartParams);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	//@RequiresPermissions("tcartparams:update")
	public R update(@RequestBody TCartParamsEntity tCartParams){
		tCartParams.setUpdateTime(DateUtil.getNowTimestamp());
		tCartParamsService.updateByCartId(tCartParams);
		return R.ok();
	}
	
	@ResponseBody
	@RequestMapping("/update1")
	//@RequiresPermissions("tcartparams:update")
	public R update1(@RequestBody TCartParam2Entity tCartParam2){
		tCartParam2.setUpdateTime(DateUtil.getNowTimestamp());
		tCartParam2Service.updateByCartId(tCartParam2);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tcartparams:delete")
	public R delete(@RequestBody Integer[] ids){
		tCartParamsService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
