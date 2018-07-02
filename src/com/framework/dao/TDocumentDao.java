package com.framework.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.framework.entity.TDocumentEntity;

/**
 * 
 * 
 * @author R & D
 * @email 908350381@qq.com
 * @date 2018-06-12 17:37:37
 */
public interface TDocumentDao extends BaseDao<TDocumentEntity> {
	
	public List<TDocumentEntity> queryDocumentByTypeCd(Object[] code);
	
	public int queryDocumentCountByTypeCd(@Param("typeCd")String typeCd);
	
	public int queryDocumentCount(@Param("typeCd")String typeCd,@Param("documentId")int documentId);
}
