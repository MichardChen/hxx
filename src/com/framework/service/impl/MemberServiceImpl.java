package com.framework.service.impl;

import java.util.List;
import java.util.Map;

import com.framework.vo.report.OrderReportVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.dao.MemberDao;
import com.framework.entity.Member;
import com.framework.service.MemberService;

@Service("memberService")
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDao memberDao;
	
	@Override
	public Member queryObject(Long id) {
		return memberDao.queryObject(id);
	}

	@Override
	public List<Member> queryList(Map<String, Object> map) {
		return memberDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return memberDao.queryTotal(map);
	}

	@Override
	public void save(Member member) {
		memberDao.save(member);
	}

	@Override
	public void update(Member member) {
		memberDao.update(member);
	}

	@Override
	public void deleteBatch(Long[] memberIds) {
		memberDao.deleteBatch(memberIds);
	}

	@Override
	public Member queryByMobile(String mobile) {
		return memberDao.queryByMobile(mobile);
	}

	/**
	 * 获取每天注册人数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Override
	public List<OrderReportVo> getRegisterCountByDate(String startDate, String endDate) {
		return memberDao.getRegisterCountByDate(startDate, endDate);
	}
}
