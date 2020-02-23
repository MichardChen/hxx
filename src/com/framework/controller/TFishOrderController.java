package com.framework.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.framework.constants.Constants;
import com.framework.constants.ReportConstants;
import com.framework.dao.MemberDao;
import com.framework.dao.TCodemstDao;
import com.framework.dao.TFishSupplyDao;
import com.framework.entity.Member;
import com.framework.entity.TCodemstEntity;
import com.framework.entity.TFishSupplyEntity;
import com.framework.service.CommonService;
import com.framework.utils.*;
import com.framework.vo.AdminOrderListVo;
import com.framework.vo.report.EchartSeries;
import com.framework.vo.report.OrderReportVo;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import com.framework.entity.TFishOrderEntity;
import com.framework.service.TFishOrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
		DealEchatsDataUtils.dealManyLinesReportResult(result, "typeCount", typeCount);
		DealEchatsDataUtils.dealManyLinesReportResult(result, "typeAmount", typeAmount);
		DealEchatsDataUtils.dealUniqueLineReportResult(result, "statusCount", statusCount);
		return R.ok().put("result", result);
	}

	/**
	 * 订单报表数据导出
	 * @param request
	 * @param response
	 * @param startDate
	 * @param endDate
	 * @param type
	 * @throws RowsExceededException
	 * @throws WriteException
	 * @throws IOException
	 */
	@RequestMapping("/exportExcel")
	@RequiresPermissions("order:type:report:list")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,
							@RequestParam(value = "startDate", required = false)String startDate,
							@RequestParam(value = "endDate", required = false)String endDate,
							@RequestParam(value = "type")String type)
			throws RowsExceededException, WriteException, IOException {
		String fileName = getExcelFileName(type, startDate, endDate);
		//设置表格标题行
		String[] headers = getExcelHeader(type);
		List<Object[]> dataList = new ArrayList<Object[]>();
		getExportExcelData(dataList, type,headers.length, startDate, endDate);
		//使用流将数据导出
		OutputStream out = null;
		try {
			//防止中文乱码
			String headStr = "attachment; filename=\"" + new String(fileName.getBytes("gb2312"), "ISO8859-1" ) + "\"";
			response.setContentType("octets/stream");
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", headStr);
			out = response.getOutputStream();
			ExportExcelSeedBack ex = new ExportExcelSeedBack(fileName, headers, dataList);//没有标题
			ex.export(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取excel的标题行
	 * @param type
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public String getExcelFileName(String type, String startDate, String endDate){
		StringBuffer name = new StringBuffer();
		if(ReportConstants.ORDER_EXPORT_TYPE.GROUP_BY_TYPE_COUNT.equalsIgnoreCase(type)){
			name.append("订单分类数量统计");
		}else if(ReportConstants.ORDER_EXPORT_TYPE.GROUP_BY_TYPE_AMOUNT.equalsIgnoreCase(type)){
			name.append("订单分类总金额统计");
		} else {
			name.append("订单各状态数量统计");
		}
		name.append(".xls");
		return name.toString();
	}

	/**
	 * 获取excel的标题行
	 * @param type
	 * @return
	 */
	public String[] getExcelHeader(String type){
		if(ReportConstants.ORDER_EXPORT_TYPE.GROUP_BY_TYPE_COUNT.equalsIgnoreCase(type)){
			return new String[] {"序号","订单类型","下单时间","订单数量"};
		}else if(ReportConstants.ORDER_EXPORT_TYPE.GROUP_BY_TYPE_AMOUNT.equalsIgnoreCase(type)){
			return new String[] {"序号","订单类型","下单时间","订单金额"};
		} else {
			return new String[] {"序号","订单状态","订单数量"};
		}
	}

	/**
	 * 获取导出的excel中的行数据
	 * @param dataList
	 * @param type
	 * @param cellLength
	 * @param startDate
	 * @param endDate
	 */
	public void getExportExcelData(List<Object[]> dataList, String type, int cellLength, String startDate, String endDate){
		//根据product_type分类统计有多少订单量
		if(ReportConstants.ORDER_EXPORT_TYPE.GROUP_BY_TYPE_COUNT.equalsIgnoreCase(type)){
			List<OrderReportVo> typeCount = tFishOrderService.getOrderCountByType(startDate, endDate);
			getExportExcelReportTypeCountOrAmountList(dataList, typeCount, cellLength);
		}//根据product_type分类统计订单总金额(预付款+尾款)
		else if(ReportConstants.ORDER_EXPORT_TYPE.GROUP_BY_TYPE_AMOUNT.equalsIgnoreCase(type)){
			List<OrderReportVo> typeAmount = tFishOrderService.getOrderAmountByType(startDate, endDate);
			getExportExcelReportTypeCountOrAmountList(dataList, typeAmount, cellLength);
		}//查询各个订单状态下的订单数量
		else {
			List<OrderReportVo> statusCount = tFishOrderService.getOrderCountByStatus(startDate, endDate);
			getExportExcelReportStatusCountList(dataList, statusCount, cellLength);
		}
	}

	/**
	 * 获取 根据product_type分类统计有多少订单量、根据product_type分类统计订单总金额(预付款+尾款) 导出excel数据
	 * @param dataList
	 * @param grouppList
	 * @param cellLength
	 */
	public void getExportExcelReportTypeCountOrAmountList(List<Object[]> dataList, List<OrderReportVo> grouppList, int cellLength){
		Object[] objs = null;
		for (int i = 0; i < grouppList.size(); i++) {
			objs = new Object[cellLength];
			objs[0] = 0;//设置序号,在工具类中会出现
			objs[1] = grouppList.get(i).getName();
			objs[2] = grouppList.get(i).getTime();
			objs[3] = grouppList.get(i).getValue();
			dataList.add(objs);//数据添加到excel表格
		}
	}

	/**
	 * 获取 查询各个订单状态下的订单数量 导出excel数据
	 * @param dataList
	 * @param grouppList
	 * @param cellLength
	 */
	public void getExportExcelReportStatusCountList(List<Object[]> dataList, List<OrderReportVo> grouppList, int cellLength){
		Object[] objs = null;
		for (int i = 0; i < grouppList.size(); i++) {
			objs = new Object[cellLength];
			objs[0] = 0;//设置序号,在工具类中会出现
			objs[1] = grouppList.get(i).getName();
			objs[2] = grouppList.get(i).getValue();
			dataList.add(objs);//数据添加到excel表格
		}
	}

}
