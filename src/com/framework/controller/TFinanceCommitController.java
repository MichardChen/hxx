package com.framework.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.framework.dao.LocationCityDao;
import com.framework.dao.LocationProvinceDao;
import com.framework.dao.SysUserDao;
import com.framework.dao.TCodemstDao;
import com.framework.entity.LocationCityEntity;
import com.framework.entity.LocationProvinceEntity;
import com.framework.entity.SysUserEntity;
import com.framework.entity.TBrandEntity;
import com.framework.entity.TBrandSeriesEntity;
import com.framework.entity.TCodemstEntity;
import com.framework.entity.TFinanceCommitEntity;
import com.framework.entity.TFinanceEntity;
import com.framework.model.FinanceCommitDetailModel;
import com.framework.model.FinanceCommitListModel;
import com.framework.service.TBrandSeriesService;
import com.framework.service.TBrandService;
import com.framework.service.TFinanceCommitService;
import com.framework.service.TFinanceService;
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
 * @date 2018-06-02 16:18:21
 */
@Controller
@RequestMapping("tfinancecommit")
public class TFinanceCommitController {
	@Autowired
	private TFinanceCommitService tFinanceCommitService;
	@Autowired
	private TBrandSeriesService tBrandSeriesService;
	@Autowired
	private SysUserDao userDao;
	@Autowired
	private TBrandService brandService;
	@Autowired
	private TFinanceService financeService;
	@Autowired
	private TCodemstDao codeMstDao;
	@Autowired
	private LocationCityDao cityDao;
	@Autowired
	private LocationProvinceDao provinceDao;
	
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
	public R list(Integer page, Integer limit,@RequestParam("date")String date){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("date", date);
		
		//查询列表数据
		List<TFinanceCommitEntity> tFinanceCommitList = tFinanceCommitService.queryList(map);
		int total = tFinanceCommitService.queryTotal(map);
		List<FinanceCommitListModel> models = new ArrayList<>();
		FinanceCommitListModel m = null;
		for(TFinanceCommitEntity e : tFinanceCommitList){
			m = new FinanceCommitListModel();
			m.setAge(StringUtil.toString(e.getAge()));
			m.setId(e.getId());
			m.setIdcardNo(e.getIdcardNo());
			m.setMark(e.getMark());
			m.setMobile(e.getMobile());
			m.setName(e.getName());
			m.setCreateTime(DateUtil.format(e.getCreateTime()));
			m.setUpdateTime(DateUtil.format(e.getUpdateTime()));
			m.setSex(e.getSex() == 1 ? "男":"女");
			LocationCityEntity city = cityDao.queryObject(e.getCityId());
			if(city != null){
				m.setCityId(city.getName());
			}
			LocationProvinceEntity province = provinceDao.queryObject(e.getProvinceId());
			if(province != null){
				m.setProvinceId(province.getName());
			}
			SysUserEntity update = userDao.queryObject(e.getUpdateBy());
			if(update != null){
				m.setUpdateBy(update.getUsername());
			}else{
				m.setUpdateBy(StringUtil.STRING_BLANK);
			}
			TBrandEntity brand = brandService.queryObject(e.getBrandId());
			if(brand != null){
				m.setBrandId(brand.getBrand());
			}else{
				m.setBrandId(StringUtil.STRING_BLANK);
			}
			TBrandSeriesEntity seriesEntity = tBrandSeriesService.queryObject(e.getBrandSeriesId());
			if(seriesEntity != null){
				m.setBrandSeriesId(seriesEntity.getCarSerial());
			}else{
				m.setBrandSeriesId("");
			}
			TFinanceEntity financeEntity = financeService.queryObject(e.getFinanceId());
			if(financeEntity != null){
				m.setFinanceId(financeEntity.getName());
			}else{
				m.setFinanceId("");
			}
			TCodemstEntity mst = codeMstDao.queryByCode(e.getStatus());
			if(mst != null){
				m.setStatus(mst.getName());
			}else{
				m.setStatus("");
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
	@RequiresPermissions("tfinancecommit:info")
	public R info(@PathVariable("id") Integer id){
		TFinanceCommitEntity e = tFinanceCommitService.queryObject(id);
		FinanceCommitDetailModel m = new FinanceCommitDetailModel();
		if(e != null){
			m.setAge(StringUtil.toString(e.getAge()));
			m.setId(e.getId());
			m.setIdcardNo(e.getIdcardNo());
			m.setMark(e.getMark());
			m.setMobile(e.getMobile());
			m.setName(e.getName());
			m.setCreateTime(DateUtil.format(e.getCreateTime()));
			m.setUpdateTime(DateUtil.format(e.getUpdateTime()));
			m.setSex(e.getSex() == 1 ? "男":"女");
			LocationCityEntity city = cityDao.queryObject(e.getCityId());
			if(city != null){
				m.setCityId(city.getName());
			}
			LocationProvinceEntity province = provinceDao.queryObject(e.getProvinceId());
			if(province != null){
				m.setProvinceId(province.getName());
			}
			SysUserEntity update = userDao.queryObject(e.getUpdateBy());
			if(update != null){
				m.setUpdateBy(update.getUsername());
			}else{
				m.setUpdateBy(StringUtil.STRING_BLANK);
			}
			TBrandEntity brand = brandService.queryObject(e.getBrandId());
			if(brand != null){
				m.setBrandId(brand.getBrand());
			}else{
				m.setBrandId(StringUtil.STRING_BLANK);
			}
			TBrandSeriesEntity seriesEntity = tBrandSeriesService.queryObject(e.getBrandSeriesId());
			if(seriesEntity != null){
				m.setBrandSeriesId(seriesEntity.getCarSerial());
			}else{
				m.setBrandSeriesId("");
			}
			TFinanceEntity financeEntity = financeService.queryObject(e.getFinanceId());
			if(financeEntity != null){
				m.setFinanceId(financeEntity.getName());
			}else{
				m.setFinanceId("");
			}
			m.setStatus(e.getStatus());
		}
		return R.ok().put("tFinanceCommit", m);
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
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@RequiresPermissions("tfinancecommit:update")
	public R update(@RequestParam("id")Integer id,@RequestParam("status")String status,@RequestParam("mark")String mark) throws Exception{
		
		TFinanceCommitEntity tFinanceCommit = new TFinanceCommitEntity();
		tFinanceCommit.setStatus(status);
		tFinanceCommit.setId(id);
		tFinanceCommit.setMark(mark);
		int userid = ShiroUtils.getUserId().intValue();
		tFinanceCommit.setUpdateBy(userid);
		tFinanceCommit.setUpdateTime(DateUtil.getNowTimestamp());
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
