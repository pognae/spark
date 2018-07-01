/*
 * @(#)ComCodeServiceImpl.java 1.0 2016/04/12
 * 
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.common.service;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wowpmd.common.dao.ComCodeDAO;
import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.common.model.RecordVO;
import com.wowpmd.service.BaseService;

/**
 * 공통코드를 검색하는 서비스 클래스이다.
 * 
 * @author 정민승
 * @version 1.0 2016/04/12
 */
@Service
public class ComCodeServiceImpl extends BaseService implements ComCodeService {
    /**
     * 공통코드를 검색하는 DAO
     */
    @Autowired
    private ComCodeDAO comCodeDAO;
    
    /**
     * 공통코드를 검색한다.
     * 
     * @param params 파라메터
     * @return 검색결과
     */
    public List<Object> getComCode(ParamsVO params) {
        if (params.containsKey(INCLUDE_CODES)) {
            params.put(INCLUDE_CODES, params.getStringArray(INCLUDE_CODES));
        }
        
        if (params.containsKey(EXCLUDE_CODES)) {
            params.put(EXCLUDE_CODES, params.getStringArray(EXCLUDE_CODES));
        }
        
        // 공통코드를 검색한다.
        List<Object> data = comCodeDAO.getComCode(params);
        
        // 코드 이름을 포맷한다.
        formatCodeName(data, params);
        
        // 디폴트 코드를 추가한다.
        addDefaultCode(data, params);
        
        return data;
    }
    
    /**
     * 코드 이름을 포맷한다.
     * 
     * @param data 데이터
     * @param params 파라메터
     */
    private void formatCodeName(List<Object> data, ParamsVO params) {
        if (params.containsKey(FORMAT_PATTERN)) {
            String pattern = params.getString(FORMAT_PATTERN);
            
            Iterator<Object> iterator = data.iterator();
            
            while (iterator.hasNext()) {
                RecordVO code = (RecordVO) iterator.next();
                
                code.put(NAME, MessageFormat.format(pattern, code.getString(CODE), code.getString(NAME)));
            }
        }
    }
    
    /**
     * 디폴트 코드를 추가한다.
     * 
     * @param data 데이터
     * @param params 파라메터
     */
    private void addDefaultCode(List<Object> data, ParamsVO params) {
        if (params.containsKey(DEFAULT_CODE)) {
            RecordVO code = new RecordVO();
            
            if (params.containsKey(DEFAULT_NAME)) {
                code.put(CODE, params.getString(DEFAULT_CODE));
                code.put(NAME, params.getString(DEFAULT_NAME));
            }
            else {
                code.put(CODE, params.getString(DEFAULT_CODE));
                code.put(NAME, params.getString(DEFAULT_CODE));
            }
            
            data.add(0, code);
        }
    }
    
    public List<Object> getComCodeList(Map<String, Object> paramMap) {
        List<Object> list = comCodeDAO.getComCodeList(paramMap);
        
        return list;
    }
    
    public List<Object> getTopMenu(){
    	List<Object> list = comCodeDAO.getTopMeneList();
    	
    	return list;
    }
    
    public Object getTopMenu1(ParamsVO params){
        
    	Object data = comCodeDAO.getTopMeneList1(params);
        
        return data;
    }
    
    public String logMenuInsert(ParamsVO params){
    	comCodeDAO.logMenuInsert(params);
    	
    	return "insert";
    }
    
}