/*
 * @(#)SampleServiceImpl.java 1.0 2016/04/12
 *
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.acc.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wowpmd.acc.dao.AccAccDAO;
import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.common.model.RecordVO;
import com.wowpmd.service.BaseService;
import com.wowpmd.vo.PagingVO;

/**
 * AccAccServiceImpl
 *
 * @author 채선영
 * @version 1.0 2016/04/29
 */

@Service
public class AccAccServiceImpl extends BaseService implements AccAccService {
    /**
     * accAcc DAO
     */
    @Autowired
    private AccAccDAO accAccDAO;

    /**
     * acco4040 조회
     *
     * @param params 파라메터
     * @return 조회결과 List
     */

    public PagingVO acco4040Search(ParamsVO params) {
    	return accAccDAO.acco4040Search(params);
    }

    /**
     * acco4010 조회
     *
     * @param params 파라메터
     * @return 조회결과 List
     */

    public List<Object> acco4010Search(ParamsVO params){
    	return accAccDAO.acco4010Search(params);
    }

    public List<Object> getAssetList(ParamsVO params){
    	return accAccDAO.getAssetList(params);
    }

    public List<Object> getBondList(ParamsVO params){
    	return accAccDAO.getBondList(params);
    }

    public List<Object> getCapitalList(ParamsVO params){
    	return accAccDAO.getCapitalList(params);
    }

	@Override
	public int insertSlip(Map<String, Object> params) {
		return accAccDAO.insertSlip(params);
	}

	@Override
	public List<Object> getAcctCode(ParamsVO params) {
		return accAccDAO.getAcctCode(params);
	}

	@Override
	public PagingVO getAcctCodeList(ParamsVO params) {
		return accAccDAO.getAcctCodeList(params);
	}

	@Override
	public List<Object> getSlipList(ParamsVO params) {
		return accAccDAO.getSlipList(params);
	}

	@Override
	public int updateSlipDel(Map<String, Object> params) {
		return accAccDAO.updateSlipDel(params);
	}

	@Override
	public List<Object> getSlipDlnStatList(ParamsVO params) {
		return accAccDAO.getSlipDlnStatList(params);
	}

	@Override
	public int updateClosed(Map<String, Object> params) {
		int cnt = 0;
		BigDecimal zero = new BigDecimal("0");

		// 1. 전표 읽기
		List<Object> slipList = accAccDAO.getSlipByRegDtmList(params);

		// 2. 전거래의 원장 현금계정금액 조회
		String cashAcountCode = "111000";
		params.put("acctCode", cashAcountCode);

		BigDecimal cashAcount = this.init(accAccDAO.getAcountByBal(params));

		// 3. 전거래의 원장 손실계정금액 조회
		String lossAcountCode = "400000";
		params.put("acctCode", lossAcountCode);

		BigDecimal loassAcount = this.init(accAccDAO.getAcountByBal(params));

		// 4. 전거래의 원장 이익계정금액 조회
		String benefitAcountCode = "500000";
		params.put("acctCode", benefitAcountCode);

		BigDecimal benefitAcount = this.init(accAccDAO.getAcountByBal(params));

		for(Object slip : slipList) {
			//전거래의 원장 금액 조회(마감 마지막날 잔액 조회)
			Map<String, Object> param = (Map<String, Object>) slip;

			String slipIO = param.get("procIo").toString();
			String slipBalStr = param.get("bal").toString();
			String slipAcctCode = param.get("acctCode").toString();
			BigDecimal slipBal = this.init(slipBalStr);

			params.put("acctCode", slipAcctCode);
			String balStr = accAccDAO.getAcountByBal(params);
			BigDecimal bal = this.init(balStr);

			// slipms의 계정 정보 (차대 구분 등) 쿼리
			Map<String, String> acctMst = accAccDAO.getAcountMst(params);
			String acctPlid = acctMst.get("acctPlid");
			String acctDcid = acctMst.get("acctDcid");

			// 계정 잔액 확인 오늘날짜 있으면 업데이트
			Map<String, String> totalAcountBal = accAccDAO.getTotalAcountBal(params);
			BigDecimal tacctCd = this.init(totalAcountBal.get("tacctCd")==null?"":String.valueOf(totalAcountBal.get("tacctCd")));
			BigDecimal tacctBal = this.init(String.valueOf(totalAcountBal.get("tacctBal")));

			if(tacctCd.compareTo(zero) != 0) {
				if(StringUtils.equals(acctPlid, "1")) {
					tacctBal = tacctBal.add(slipBal);
				} else {
					if(StringUtils.equals(acctDcid, "D")) {
						if(StringUtils.equals(slipIO, "01") || StringUtils.equals(slipIO, "02")) {
							tacctBal = tacctBal.subtract(slipBal);
						} else if(StringUtils.equals(slipIO, "03") || StringUtils.equals(slipIO, "04")) {
							tacctBal = tacctBal.add(slipBal);
						}
					}
				}

				params.put("tacctBal", tacctBal);
				cnt = accAccDAO.updateTotalAcountMst(params);

				// 현금계정조회
				params.put("acctCode", cashAcountCode);
				totalAcountBal = null;
				totalAcountBal = accAccDAO.getTotalAcountBal(params);
				tacctCd = this.init(totalAcountBal.get("tacctCd")==null?"":String.valueOf(totalAcountBal.get("tacctCd")));
				tacctBal = this.init(String.valueOf(totalAcountBal.get("tacctBal")));

				if(tacctCd.compareTo(zero) != 0) {
					//오늘 날짜의 데이터가 있는경우 update (현금계정)
					if(StringUtils.equals(slipIO, "01") || StringUtils.equals(slipIO, "02")) {
						tacctBal = tacctBal.add(slipBal);
					} else if(StringUtils.equals(slipIO, "03") || StringUtils.equals(slipIO, "04")) {
						tacctBal = tacctBal.subtract(slipBal);
					}

					params.put("acctCode", cashAcountCode);
					params.put("tacctBal", tacctBal);
					cnt = accAccDAO.updateTotalAcountMst(params);
				} else {
					if(StringUtils.equals(slipIO, "01") || StringUtils.equals(slipIO, "02")) {
						tacctBal = cashAcount.add(slipBal);
					} else if(StringUtils.equals(slipIO, "03") || StringUtils.equals(slipIO, "04")) {
						tacctBal = cashAcount.subtract(slipBal);
					}

					params.put("acctCode", cashAcountCode);
					params.put("tacctBal", tacctBal);
					cnt = accAccDAO.insertTotalAcountMst(params);
				}

				// 손실/이익 부분 수정
				if(StringUtils.equals(StringUtils.substring(slipAcctCode, 0, 1), "4") || StringUtils.equals(StringUtils.substring(slipAcctCode, 0, 1), "5")) {
					if(StringUtils.equals(StringUtils.substring(slipAcctCode, 0, 1), "4")) {
						params.put("acctCode", lossAcountCode);
					} else if(StringUtils.equals(StringUtils.substring(slipAcctCode, 0, 1), "5")) {
						params.put("acctCode", benefitAcountCode);
					}

					totalAcountBal = accAccDAO.getTotalAcountBal(params);
					tacctCd = this.init(totalAcountBal.get("tacctCd")==null?"":String.valueOf(totalAcountBal.get("tacctCd")));
					tacctBal = this.init(String.valueOf(totalAcountBal.get("tacctBal")));

					if(tacctCd.compareTo(zero) != 0) {
						params.put("tacctBal", tacctBal);
						cnt = accAccDAO.updateTotalAcountMst(params);
					}
				}
			} else {
				if(StringUtils.equals(acctPlid, "1")) {
					tacctBal = bal.add(slipBal);
				} else {
					if(StringUtils.equals(acctDcid, "D")) {
						if(StringUtils.equals(slipIO, "01") || StringUtils.equals(slipIO, "02")) {
							tacctBal = bal.subtract(slipBal);
						} else if(StringUtils.equals(slipIO, "03") || StringUtils.equals(slipIO, "04")) {
							tacctBal = bal.add(slipBal);
						}
					} else {
						if(StringUtils.equals(slipIO, "01") || StringUtils.equals(slipIO, "02")) {
							tacctBal = bal.add(slipBal);
						} else if(StringUtils.equals(slipIO, "03") || StringUtils.equals(slipIO, "04")) {
							tacctBal = bal.subtract(slipBal);
						}
					}
				}

				// 오늘 날짜의 데이터가 없으면 INSERT (해당계정)
				params.put("tacctBal", tacctBal);
				cnt = accAccDAO.insertTotalAcountMst(params);

				params.put("acctCode", cashAcountCode);
				totalAcountBal = accAccDAO.getTotalAcountBal(params);
				tacctCd = this.init(totalAcountBal.get("tacctCd")==null?"":String.valueOf(totalAcountBal.get("tacctCd")));
				tacctBal = this.init(String.valueOf(totalAcountBal.get("tacctBal")));

				if(tacctCd.compareTo(zero) != 0) {
					// 오늘 날짜의 데이터가 있는경우 update (현금계정)
					if(StringUtils.equals(slipIO, "01") || StringUtils.equals(slipIO, "02")) {
						tacctBal = tacctBal.add(slipBal);
					} else if(StringUtils.equals(slipIO, "03") || StringUtils.equals(slipIO, "04")) {
						tacctBal = tacctBal.subtract(slipBal);
					}

					params.put("tacctBal", tacctBal);
					cnt = accAccDAO.updateTotalAcountMst(params);
				} else {
					if(StringUtils.equals(slipIO, "01") || StringUtils.equals(slipIO, "02")) {
						tacctBal = bal.add(slipBal);
					} else if(StringUtils.equals(slipIO, "03") || StringUtils.equals(slipIO, "04")) {
						tacctBal = bal.subtract(slipBal);
					}

					params.put("tacctBal", tacctBal);
					cnt = accAccDAO.insertTotalAcountMst(params);
				}
			}

			if(StringUtils.equals(StringUtils.substring(slipAcctCode, 0, 1), "4") || StringUtils.equals(StringUtils.substring(slipAcctCode, 0, 1), "5")) {
				if(StringUtils.equals(StringUtils.substring(slipAcctCode, 0, 1), "4")) {
					params.put("acctCode", lossAcountCode);
				} else if(StringUtils.equals(StringUtils.substring(slipAcctCode, 0, 1), "5")) {
					params.put("acctCode", benefitAcountCode);
				}

				totalAcountBal = accAccDAO.getTotalAcountBal(params);
				tacctCd = this.init(totalAcountBal.get("tacctCd")==null?"":String.valueOf(totalAcountBal.get("tacctCd")));
				tacctBal = this.init(String.valueOf(totalAcountBal.get("tacctBal")));

				if(tacctCd.compareTo(zero) != 0) {
					params.put("tacctBal", tacctBal);
					cnt = accAccDAO.updateTotalAcountMst(params);
				} else {
					if(StringUtils.equals(StringUtils.substring(slipAcctCode, 0, 1), "4")) {
						tacctBal = loassAcount.add(slipBal);
					} else if(StringUtils.equals(StringUtils.substring(slipAcctCode, 0, 1), "5")) {
						tacctBal = benefitAcount.add(slipBal);
					}

					params.put("tacctBal", tacctBal);
					cnt = accAccDAO.insertTotalAcountMst(params);
				}
			}

			params.put("slipNo", param.get("slipNo"));
			cnt = accAccDAO.updateSlipMstClosed(params);
		}

		return cnt;
	}

	private BigDecimal init(String param) {
		BigDecimal bd = new BigDecimal(0);
		if(!StringUtils.isEmpty(param)) {
			bd = new BigDecimal(param);
		}

		return bd;
	}
}