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

public interface HouseDAO {

	public List<Object> rsd1020Search(ParamsVO params);

	public int insertHouse(ParamsVO params);

	public int updateHouse(ParamsVO params);

}