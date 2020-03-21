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
}
