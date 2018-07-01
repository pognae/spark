/*
 * @(#)ModelAttribute.java 1.0 2016/04/12
 * 
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.constants;

/**
 * 모델 속성 상수 클래스이다.
 * 
 * @author 이동엽
 * @version 1.0 2016/04/12
 */
public class ModelAttribute extends BaseConstants {
    /**
     * 시리얼 버전 아이디
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 데이터
     */
    public static final String DATA = "data";

    /**
     * 페이지 번호
     */
    public static final String PAGE = "page";
    
    /**
     * 페이지 크기
     */
    public static final String ROWS = "rows";
    
    /**
     * 검색 카운트
     */
    public static final String COUNT = "count";
    
    /**
     * 전체 카운트
     */
    public static final String TOTAL = "total";
    
    /**
     * 전체 페이지
     */
    public static final String PAGES = "pages";
    
    /**
     * 완료
     */
    public static final String SUCCESS = "success";
    
    /**
     * 오류
     */
    public static final String ERROR = "error";
    
    /**
     * 디폴트 생성자이다.
     */
    public ModelAttribute() {
        super();
    }
}