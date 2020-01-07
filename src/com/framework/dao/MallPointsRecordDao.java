package com.framework.dao;

import com.framework.entity.MallPointsRecord;
import com.framework.vo.MallPointsListVo;

import java.util.List;
import java.util.Map;

/**
 * @author ChenDang
 * @date 2019/12/21 0021
 */
public interface MallPointsRecordDao extends BaseDao<MallPointsRecord>{

    int save(MallPointsRecord record);

    List<MallPointsListVo> queryAllList(Map<String, Object> map);

    int queryAllTotal(Map<String, Object> map);
}
