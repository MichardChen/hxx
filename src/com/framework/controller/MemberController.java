package com.framework.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.constants.ReportConstants;
import com.framework.utils.*;
import com.framework.vo.report.OrderReportVo;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.framework.entity.Member;
import com.framework.entity.SysMenuEntity;
import com.framework.service.MemberService;
import com.framework.service.SysMenuService;
import com.framework.service.SysUserService;
import com.framework.utils.Constant.MenuType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统菜单
 * @author R & D
 * @email 908350381@qq.com
 * @date 2016年10月27日 下午9:58:15
 */
@RestController
@RequestMapping("/basic/member")
public class MemberController extends AbstractController {
	
	@Autowired
	private MemberService memberService;

	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysMenuService sysMenuService;
	
	/**
	 * 所有用户列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("basic:member:list")
	public R list(Integer page, Integer limit,String mobile,String name) {
		Map<String, Object> map = new HashMap<>();
		map.put("mobile", mobile);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("name", name);

		// 查询列表数据
		List<Member> menuList = memberService.queryList(map);
		int total = memberService.queryTotal(map);

		PageUtils pageUtil = new PageUtils(menuList, total, limit, page);

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 用户信息
	 */
	@RequestMapping("/info/{memberId}")
	@RequiresPermissions("basic:member:info")
	public R info(@PathVariable("memberId") Long memberId) {
		Member member = memberService.queryObject(memberId);
		return R.ok().put("member", member);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("basic:member:save")
	public R save(@RequestBody Member member) {
		memberService.save(member);
		return R.ok();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("basic:member:update")
	public R update(@RequestBody Member member) {
		memberService.update(member);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("basic:member:delete")
	public R delete(@RequestBody Long[] memberIds) {
		for (Long memberId : memberIds) {
			if (memberId.longValue() <= 28) {
				return R.error("系统菜单，不能删除");
			}
		}
		memberService.deleteBatch(memberIds);

		return R.ok();
	}

	/**
	 * 选择菜单(添加、修改菜单)
	 */
	@RequestMapping("/select")
	@RequiresPermissions("sys:menu:select")
	public R select(){
		// 查询列表数据
		List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();

		// 添加顶级菜单
		SysMenuEntity root = new SysMenuEntity();
		root.setMenuId(0L);
		root.setName("一级菜单");
		root.setParentId(-1L);
		root.setOpen(true);
		menuList.add(root);

		return R.ok().put("menuList", menuList);
	}

	/**
	 * 角色授权菜单
	 */
	@RequestMapping("/perms")
	@RequiresPermissions("sys:menu:perms")
	public R perms() {
		// 查询列表数据
		List<SysMenuEntity> menuList = sysMenuService.queryList(new HashMap<String, Object>());

		return R.ok().put("menuList", menuList);
	}

	/**
	 * 用户菜单列表
	 */
	@RequestMapping("/user")
	public R user() {
		List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(getUserId());

		return R.ok().put("menuList", menuList);
	}

	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(SysMenuEntity menu) {
		if (StringUtils.isBlank(menu.getName())) {
			throw new RRException("菜单名称不能为空");
		}

		if (menu.getParentId() == null) {
			throw new RRException("上级菜单不能为空");
		}

		// 菜单
		if (menu.getType() == MenuType.MENU.getValue()) {
			if (StringUtils.isBlank(menu.getUrl())) {
				throw new RRException("菜单URL不能为空");
			}
		}

		// 上级菜单类型
		int parentType = MenuType.CATALOG.getValue();
		if (menu.getParentId() != 0) {
			SysMenuEntity parentMenu = sysMenuService.queryObject(menu.getParentId());
			parentType = parentMenu.getType();
		}

		// 目录、菜单
		if (menu.getType() == MenuType.CATALOG.getValue() || menu.getType() == MenuType.MENU.getValue()) {
			if (parentType != MenuType.CATALOG.getValue()) {
				throw new RRException("上级菜单只能为目录类型");
			}
			return;
		}

		// 按钮
		if (menu.getType() == MenuType.BUTTON.getValue()) {
			if (parentType != MenuType.MENU.getValue()) {
				throw new RRException("上级菜单只能为菜单类型");
			}
			return;
		}
	}

	/**
	 * 获取报表统计数据
	 */
	@ResponseBody
	@RequestMapping("/getReportData")
	@RequiresPermissions("member:report:list")
	public R getReportData(@RequestParam(value = "startDate", required = false)String startDate, @RequestParam(value = "endDate", required = false)String endDate){
		Map<String, Object> result = new HashMap<>();
		//获取每天注册人数
		List<OrderReportVo> registerCount = memberService.getRegisterCountByDate(startDate, endDate);
		//处理查询的报表数据返回给前端
		DealEchatsDataUtils.dealUniqueLineReportResult(result, "registerCount", registerCount);
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
	@RequiresPermissions("member:report:list")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,
							@RequestParam(value = "startDate", required = false)String startDate,
							@RequestParam(value = "endDate", required = false)String endDate,
							@RequestParam(value = "type", required = false)String type)
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
		name.append("每日注册用户数量统计");
		name.append(".xls");
		return name.toString();
	}

	/**
	 * 获取excel的标题行
	 * @param type
	 * @return
	 */
	public String[] getExcelHeader(String type){
		return new String[] {"序号","注册时间", "用户数量"};
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
		//获取每天注册人数
		List<OrderReportVo> registerCount = memberService.getRegisterCountByDate(startDate, endDate);
		getExportExcelReportRegisterCountList(dataList, registerCount, cellLength);
	}

	/**
	 * 获取 获取每天注册人数 导出excel数据
	 * @param dataList
	 * @param grouppList
	 * @param cellLength
	 */
	public void getExportExcelReportRegisterCountList(List<Object[]> dataList, List<OrderReportVo> grouppList, int cellLength){
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
