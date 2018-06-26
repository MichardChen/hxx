package com.framework.controller;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.framework.dao.SysUserDao;
import com.framework.dao.TCodemstDao;
import com.framework.entity.SysUserEntity;
import com.framework.entity.TCodemstEntity;
import com.framework.entity.TNewsEntity;
import com.framework.entity.TStoryEntity;
import com.framework.model.NewsListModel;
import com.framework.model.TNewsAddUpdateModel;
import com.framework.service.FileService;
import com.framework.service.TNewsService;
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
 * @date 2018-03-16 17:13:03
 */
@Controller
@RequestMapping("tnews")
public class TNewsController {
	@Autowired
	private TNewsService tNewsService;
	@Autowired
	private TCodemstDao codeMstDao;
	@Autowired
	private SysUserDao userDao;
	
	@RequestMapping("/tnews.html")
	public String list(){
		return "tnews/tnews.html";
	}
	
	@RequestMapping("/tnews_add.html")
	public String add(){
		return "tnews/tnews_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tnews:list")
	public R list(Integer page, Integer limit,@RequestParam("date")String date){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("date", date);
		//查询列表数据
		List<TNewsEntity> tNewsList = tNewsService.queryList(map);
		int total = tNewsService.queryTotal(map);
		List<NewsListModel> models = new ArrayList<>();
		NewsListModel model = null;
		for(TNewsEntity m : tNewsList) {
			model = new NewsListModel();
			model.setContentUrl(m.getContentUrl());
			model.setFlg(m.getFlg() == 0 ? "是":"否");
			model.setHotFlg(m.getHotFlg() == 0 ? "否" : "是");
			model.setId(m.getId());
			model.setNewsTitle(m.getNewsTitle());
			model.setTopFlg(m.getTopFlg() == 1 ? "是":"否");
			TCodemstEntity mst = codeMstDao.queryByCode(m.getNewsTypeCd());
			if(mst != null) {
				model.setNewsTypeCd(mst.getName());
			}else {
				model.setNewsTypeCd("");
			}
			SysUserEntity admin = userDao.queryObject(m.getCreateBy());
			if(admin != null){
				model.setCreateBy(admin.getUsername());
			}else{
				model.setCreateBy(StringUtil.STRING_BLANK);
			}
			
			SysUserEntity update = userDao.queryObject(m.getUpdateBy());
			if(update != null){
				model.setUpdateBy(update.getUsername());
			}else{
				model.setUpdateBy(StringUtil.STRING_BLANK);
			}
			model.setUpdateTime(StringUtil.toString(m.getUpdateTime()));
			model.setCreateTime(StringUtil.toString(m.getCreateTime()));
			models.add(model);
		}
		
		PageUtils pageUtil = new PageUtils(models, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tnews:info")
	public R info(@PathVariable("id") Integer id){
		TNewsEntity tNews = tNewsService.queryObject(id);
		TNewsAddUpdateModel model = new TNewsAddUpdateModel();
		model.setContent(tNews.getContent());
		model.setHotFlg(tNews.getHotFlg());
		model.setId(tNews.getId());
		model.setNewsLogo(tNews.getNewsLogo());
		model.setNewsTitle(tNews.getNewsTitle());
		model.setNewsTypeCd(tNews.getNewsTypeCd());
		model.setTopFlg(tNews.getTopFlg());
		return R.ok().put("tNews", model);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tnews:save")
	public R save(@RequestParam("tNews")String tNews,@RequestParam(value="uFile",required=false)MultipartFile uploadFile) throws Exception{
		
		TNewsEntity story = new TNewsEntity();
		JSONObject viewModel = JSONObject.parseObject(tNews);
		int userid = ShiroUtils.getUserId().intValue();
		story.setCreateBy(userid);
		story.setCreateTime(DateUtil.getNowTimestamp());
		story.setUpdateBy(userid);
		story.setNewsTitle(viewModel.getString("newsTitle"));
		story.setUpdateTime(DateUtil.getNowTimestamp());
		story.setHotFlg(viewModel.getInteger("hotFlg"));
		story.setTopFlg(viewModel.getInteger("topFlg"));
		story.setNewsTypeCd(viewModel.getString("newsTypeCd"));
		story.setFlg(1);
		String htmlContent = StringUtil.formatHTML(viewModel.getString("newsTitle"), viewModel.getString("content"));
		story.setContent(htmlContent);
		//生成html
		FileService fs=new FileService();
		String uuid = UUID.randomUUID().toString();
		//生成html文件
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(Constants.HTTPS_FILE_HOST.DOCUMENT+uuid+".html"),"utf-8"),true);
			pw.println(htmlContent);
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//上传图片
		String logo = fs.upload(uploadFile, Constants.FILE_HOST.IMG, Constants.HOST.IMG);
		if(StringUtil.isNoneBlank(logo)){
			story.setNewsLogo(logo);
		}
		String contentUrl = Constants.HTTPS_HOST.DOCUMENT+uuid+".html";
		story.setContentUrl(contentUrl);
		tNewsService.save(story);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tnews:update")
	public R update(@RequestParam("tNews")String tNews,@RequestParam(value="uFile",required=false)MultipartFile uploadFile) throws Exception{
		TNewsEntity story = new TNewsEntity();
		JSONObject viewModel = JSONObject.parseObject(tNews);
		int userid = ShiroUtils.getUserId().intValue();
		story.setUpdateBy(userid);
		story.setNewsTitle(viewModel.getString("newsTitle"));
		story.setUpdateTime(DateUtil.getNowTimestamp());
		story.setHotFlg(viewModel.getInteger("hotFlg"));
		story.setTopFlg(viewModel.getInteger("topFlg"));
		story.setNewsTypeCd(viewModel.getString("newsTypeCd"));
		story.setId(viewModel.getInteger("id"));
		String htmlContent = StringUtil.formatHTML(viewModel.getString("newsTitle"), viewModel.getString("content"));
		story.setContent(htmlContent);
		//生成html
		FileService fs=new FileService();
		String uuid = UUID.randomUUID().toString();
		//生成html文件
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(Constants.HTTPS_FILE_HOST.DOCUMENT+uuid+".html"),"utf-8"),true);
			pw.println(htmlContent);
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//上传图片
		String logo = fs.upload(uploadFile, Constants.FILE_HOST.IMG, Constants.HOST.IMG);
		if(StringUtil.isNoneBlank(logo)){
			story.setNewsLogo(logo);
		}
		String contentUrl = Constants.HTTPS_HOST.DOCUMENT+uuid+".html";
		story.setContentUrl(contentUrl);
		tNewsService.update(story);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tnews:delete")
	public R delete(@RequestBody Integer[] ids){
		tNewsService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
