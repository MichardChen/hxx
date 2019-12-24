package com.framework.dao;

import com.framework.entity.MallPointsRecord;

/**
 * @author ChenDang
 * @date 2019/12/21 0021
 */
public interface MallPointsRecordDao extends BaseDao<MallPointsRecord>{

    int save(MallPointsRecord record);
}
