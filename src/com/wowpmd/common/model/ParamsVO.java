/*
 * @(#)ParamsVO.java 1.0 2016/04/12
 * 
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.common.model;

import org.springframework.web.multipart.MultipartFile;

import com.wowpmd.vo.BaseVO;

/**
 * 파라메터 VO 클래스이다.
 * 
 * @author 이동엽
 * @version 1.0 2016/04/12
 */
public class ParamsVO extends BaseVO {
    /**
     * 시리얼 버전 아이디
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 디폴트 페이지 번호
     */
    public static final int DEFAULT_PAGE = 1;
    
    /**
     * 디폴트 페이지 크기
     */
    public static final int DEFAULT_ROWS = 10;
    
    /**
     * 페이지 번호
     */
    public static final String PAGE = "page";
    
    /**
     * 페이지 크기
     */
    public static final String ROWS = "rows";
    
    /**
     * 페이징 모드
     */
    public static final String PAGING = "paging";
    
    /**
     * 시작 행번호
     */
    public static final String START = "start";
    
    /**
     * 종료 행번호
     */
    public static final String END = "end";
    
    
    /**
     * 디폴트 생성자이다.
     */
    public ParamsVO() {
        super();
    }
    
    /**
     * 값을 추가한다.
     * 
     * @param key 키
     * @param value 값
     * @return 기존 값
     */
    public Object add(Object key, Object value) {
        if (value instanceof Object[]) {
            Object[] values = (Object[]) value;
            
            if (values.length == 1) {
                return put(key, values[0]);
            }
        }
        
        return put(key, value);
    }
    
    /**
     * 페이지 번호를 반환한다.
     * 
     * @return 페이지 번호
     */
    public int getPage() {
        return getInt(PAGE, DEFAULT_PAGE);
    }
    
    /**
     * 페이지 크기를 반환한다.
     * 
     * @return 페이지 크기
     */
    public int getRows() {
        return getInt(ROWS, DEFAULT_ROWS);
    }
    
    /**
     * 파일을 반환한다.
     * 
     * @param key 키
     * @return 파일
     */
    public MultipartFile getFile(String key) {
        return (MultipartFile) get(key);
    }
    
    /**
     * 파일 배열을 반환한다.
     * 
     * @param key 키
     * @return 파일 배열
     */
    public MultipartFile[] getFileArray(String key) {
        Object value = get(key);
        
        if (value instanceof MultipartFile[]) {
            return (MultipartFile[]) value;
        }
        
        if (value instanceof MultipartFile) {
            return new MultipartFile[] { (MultipartFile) value };
        }
        
        return new MultipartFile[0];
    }
}