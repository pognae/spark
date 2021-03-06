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
public interface BasicDAO {

	public List<Object> bsc1010Search(ParamsVO params);

	public List<Object> bsc1020Search(ParamsVO params);

	public List<Object> bsc1030Search(ParamsVO params);

	public Object bsc1040Search(ParamsVO params);

	public Object bsc1040UnitSearch(ParamsVO params);

	public Object bsc1050Search(ParamsVO params);

	public int insertCustom(ParamsVO params);

	public int insertCommonCode(ParamsVO params);

	public int insertAccount(ParamsVO params);

	public int insertAccountCost(ParamsVO params);

	public int insertAccountAmount(ParamsVO params);
	
	public int insertChargeCost(ParamsVO params);

}