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
public  class ResidentDAOImpl extends BaseDAO implements ResidentDAO {

	@Override
	public int insertResident(ParamsVO params) {
		return insert("resident.insertResident", params);
	}

	@Override
	public List<Object> rsd1010Search(ParamsVO params) {
		return search("resident.rsd1010Search", params);
	}

	@Override
	public List<Object> rsd1030Search(ParamsVO params) {
		return search("resident.rsd1030Search", params);
	}

	@Override
	public int insertVehicle(ParamsVO params) {
		return insert("resident.insertVehicle", params);
	}


}