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

/**
 * 공통코드를 검색하는 DAO 인터페이스이다.
 * 
 * @author 정민승
 * @version 1.0 2016/04/12
 */
public interface ComCodeDAO {
    /**
     * 공통코드를 검색한다.
     * 
     * @param params 파라메터
     * @return 검색결과
     */
    public List<Object> getComCode(ParamsVO params);
    
    public List<Object> getComCodeList(Map<String, Object> paramMap);

	public List<Object> getTopMeneList();

	public Object getTopMeneList1(ParamsVO params);

	public int logMenuInsert(ParamsVO params);
	
	List<Code> getCodeListForCodeHandler();
}