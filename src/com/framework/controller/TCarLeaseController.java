package com.framework.controller;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Controller;

import com.alibaba.druid.mock.MockArray;
import com.alibaba.fastjson.JSONObject;
import com.framework.constants.Constants;
import com.framework.dao.SysUserDao;
import com.framework.entity.SysUserEntity;
import com.framework.entity.TBrandEntity;
import com.framework.entity.TBrandSeriesEntity;
import com.framework.entity.TCarLeaseEntity;
import com.framework.entity.TStoryEntity;
import com.framework.model.LeaseCarViewModel;
import com.framework.model.TCarLeaseListModel;
import com.framework.service.FileService;
import com.framework.service.TBrandSeriesService;
import com.framework.service.TBrandService;
import com.framework.service.TCarLeaseService;
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
 * @date 2018-05-22 21:25:48
 */
@Controller
@RequestMapping("tcarlease")
public class TCarLeaseController {
	@Autowired
	private TCarLeaseService tCarLeaseService;
	@Autowired
	private TBrandService brandService;
	@Autowired
	private TBrandSeriesService seriesService;
	@Autowired
	private SysUserDao userDao;
	
	@RequestMapping("/tcarlease.html")
	public String list(){
		return "tcarlease/tcarlease.html";
	}
	
	@RequestMapping("/tcarlease_add.html")
	public String add(){
		return "tcarlease/tcarlease_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tcarlease:list")
	public R list(Integer page, Integer limit,@RequestParam("queryCarName")String queryCarName,@RequestParam("queryBrand")Integer queryBrand){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("queryCarName", queryCarName);
		map.put("queryBrand", queryBrand);
		//查询列表数据
		List<TCarLeaseEntity> tCarLeaseList = tCarLeaseService.queryList(map);
		int total = tCarLeaseService.queryTotal(map);
		
		List<TCarLeaseListModel> list = new ArrayList<>();
		TCarLeaseListModel model = null;
		for(TCarLeaseEntity entity : tCarLeaseList){
			model = new TCarLeaseListModel();
			model.setId(entity.getId());
			TBrandEntity brandEntity = brandService.queryObject(entity.getBrand());
			if(brandEntity != null){
				model.setBrand(brandEntity.getBrand());
				model.setCarName(entity.getCarName());
			}
			TBrandSeriesEntity seriesEntity = seriesService.queryObject(entity.getCarSeriesId());
			if(seriesEntity != null){
				model.setCarSeriesId(seriesEntity.getCarSerial());
			}
			model.setUpdateTime(StringUtil.toString(entity.getUpdateTime()));
			model.setCreateTime(StringUtil.toString(entity.getCreateTime()));
			SysUserEntity admin = userDao.queryObject(entity.getCreateBy());
			if(admin != null){
				model.setCreateBy(admin.getUsername());
			}else{
				model.setCreateBy(StringUtil.STRING_BLANK);
			}
			
			SysUserEntity update = userDao.queryObject(entity.getUpdateBy());
			if(update != null){
				model.setUpdateBy(update.getUsername());
			}else{
				model.setUpdateBy(StringUtil.STRING_BLANK);
			}
			model.setFlg(entity.getFlg() == 1 ? "在售":"下架");
			model.setMonthPayment(entity.getMonthPayment());
			model.setFirstPayment(entity.getFinalPayment());
			model.setDescUrl(entity.getDescUrl());
			list.add(model);
		}
		
		PageUtils pageUtil = new PageUtils(list, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tcarlease:info")
	public R info(@PathVariable("id") Integer id){
		TCarLeaseEntity tCarLease = tCarLeaseService.queryObject(id);
/*		LeaseCarViewModel model = new LeaseCarViewModel();
		if(tCarLease != null){
			model.setBrand(tCarLease.getBrand());
			model.setCarColor(tCarLease.getCarColor());
			model.setCarCost(tCarLease.getCarCost());
			model.setCarLevelCd(tCarLease.getCarLevelCd());
			model.setCarName(tCarLease.getCarName());
			model.setCarSeriesId(tCarLease.getCarSeriesId());
			model.setCartParam2Id(tCarLease.getCartParam2Id());
			model.setCartParamsId(tCarLease.getCartParamsId());
			model.setCarTypeInfo(tCarLease.getCarTypeInfo());
			model.setContent(tCarLease.getContent());
			model.setFinalPayment(tCarLease.getFinalPayment());
			model.setFirmCost(tCarLease.getFirmCost());
			model.setFirstPayment(tCarLease.getFirstPayment());
			model.setFirstPayment1(tCarLease.getFirstPayment1());
			model.setIcon(tCarLease.getIcon());
			model.setId(tCarLease.getId());
			model.setLabels(tCarLease.getLabels());
			model.setTitleLabel(tCarLease.getTitleLabel());
			model.setMark(tCarLease.getMark());
			model.setMark1(tCarLease.getMark1());
			model.setMonthPayment(tCarLease.getMonthPayment());
			model.setMonthPayment1(tCarLease.getMonthPayment1());
			model.setPeriods(tCarLease.getPeriods());
			model.setPeriods1(tCarLease.getPeriods1());
			model.setRealFirstPayment(tCarLease.getRealFirstPayment());
			model.setSalecount(tCarLease.getSalecount());
			model.setServiceFee(tCarLease.getServiceFee());
			model.setTfirstYearFirstPay(tCarLease.getTfirstYearFirstPay());
			model.setTfirstYearMonthPayment(tCarLease.getTfirstYearMonthPayment());
			model.setTmonthPayment(tCarLease.getTmonthPayment());
			model.setTperiods(tCarLease.getTperiods());
			model.setYear(tCarLease.getYear());
		}*/
		return R.ok().put("tCarLease", tCarLease);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tcarlease:save")
	public R save(@RequestParam("tCarLease")String tCarLease,@RequestParam(value="uFile",required=false)MultipartFile uploadFile,@RequestParam(value="uFile1",required=false)MultipartFile uploadFile1){
		
		TCarLeaseEntity entity = new TCarLeaseEntity();
		JSONObject viewModel = JSONObject.parseObject(tCarLease);
		int userid = ShiroUtils.getUserId().intValue();
		entity.setCreateBy(userid);
		entity.setCreateTime(DateUtil.getNowTimestamp());
		entity.setUpdateBy(userid);
		entity.setUpdateTime(DateUtil.getNowTimestamp());
		entity.setBrand(viewModel.getInteger("brand"));
		entity.setCarName(viewModel.getString("carName"));
		//先使用年数
		entity.setYear(viewModel.getString("year"));
		entity.setCarTypeInfo(viewModel.getString("carTypeInfo"));
		entity.setFirstPayment(viewModel.getBigDecimal("firstPayment"));
		entity.setMonthPayment(viewModel.getBigDecimal("monthPayment"));
		entity.setTitleLabel(viewModel.getString("titleLabel"));
		entity.setCarSeriesId(viewModel.getInteger("carSeriesId"));
		entity.setCarCost(viewModel.getBigDecimal("carCost"));
		entity.setCarColor(viewModel.getString("carColor"));
		entity.setFirmCost(viewModel.getBigDecimal("firmCost"));
		entity.setFinalPayment(viewModel.getBigDecimal("finalPayment"));
		entity.setLabels(viewModel.getString("labels"));
		entity.setRealFirstPayment(viewModel.getBigDecimal("realFirstPayment"));
		entity.setServiceFee(viewModel.getBigDecimal("serviceFee"));
		entity.setMark(viewModel.getString("mark"));
		entity.setFirstPayment1(viewModel.getBigDecimal("firstPayment1"));
		entity.setMonthPayment1(viewModel.getBigDecimal("monthPayment1"));
		entity.setPeriods1(36);
		entity.setMark1(viewModel.getString("mark1"));
		entity.setTfirstYearFirstPay(viewModel.getBigDecimal("tfirstYearFirstPay"));
		entity.setTfirstYearMonthPayment(viewModel.getBigDecimal("tfirstYearMonthPayment"));
		entity.setTperiods(viewModel.getInteger("tperiods"));
		entity.setTmonthPayment(viewModel.getBigDecimal("tmonthPayment"));
		entity.setFlg(1);
		
		//生成html
		FileService fs=new FileService();
		//上传图片
		String logo = fs.upload(uploadFile, Constants.FILE_HOST.IMG, Constants.HOST.IMG);
		if(StringUtil.isNoneBlank(logo)){
			entity.setIcon(logo);
		}
		
		//pc封面
		String logo1 = fs.upload(uploadFile1, Constants.FILE_HOST.IMG, Constants.HOST.IMG);
		if(StringUtil.isNoneBlank(logo1)){
			entity.setPcIcon(logo1);
		}
		
		String htmlContent = StringUtil.formatHTML("", viewModel.getString("content"));
		entity.setContent(htmlContent);
		//生成html
		String uuid = UUID.randomUUID().toString();
		//生成html文件
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(Constants.FILE_HOST.DOCUMENT+uuid+".html"),"utf-8"),true);
			pw.println(htmlContent);
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String contentUrl = Constants.HOST.DOCUMENT+uuid+".html";
		entity.setDescUrl(contentUrl);
		
		tCarLeaseService.save(entity);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tcarlease:update")
	public R update(@RequestParam("tCarLease")String tCarLease,@RequestParam(value="uFile",required=false)MultipartFile uploadFile,@RequestParam(value="uFile1",required=false)MultipartFile uploadFile1){
		TCarLeaseEntity entity = new TCarLeaseEntity();
		JSONObject viewModel = JSONObject.parseObject(tCarLease);
		int userid = ShiroUtils.getUserId().intValue();
		entity.setUpdateBy(userid);
		entity.setUpdateTime(DateUtil.getNowTimestamp());
		entity.setBrand(viewModel.getInteger("brand"));
		entity.setCarName(viewModel.getString("carName"));
		entity.setId(viewModel.getInteger("id"));
		//先使用年数
		entity.setYear(viewModel.getString("year"));
		entity.setCarTypeInfo(viewModel.getString("carTypeInfo"));
		entity.setFirstPayment(viewModel.getBigDecimal("firstPayment"));
		entity.setMonthPayment(viewModel.getBigDecimal("monthPayment"));
		entity.setTitleLabel(viewModel.getString("titleLabel"));
		entity.setCarSeriesId(viewModel.getInteger("carSeriesId"));
		entity.setCarCost(viewModel.getBigDecimal("carCost"));
		entity.setCarColor(viewModel.getString("carColor"));
		entity.setFirmCost(viewModel.getBigDecimal("firmCost"));
		entity.setFinalPayment(viewModel.getBigDecimal("finalPayment"));
		entity.setLabels(viewModel.getString("labels"));
		entity.setRealFirstPayment(viewModel.getBigDecimal("realFirstPayment"));
		entity.setServiceFee(viewModel.getBigDecimal("serviceFee"));
		entity.setMark(viewModel.getString("mark"));
		entity.setFirstPayment1(viewModel.getBigDecimal("firstPayment1"));
		entity.setMonthPayment1(viewModel.getBigDecimal("monthPayment1"));
		entity.setPeriods1(36);
		entity.setMark1(viewModel.getString("mark1"));
		entity.setTfirstYearFirstPay(viewModel.getBigDecimal("tfirstYearFirstPay"));
		entity.setTfirstYearMonthPayment(viewModel.getBigDecimal("tfirstYearMonthPayment"));
		entity.setTperiods(viewModel.getInteger("tperiods"));
		entity.setTmonthPayment(viewModel.getBigDecimal("tmonthPayment"));
		
		//生成html
		FileService fs=new FileService();
		//上传图片
		String logo = fs.upload(uploadFile, Constants.FILE_HOST.IMG, Constants.HOST.IMG);
		if(StringUtil.isNoneBlank(logo)){
			entity.setIcon(logo);
		}
		
		//pc封面
		String logo1 = fs.upload(uploadFile1, Constants.FILE_HOST.IMG, Constants.HOST.IMG);
		if(StringUtil.isNoneBlank(logo1)){
			entity.setPcIcon(logo1);
		}
		
		String htmlContent = StringUtil.formatHTML("", viewModel.getString("content"));
		entity.setContent(htmlContent);
		//生成html
		String uuid = UUID.randomUUID().toString();
		//生成html文件
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(Constants.FILE_HOST.DOCUMENT+uuid+".html"),"utf-8"),true);
			pw.println(htmlContent);
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String contentUrl = Constants.HOST.DOCUMENT+uuid+".html";
		entity.setDescUrl(contentUrl);
		
		tCarLeaseService.update(entity);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tcarlease:delete")
	public R delete(@RequestBody Integer[] ids){
		tCarLeaseService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
