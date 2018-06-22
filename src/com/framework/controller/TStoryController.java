package com.framework.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.framework.constants.Constants;
import com.framework.dao.SysUserDao;
import com.framework.entity.SysUserEntity;
import com.framework.entity.TStoryEntity;
import com.framework.model.StoreAddUpdateModel;
import com.framework.model.StoryListModel;
import com.framework.service.FileService;
import com.framework.service.TStoryService;
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
 * @date 2018-05-14 11:40:45
 */
@Controller
@RequestMapping("/tstory")
public class TStoryController extends AbstractController{
	@Autowired
	private TStoryService tStoryService;
	@Autowired
	private SysUserDao userDao;
	
	@RequestMapping("/tstorylist")
	public String list(){
		return "tstory/tstory.html";
	}
	
	@RequestMapping("/tstory_add.html")
	public String add(){
		return "tstory/tstory_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tstory:list")
	public R list(Integer page, Integer limit,@RequestParam("date")String date){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("date", date);
		
		//查询列表数据
		List<TStoryEntity> tStoryList = tStoryService.queryList(map);
		int total = tStoryService.queryTotal(map);
		List<StoryListModel> models = new ArrayList<>();
		StoryListModel model = null;
		for(TStoryEntity story : tStoryList){
			model = new StoryListModel();
			model.setUpdateTime(StringUtil.toString(story.getUpdateTime()));
			model.setCreateTime(StringUtil.toString(story.getCreateTime()));
			model.setDescUrl(story.getDescUrl());
			model.setFlg(story.getFlg()==1?"正常":"删除");
			model.setId(story.getId());
			model.setStoryIcon(story.getStoryIcon());
			model.setStoryTitle(story.getStoryTitle());
			SysUserEntity admin = userDao.queryObject(story.getCreateBy());
			if(admin != null){
				model.setCreateBy(admin.getUsername());
			}else{
				model.setCreateBy(StringUtil.STRING_BLANK);
			}
			
			SysUserEntity update = userDao.queryObject(story.getUpdateBy());
			if(update != null){
				model.setUpdateBy(update.getUsername());
			}else{
				model.setUpdateBy(StringUtil.STRING_BLANK);
			}
			
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
	@RequiresPermissions("tstory:info")
	public R info(@PathVariable("id") Integer id){
		TStoryEntity tStory = tStoryService.queryObject(id);
		StoreAddUpdateModel model = new StoreAddUpdateModel();
		model.setContent(tStory.getContent());
		model.setIcon(tStory.getStoryIcon());
		model.setTitle(tStory.getStoryTitle());
		model.setId(id);
		return R.ok().put("tStory", model);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tstory:save")
	public R save(@RequestParam("tStory")String tStory,@RequestParam("uFile")MultipartFile uploadFile) throws Exception{

		TStoryEntity story = new TStoryEntity();
		JSONObject viewModel = JSONObject.parseObject(tStory);
		int userid = ShiroUtils.getUserId().intValue();
		story.setCreateBy(userid);
		story.setCreateTime(DateUtil.getNowTimestamp());
		story.setUpdateBy(userid);
		story.setStoryTitle(viewModel.getString("title"));
		story.setUpdateTime(DateUtil.getNowTimestamp());
		story.setFlg(1);
		String htmlContent = StringUtil.formatHTML(viewModel.getString("title"), viewModel.getString("content"));
		story.setContent(htmlContent);
		//生成html
		FileService fs=new FileService();
		String uuid = UUID.randomUUID().toString();
		//生成html文件
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(Constants.FILE_HOST.DOCUMENT+uuid+".html"),"utf-8"),true);
			pw.println(htmlContent);
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//上传图片
		String logo = fs.upload(uploadFile, Constants.FILE_HOST.IMG, Constants.HOST.IMG);
		if(StringUtil.isNoneBlank(logo)){
			story.setStoryIcon(logo);
		}
		String contentUrl = Constants.HOST.DOCUMENT+uuid+".html";
		story.setDescUrl(contentUrl);
		tStoryService.save(story);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tstory:update")
	public R update(@RequestParam("tStory")String tStory,@RequestParam(value="uFile",required=false)MultipartFile uploadFile) throws Exception{
		TStoryEntity story = new TStoryEntity();
		JSONObject viewModel = JSONObject.parseObject(tStory);
		int userid = ShiroUtils.getUserId().intValue();
		story.setUpdateBy(userid);
		story.setStoryTitle(viewModel.getString("title"));
		story.setUpdateTime(DateUtil.getNowTimestamp());
		story.setId(viewModel.getInteger("id"));
		String htmlContent = StringUtil.formatHTML(viewModel.getString("title"), viewModel.getString("content"));
		story.setContent(htmlContent);
		//生成html
		FileService fs=new FileService();
		String uuid = UUID.randomUUID().toString();
		//生成html文件
		try {
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(Constants.FILE_HOST.DOCUMENT+uuid+".html"),"utf-8"),true);
			pw.println(htmlContent);
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//上传图片
		String logo = fs.upload(uploadFile, Constants.FILE_HOST.IMG, Constants.HOST.IMG);
		if(StringUtil.isNoneBlank(logo)){
			story.setStoryIcon(logo);
		}
		String contentUrl = Constants.HOST.DOCUMENT+uuid+".html";
		story.setDescUrl(contentUrl);
		tStoryService.update(story);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tstory:delete")
	public R delete(@RequestBody Integer[] ids){
		int userid = ShiroUtils.getUserId().intValue();
		tStoryService.deleteBatch(ids,DateUtil.getNowTimestamp(),userid);
		return R.ok();
	}
	
}
