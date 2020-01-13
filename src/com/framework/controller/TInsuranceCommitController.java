package com.framework.controller;

import com.framework.dao.*;
import com.framework.entity.*;
import com.framework.model.InsuranceCommitDetailModel;
import com.framework.model.InsuranceCommitListModel;
import com.framework.service.TBrandSeriesService;
import com.framework.service.TBrandService;
import com.framework.service.TInsuranceCommitService;
import com.framework.service.TInsuranceService;
import com.framework.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-06-02 16:18:21
 */
@Controller
@RequestMapping("tinsurancecommit")
public class TInsuranceCommitController {
	@Autowired
	private TInsuranceCommitService tInsuranceCommitService;
	@Autowired
	private TBrandSeriesService tBrandSeriesService;
	@Autowired
	private SysUserDao userDao;
	@Autowired
	private TBrandService brandService;
	@Autowired
	private TInsuranceService tInsuranceService;
	@Autowired
	private TCodemstDao codeMstDao;
	@Autowired
	private LocationCityDao cityDao;
	@Autowired
	private LocationProvinceDao provinceDao;
	@Autowired
	private MemberDao memberDao;
	
	@RequestMapping("/tinsurancecommit.html")
	public String list(){
		return "tinsurancecommit/tinsurancecommit.html";
	}
	
	@RequestMapping("/tinsurancecommit_add.html")
	public String add(){
		return "tinsurancecommit/tinsurancecommit_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tinsurancecommit:list")
	public R list(Integer page, Integer limit,@RequestParam("date")String date){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("date", date);
		
		//查询列表数据
		List<TInsuranceCommitEntity> tFinanceCommitList = tInsuranceCommitService.queryList(map);
		int total = tInsuranceCommitService.queryTotal(map);
		List<InsuranceCommitListModel> models = new ArrayList<>();
		InsuranceCommitListModel m = null;
		for(TInsuranceCommitEntity e : tFinanceCommitList){
			m = new InsuranceCommitListModel();
			m.setAge(StringUtil.toString(e.getAge()));
			m.setId(e.getId());
			m.setIdcardNo(e.getIdcardNo());
			m.setMark(e.getMark());
			m.setMobile(e.getMobile());
			m.setName(e.getName());
			m.setCreateTime(DateUtil.format(e.getCreateTime()));
			m.setUpdateTime(DateUtil.format(e.getUpdateTime()));
			if(e.getSex() != null){
				m.setSex(e.getSex() == 1 ? "男":"女");
			}
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
			TInsuranceEntity financeEntity = tInsuranceService.queryObject(e.getFinanceId());
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
	@RequiresPermissions("tinsurancecommit:info")
	public R info(@PathVariable("id") Integer id){
		TInsuranceCommitEntity e = tInsuranceCommitService.queryObject(id);
		InsuranceCommitDetailModel m = new InsuranceCommitDetailModel();
		if(e != null){
			m.setAge(StringUtil.toString(e.getAge()));
			m.setId(e.getId());
			Member member = memberDao.queryByMobile(e.getMobile());
			if(member!=null){
				m.setIdcardNo(member.getIdCardNo());
			}
			m.setMark(e.getMark());
			m.setMobile(e.getMobile());
			m.setName(e.getName());
			m.setCreateTime(DateUtil.format(e.getCreateTime()));
			m.setUpdateTime(DateUtil.format(e.getUpdateTime()));
			if(e.getSex() != null){
				m.setSex(e.getSex() == 1 ? "男":"女");
			}

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
			TInsuranceEntity financeEntity = tInsuranceService.queryObject(e.getFinanceId());
			if(financeEntity != null){
				m.setFinanceId(financeEntity.getName());
			}else{
				m.setFinanceId("");
			}
			m.setStatus(e.getStatus());
		}
		return R.ok().put("tInsuranceCommit", m);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tinsurancecommit:save")
	public R save(@RequestBody TInsuranceCommitEntity tInsuranceCommitEntity){
		tInsuranceCommitService.save(tInsuranceCommitEntity);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@RequiresPermissions("tinsurancecommit:update")
	public R update(@RequestParam("id")Integer id,@RequestParam("status")String status,@RequestParam("mark")String mark) throws Exception{
		
		TInsuranceCommitEntity tFinanceCommit = new TInsuranceCommitEntity();
		tFinanceCommit.setStatus(status);
		tFinanceCommit.setId(id);
		tFinanceCommit.setMark(mark);
		int userid = ShiroUtils.getUserId().intValue();
		tFinanceCommit.setUpdateBy(userid);
		tFinanceCommit.setUpdateTime(DateUtil.getNowTimestamp());
		tInsuranceCommitService.update(tFinanceCommit);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tinsurancecommit:delete")
	public R delete(@RequestBody Integer[] ids){
		tInsuranceCommitService.deleteBatch(ids);
		return R.ok();
	}
}
