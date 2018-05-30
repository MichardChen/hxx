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

import com.framework.entity.TVertifyCodeEntity;
import com.framework.service.TVertifyCodeService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-30 10:21:33
 */
@Controller
@RequestMapping("tvertifycode")
public class TVertifyCodeController {
	@Autowired
	private TVertifyCodeService tVertifyCodeService;
	
	@RequestMapping("/tvertifycode.html")
	public String list(){
		return "tvertifycode/tvertifycode.html";
	}
	
	@RequestMapping("/tvertifycode_add.html")
	public String add(){
		return "tvertifycode/tvertifycode_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tvertifycode:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<TVertifyCodeEntity> tVertifyCodeList = tVertifyCodeService.queryList(map);
		int total = tVertifyCodeService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(tVertifyCodeList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tvertifycode:info")
	public R info(@PathVariable("id") Integer id){
		TVertifyCodeEntity tVertifyCode = tVertifyCodeService.queryObject(id);
		
		return R.ok().put("tVertifyCode", tVertifyCode);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tvertifycode:save")
	public R save(@RequestBody TVertifyCodeEntity tVertifyCode){
		tVertifyCodeService.save(tVertifyCode);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tvertifycode:update")
	public R update(@RequestBody TVertifyCodeEntity tVertifyCode){
		tVertifyCodeService.update(tVertifyCode);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tvertifycode:delete")
	public R delete(@RequestBody Integer[] ids){
		tVertifyCodeService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
