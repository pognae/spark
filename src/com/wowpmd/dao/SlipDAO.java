/*
 * @(#)SampleDAO.java 1.0 2016/04/12
 *
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.dao;

import java.util.List;
import java.util.Map;

import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.common.model.RecordVO;
import com.wowpmd.vo.PagingVO;
import com.wowpmd.vo.ResultVO;

/**
 * AccAccServiceImpl
 *
 * @version 1.0 2016/04/29
 */
public interface SlipDAO {

	public List<Object> acntLclasList(ParamsVO params);

	public List<Object> slp1010Search(ParamsVO params);

	public int insertSlip(ParamsVO params);

}