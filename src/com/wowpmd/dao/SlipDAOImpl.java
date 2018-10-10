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
public  class SlipDAOImpl extends BaseDAO implements SlipDAO {

	@Override
	public List<Object> acntLclasList(ParamsVO params) {
		return search("slip.acntLclasList", params);
	}

	@Override
	public List<Object> slp1010Search(ParamsVO params) {
		return search("slip.slp1010Search", params);
	}

	@Override
	public int insertSlip(ParamsVO params) {
		return update("slip.insertSlip", params);
	}


}