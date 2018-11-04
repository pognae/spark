/*
 * @(#)SampleDAOImpl.java 1.0 2016/04/12
 *
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.vo.ResultVO;

@Repository
public  class BasicDAOImpl extends BaseDAO implements BasicDAO {

	@Override
	public List<Object> bsc1010Search(ParamsVO params) {
		return search("basic.bsc1010Search", params);
	}

	@Override
	public int insertCustom(ParamsVO params) {
		return update("basic.insertCustom", params);
	}

	@Override
	public List<Object> bsc1020Search(ParamsVO params) {
		return search("basic.bsc1020Search", params);
	}

	@Override
	public List<Object> bsc1030Search(ParamsVO params) {
		return search("basic.bsc1030Search", params);
	}

	@Override
	public int insertCommonCode(ParamsVO params) {
		return update("basic.insertCommonCode", params);
	}

	@Override
	public int insertAccount(ParamsVO params) {
		return update("basic.insertAccount", params);
	}

	@Override
	public Object bsc1040Search(ParamsVO params) {
		return select("basic.bsc1040Search", params);
	}

	@Override
	public int insertAccountCost(ParamsVO params) {
		return insert("basic.insertAccountCost", params);
	}

	@Override
	public Object bsc1050Search(ParamsVO params) {
		return select("basic.bsc1050Search", params);
	}

	@Override
	public int insertAccountAmount(ParamsVO params) {

		int smAmount			  = Integer.parseInt(((String) params.get("smAmount")).trim().replaceAll(",", ""));
		int gnrlManageCst		  = Integer.parseInt(((String) params.get("gnrlManageCst")).trim().replaceAll(",", ""));
		int cmptProcessCst		  = Integer.parseInt(((String) params.get("cmptProcessCst")).trim().replaceAll(",", ""));
		int mvnManReprsntMtgCst	  = Integer.parseInt(((String) params.get("mvnManReprsntMtgCst")).trim().replaceAll(",", ""));
		int clnCst			 	  = Integer.parseInt(((String) params.get("clnCst")).trim().replaceAll(",", ""));
		int fgtManageVrscCst	  = Integer.parseInt(((String) params.get("fgtManageVrscCst")).trim().replaceAll(",", ""));
		int elvtrMntnceCst		  = Integer.parseInt(((String) params.get("elvtrMntnceCst")).trim().replaceAll(",", ""));
		int elctySafeVrscCst	  = Integer.parseInt(((String) params.get("elctySafeVrscCst")).trim().replaceAll(",", ""));
		int copertnElctyCst		  = Integer.parseInt(((String) params.get("copertnElctyCst")).trim().replaceAll(",", ""));
		int copertnCptlCst		  = Integer.parseInt(((String) params.get("copertnCptlCst")).trim().replaceAll(",", ""));
		int fireIrncfCst		  = Integer.parseInt(((String) params.get("fireIrncfCst")).trim().replaceAll(",", ""));
		int lngtrRpairsRsvmneyCst = Integer.parseInt(((String) params.get("lngtrRpairsRsvmneyCst")).trim().replaceAll(",", ""));
		int rsvfndCst			  = smAmount - (gnrlManageCst + cmptProcessCst + mvnManReprsntMtgCst + clnCst + fgtManageVrscCst + elvtrMntnceCst + elctySafeVrscCst + copertnElctyCst + copertnCptlCst + fireIrncfCst + lngtrRpairsRsvmneyCst);

		params.add("smAmount",			    smAmount             );
		params.add("gnrlManageCst",         gnrlManageCst        );
		params.add("cmptProcessCst",        cmptProcessCst       );
		params.add("mvnManReprsntMtgCst",   mvnManReprsntMtgCst  );
		params.add("clnCst",                clnCst               );
		params.add("fgtManageVrscCst",      fgtManageVrscCst     );
		params.add("elvtrMntnceCst",        elvtrMntnceCst       );
		params.add("elctySafeVrscCst",      elctySafeVrscCst     );
		params.add("copertnElctyCst",       copertnElctyCst      );
		params.add("copertnCptlCst",        copertnCptlCst       );
		params.add("fireIrncfCst",          fireIrncfCst         );
		params.add("lngtrRpairsRsvmneyCst", lngtrRpairsRsvmneyCst);
		params.add("rsvfndCst",             rsvfndCst            );

		insert("basic.insertAccountAmount", params);
		insert("basic.insertAccountCost", params);

		return 1;

	}

	@Override
	public Object bsc1040UnitSearch(ParamsVO params) {
		return select("basic.bsc1040UnitSearch", params);
	}

	@Override
	public int insertChargeCost(ParamsVO params) {
		return insert("basic.insertChargeCost", params);
	}


}