package com.framework.dao;

import com.framework.entity.MallPointsExchangeRecord;
import com.framework.vo.MallExchangeRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author ChenDang
 * @date 2019/12/21 0021
 */
public interface MallPointsExchangeRecordDao extends BaseDao<MallPointsExchangeRecord> {

    int save(MallPointsExchangeRecord record);

    List<MallExchangeRecordVo> queryAllList(Map<String, Object> map);

    int queryAllTotal(Map<String, Object> map);

    MallExchangeRecordVo queryVoObject(@Param("id") Long id);

    /**
     * 更新物流及收货人信息
     * @param record
     */
    void updateReceiveInfo(MallPointsExchangeRecord record);
}
