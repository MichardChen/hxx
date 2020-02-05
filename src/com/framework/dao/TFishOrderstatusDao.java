package com.framework.dao;

import com.framework.entity.TFishOrderstatusEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2020-01-10 08:15:09
 */
public interface TFishOrderstatusDao extends BaseDao<TFishOrderstatusEntity> {

    public TFishOrderstatusEntity queryByOrderNo(@Param("orderNo") String orderNo, @Param("status") String status);
}
