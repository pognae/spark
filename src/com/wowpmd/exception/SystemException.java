/*
 * @(#)SystemException.java 1.0 2016/04/12
 * 
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.exception;

/**
 * 시스템 오류 클래스이다.
 * 
 * @author 이동엽
 * @version 1.0 2016/04/12
 */
public class SystemException extends BaseException {
    /**
     * 시리얼 버전 아이디
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 메시지를 인자로 가지는 생성자이다.
     * 
     * @param message 메시지
     */
    public SystemException(String message) {
        super(message);
    }
    
    /**
     * 코드와 메시지를 인자로 가지는 생성자이다.
     * 
     * @param code 코드
     * @param message 메시지
     */
    public SystemException(String code, String message) {
        super(code, message);
    }
}