package com.framework.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import com.framework.entity.BankCardEntity;
import com.framework.service.BankCardService;
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
@RequestMapping("/bankcard")
public class BankCardController {
	@Autowired
	private BankCardService bankCardService;

	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("bankcard:list")
	public R list(Integer page, Integer limit, String bankNo,String bankOpen, String flg,
				  String createBy,String bankOwner, String bank){
		Map<String, Object> map = new HashMap<>();
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("bankNo", bankNo);
		map.put("bankOpen", bankOpen);
		map.put("bank", bank);
		map.put("bankOwner", bankOwner);
		map.put("createBy", createBy);
		map.put("flg", flg);

		//查询列表数据
		List<BankCardEntity> bankCardList = bankCardService.queryList(map);
		int total = bankCardService.queryTotal(map);
		
		PageUtils pageUtil = new PageUtils(bankCardList, total, limit, page);
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@ResponseBody
	@RequestMapping("/info/{id}")
	@RequiresPermissions("bankcard:info")
	public R info(@PathVariable("id") Integer id){
		BankCardEntity bankCard = bankCardService.queryObject(id);
		
		return R.ok().put("bankCard", bankCard);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("bankcard:save")
	public R save(@RequestBody BankCardEntity bankCard){
		bankCard.setCreateTime(new Date());
		bankCard.setUpdateTime(new Date());
		bankCardService.save(bankCard);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("bankcard:update")
	public R update(@RequestBody BankCardEntity bankCard){
		bankCard.setUpdateTime(new Date());
		bankCardService.update(bankCard);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("bankcard:delete")
	public R delete(@RequestBody Integer[] ids){
		bankCardService.deleteBatch(ids);
		
		return R.ok();
	}

	/**
	 * 启用银行卡
	 */
	@ResponseBody
	@RequestMapping("/enable")
	@RequiresPermissions("bankcard:enable")
	public R enable(@RequestBody Integer[] ids){
		bankCardService.enable(ids);
		return R.ok();
	}

	/**
	 * 禁用银行卡
	 */
	@ResponseBody
	@RequestMapping("/disable")
	@RequiresPermissions("bankcard:delete")
	public R disable(@RequestBody Integer[] ids){
		bankCardService.disable(ids);
		return R.ok();
	}
	
}
