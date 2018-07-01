/*
 * @(#)ComCodeService.java 1.0 2016/04/12
 * 
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.common.service;

import java.util.List;
import java.util.Map;

import com.wowpmd.common.model.ParamsVO;

/**
 * 공통코드를 검색하는 서비스 인터페이스이다.
 * 
 * @author 정민승
 * @version 1.0 2016/04/12
 */
public interface ComCodeService {
    /**
     * 코드
     */
    public static final String CODE = "codeValue";
    
    /**
     * 이름
     */
    public static final String NAME = "codeName";
    
    /**
     * 디폴트 코드
     */
    public static final String DEFAULT_CODE = "defaultCode";
    
    /**
     * 디폴트 이름
     */
    public static final String DEFAULT_NAME = "defaultName";
    
    /**
     * 포함 코드
     */
    public static final String INCLUDE_CODES = "includeCodes";
    
    /**
     * 제외 코드
     */
    public static final String EXCLUDE_CODES = "excludeCodes";
    
    /**
     * 포맷 패턴
     */
    public static final String FORMAT_PATTERN = "formatPattern";
    
    /**
     * 공통코드를 검색한다.
     * 
     * @param params 파라메터
     * @return 검색결과
     */
    public List<Object> getComCode(ParamsVO params);
    
    public List<Object> getComCodeList(Map<String, Object> paramMap);

	public List<Object> getTopMenu();
	
	public Object getTopMenu1(ParamsVO params);

	public String logMenuInsert(ParamsVO params);
}