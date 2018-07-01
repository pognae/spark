/*
 * @(#)RequestAttribute.java 1.0 2016/04/12
 * 
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.constants;

/**
 * HTTP 요청 속성 상수 클래스이다.
 * 
 * @author 이동엽
 * @version 1.0 2016/04/12
 */
public class RequestAttribute extends BaseConstants {
    /**
     * 시리얼 버전 아이디
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 액션
     */
    public static final String ACTION = "action";
    
    /**
     * 메시지
     */
    public static final String MESSAGE = "message";
    
    /**
     * 파라메터
     */
    public static final String PARAMETERS = "parameters";
    
    /**
     * 디폴트 생성자이다.
     */
    public RequestAttribute() {
        super();
    }
}