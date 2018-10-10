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
import com.wowpmd.dao.HouseDAO;
import com.wowpmd.vo.ResultVO;

@Service
public class HouseServiceImpl extends BaseService implements HouseService {

    @Autowired
    private HouseDAO houseDAO;

	@Override
	public List<Object> rsd1020Search(ParamsVO params) {
		return houseDAO.rsd1020Search(params);
	}

	@Override
	public ResultVO insertHouse(ParamsVO params) {
		houseDAO.insertHouse(params);

		return success("등록되었습니다.");
	}

	@Override
	public ResultVO updateHouse(ParamsVO params) {
		houseDAO.updateHouse(params);

		return success("수정되었습니다.");
	}


}