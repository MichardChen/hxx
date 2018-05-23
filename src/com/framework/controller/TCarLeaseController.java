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
import com.framework.entity.TStoryEntity;
import com.framework.service.FileService;
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
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<TCarLeaseEntity> tCarLeaseList = tCarLeaseService.queryList(map);
		int total = tCarLeaseService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(tCarLeaseList, total, limit, page);
		
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
		
		return R.ok().put("tCarLease", tCarLease);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tcarlease:save")
	public R save(@RequestParam("tCarLease")String tCarLease,@RequestParam("uFile")MultipartFile uploadFile){
		
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
		
		//生成html
		FileService fs=new FileService();
		//上传图片
		String logo = fs.upload(uploadFile, Constants.FILE_HOST.IMG, Constants.HOST.IMG);
		if(StringUtil.isNoneBlank(logo)){
			entity.setIcon(logo);
		}
		tCarLeaseService.save(entity);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tcarlease:update")
	public R update(@RequestBody TCarLeaseEntity tCarLease){
		tCarLeaseService.update(tCarLease);
		
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
