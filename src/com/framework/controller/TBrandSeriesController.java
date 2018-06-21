package com.framework.controller;

import java.util.ArrayList;
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

import com.framework.dao.SysUserDao;
import com.framework.entity.SysUserEntity;
import com.framework.entity.TBrandEntity;
import com.framework.entity.TBrandSeriesEntity;
import com.framework.model.BrandSeriesListModel;
import com.framework.model.TBrandSeriesModel;
import com.framework.service.TBrandSeriesService;
import com.framework.service.TBrandService;
import com.framework.utils.DateUtil;
import com.framework.utils.PageUtils;
import com.framework.utils.R;
import com.framework.utils.ShiroUtils;
import com.framework.utils.StringUtil;


/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-23 13:43:22
 */
@Controller
@RequestMapping("tbrandseries")
public class TBrandSeriesController {
	@Autowired
	private TBrandSeriesService tBrandSeriesService;
	@Autowired
	private SysUserDao userDao;
	@Autowired
	private TBrandService brandService;
	
	@RequestMapping("/tbrandseries.html")
	public String list(){
		return "tbrandseries/tbrandseries.html";
	}
	
	@RequestMapping("/tbrandseries_add.html")
	public String add(){
		return "tbrandseries/tbrandseries_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tbrandseries:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<TBrandSeriesEntity> tBrandSeriesList = tBrandSeriesService.queryList(map);
		int total = tBrandSeriesService.queryTotal(map);
		List<BrandSeriesListModel> models = new ArrayList<>();
		BrandSeriesListModel m = null;
		for(TBrandSeriesEntity entity : tBrandSeriesList){
			m = new BrandSeriesListModel();
			m.setId(entity.getId());
			m.setCarSerial(entity.getCarSerial());
			m.setCreateTime(DateUtil.format(entity.getCreateTime()));
			m.setUpdateTime(DateUtil.format(entity.getUpdateTime()));
			SysUserEntity admin = userDao.queryObject(entity.getCreateBy());
			if(admin != null){
				m.setCreateBy(admin.getUsername());
			}else{
				m.setCreateBy(StringUtil.STRING_BLANK);
			}
			
			SysUserEntity update = userDao.queryObject(entity.getUpdateBy());
			if(update != null){
				m.setUpdateBy(update.getUsername());
			}else{
				m.setUpdateBy(StringUtil.STRING_BLANK);
			}
			
			TBrandEntity brand = brandService.queryObject(entity.getBrandId());
			if(brand != null){
				m.setBrandId(brand.getBrand());
			}else{
				m.setBrandId(StringUtil.STRING_BLANK);
			}
			
			models.add(m);
		}
		
		PageUtils pageUtil = new PageUtils(models, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tbrandseries:info")
	public R info(@PathVariable("id") Integer id){
		TBrandSeriesEntity tBrandSeries = tBrandSeriesService.queryObject(id);
		TBrandSeriesModel model = new TBrandSeriesModel();
		if(tBrandSeries != null){
			model.setBrandId(tBrandSeries.getBrandId());
			model.setCarSerial(tBrandSeries.getCarSerial());
			model.setId(tBrandSeries.getId());
		}
		return R.ok().put("tBrandSeries", model);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tbrandseries:save")
	public R save(@RequestBody TBrandSeriesEntity tBrandSeries){
		int userid = ShiroUtils.getUserId().intValue();
		tBrandSeries.setCreateBy(userid);
		tBrandSeries.setCreateTime(DateUtil.getNowTimestamp());
		tBrandSeries.setUpdateBy(userid);
		tBrandSeries.setUpdateTime(DateUtil.getNowTimestamp());
		tBrandSeriesService.save(tBrandSeries);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tbrandseries:update")
	public R update(@RequestBody TBrandSeriesEntity tBrandSeries){
		
		int userid = ShiroUtils.getUserId().intValue();
		tBrandSeries.setUpdateBy(userid);
		tBrandSeries.setUpdateTime(DateUtil.getNowTimestamp());
		tBrandSeriesService.update(tBrandSeries);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tbrandseries:delete")
	public R delete(@RequestBody Integer[] ids){
		tBrandSeriesService.deleteBatch(ids);
		
		return R.ok();
	}
	
	@ResponseBody
	@RequestMapping("/queryBrandSeries/{id}")
	@RequiresPermissions("tbrandseries:list")
	public R queryBrandSeries(@PathVariable("id") Integer id){
		//查询列表数据
		List<TBrandSeriesEntity> tBrandSeries = tBrandSeriesService.queryCarSeriesList(id);
		return R.ok().put("tBrandSeries", tBrandSeries);
	}
	
}
