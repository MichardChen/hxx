package com.framework.service;

import java.util.List;
import java.util.Map;

import com.framework.entity.Member;
import com.framework.vo.report.OrderReportVo;
import org.apache.ibatis.annotations.Param;

public interface MemberService {

	Member queryObject(Long id);

	List<Member> queryList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);

	void save(Member member);

	void update(Member member);

	void deleteBatch(Long[] memberIds);

	Member queryByMobile(String mobile);

	/**
	 * 获取每天注册人数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<OrderReportVo> getRegisterCountByDate(String startDate, String endDate);
}
