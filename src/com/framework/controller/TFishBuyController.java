package com.framework.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.dao.*;
import com.framework.entity.*;
import com.framework.utils.StringUtil;
import com.framework.vo.TFishBuyVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.framework.service.TFishBuyService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2020-01-05 12:40:44
 */
@Controller
@RequestMapping("tfishbuy")
public class TFishBuyController {
	@Autowired
	private TFishBuyService tFishBuyService;
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
	
	@RequestMapping("/tfishbuy.html")
	public String list(){
		return "tfishbuy/tfishbuy.html";
	}
	
	@RequestMapping("/tfishbuy_add.html")
	public String add(){
		return "tfishbuy/tfishbuy_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tfishbuy:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<TFishBuyEntity> tFishBuyList = tFishBuyService.queryList(map);
		int total = tFishBuyService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(tFishBuyList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tfishbuy:info")
	public R info(@PathVariable("id") Integer id){
		TFishBuyEntity tFishBuy = tFishBuyService.queryObject(id);
		TFishBuyVo vo = new TFishBuyVo();
		Member member = memberDao.queryObject(tFishBuy.getMemberId());
		vo.setProvice(tFishBuy.getProvice());
		vo.setCity(tFishBuy.getCity());
		if(member != null){
			vo.setMember(member.getNickName()+"-"+member.getMobile());
		}
		vo.setId(tFishBuy.getId());
		vo.setCreateTime(tFishBuy.getCreateTime());
		vo.setUpdateTime(tFishBuy.getUpdateTime());
		vo.setLatestTime(tFishBuy.getLatestTime());
		vo.setImg(tFishBuy.getImg());
		vo.setMainType(tFishBuy.getMainType());
		vo.setMark(tFishBuy.getMark());
		vo.setWeight(tFishBuy.getWeight());
		vo.setUnit(tFishBuy.getUnit());
		vo.setSize(tFishBuy.getSize());
		vo.setPrice(tFishBuy.getPrice());
		vo.setLabels(tFishBuy.getLabels());
		vo.setLatestTime(tFishBuy.getLatestTime());
		vo.setProductTypeCd(tFishBuy.getProductType());
		vo.setOrderNo(tFishBuy.getOrderNo());
		vo.setUpdateMark(tFishBuy.getUpdateMark());
		vo.setStatus(tFishBuy.getStatus());

		String imgs = tFishBuy.getImg();
		List<String> images = new ArrayList<>();
		if(StringUtil.isNoneBlank(imgs)){
			String[] imgList = imgs.split(",");
			for(String str:imgList){
				images.add(str);
			}
		}
		vo.setImgs(images);
		return R.ok().put("tFishBuy", vo);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tfishbuy:save")
	public R save(@RequestBody TFishBuyEntity tFishBuy){
		tFishBuyService.save(tFishBuy);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tfishbuy:update")
	public R update(@RequestBody TFishBuyEntity tFishBuy){
		tFishBuyService.update(tFishBuy);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tfishbuy:delete")
	public R delete(@RequestBody Integer[] ids){
		tFishBuyService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
