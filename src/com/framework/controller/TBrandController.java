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
import com.framework.dao.TCodemstDao;
import com.framework.entity.SysUserEntity;
import com.framework.entity.TBrandEntity;
import com.framework.model.BrandListModel;
import com.framework.model.BrandModel;
import com.framework.service.FileService;
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
 * @date 2018-05-16 23:00:02
 */
@Controller
@RequestMapping("tbrand")
public class TBrandController {
	@Autowired
	private TBrandService tBrandService;
	@Autowired
	private TCodemstDao codeMstDao;
	@Autowired
	private SysUserDao userDao;

	@RequestMapping("/tbrand.html")
	public String list() {
		return "tbrand/tbrand.html";
	}

	@RequestMapping("/tbrand_add.html")
	public String add() {
		return "tbrand/tbrand_add.html";
	}

	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tbrand:list")
	public R list(Integer page, Integer limit,@RequestParam("queryBrand")String queryBrand) {
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("queryBrand", queryBrand);
		System.out.println("参数："+queryBrand);

		// 查询列表数据
		List<TBrandEntity> tBrandList = tBrandService.queryList(map);
		int total = tBrandService.queryTotal(map);

		List<BrandListModel> models = new ArrayList<>();
		BrandListModel model = null;
		for(TBrandEntity m : tBrandList) {
			model = new BrandListModel();
			
			SysUserEntity admin = userDao.queryObject(m.getCreateBy());
			if(admin != null){
				model.setCreateBy(admin.getUsername());
			}else{
				model.setCreateBy(StringUtil.STRING_BLANK);
			}
			
			SysUserEntity update = userDao.queryObject(m.getUpdateBy());
			if(update != null){
				model.setUpdateBy(update.getUsername());
			}else{
				model.setUpdateBy(StringUtil.STRING_BLANK);
			}
			model.setUpdateTime(StringUtil.toString(m.getUpdateTime()));
			model.setCreateTime(StringUtil.toString(m.getCreateTime()));
			if(m.getShowflg() == 1){
				model.setShowflg("是");
			}else {
				model.setShowflg("否");
			}
			if(m.getImportflg() == 1){
				model.setImportflg("是");
			}else {
				model.setImportflg("否");
			}
			if(m.getFlg() == 1){
				model.setFlg("上架中");
			}else{
				model.setFlg("已下架");
			}
			model.setBrand(m.getBrand());
			model.setId(m.getId());
			models.add(model);
		}
				
		PageUtils pageUtil = new PageUtils(models, total, limit, page);

		return R.ok().put("page", pageUtil);
	}

	@ResponseBody
	@RequestMapping("/queryAllBrand")
	@RequiresPermissions("tbrand:list")
	public R queryAllBrand() {
		// 查询列表数据
		List<TBrandEntity> tBrandList = tBrandService.queryAllList(null);
		return R.ok().put("tBrandList", tBrandList);
	}

	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tbrand:info")
	public R info(@PathVariable("id") Integer id) {
		TBrandEntity tBrand = tBrandService.queryObject(id);
		BrandModel model = new BrandModel();
		if(tBrand != null){
			model.setId(tBrand.getId());
			model.setBrand(tBrand.getBrand());
			model.setBrandIcon(tBrand.getBrandIcon());
			model.setWord(tBrand.getWord());
			model.setShowflg(tBrand.getShowflg());
			model.setImportflg(tBrand.getImportflg());
			model.setFlg(tBrand.getFlg());
		}
		return R.ok().put("tBrand", model);
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tbrand:save")
	public R save(@RequestParam("tBrand") String tb,@RequestParam(value = "uFile", required = false) MultipartFile uploadFile) throws Exception{

		TBrandEntity tBrand = new TBrandEntity();
		int userid = ShiroUtils.getUserId().intValue();
		JSONObject viewModel = JSONObject.parseObject(tb);
		TBrandEntity exitBrand = tBrandService.queryObjectByName(viewModel.getString("brand"));
		if(exitBrand != null){
			//已存在
			return R.error("此品牌已存在,不能重复添加");
		}
		tBrand.setCreateBy(userid);
		tBrand.setCreateTime(DateUtil.getNowTimestamp());
		tBrand.setUpdateBy(userid);
		tBrand.setUpdateTime(DateUtil.getNowTimestamp());
		tBrand.setBrand(viewModel.getString("brand"));
		tBrand.setWord(viewModel.getString("word"));
		tBrand.setFlg(viewModel.getInteger("flg"));
		tBrand.setShowflg(viewModel.getInteger("showflg"));
		tBrand.setImportflg(viewModel.getInteger("importflg"));

		// 生成html
		FileService fs = new FileService();
		// 上传图片
		String logo = fs.upload(uploadFile, Constants.FILE_HOST.IMG, Constants.HOST.IMG);
		if (StringUtil.isNoneBlank(logo)) {
			tBrand.setBrandIcon(logo);
		}
		
		tBrandService.save(tBrand);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tbrand:update")
	public R update(@RequestParam("tBrand") String tb,@RequestParam(value = "uFile", required = false) MultipartFile uploadFile) throws Exception{
		TBrandEntity tBrand = new TBrandEntity();
		int userid = ShiroUtils.getUserId().intValue();
		JSONObject viewModel = JSONObject.parseObject(tb);
		
		TBrandEntity exitBrand = tBrandService.queryObjectByName(viewModel.getString("brand"));
		if((exitBrand != null)&&(exitBrand.getId()!=viewModel.getInteger("id"))){
			//已存在
			return R.error("此品牌已存在,不能重复添加");
		}
		
		tBrand.setUpdateBy(userid);
		tBrand.setUpdateTime(DateUtil.getNowTimestamp());
		tBrand.setBrand(viewModel.getString("brand"));
		tBrand.setWord(viewModel.getString("word"));
		tBrand.setId(viewModel.getInteger("id"));
		tBrand.setShowflg(viewModel.getInteger("showflg"));
		tBrand.setImportflg(viewModel.getInteger("importflg"));
		tBrand.setFlg(viewModel.getInteger("flg"));
		// 生成html
		FileService fs = new FileService();
		// 上传图片
		String logo = fs.upload(uploadFile, Constants.FILE_HOST.IMG, Constants.HOST.IMG);
		if (StringUtil.isNoneBlank(logo)) {
			tBrand.setBrandIcon(logo);
		}
		
		tBrandService.update(tBrand);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tbrand:delete")
	public R delete(@RequestBody Integer[] ids) {
		tBrandService.deleteBatch(ids);

		return R.ok();
	}

}
