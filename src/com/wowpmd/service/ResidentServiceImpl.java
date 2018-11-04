/*
 * @(#)SampleServiceImpl.java 1.0 2016/04/12
 *
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.dao.ResidentDAO;
import com.wowpmd.vo.ResultVO;

@Service
public class ResidentServiceImpl extends BaseService implements ResidentService {

    @Autowired
    private ResidentDAO residentDAO;

	@Override
	public ResultVO insertResident(ParamsVO params) {
		residentDAO.insertResident(params);

		return success("등록되었습니다.");
	}

	@Override
	public List<Object> rsd1010Search(ParamsVO params) {
		return residentDAO.rsd1010Search(params);
	}

	@Override
	public List<Object> rsd1030Search(ParamsVO params) {
		return residentDAO.rsd1010Search(params);
	}

	@Override
	public ResultVO insertVehicle(ParamsVO params) {
		residentDAO.insertResident(params);

		return success("등록되었습니다.");
	}


}