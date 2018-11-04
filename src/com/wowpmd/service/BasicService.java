/*
 * @(#)SampleService.java 1.0 2016/04/12
 *
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.service;

import java.util.List;

import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.vo.ResultVO;

/**
 * BasicService
 *
 * @version 1.0 2016/04/29
 */

public interface BasicService {

	public List<Object> bsc1010Search(ParamsVO params);

	public List<Object> bsc1020Search(ParamsVO params);

	public List<Object> bsc1030Search(ParamsVO params);

	public Object bsc1040Search(ParamsVO params);

	public Object bsc1040UnitSearch(ParamsVO params);

	public Object bsc1050Search(ParamsVO params);

	public ResultVO insertCustom(ParamsVO params);

	public ResultVO insertAccount(ParamsVO params);

	public ResultVO insertAccountCost(ParamsVO params);

	public ResultVO insertAccountAmount(ParamsVO params);
	
	public ResultVO insertChargeCost(ParamsVO params);

	public ResultVO insertCommonCode(ParamsVO params);
}