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

import com.framework.entity.TQuestionEntity;
import com.framework.service.TQuestionService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-16 22:14:11
 */
@Controller
@RequestMapping("tquestion")
public class TQuestionController {
	@Autowired
	private TQuestionService tQuestionService;
	
	@RequestMapping("/tquestion.html")
	public String list(){
		return "tquestion/tquestion.html";
	}
	
	@RequestMapping("/tquestion_add.html")
	public String add(){
		return "tquestion/tquestion_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tquestion:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<TQuestionEntity> tQuestionList = tQuestionService.queryList(map);
		int total = tQuestionService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(tQuestionList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tquestion:info")
	public R info(@PathVariable("id") Integer id){
		TQuestionEntity tQuestion = tQuestionService.queryObject(id);
		
		return R.ok().put("tQuestion", tQuestion);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tquestion:save")
	public R save(@RequestBody TQuestionEntity tQuestion){
		tQuestionService.save(tQuestion);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tquestion:update")
	public R update(@RequestBody TQuestionEntity tQuestion){
		tQuestionService.update(tQuestion);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tquestion:delete")
	public R delete(@RequestBody Integer[] ids){
		tQuestionService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
