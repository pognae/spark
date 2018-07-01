/*
 * @(#)ComCodeDAOImpl.java 1.0 2016/04/12
 *
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wowpmd.common.Code;
import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.dao.BaseDAO;

@Repository
public  class SystemDAOImpl extends BaseDAO implements SystemDAO {

	@SuppressWarnings("rawtypes")
	@Override
	public List getMenuList(ParamsVO params) {
		return sqlSession.selectList("system.getMenuList", params);
	}


}