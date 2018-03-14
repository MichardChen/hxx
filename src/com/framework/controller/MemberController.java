package com.framework.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.framework.entity.Member;
import com.framework.entity.SysMenuEntity;
import com.framework.service.MemberService;
import com.framework.service.SysMenuService;
import com.framework.service.SysUserService;
import com.framework.utils.Constant.MenuType;
import com.framework.utils.PageUtils;
import com.framework.utils.R;
import com.framework.utils.RRException;

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
	public R list(Integer page, Integer limit,String mobile,String userTypeCd) {
		Map<String, Object> map = new HashMap<>();
		map.put("mobile", mobile);
		map.put("offset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("userTypeCd", userTypeCd);

		// 查询列表数据
		List<Member> menuList = memberService.queryList(map);
		int total = memberService.queryTotal(map);

		PageUtils pageUtil = new PageUtils(menuList, total, limit, page);

		return R.ok().put("page", pageUtil);
	}

	/**
	 * 选择菜单(添加、修改菜单)
	 */
	@RequestMapping("/select")
	@RequiresPermissions("sys:menu:select")
	public R select() {
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
}
