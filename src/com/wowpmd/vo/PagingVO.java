/*
 * @(#)PagingVO.java 1.0 2016/04/12
 * 
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.vo;

/**
 * 페이징 VO 클래스이다.
 * 
 * @author 이동엽
 * @version 1.0 2016/04/12
 */
public class PagingVO extends BaseVO {
    /**
     * 시리얼 버전 아이디
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 페이지 번호
     */
    public static final String PAGE = "page";
    
    /**
     * 페이지 크기
     */
    public static final String ROWS = "rows";
    
    /**
     * 데이터
     */
    public static final String DATA = "data";
    
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
     * 디폴트 생성자이다.
     */
    public PagingVO() {
        super();
    }
    
    /**
     * 페이지 번호를 반환한다.
     * 
     * @return 페이지 번호
     */
    public int getPage() {
        return getInt(PAGE);
    }
    
    /**
     * 페이지 크기를 반환한다.
     * 
     * @return 페이지 크기
     */
    public int getRows() {
        return getInt(ROWS);
    }
    
    /**
     * 데이터를 반환한다.
     * 
     * @return 데이터
     */
    public Object getData() {
        return get(DATA);
    }
    
    /**
     * 검색 카운트를 반환한다.
     * 
     * @return 검색 카운트
     */
    public int getCount() {
        return getInt(COUNT);
    }
    
    /**
     * 전체 카운트를 반환한다.
     * 
     * @return 전체 카운트
     */
    public int getTotal() {
        return getInt(TOTAL);
    }
    
    /**
     * 전체 페이지를 반환한다.
     * 
     * @return 전체 페이지
     */
    public int getPages() {
        return getInt(PAGES);
    }
}