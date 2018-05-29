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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.framework.constants.Constants;
import com.framework.dao.TCodemstDao;
import com.framework.entity.TCarSecondhandEntity;
import com.framework.entity.TCarouselEntity;
import com.framework.entity.TCodemstEntity;
import com.framework.model.CarouselListModel;
import com.framework.service.FileService;
import com.framework.service.TCarouselService;
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
 * @date 2018-05-16 22:46:34
 */
@Controller
@RequestMapping("tcarousel")
public class TCarouselController {
	@Autowired
	private TCarouselService tCarouselService;
	@Autowired
	private TCodemstDao codeMstDao;

	@RequestMapping("/tcarousel.html")
	public String list() {
		return "tcarousel/tcarousel.html";
	}

	@RequestMapping("/tcarousel_add.html")
	public String add() {
		return "tcarousel/tcarousel_add.html";
	}

	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tcarousel:list")
	public R list(Integer page, Integer limit) {
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);

		// 查询列表数据
		List<TCarouselEntity> tCarouselList = tCarouselService.queryList(map);
		int total = tCarouselService.queryTotal(map);
		List<CarouselListModel> lists = new ArrayList<>();
		CarouselListModel model = null;
		for(TCarouselEntity e : tCarouselList){
			model = new CarouselListModel();
			model.setFlg(e.getFlg() == 1 ? "正常" : "删除");
			model.setId(e.getId());
			model.setImgUrl(e.getImgUrl());
			model.setMark(e.getMark());
			model.setRealUrl(e.getRealUrl());
			TCodemstEntity mst = codeMstDao.queryByCode(e.getTypeCd());
			if(mst != null){
				model.setTypeCd(mst.getName());
			}else{
				model.setTypeCd("");
			}
			lists.add(model);
		}

		PageUtils pageUtil = new PageUtils(lists, total, limit, page);

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tcarousel:info")
	public R info(@PathVariable("id") Integer id) {
		TCarouselEntity tCarousel = tCarouselService.queryObject(id);

		return R.ok().put("tCarousel", tCarousel);
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tcarousel:save")
	public R save(@RequestParam("tCarousel") String tCarousel,@RequestParam(value = "uFile", required = false) MultipartFile uploadFile) {

		TCarouselEntity entity = new TCarouselEntity();
		JSONObject viewModel = JSONObject.parseObject(tCarousel);
		int userid = ShiroUtils.getUserId().intValue();
		entity.setCreateBy(userid);
		entity.setCreateTime(DateUtil.getNowTimestamp());
		entity.setUpdateBy(userid);
		entity.setUpdateTime(DateUtil.getNowTimestamp());
		entity.setTypeCd(viewModel.getString("typeCd"));
		entity.setFlg(1);
		entity.setMark(viewModel.getString("mark"));
		entity.setRealUrl(viewModel.getString("realUrl"));

		// 生成html
		FileService fs = new FileService();
		// 上传图片
		String logo = fs.upload(uploadFile, Constants.FILE_HOST.IMG, Constants.HOST.IMG);
		if (StringUtil.isNoneBlank(logo)) {
			entity.setImgUrl(logo);
		}

		tCarouselService.save(entity);

		return R.ok();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tcarousel:update")
	public R update(@RequestBody TCarouselEntity tCarousel) {
		tCarouselService.update(tCarousel);

		return R.ok();
	}

	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tcarousel:delete")
	public R delete(@RequestBody Integer[] ids) {
		tCarouselService.deleteBatch(ids);

		return R.ok();
	}

}
