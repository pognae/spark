/*
 * @(#)MessagesVO.java 1.0 2016/04/12
 * 
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.vo;

/**
 * 메시지 VO 클래스이다.
 * 
 * @author 이동엽
 * @version 1.0 2016/04/12
 */
public class MessagesVO extends BaseVO {
    /**
     * 시리얼 버전 아이디
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 메시지
     */
    public static final String MESSAGE = "message";
    
    /**
     * 디폴트 생성자이다.
     */
    public MessagesVO() {
        super();
    }
    
    /**
     * 메시지를 반환한다.
     * 
     * @return 메시지
     */
    public String getMessage() {
        return getMessage(MESSAGE, "");
    }
    
    /**
     * 메시지를 반환한다.
     * 
     * @param key 키
     * @return 메시지
     */
    public String getMessage(String key) {
        return getMessage(key, "");
    }
    
    /**
     * 메시지를 반환한다.
     * 
     * @param key 키
     * @param defaultValue 디폴트 메시지
     * @return 메시지
     */
    public String getMessage(String key, String defaultValue) {
        return getString(key, defaultValue);
    }
}