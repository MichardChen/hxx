package com.framework.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.framework.entity.SysUserEntity;

/**
 * 系统用户
 * @author R & D
 * @email 908350381@qq.com
 * @date 2016年9月18日 上午9:34:11
 */
public interface SysUserDao extends BaseDao<SysUserEntity> {
	/**
	 * 查询用户的所有权限
	 * @param userId 用户ID
	 */
	List<String> queryAllPerms(Long userId);

	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);

	/**
	 * 根据用户名，查询系统用户
	 */
	SysUserEntity queryByUserName(String username);

	/**
	 * 修改密码
	 */
	int updatePassword(Map<String, Object> map);
	
	SysUserEntity queryByUserNamePwd(String username,String password);
	
	List<SysUserEntity> querySaleManager(@Param("fromRow")int fromRow,@Param("pageSize")int pageSize,@Param("roleId")int roleId);
}
