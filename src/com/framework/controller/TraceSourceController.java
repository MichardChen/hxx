package com.framework.controller;

import com.framework.constants.Constants;
import com.framework.entity.TraceSourceEntity;
import com.framework.service.TraceSourceService;
import com.framework.utils.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;


/**
 * @author R & D
 * @email 908350381@qq.com
 * @date 2020-01-09 20:42:50
 */
@Controller
@RequestMapping("/tracesource")
public class TraceSourceController {
	@Autowired
	private TraceSourceService traceSourceService;

	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tracesource:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);

		//查询列表数据
		List<TraceSourceEntity> tTraceSourceList = traceSourceService.queryList(map);
		int total = traceSourceService.queryTotal(map);

		PageUtils pageUtil = new PageUtils(tTraceSourceList, total, limit, page);

		return R.ok().put("page", pageUtil);
	}


	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tracesource:info")
	public R info(@PathVariable("id") Integer id){
		TraceSourceEntity traceSource = traceSourceService.queryObject(id);
		return R.ok().put("traceSource", traceSource);
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tracesource:save")
	public R save(@RequestBody TraceSourceEntity traceSource){
		traceSource.setCreateTime(new Date());
		traceSource.setUpdateTime(new Date());
		traceSource.setCreateBy(ShiroUtils.getUserId().intValue());
		traceSource.setUpdateBy(ShiroUtils.getUserId().intValue());
		//处理图片logo及生成详情html
		try{
			dealHtml(traceSource);
		} catch(Exception ex) {
			ex.printStackTrace();
			return R.error(ex.getMessage());
		}
		traceSourceService.save(traceSource);
		return R.ok();
	}

	/**
	 * 处理生成详情html
	 * @param traceSource
	 */
	public void dealHtml(TraceSourceEntity traceSource) throws Exception{
		//生成html文件
 		String uuid = UUID.randomUUID().toString().replaceAll("-","");
		String htmlContent = StringUtil.formatHTML(traceSource.getOrderNo(), traceSource.getContent());
		//二维码地址不更新
		if(traceSource.getId() != null){
			TraceSourceEntity entity = traceSourceService.queryObject(traceSource.getId());
			if(StringUtil.isNotBlank(entity.getContentUrl())){
				uuid = entity.getContentUrl().substring(Constants.HTTPS_HOST.TRACE_SOURCE.length(), entity.getContentUrl().length()-5);
			}
		}
		String contentUrl = Constants.HTTPS_HOST.TRACE_SOURCE + uuid + ".html";//访问url
		String qrCodeUrl = Constants.HTTPS_HOST.TRACE_SOURCE + uuid + ".png";//二维码访问路径
		//生成二维码
		boolean codeFlag = Zxing.orCode(contentUrl, Constants.HTTPS_FILE_HOST.TRACE_SOURCE + uuid + ".png");
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(
				new FileOutputStream(Constants.HTTPS_FILE_HOST.TRACE_SOURCE + uuid + ".html"),"utf-8"),true);
		pw.println(htmlContent);
		pw.close();
		//html访问路径
		traceSource.setContentUrl(contentUrl);
		//二维码访问路径
		traceSource.setQrCodeUrl(Constants.HTTPS_HOST.TRACE_SOURCE + uuid + ".png");
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tracesource:update")
	public R update(@RequestBody TraceSourceEntity traceSource){
		traceSource.setUpdateTime(new Date());
		traceSource.setUpdateBy(ShiroUtils.getUserId().intValue());
		//处理图片logo及生成详情html
		try{
			dealHtml(traceSource);
		} catch(Exception ex) {
			ex.printStackTrace();
			return R.error(ex.getMessage());
		}
		traceSourceService.update(traceSource);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tracesource:delete")
	public R delete(@RequestBody Integer[] ids){
		traceSourceService.deleteBatch(ids);
		return R.ok();
	}

}