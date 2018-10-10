/*
 * @(#)SampleServiceImpl.java 1.0 2016/04/12
 *
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.dao.SlipDAO;
import com.wowpmd.vo.ResultVO;

@Service
public class SlipServiceImpl extends BaseService implements SlipService {

    @Autowired
    private SlipDAO slipDAO;

	@Override
	public List<Object> acntLclasList(ParamsVO params) {
		return slipDAO.acntLclasList(params);
	}

	@Override
	public List<Object> slp1010Search(ParamsVO params) {

		if(params.get("dealDate") != null) {
			params.add("dealDate", StringUtils.replace(params.get("dealDate").toString(), "-", ""));
		} else if(params.get("today") != null) {
			params.add("dealDate", StringUtils.replace(params.get("today").toString(), "-", ""));
		}

		return slipDAO.slp1010Search(params);
	}

	@Override
	public ResultVO insertSlip(ParamsVO params) {

		params.add("dealDate", params.get("dealDate").toString().replaceAll("-", ""));
		params.add("suplyPrice", params.get("suplyPrice").toString().replaceAll(",", ""));
		params.add("splpcAm", params.get("splpcAm").toString().replaceAll(",", ""));
		params.add("vat", params.get("vat").toString().replaceAll(",", ""));

		slipDAO.insertSlip(params);

		return success("등록되었습니다.");
	}


}