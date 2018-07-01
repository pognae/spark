/*
 * @(#)SampleDAOImpl.java 1.0 2016/04/12
 *
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.common.model.RecordVO;
import com.wowpmd.dao.BaseDAO;
import com.wowpmd.vo.PagingVO;

@Repository
public  class BasicDAOImpl extends BaseDAO implements BasicDAO {

	@Override
	public List<Object> code1010Search(ParamsVO params) {
		return search("basic.code1010Search", params);
	}


}