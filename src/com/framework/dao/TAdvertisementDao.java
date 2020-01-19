package com.framework.dao;

import com.framework.entity.TAdvertisementEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2020-01-16 10:46:39
 */
public interface TAdvertisementDao extends BaseDao<TAdvertisementEntity> {

    List<TAdvertisementEntity> queryListByTypeCd(@Param("typeCd")String typeCd);
}
