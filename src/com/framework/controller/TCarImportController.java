package com.framework.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.framework.constants.Constants;
import com.framework.dao.SysUserDao;
import com.framework.entity.SysUserEntity;
import com.framework.entity.TBrandEntity;
import com.framework.entity.TCarImportEntity;
import com.framework.model.ImportCarListModel;
import com.framework.service.FileService;
import com.framework.service.TBrandService;
import com.framework.service.TCarImportService;
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
@RequestMapping("tcarimport")
public class TCarImportController {
	@Autowired
	private TCarImportService tCarImportService;
	@Autowired
	private SysUserDao userDao;
	@Autowired
	private TBrandService brandService;
	
	@RequestMapping("/tcarimport.html")
	public String list(){
		return "tcarimport/tcarimport.html";
	}
	
	@RequestMapping("/tcarimport_add.html")
	public String add(){
		return "tcarimport/tcarimport_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tcarimport:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<TCarImportEntity> tCarImportList = tCarImportService.queryList(map);
		int total = tCarImportService.queryTotal(map);
		List<ImportCarListModel> list = new ArrayList<>();
		ImportCarListModel model = null;
		for(TCarImportEntity entity : tCarImportList){
			model = new ImportCarListModel();
			
			model.setId(entity.getId());
			TBrandEntity brandEntity = brandService.queryObject(entity.getBrand());
			if(brandEntity != null){
				model.setBrand(brandEntity.getBrand());
				model.setCarName(entity.getCarName());
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
			model.setMarketPrice(entity.getMarketPrice());
			model.setNowPrice(entity.getNowPrice());
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
	@RequiresPermissions("tcarimport:info")
	public R info(@PathVariable("id") Integer id){
		TCarImportEntity tCarImport = tCarImportService.queryObject(id);
		
		return R.ok().put("tCarImport", tCarImport);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tcarimport:save")
	public R save(@RequestParam("tCarImport")String tCarImport,@RequestParam(value="uFile",required=false)MultipartFile uploadFile){
		
		TCarImportEntity entity = new TCarImportEntity();
		JSONObject viewModel = JSONObject.parseObject(tCarImport);
		int userid = ShiroUtils.getUserId().intValue();
		entity.setCreateBy(userid);
		entity.setCreateTime(DateUtil.getNowTimestamp());
		entity.setUpdateBy(userid);
		entity.setUpdateTime(DateUtil.getNowTimestamp());
		
		
		entity.setBrand(viewModel.getInteger("brand"));
		entity.setCarName(viewModel.getString("carName"));
		entity.setNowPrice(viewModel.getBigDecimal("nowPrice"));
		entity.setMarketPrice(viewModel.getBigDecimal("marketPrice"));
		entity.setMark(viewModel.getString("mark"));
		entity.setServicePiror(viewModel.getString("servicePiror"));
		entity.setFavour(viewModel.getString("favour"));
		entity.setLabels(viewModel.getString("labels"));
		entity.setCarLevelCd(viewModel.getString("carLevelCd"));
		entity.setTitleLabel(viewModel.getString("titleLabel"));
		//先使用年数
		entity.setCarSeriesId(viewModel.getInteger("carSeriesId"));
		entity.setCarColor(viewModel.getString("carColor"));
		entity.setCarClassCd(viewModel.getString("carClassCd"));
		entity.setMaxSave(viewModel.getBigDecimal("maxSave"));
		entity.setHot(viewModel.getInteger("hot"));
		entity.setLabels(viewModel.getString("labels"));
		entity.setFlg(1);
		
		//生成html
		FileService fs=new FileService();
		//上传图片
		String logo = fs.upload(uploadFile, Constants.FILE_HOST.IMG, Constants.HOST.IMG);
		if(StringUtil.isNoneBlank(logo)){
			entity.setIcon(logo);
		}
		tCarImportService.save(entity);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tcarimport:update")
	public R update(@RequestParam("tCarImport")String tCarImport,@RequestParam(value="uFile",required=false)MultipartFile uploadFile){
		
		TCarImportEntity entity = new TCarImportEntity();
		JSONObject viewModel = JSONObject.parseObject(tCarImport);
		int userid = ShiroUtils.getUserId().intValue();
		entity.setUpdateBy(userid);
		entity.setUpdateTime(DateUtil.getNowTimestamp());
		entity.setBrand(viewModel.getInteger("brand"));
		entity.setCarName(viewModel.getString("carName"));
		entity.setCarLevelCd(viewModel.getString("carLevelCd"));
		entity.setNowPrice(viewModel.getBigDecimal("nowPrice"));
		entity.setMarketPrice(viewModel.getBigDecimal("marketPrice"));
		entity.setMark(viewModel.getString("mark"));
		entity.setCarClassCd(viewModel.getString("carClassCd"));
		entity.setFavour(viewModel.getString("favour"));
		entity.setServicePiror(viewModel.getString("servicePiror"));
		entity.setHot(viewModel.getInteger("hot"));
		entity.setMaxSave(viewModel.getBigDecimal("maxSave"));
		//先使用年数
		entity.setCarSeriesId(viewModel.getInteger("carSeriesId"));
		entity.setCarColor(viewModel.getString("carColor"));
		entity.setLabels(viewModel.getString("labels"));
		entity.setId(viewModel.getInteger("id"));
		
		//生成html
		FileService fs=new FileService();
		//上传图片
		String logo = fs.upload(uploadFile, Constants.FILE_HOST.IMG, Constants.HOST.IMG);
		if(StringUtil.isNoneBlank(logo)){
			entity.setIcon(logo);
		}
		tCarImportService.update(entity);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tcarimport:delete")
	public R delete(@RequestBody Integer[] ids){
		tCarImportService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
