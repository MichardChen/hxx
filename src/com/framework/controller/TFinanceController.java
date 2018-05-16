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

import com.framework.entity.TFinanceEntity;
import com.framework.service.TFinanceService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-16 22:28:57
 */
@Controller
@RequestMapping("tfinance")
public class TFinanceController {
	@Autowired
	private TFinanceService tFinanceService;
	
	@RequestMapping("/tfinance.html")
	public String list(){
		return "tfinance/tfinance.html";
	}
	
	@RequestMapping("/tfinance_add.html")
	public String add(){
		return "tfinance/tfinance_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tfinance:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<TFinanceEntity> tFinanceList = tFinanceService.queryList(map);
		int total = tFinanceService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(tFinanceList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tfinance:info")
	public R info(@PathVariable("id") Integer id){
		TFinanceEntity tFinance = tFinanceService.queryObject(id);
		
		return R.ok().put("tFinance", tFinance);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tfinance:save")
	public R save(@RequestBody TFinanceEntity tFinance){
		tFinanceService.save(tFinance);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tfinance:update")
	public R update(@RequestBody TFinanceEntity tFinance){
		tFinanceService.update(tFinance);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tfinance:delete")
	public R delete(@RequestBody Integer[] ids){
		tFinanceService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
