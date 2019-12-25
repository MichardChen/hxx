package com.framework.dao;

import java.util.List;
import java.util.Map;

import com.framework.entity.Member;
import org.apache.ibatis.annotations.Param;

/**
 * 用户表
 * @author R & D
 * @email 908350381@qq.com
 * @date 2016年9月18日 上午9:34:11
 */
public interface MemberDao extends BaseDao<Member> {
	
	List<String> queryAllPerms(Long memberId);

	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long memberId);

	/**
	 * 根据用户名，查询用户
	 */
	Member queryByMemberName(String membername);

	Member queryByMobile(String mobile);

	/**
	 * 修改密码
	 */
	int updatePassword(Map<String, Object> map);

	/**
	 * 修改积分
	 */
	int updatePoints(@Param("points")int points,@Param("memberId")int memberId);

	/**
	 * 修改昵称
	 * @param nickName
	 * @param memberId
	 * @return
	 */
	int updateNickName(@Param("nickName")String nickName,@Param("memberId")int memberId);

	/**
	 * 更改头像
	 * @param icon
	 * @param memberId
	 * @return
	 */
	int updateIcon(@Param("icon")String icon,@Param("memberId")int memberId);

}
