package com.framework.dao;

import com.framework.entity.MallProduct;
import org.apache.ibatis.annotations.Param;

/**
 * @author ChenDang
 * @date 2019/12/21 0021
 */
public interface MallProductDao extends BaseDao<MallProduct>{

    /**
     * 更新数量
     * @param productId
     * @param quality
     * @return
     */
    int updateQuality(@Param("productId")int productId,@Param("quality")int quality);
}
