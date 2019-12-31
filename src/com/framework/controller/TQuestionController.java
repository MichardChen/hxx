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
import org.springframework.stereotype.Controller;

import com.framework.constants.Constants;
import com.framework.dao.SysUserDao;
import com.framework.dao.TBrandDao;
import com.framework.dao.TBrandSeriesDao;
import com.framework.dao.TCarImportDao;
import com.framework.dao.TCarLeaseDao;
import com.framework.dao.TCarSecondhandDao;
import com.framework.entity.SysUserEntity;
import com.framework.entity.TBrandEntity;
import com.framework.entity.TBrandSeriesEntity;
import com.framework.entity.TCarImportEntity;
import com.framework.entity.TCarLeaseEntity;
import com.framework.entity.TCarSecondhandEntity;
import com.framework.entity.TQuestionAnswerEntity;
import com.framework.entity.TQuestionEntity;
import com.framework.model.QuestionAnswerListModel;
import com.framework.model.QuestionListModel;
import com.framework.model.TQuestionModel;
import com.framework.service.TQuestionService;
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
 * @date 2018-05-16 22:14:11
 */
@Controller
@RequestMapping("tquestion")
public class TQuestionController {
	@Autowired
	private TQuestionService tQuestionService;
	@Autowired
	private SysUserDao userDao;
	@Autowired
	private TCarLeaseDao carLeaseDao;
	@Autowired
	private TCarImportDao carImportDao;
	@Autowired
	private TCarSecondhandDao carSecondhandDao;
	@Autowired
	private TBrandDao brandDao;
	@Autowired
	private TBrandSeriesDao brandSeriesDao;
	
	
	@RequestMapping("/tquestion.html")
	public String list(){
		return "tquestion/tquestion.html";
	}
	
	@RequestMapping("/tquestion_add.html")
	public String add(){
		return "tquestion/tquestion_add.html";
	}
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tquestion:list")
	public R list(Integer page, Integer limit,@RequestParam("date")String date){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("date", date);
		//查询列表数据
		List<TQuestionEntity> tQuestionList = tQuestionService.queryList(map);
		int total = tQuestionService.queryTotal(map);
		
		List<QuestionListModel> models = new ArrayList<>();
		QuestionListModel model = null;
		for(TQuestionEntity data : tQuestionList){
			model = new QuestionListModel();
			model.setCreateTime(StringUtil.toString(data.getCreateTime()));
			if(StringUtil.equals(data.getStatus(), Constants.FEEDBACK_STATUS.HANDLE)){
				model.setStatus("已处理");
			}
			if(StringUtil.equals(data.getStatus(), Constants.FEEDBACK_STATUS.STAY_HANDLE)){
				model.setStatus("待处理");
			}
			
			model.setId(data.getId());
			SysUserEntity admin = userDao.queryObject(data.getEmployeeId());
			if(admin != null){
				model.setEmployeeId(admin.getRealName());
			}else{
				model.setEmployeeId(StringUtil.STRING_BLANK);
			}
			int userid = data.getUpdateBy() == null ? 0 : data.getUpdateBy();
			SysUserEntity uadmin = userDao.queryObject(userid);
			if(uadmin != null){
				model.setUpdateBy(uadmin.getUsername());
			}else{
				model.setUpdateBy(StringUtil.STRING_BLANK);
			}
			model.setUpdateTime(StringUtil.toString(data.getUpdateTime()));
			model.setLinkMan(data.getLinkMan());
			model.setMobile(data.getMobile());
			model.setQuestion(data.getQuestion());
			
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
	@RequiresPermissions("tquestion:info")
	public R info(@PathVariable("id") Integer id){
		TQuestionEntity tQuestion = tQuestionService.queryObject(id);
		TQuestionModel model = new TQuestionModel();
		if(tQuestion != null){
			model.setCreateTime(StringUtil.toString(tQuestion.getCreateTime()));
			
			SysUserEntity userEntity = userDao.queryObject(tQuestion.getEmployeeId());
			if(userEntity != null){
				model.setEmployeeId(userEntity.getRealName());
			}else{
				model.setEmployeeId("");
			}
			
			SysUserEntity updateBy = userDao.queryObject(tQuestion.getUpdateBy());
			if(updateBy != null){
				model.setEmployeeId(updateBy.getRealName());
			}else{
				model.setEmployeeId("");
			}
			model.setUpdateTime(StringUtil.toString(tQuestion.getUpdateTime()));
			model.setId(tQuestion.getId());
			model.setLinkMan(tQuestion.getLinkMan());
			model.setMobile(tQuestion.getMobile());
			model.setQuestion(tQuestion.getQuestion());
			model.setStatus(tQuestion.getStatus());
			model.setTypeCd(tQuestion.getTypeCd());
		}
		return R.ok().put("tQuestion", model);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tquestion:save")
	public R save(@RequestBody TQuestionEntity tQuestion){
		tQuestionService.save(tQuestion);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tquestion:update")
	public R update(@RequestParam("status")String status,@RequestParam("id")Integer id){
		TQuestionEntity tQuestion = new TQuestionEntity();
		tQuestion.setId(id);
		tQuestion.setStatus(status);
		tQuestion.setUpdateBy(ShiroUtils.getUserId().intValue());
		tQuestion.setUpdateTime(DateUtil.getNowTimestamp());
		tQuestionService.update(tQuestion);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tquestion:delete")
	public R delete(@RequestBody Integer[] ids){
		tQuestionService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
