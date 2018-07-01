/*
 * @(#)ComCodeDAO.java 1.0 2016/04/12
 *
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.common.dao;

import java.util.List;
import java.util.Map;

import com.wowpmd.common.Code;
import com.wowpmd.common.model.ParamsVO;

public interface SystemDAO {

	@SuppressWarnings("rawtypes")
	public List getMenuList(ParamsVO params);
}