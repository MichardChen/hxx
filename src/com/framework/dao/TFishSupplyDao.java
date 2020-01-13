package com.framework.dao;

import com.framework.entity.TFishSupplyEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2020-01-05 14:41:53
 */
public interface TFishSupplyDao extends BaseDao<TFishSupplyEntity> {

    public List<TFishSupplyEntity> querySupplyList(Map<String, Object> map);
}
