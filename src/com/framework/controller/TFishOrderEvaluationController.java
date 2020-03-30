package com.framework.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.dao.TCodemstDao;
import com.framework.entity.TCodemstEntity;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.framework.entity.TFishOrderEvaluationEntity;
import com.framework.service.TFishOrderEvaluationService;
import com.framework.utils.PageUtils;
import com.framework.utils.R;


/**
 * 
 * 
 * @author R & D
 * @email adang369@126.com
 * @date 2020-03-22 14:48:12
 */
@Controller
@RequestMapping("tfishorderevaluation")
public class TFishOrderEvaluationController {
	@Autowired
	private TCodemstDao codemstDao;
	@Autowired
	private TFishOrderEvaluationService tFishOrderEvaluationService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("tfishorderevaluation:list")
	public R list(Integer page, Integer limit, String orderNo,String orderTypeCd, String flg){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("orderNo", orderNo);
		map.put("orderTypeCd", orderTypeCd);
		map.put("flg", flg);

		//查询列表数据
		List<TFishOrderEvaluationEntity> tFishOrderEvaluationList = tFishOrderEvaluationService.queryList(map);
		int total = tFishOrderEvaluationService.queryTotal(map);
		if(tFishOrderEvaluationList != null && tFishOrderEvaluationList.size()>0){
			for(TFishOrderEvaluationEntity item: tFishOrderEvaluationList){
				TCodemstEntity orderType = codemstDao.queryByCode(item.getOrderTypeCd());
				item.setOrderTypeCd(orderType == null ? "" : orderType.getName());
			}
		}
		PageUtils pageUtil = new PageUtils(tFishOrderEvaluationList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tfishorderevaluation:info")
	public R info(@PathVariable("id") Integer id){
		TFishOrderEvaluationEntity tFishOrderEvaluation = tFishOrderEvaluationService.queryObject(id);
		
		return R.ok().put("tFishOrderEvaluation", tFishOrderEvaluation);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("tfishorderevaluation:save")
	public R save(@RequestBody TFishOrderEvaluationEntity tFishOrderEvaluation){
		tFishOrderEvaluationService.save(tFishOrderEvaluation);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("tfishorderevaluation:update")
	public R update(@RequestBody TFishOrderEvaluationEntity tFishOrderEvaluation){
		tFishOrderEvaluationService.update(tFishOrderEvaluation);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("tfishorderevaluation:delete")
	public R delete(@RequestBody Integer[] ids){
		tFishOrderEvaluationService.deleteBatch(ids);
		
		return R.ok();
	}

	/**
	 * 审核通过
	 */
	@ResponseBody
	@RequestMapping("/auditPass")
	@RequiresPermissions("tfishorderevaluation:audit:pass")
	public R auditPass(@RequestBody Integer[] ids){
		tFishOrderEvaluationService.auditPass(ids);
		return R.ok();
	}

	/**
	 * 审核不过
	 */
	@ResponseBody
	@RequestMapping("/auditFail")
	@RequiresPermissions("tfishorderevaluation:audit:fail")
	public R auditFail(@RequestBody Integer[] ids){
		tFishOrderEvaluationService.auditFail(ids);
		return R.ok();
	}
	
}
