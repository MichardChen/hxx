package com.framework.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.constants.Constants;
import com.framework.dao.MemberDao;
import com.framework.dao.TCodemstDao;
import com.framework.dao.TFishSupplyDao;
import com.framework.entity.Member;
import com.framework.entity.TCodemstEntity;
import com.framework.entity.TFishSupplyEntity;
import com.framework.service.CommonService;
import com.framework.utils.*;
import com.framework.vo.AdminOrderListVo;
import com.framework.vo.report.OrderReportVo;
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
	@Autowired
	private CommonService commonService;
	@Autowired
	private TFishSupplyDao supplyDao;

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
			vo.setFromUserId(fromUser == null ? "" : StringUtil.isBlank(fromUser.getNickName()) ? fromUser.getMobile() : fromUser.getNickName() + fromUser.getMobile());
			Member toUser = memberDao.queryObject(entity.getToUserId());
			vo.setToUserId(toUser == null ? "" : StringUtil.isBlank(toUser.getNickName()) ? toUser.getMobile() : toUser.getNickName() + toUser.getMobile());
			TCodemstEntity status = codemstDao.queryByCode(entity.getStatus());
			vo.setStatus(status == null ? "" : status.getName());
			TCodemstEntity orderType = codemstDao.queryByCode(entity.getOrderTypeCd());
			vo.setOrderTypeCd(orderType == null ? "" : orderType.getName());
			vo.setId(entity.getId());

			if(Constants.ORDER_TYPE.SUPPLY.equals(entity.getOrderTypeCd())){
				TFishSupplyEntity supplyEntity = supplyDao.queryObject(entity.getInfoId());
				if(supplyEntity != null){
					vo.setProductType(supplyEntity.getProductType());
				}
			}

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
		Member fromUser = memberDao.queryObject(tFishOrder.getFromUserId());
		Member toUser = memberDao.queryObject(tFishOrder.getToUserId());
		String fromUserName = fromUser == null ? "" : StringUtil.isBlank(fromUser.getNickName()) ? fromUser.getMobile() : fromUser.getNickName() + fromUser.getMobile();
		String toUserName = toUser == null ? "" : StringUtil.isBlank(toUser.getNickName()) ? toUser.getMobile() : toUser.getNickName() + toUser.getMobile();

		String supplyInfo = "";
		if(tFishOrder != null){
			if(Constants.ORDER_TYPE.SUPPLY.equals(tFishOrder.getOrderTypeCd())){
				TFishSupplyEntity supplyEntity = supplyDao.queryObject(tFishOrder.getInfoId());
				if(supplyEntity != null){
					supplyInfo = "供应订单号："+supplyEntity.getOrderNo()+",供应信息："
							+supplyEntity.getProductType()+" "+supplyEntity.getMainType()+"  "
							+"规格："+supplyEntity.getSize()+" 价格：" + supplyEntity.getPrice() + supplyEntity.getUnit()+"  "+ "供应量："+
							supplyEntity.getWeight()+"  "+" 供应地："+supplyEntity.getProvice()+supplyEntity.getCity();
				}
			}

		}
		return R.ok().put("tFishOrder", tFishOrder)
				.put("fromUser",fromUserName)
				.put("toUser",toUserName)
				.put("supplyInfo",supplyInfo);
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
		commonService.saveOrderStatus(ShiroUtils.getUserId().intValue(), tFishOrder.getOrderNo()
				, Constants.ORDER_TYPE.SUPPLY
				, tFishOrder.getStatus(), "后台操作订单，更新状态为："+tFishOrder.getStatus(), tFishOrder.toString());
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


	/**
	 * 获取报表统计数据
	 */
	@ResponseBody
	@RequestMapping("/getReportData")
	@RequiresPermissions("order:type:report:list")
	public R getReportData(@RequestParam(value = "startDate", required = false)String startDate, @RequestParam(value = "endDate", required = false)String endDate){
		Map<String, Object> result = new HashMap<>();
		//根据product_type分类统计有多少订单量
		List<OrderReportVo> typeCount = tFishOrderService.getOrderCountByType(startDate, endDate);
		//根据product_type分类统计订单总金额(预付款+尾款)
		List<OrderReportVo> typeAmount = tFishOrderService.getOrderAmountByType(startDate, endDate);
		//查询各个订单状态下的订单数量
		List<OrderReportVo> statusCount = tFishOrderService.getOrderCountByStatus(startDate, endDate);
		//处理查询的报表数据返回给前端
		dealReportResult(result, "typeCount", typeCount);
		dealReportResult(result, "typeAmount", typeAmount);
		dealReportResult(result, "statusCount", statusCount);
		return R.ok().put("result", result);
	}

	/**
	 * 处理查询的报表数据返回给前端
	 * @param result 最终结果map
	 * @param key 前端报表的取值key
	 * @param queryList 查询到的结果集
	 */
	public void dealReportResult(Map<String, Object> result, String key, List<OrderReportVo> queryList){
		result.put(key, null);
		if(queryList != null && queryList.size()>0){
			ArrayList<String> nameList = new ArrayList<>();
			ArrayList<String> valueList = new ArrayList<>();
			for(OrderReportVo item: queryList){
				nameList.add(StringUtil.isBlank(item.getName())?item.getCode():item.getName());
				valueList.add(StringUtil.isBlank(item.getValue())?null:item.getValue());
			}
			Map<String, Object> queryMap = new HashMap<>();
			queryMap.put("xAxis", nameList);
			queryMap.put("series", valueList);
			result.put(key, queryMap);
		}
	}
}
