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

public interface HouseService {

	public List<Object> rsd1020Search(ParamsVO params);

	public ResultVO insertHouse(ParamsVO params);

	public ResultVO updateHouse(ParamsVO params);
}