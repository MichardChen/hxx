package com.framework.dao;

import com.framework.entity.TFishBuyEntity;
import com.framework.entity.TFishSupplyEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2020-01-05 12:40:44
 */
public interface TFishBuyDao extends BaseDao<TFishBuyEntity> {

    public List<TFishBuyEntity> queryBuyList(Map<String, Object> map);
}
