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

import com.framework.entity.TNewsEntity;
import com.framework.model.TNewsAddUpdateModel;
import com.framework.service.TNewsService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-03-16 17:13:03
 */
@Controller
@RequestMapping("tnews")
public class TNewsController {
	@Autowired
	private TNewsService tNewsService;
	
	@RequestMapping("/tnews.html")
	public String list(){
		return "tnews/tnews.html";
	}
	
	@RequestMapping("/tnews_add.html")
	public String add(){
		return "tnews/tnews_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tnews:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<TNewsEntity> tNewsList = tNewsService.queryList(map);
		int total = tNewsService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(tNewsList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tnews:info")
	public R info(@PathVariable("id") Integer id){
		TNewsEntity tNews = tNewsService.queryObject(id);
		TNewsAddUpdateModel model = new TNewsAddUpdateModel();
		model.setContent(tNews.getContent());
		model.setHotFlg(tNews.getHotFlg());
		model.setId(tNews.getId());
		model.setNewsLogo(tNews.getNewsLogo());
		model.setNewsTitle(tNews.getNewsTitle());
		model.setNewsTypeCd(tNews.getNewsTypeCd());
		model.setTopFlg(tNews.getTopFlg());
		return R.ok().put("tNews", model);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tnews:save")
	public R save(@RequestBody TNewsEntity tNews){
		tNewsService.save(tNews);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tnews:update")
	public R update(@RequestBody TNewsEntity tNews){
		tNewsService.update(tNews);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tnews:delete")
	public R delete(@RequestBody Integer[] ids){
		tNewsService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
