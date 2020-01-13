package com.framework.controller;

import com.framework.constants.Constants;
import com.framework.dao.*;
import com.framework.entity.Member;
import com.framework.entity.TFishSupplyEntity;
import com.framework.service.CommonService;
import com.framework.service.TFishSupplyService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;
import com.framework.utils.ShiroUtils;
import com.framework.utils.StringUtil;
import com.framework.vo.TFishSupplyVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2020-01-05 14:41:53
 */
@Controller
@RequestMapping("tfishsupply")
public class TFishSupplyController {
	@Autowired
	private TFishSupplyService tFishSupplyService;

	@Autowired
	private LocationProvinceDao locationProvinceDao;
	@Autowired
	private LocationCityDao locationCityDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private TCodemstDao codemstDao;
	@Autowired
	private TBrandSeriesDao tBrandSeriesDao;
	@Autowired
	private CommonService commonService;
	
	@RequestMapping("/tfishsupply.html")
	public String list(){
		return "tfishsupply/tfishsupply.html";
	}
	
	@RequestMapping("/tfishsupply_add.html")
	public String add(){
		return "tfishsupply/tfishsupply_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tfishsupply:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<TFishSupplyEntity> tFishSupplyList = tFishSupplyService.queryList(map);
		int total = tFishSupplyService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(tFishSupplyList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tfishsupply:info")
	public R info(@PathVariable("id") Integer id){
		TFishSupplyEntity tFishSupply = tFishSupplyService.queryObject(id);
		TFishSupplyVo vo = new TFishSupplyVo();
		Member member = memberDao.queryObject(tFishSupply.getMemberId());
		vo.setProvice(tFishSupply.getProvice());
		vo.setCity(tFishSupply.getCity());
		if(member != null){
			vo.setMember(member.getNickName()+"-"+member.getMobile());
		}
		vo.setId(tFishSupply.getId());
		vo.setCreateTime(tFishSupply.getCreateTime());
		vo.setUpdateTime(tFishSupply.getUpdateTime());
		vo.setLatestTime(tFishSupply.getLatestTime());
		vo.setImg(tFishSupply.getImg());
		vo.setMainType(tFishSupply.getMainType());
		vo.setMark(tFishSupply.getMark());
		vo.setWeight(tFishSupply.getWeight());
		vo.setUnit(tFishSupply.getUnit());
		vo.setSize(tFishSupply.getSize());
		vo.setPrice(tFishSupply.getPrice());
		vo.setProductTypeCd(tFishSupply.getProductType());
		vo.setOrderNo(tFishSupply.getOrderNo());
		vo.setUpdateMark(tFishSupply.getUpdateMark());
		vo.setStatus(tFishSupply.getStatus());
		String imgs = tFishSupply.getImg();
		List<String> images = new ArrayList<>();
		if(StringUtil.isNoneBlank(imgs)){
			String[] imgList = imgs.split(",");
			for(String str:imgList){
				images.add(str);
			}
		}
		vo.setImgs(images);


		return R.ok().put("tFishSupply", vo);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tfishsupply:save")
	public R save(@RequestBody TFishSupplyEntity tFishSupply){
		tFishSupplyService.save(tFishSupply);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@Transactional
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tfishsupply:update")
	public R update(@RequestBody TFishSupplyEntity tFishSupply){
		tFishSupply.setUpdateBy(ShiroUtils.getUserId().intValue());
		tFishSupplyService.update(tFishSupply);
		if(StringUtil.isNoneBlank(tFishSupply.getStatus())){
			commonService.saveOrderStatus(ShiroUtils.getUserId().intValue(),
											tFishSupply.getOrderNo()
											,Constants.ORDER_TYPE.SUPPLY
											,tFishSupply.getStatus()
											,"后台审核,审核状态为"+tFishSupply.getStatus(),"");
		}
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tfishsupply:delete")
	public R delete(@RequestBody Integer[] ids){
		tFishSupplyService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
