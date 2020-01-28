package com.framework.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.dao.TBrandSeriesDao;
import com.framework.entity.TBrandSeriesEntity;
import com.framework.utils.DateUtil;
import com.framework.utils.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import com.framework.entity.TMarketPriceEntity;
import com.framework.service.TMarketPriceService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2020-01-17 17:10:04
 */
@Controller
@RequestMapping("tmarketprice")
public class TMarketPriceController {
	@Autowired
	private TMarketPriceService tMarketPriceService;
	@Autowired
	private TBrandSeriesDao tBrandSeriesDao;
	
	@RequestMapping("/tmarketprice.html")
	public String list(){
		return "tmarketprice/tmarketprice.html";
	}
	
	@RequestMapping("/tmarketprice_add.html")
	public String add(){
		return "tmarketprice/tmarketprice_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tmarketprice:list")
	public R list(Integer page, Integer limit, @RequestParam("date")String date){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("createDate",date);
		//查询列表数据
		List<TMarketPriceEntity> tMarketPriceList = tMarketPriceService.queryList(map);
		int total = tMarketPriceService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(tMarketPriceList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tmarketprice:info")
	public R info(@PathVariable("id") Integer id){
		TMarketPriceEntity tMarketPrice = tMarketPriceService.queryObject(id);
		
		return R.ok().put("tMarketPrice", tMarketPrice);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tmarketprice:save")
	public R save(@RequestBody TMarketPriceEntity tMarketPrice){
	    tMarketPrice.setCreateBy(ShiroUtils.getUserId().intValue());
	    tMarketPrice.setCreateTime(DateUtil.getNowTimestamp());
	    tMarketPrice.setUpdateBy(ShiroUtils.getUserId().intValue());
	    tMarketPrice.setUpdateTime(DateUtil.getNowTimestamp());
		TBrandSeriesEntity brandSeriesEntity = tBrandSeriesDao.queryObjectByName(tMarketPrice.getBrandSeries());
		if(brandSeriesEntity != null){
			tMarketPrice.setImg(brandSeriesEntity.getSeriesIcon());
		}
	    tMarketPrice.setFlg(1);
		tMarketPriceService.save(tMarketPrice);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tmarketprice:update")
	public R update(@RequestBody TMarketPriceEntity tMarketPrice){

        tMarketPrice.setUpdateBy(ShiroUtils.getUserId().intValue());
        tMarketPrice.setUpdateTime(DateUtil.getNowTimestamp());
		TBrandSeriesEntity brandSeriesEntity = tBrandSeriesDao.queryObjectByName(tMarketPrice.getBrandSeries());
		if(brandSeriesEntity != null){
			tMarketPrice.setImg(brandSeriesEntity.getSeriesIcon());
		}
		tMarketPriceService.update(tMarketPrice);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tmarketprice:delete")
	public R delete(@RequestBody Integer[] ids){

		tMarketPriceService.deleteBatch(ids,0,DateUtil.getNowTimestamp(),ShiroUtils.getUserId().intValue());
		
		return R.ok();
	}
	
}
