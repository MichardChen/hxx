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

import com.framework.entity.TFinanceCommitEntity;
import com.framework.service.TFinanceCommitService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-06-02 16:18:21
 */
@Controller
@RequestMapping("tfinancecommit")
public class TFinanceCommitController {
	@Autowired
	private TFinanceCommitService tFinanceCommitService;
	
	@RequestMapping("/tfinancecommit.html")
	public String list(){
		return "tfinancecommit/tfinancecommit.html";
	}
	
	@RequestMapping("/tfinancecommit_add.html")
	public String add(){
		return "tfinancecommit/tfinancecommit_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tfinancecommit:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<TFinanceCommitEntity> tFinanceCommitList = tFinanceCommitService.queryList(map);
		int total = tFinanceCommitService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(tFinanceCommitList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tfinancecommit:info")
	public R info(@PathVariable("id") Integer id){
		TFinanceCommitEntity tFinanceCommit = tFinanceCommitService.queryObject(id);
		
		return R.ok().put("tFinanceCommit", tFinanceCommit);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tfinancecommit:save")
	public R save(@RequestBody TFinanceCommitEntity tFinanceCommit){
		tFinanceCommitService.save(tFinanceCommit);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tfinancecommit:update")
	public R update(@RequestBody TFinanceCommitEntity tFinanceCommit){
		tFinanceCommitService.update(tFinanceCommit);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tfinancecommit:delete")
	public R delete(@RequestBody Integer[] ids){
		tFinanceCommitService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
