/*
 * @(#)SampleServiceImpl.java 1.0 2016/04/12
 *
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.dao.BasicDAO;
import com.wowpmd.vo.PagingVO;

/**
 * AccAccServiceImpl
 *
 * @author 채선영
 * @version 1.0 2016/04/29
 */

@Service
public class BasicServiceImpl extends BaseService implements BasicService {

    @Autowired
    private BasicDAO basicDAO;

	@Override
	public List<Object> bsc1010Search(ParamsVO params) {
		return basicDAO.bsc1010Search(params);
	}

}