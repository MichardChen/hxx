package com.framework.controller;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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

import com.alibaba.fastjson.JSONObject;
import com.framework.constants.Constants;
import com.framework.entity.TCarLeaseEntity;
import com.framework.entity.TCarSecondhandEntity;
import com.framework.entity.TStoryEntity;
import com.framework.service.FileService;
import com.framework.service.TCarSecondhandService;
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
 * @date 2018-05-22 21:25:47
 */
@Controller
@RequestMapping("tcarsecondhand")
public class TCarSecondhandController {
	@Autowired
	private TCarSecondhandService tCarSecondhandService;
	
	@RequestMapping("/tcarsecondhand.html")
	public String list(){
		return "tcarsecondhand/tcarsecondhand.html";
	}
	
	@RequestMapping("/tcarsecondhand_add.html")
	public String add(){
		return "tcarsecondhand/tcarsecondhand_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tcarsecondhand:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<TCarSecondhandEntity> tCarSecondhandList = tCarSecondhandService.queryList(map);
		int total = tCarSecondhandService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(tCarSecondhandList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tcarsecondhand:info")
	public R info(@PathVariable("id") Integer id){
		TCarSecondhandEntity tCarSecondhand = tCarSecondhandService.queryObject(id);
		
		return R.ok().put("tCarSecondhand", tCarSecondhand);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tcarsecondhand:save")
	public R save(@RequestParam("tCarSecondhand")String tCarSecondhand,@RequestParam(value="uFile",required=false)MultipartFile uploadFile){
		
		TCarSecondhandEntity entity = new TCarSecondhandEntity();
		JSONObject viewModel = JSONObject.parseObject(tCarSecondhand);
		int userid = ShiroUtils.getUserId().intValue();
		entity.setCreateBy(userid);
		entity.setCreateTime(DateUtil.getNowTimestamp());
		entity.setUpdateBy(userid);
		entity.setUpdateTime(DateUtil.getNowTimestamp());
		entity.setBrand(viewModel.getInteger("brand"));
		entity.setCarName(viewModel.getString("carName"));
		entity.setProvinceId(viewModel.getInteger("provinceId"));
		entity.setCityId(viewModel.getInteger("cityId"));
		entity.setKilomiters(viewModel.getBigDecimal("kilomiters"));
		entity.setAge(viewModel.getBigDecimal("age"));
		entity.setCarLevelCd(viewModel.getString("carLevelCd"));
		//先使用年数
		entity.setYear(viewModel.getString("year"));
		entity.setFirstPayment(viewModel.getBigDecimal("firstPayment"));
		entity.setMonthPayment(viewModel.getBigDecimal("monthPayment"));
		entity.setTitleLabel(viewModel.getString("titleLabel"));
		entity.setCarSeriesId(viewModel.getInteger("carSeriesId"));
		entity.setCarCost(viewModel.getBigDecimal("carCost"));
		entity.setCarTaxCost(viewModel.getBigDecimal("carTaxCost"));
		entity.setCarColor(viewModel.getString("carColor"));
		entity.setFinalPayment(viewModel.getBigDecimal("finalPayment"));
		entity.setLabels(viewModel.getString("labels"));
		entity.setPeriods(viewModel.getInteger("periods"));
		entity.setFinalPayment(viewModel.getBigDecimal("finalPayment"));
		
		//生成html
		FileService fs=new FileService();
		//上传图片
		String logo = fs.upload(uploadFile, Constants.FILE_HOST.IMG, Constants.HOST.IMG);
		if(StringUtil.isNoneBlank(logo)){
			entity.setIcon(logo);
		}
		tCarSecondhandService.save(entity);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tcarsecondhand:update")
	public R update(@RequestBody TCarSecondhandEntity tCarSecondhand){
		tCarSecondhandService.update(tCarSecondhand);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tcarsecondhand:delete")
	public R delete(@RequestBody Integer[] ids){
		tCarSecondhandService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
