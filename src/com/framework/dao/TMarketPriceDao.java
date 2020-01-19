package com.framework.dao;

import com.framework.entity.TMarketPriceEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2020-01-17 17:10:04
 */
public interface TMarketPriceDao extends BaseDao<TMarketPriceEntity> {

    int updateFlgBatch(@Param("flg") int flg,@Param("updateTime") Date updateTime,@Param("updateBy") int updateBy,@Param("id") Object[] id);

    int updateFlg(@Param("flg") int flg,@Param("updateTime") Date updateTime,@Param("updateBy") int updateBy,@Param("id") int id);
}
