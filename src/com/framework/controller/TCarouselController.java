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

import com.framework.entity.TCarouselEntity;
import com.framework.service.TCarouselService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-16 22:46:34
 */
@Controller
@RequestMapping("tcarousel")
public class TCarouselController {
	@Autowired
	private TCarouselService tCarouselService;
	
	@RequestMapping("/tcarousel.html")
	public String list(){
		return "tcarousel/tcarousel.html";
	}
	
	@RequestMapping("/tcarousel_add.html")
	public String add(){
		return "tcarousel/tcarousel_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tcarousel:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<TCarouselEntity> tCarouselList = tCarouselService.queryList(map);
		int total = tCarouselService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(tCarouselList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tcarousel:info")
	public R info(@PathVariable("id") Integer id){
		TCarouselEntity tCarousel = tCarouselService.queryObject(id);
		
		return R.ok().put("tCarousel", tCarousel);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tcarousel:save")
	public R save(@RequestBody TCarouselEntity tCarousel){
		tCarouselService.save(tCarousel);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tcarousel:update")
	public R update(@RequestBody TCarouselEntity tCarousel){
		tCarouselService.update(tCarousel);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tcarousel:delete")
	public R delete(@RequestBody Integer[] ids){
		tCarouselService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
