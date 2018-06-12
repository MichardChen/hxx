package com.framework.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.framework.dao.SysUserDao;
import com.framework.dao.TCodemstDao;
import com.framework.entity.SysUserEntity;
import com.framework.entity.TCodemstEntity;
import com.framework.entity.TDocumentEntity;
import com.framework.model.DocumentListModel;
import com.framework.service.TDocumentService;
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
 * @date 2018-06-12 17:37:37
 */
@Controller
@RequestMapping("tdocument")
public class TDocumentController {
	@Autowired
	private TDocumentService tDocumentService;
	@Autowired
	private SysUserDao userDao;
	@Autowired
	private TCodemstDao codeMstDao;
	
	@RequestMapping("/tdocument.html")
	public String list(){
		return "tdocument/tdocument.html";
	}
	
	@RequestMapping("/tdocument_add.html")
	public String add(){
		return "tdocument/tdocument_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tdocument:list")
	public R list(Integer page, Integer limit){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		
		//查询列表数据
		List<TDocumentEntity> tDocumentList = tDocumentService.queryList(map);
		List<DocumentListModel> models = new ArrayList<>();
		DocumentListModel model = null;
		for(TDocumentEntity entity : tDocumentList){
			model = new DocumentListModel();
			SysUserEntity admin = userDao.queryObject(entity.getCreateBy());
			if(admin != null){
				model.setCreateBy(admin.getUsername());
			}else{
				model.setCreateBy(StringUtil.STRING_BLANK);
			}
			SysUserEntity uadmin = userDao.queryObject(entity.getUpdateBy());
			if(uadmin != null){
				model.setUpdateBy(uadmin.getUsername());
			}else{
				model.setUpdateBy(StringUtil.STRING_BLANK);
			}
			model.setId(entity.getId());
			model.setCreateTime(DateUtil.format(entity.getCreateTime()));
			model.setUpdateTime(DateUtil.format(entity.getUpdateTime()));
			model.setDescUrl(entity.getDescUrl());
			model.setFlg(entity.getFlg() == 1 ? "正常":"删除");
			model.setTitle(entity.getTitle());
			TCodemstEntity mst = codeMstDao.queryByCode(entity.getTypeCd());
			if(mst != null){
				model.setTypeCd(mst.getName());
			}else{
				model.setTypeCd("");
			}
			models.add(model);
		}
		int total = tDocumentService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(models, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tdocument:info")
	public R info(@PathVariable("id") Integer id){
		TDocumentEntity tDocument = tDocumentService.queryObject(id);
		
		return R.ok().put("tDocument", tDocument);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tdocument:save")
	public R save(@RequestBody TDocumentEntity tDocument){
		tDocumentService.save(tDocument);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tdocument:update")
	public R update(@RequestBody TDocumentEntity tDocument){
		tDocumentService.update(tDocument);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tdocument:delete")
	public R delete(@RequestBody Integer[] ids){
		tDocumentService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
