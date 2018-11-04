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

public interface SlipService {

	public List<Object> acntLclasList(ParamsVO params);

	public List<Object> slp1010Search(ParamsVO params);
	
	public List<Object> slp1020Search(ParamsVO params);

	public ResultVO insertSlip(ParamsVO params);

}