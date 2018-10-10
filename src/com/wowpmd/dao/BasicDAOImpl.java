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
import com.wowpmd.vo.ResultVO;

@Repository
public  class BasicDAOImpl extends BaseDAO implements BasicDAO {

	@Override
	public List<Object> bsc1010Search(ParamsVO params) {
		return search("basic.bsc1010Search", params);
	}

	@Override
	public int insertCustom(ParamsVO params) {
		return update("basic.insertCustom", params);
	}

	@Override
	public List<Object> bsc1020Search(ParamsVO params) {
		return search("basic.bsc1020Search", params);
	}

	@Override
	public List<Object> bsc1030Search(ParamsVO params) {
		return search("basic.bsc1030Search", params);
	}

	@Override
	public int insertCommonCode(ParamsVO params) {
		return update("basic.insertCommonCode", params);
	}

	@Override
	public int insertAccount(ParamsVO params) {
		return update("basic.insertAccount", params);
	}


}