package com.framework.dao;

import com.framework.entity.TFishOrderEvaluationEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author R & D
 * @email adang369@126.com
 * @date 2020-03-15 21:28:02
 */
public interface TFishOrderEvaluationDao extends BaseDao<TFishOrderEvaluationEntity> {

    List<TFishOrderEvaluationEntity> queryOrderEvaluateList(Map<String,Object> map);

    /**
     * 根据对象ID数组审核通过对象操作
     */
    int auditPass(Object[] id);

    /**
     * 根据对象ID数组审核不过对象操作
     */
    int auditFail(Object[] id);
}
