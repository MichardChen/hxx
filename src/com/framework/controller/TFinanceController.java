package com.framework.controller;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

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
import com.framework.entity.SysUserEntity;
import com.framework.entity.TFinanceEntity;
import com.framework.model.TFinanceListModel;
import com.framework.model.TFinanceModel;
import com.framework.service.FileService;
import com.framework.service.TFinanceService;
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
 * @date 2018-05-16 22:28:57
 */
@Controller
@RequestMapping("tfinance")
public class TFinanceController {
	@Autowired
	private TFinanceService tFinanceService;
	@Autowired
	private SysUserDao userDao;
	
	@RequestMapping("/tfinance.html")
	public String list(){
		return "tfinance/tfinance.html";
	}
	
	@RequestMapping("/tfinance_add.html")
	public String add(){
		return "tfinance/tfinance_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tfinance:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<TFinanceEntity> tFinanceList = tFinanceService.queryList(map);
		int total = tFinanceService.queryTotal(map);
		
		List<TFinanceListModel> list = new ArrayList<>();
		TFinanceListModel m = null;
		for(TFinanceEntity entity : tFinanceList){
			m = new TFinanceListModel();
			m.setTimeDistance(entity.getTimeDistance());
			m.setIcon(entity.getIcon());
			m.setId(entity.getId());
			m.setLowRate(entity.getLowRate());
			m.setLowRefund(entity.getLowRefund());
			m.setName(entity.getName());
			if(StringUtil.equals(entity.getStatus(), "1")){
				entity.setStatus("上架中");
			}else{
				entity.setStatus("已下架");
			}
			m.setStatus(entity.getStatus());
			m.setStandard(entity.getStandard());
			SysUserEntity admin = userDao.queryObject(entity.getCreateBy());
			if(admin != null){
				m.setCreateBy(admin.getUsername());
			}else{
				m.setCreateBy(StringUtil.STRING_BLANK);
			}
			m.setCreateTime(StringUtil.toString(entity.getCreateTime()));
			list.add(m);
		}
		
		PageUtils pageUtil = new PageUtils(list, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tfinance:info")
	public R info(@PathVariable("id") Integer id){
		TFinanceEntity tFinance = tFinanceService.queryObject(id);
		TFinanceModel model = new TFinanceModel();
		if(tFinance != null){
			model.setIcon(tFinance.getIcon());
			model.setId(tFinance.getId());
			model.setLowRate(tFinance.getLowRate());
			model.setLowRefund(tFinance.getLowRefund());
			model.setName(tFinance.getName());
			model.setStandard(tFinance.getStandard());
			model.setTimeDistance(tFinance.getTimeDistance());
			model.setStatus(tFinance.getStatus());
			model.setContent(tFinance.getContent());
			model.setTypeCd(tFinance.getTypeCd());
		}
		return R.ok().put("tFinance", model);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tfinance:save")
	public R save(@RequestParam("tFinance")String tFinance,@RequestParam(value="uFile",required=false)MultipartFile uploadFile) throws Exception{
		
		TFinanceEntity story = new TFinanceEntity();
		JSONObject viewModel = JSONObject.parseObject(tFinance);
		int userid = ShiroUtils.getUserId().intValue();
		story.setCreateBy(userid);
		story.setCreateTime(DateUtil.getNowTimestamp());
		story.setUpdateBy(userid);
		story.setName(viewModel.getString("name"));
		story.setUpdateTime(DateUtil.getNowTimestamp());
		story.setLowRate(viewModel.getString("lowRate"));
		story.setLowRefund(viewModel.getString("lowRefund"));
		//story.setStandard(viewModel.getString("standard"));
		story.setTimeDistance(viewModel.getString("timeDistance"));
		story.setTitle(viewModel.getString("title"));
		story.setStatus(viewModel.getString("status"));
		story.setTypeCd(viewModel.getString("typeCd"));
		story.setLabels(viewModel.getString("labels"));

		//生成html
		String htmlContent = StringUtil.formatHTML(viewModel.getString("name"), viewModel.getString("content"));
		story.setContent(htmlContent);
		//生成html
		FileService fs=new FileService();
		String uuid = UUID.randomUUID().toString();
		//生成html文件
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(Constants.HTTPS_FILE_HOST.FINANCE+uuid+".html"),"utf-8"),true);
			pw.println(htmlContent);
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String contentUrl = Constants.HTTPS_HOST.FINANCE+uuid+".html";
		story.setDescUrl(contentUrl);

		//上传图片
		String logo = fs.upload(uploadFile, Constants.FILE_HOST.IMG, Constants.HOST.IMG);
		if(StringUtil.isNoneBlank(logo)){
			story.setIcon(logo);
		}
		tFinanceService.save(story);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tfinance:update")
	public R update(@RequestParam("tFinance")String tFinance,@RequestParam(value="uFile",required=false)MultipartFile uploadFile) throws Exception{
		TFinanceEntity story = new TFinanceEntity();
		JSONObject viewModel = JSONObject.parseObject(tFinance);
		int userid = ShiroUtils.getUserId().intValue();
		story.setUpdateBy(userid);
		story.setName(viewModel.getString("name"));
		story.setUpdateTime(DateUtil.getNowTimestamp());
		story.setLowRate(viewModel.getString("lowRate"));
		story.setLowRefund(viewModel.getString("lowRefund"));
		story.setStandard(viewModel.getString("standard"));
		story.setTimeDistance(viewModel.getString("timeDistance"));
		story.setTitle(viewModel.getString("title"));
		story.setId(viewModel.getInteger("id"));
		story.setStatus(viewModel.getString("status"));
		story.setTypeCd(viewModel.getString("typeCd"));
		story.setLabels(viewModel.getString("labels"));
		//生成html
		FileService fs=new FileService();

		//生成html
		String htmlContent = StringUtil.formatHTML(viewModel.getString("name"), viewModel.getString("content"));
		story.setContent(htmlContent);
		//生成html
		String uuid = UUID.randomUUID().toString();
		//生成html文件
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(Constants.HTTPS_FILE_HOST.FINANCE+uuid+".html"),"utf-8"),true);
			pw.println(htmlContent);
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String contentUrl = Constants.HTTPS_HOST.FINANCE+uuid+".html";
		story.setDescUrl(contentUrl);

		//上传图片
		String logo = fs.upload(uploadFile, Constants.FILE_HOST.IMG, Constants.HOST.IMG);
		if(StringUtil.isNoneBlank(logo)){
			story.setIcon(logo);
		}
		tFinanceService.update(story);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tfinance:delete")
	public R delete(@RequestBody Integer[] ids){
		tFinanceService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
