/*
 * @(#)SampleDAOImpl.java 1.0 2016/04/12
 *
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.acc.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.common.model.RecordVO;
import com.wowpmd.dao.BaseDAO;
import com.wowpmd.vo.PagingVO;

@Repository
public  class AccAccDAOImpl extends BaseDAO implements AccAccDAO {


    /**PagingVO
     * acco4040 조회
     *
     * @param params 파라메터
     * @return 조회결과 List
     */

    public PagingVO acco4040Search(ParamsVO params) {
        return search("accAcc.acco4040Search", params, params.getPage(), params.getRows());
    }

    /**PagingVO
     * acco4010 조회
     *
     * @param params 파라메터
     * @return 조회결과 List
     */
    public List<Object> acco4010Search(ParamsVO params){
        return search("accAcc.acco4010Search", params);
    }

    public List<Object> getAssetList(ParamsVO params){
        return search("accAcc.getAssetList", params);
    }

    public List<Object> getBondList(ParamsVO params){
        return search("accAcc.getBondList", params);
    }

    public List<Object> getCapitalList(ParamsVO params){
        return search("accAcc.getCapitalList", params);
    }

	@Override
	public int insertSlip(Map<String, Object> params) {
		return insert("accAcc.insertSlip", params);
	}

	@Override
	public List<Object> getAcctCode(ParamsVO params) {
		return search("accAcc.getAcctCode", params);
	}

	@Override
	public PagingVO getAcctCodeList(ParamsVO params) {
		return search("accAcc.getAcctCodeList", params, params.getPage(), params.getRows());
	}

	@Override
	public List<Object> getSlipList(ParamsVO params) {
		return search("accAcc.getSlipList", params);
	}

	@Override
	public int updateSlipDel(Map<String, Object> params) {
		return update("accAcc.updateSlipDel", params);
	}

	@Override
	public List<Object> getSlipDlnStatList(ParamsVO params) {
		return search("accAcc.getSlipDlnStatList", params);
	}

	@Override
	public List<Object> getSlipByRegDtmList(Map<String, Object> params) {
		return search("accAcc.getSlipByRegDtmList", params);
	}

	@Override
	public String getAcountByBal(Map<String, Object> params) {
		return (String)select("accAcc.getAcountByBal", params);
	}

	@Override
	public Map<String, String> getAcountMst(Map<String, Object> params) {
		return (Map<String, String>)select("accAcc.getAcountMst", params);

	}

	@Override
	public Map<String, String> getTotalAcountBal(Map<String, Object> params) {
		return (Map<String, String>)select("accAcc.getTotalAcountBal", params);
	}

	@Override
	public int updateTotalAcountMst(Map<String, Object> params) {
		return update("accAcc.updateTotalAcountMst", params);
	}

	@Override
	public int insertTotalAcountMst(Map<String, Object> params) {
		return insert("accAcc.insertTotalAcountMst", params);
	}

	@Override
	public int updateSlipMstClosed(Map<String, Object> params) {
		return update("accAcc.updateSlipMstClosed", params);
	}
}