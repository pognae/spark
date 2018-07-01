/*
 * @(#)SampleService.java 1.0 2016/04/12
 *
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.acc.service;

import java.util.List;
import java.util.Map;

import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.vo.PagingVO;

/**
 * AccAccServiceImpl
 *
 * @author 채선영
 * @version 1.0 2016/04/29
 */

public interface AccAccService {

	/**
     * loao4040 조회
     *
     * @param params 파라메터
     * @return 조회결과 List
     */
	/*public List<Object> loao0520Search(ParamsVO params);*/
	public PagingVO acco4040Search(ParamsVO params);


	/**
     * loao4010 조회
     *
     * @param params 파라메터
     * @return 조회결과 List
     */
	public List<Object> acco4010Search(ParamsVO params);

	public List<Object> getAssetList(ParamsVO params);

	public List<Object> getAcctCode(ParamsVO params);

	public List<Object> getSlipList(ParamsVO params);

	public PagingVO getAcctCodeList(ParamsVO params);

	public List<Object> getBondList(ParamsVO params);

	public List<Object> getCapitalList(ParamsVO params);

	public int insertSlip(Map<String, Object> params);

	public int updateSlipDel(Map<String, Object> params);

	public int updateClosed(Map<String, Object> params);

	public List<Object> getSlipDlnStatList(ParamsVO params);
}