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
public  class HouseDAOImpl extends BaseDAO implements HouseDAO {

	@Override
	public int insertHouse(ParamsVO params) {
		return update("house.insertHouse", params);
	}

	@Override
	public List<Object> rsd1020Search(ParamsVO params) {
		return search("house.rsd1020Search", params);
	}

	@Override
	public int updateHouse(ParamsVO params) {
		return update("house.updateHouse", params);
	}

}