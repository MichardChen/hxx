package com.framework.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.dao.MemberDao;
import com.framework.dao.TCodemstDao;
import com.framework.entity.Member;
import com.framework.entity.TCodemstEntity;
import com.framework.utils.*;
import com.framework.vo.AdminOrderListVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import com.framework.entity.TFishOrderEntity;
import com.framework.service.TFishOrderService;


/**
 * 
 * 
 * @author R & D
 * @email adang369@126.com
 * @date 2020-01-23 09:07:29
 */
@Controller
@RequestMapping("tfishorder")
public class TFishOrderController {
	@Autowired
	private TFishOrderService tFishOrderService;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private TCodemstDao codemstDao;

	@RequestMapping("/tfishorder.html")
	public String list(){
		return "tfishorder/tfishorder.html";
	}
	
	@RequestMapping("/tfishorder_add.html")
	public String add(){
		return "tfishorder/tfishorder_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tfishorder:list")
	public R list(Integer page, Integer limit, @RequestParam("orderNo")String orderNo, @RequestParam("date")String date){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("orderNo",orderNo);
		map.put("createDate",date);
		
		//查询列表数据
		List<TFishOrderEntity> tFishOrderList = tFishOrderService.queryList(map);
		List<AdminOrderListVo> listVos = new ArrayList<>();
		for(TFishOrderEntity entity : tFishOrderList){
			AdminOrderListVo vo = new AdminOrderListVo();
			vo.setCreateTime(DateUtil.format(entity.getCreateTime()));
			vo.setOrderNo(entity.getOrderNo());
			vo.setFirstPay(StringUtil.toString(entity.getFirstPay()));
			vo.setSecondPay(StringUtil.toString(entity.getSecondPay()));
			Member fromUser = memberDao.queryObject(entity.getFromUserId());
			vo.setFromUserId(fromUser == null ? "" : fromUser.getNickName() + fromUser.getMobile());
			Member toUser = memberDao.queryObject(entity.getToUserId());
			vo.setToUserId(toUser == null ? "" : toUser.getNickName() + toUser.getMobile());
			TCodemstEntity status = codemstDao.queryByCode(entity.getStatus());
			vo.setStatus(status == null ? "" : status.getName());
			TCodemstEntity orderType = codemstDao.queryByCode(entity.getOrderTypeCd());
			vo.setOrderTypeCd(orderType == null ? "" : orderType.getName());
			vo.setId(entity.getId());
			listVos.add(vo);

		}
		int total = tFishOrderService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(listVos, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tfishorder:info")
	public R info(@PathVariable("id") Integer id){
		TFishOrderEntity tFishOrder = tFishOrderService.queryObject(id);
		
		return R.ok().put("tFishOrder", tFishOrder);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tfishorder:save")
	public R save(@RequestBody TFishOrderEntity tFishOrder){
		tFishOrderService.save(tFishOrder);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tfishorder:update")
	public R update(@RequestBody TFishOrderEntity tFishOrder){
		tFishOrder.setUpdateTime(DateUtil.getNowTimestamp());
		tFishOrder.setUpdateBy(ShiroUtils.getUserId().intValue());
		tFishOrderService.update(tFishOrder);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tfishorder:delete")
	public R delete(@RequestBody Integer[] ids){
		tFishOrderService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
