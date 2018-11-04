/*
 * @(#)SampleServiceImpl.java 1.0 2016/04/12
 *
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.dao.BasicDAO;
import com.wowpmd.vo.ResultVO;

@Service
public class BasicServiceImpl extends BaseService implements BasicService {

    @Autowired
    private BasicDAO basicDAO;

	@Override
	public List<Object> bsc1010Search(ParamsVO params) {
		return basicDAO.bsc1010Search(params);
	}

	@Override
	@Transactional
	public ResultVO insertCustom(ParamsVO params) {
		basicDAO.insertCustom(params);

		return success("등록되었습니다.");
	}

	@Override
	public List<Object> bsc1020Search(ParamsVO params) {
		return basicDAO.bsc1020Search(params);
	}

	@Override
	public List<Object> bsc1030Search(ParamsVO params) {
		return basicDAO.bsc1030Search(params);
	}

	@Override
	public ResultVO insertCommonCode(ParamsVO params) {

		params.add("apclStrDt", params.get("apclStrDt").toString().replaceAll("-",  ""));
		params.add("apclEndDt", params.get("apclEndDt").toString().replaceAll("-",  ""));

		basicDAO.insertCommonCode(params);

		return success("등록되었습니다.");
	}

	@Override
	public ResultVO insertAccount(ParamsVO params) {
		basicDAO.insertAccount(params);

		return success("등록되었습니다.");
	}

	@Override
	public Object bsc1040Search(ParamsVO params) {
		return basicDAO.bsc1040Search(params);
	}

	@Override
	public ResultVO insertAccountCost(ParamsVO params) {
		basicDAO.insertAccountCost(params);

		return success("등록되었습니다.");
	}

	@Override
	public Object bsc1050Search(ParamsVO params) {
		return basicDAO.bsc1050Search(params);
	}

	@Override
	@Transactional
	public ResultVO insertAccountAmount(ParamsVO params) {

		basicDAO.insertAccountAmount(params);

		return success("등록되었습니다.");
	}

	@Override
	public Object bsc1040UnitSearch(ParamsVO params) {
		return basicDAO.bsc1040UnitSearch(params);
	}

	@Override
	public ResultVO insertChargeCost(ParamsVO params) {
		basicDAO.insertChargeCost(params);

		return success("등록되었습니다.");
	}

}