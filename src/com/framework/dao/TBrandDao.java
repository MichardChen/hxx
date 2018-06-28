package com.framework.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.framework.entity.TBrandEntity;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-05-16 23:00:02
 */
public interface TBrandDao extends BaseDao<TBrandEntity> {
	
	List<TBrandEntity> queryAllList(@Param("flg")Integer flg);
	
	List<TBrandEntity> queryShowBrandList(@Param("showFlg")Integer showFlg);
	
	List<TBrandEntity> queryImportBrandList(@Param("importFlg")Integer importFlg);
}
