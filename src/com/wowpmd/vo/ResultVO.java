/*
 * @(#)ResultVO.java 1.0 2016/04/12
 * 
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.vo;

/**
 * 결과 VO 클래스이다.
 * 
 * @author 이동엽
 * @version 1.0 2016/04/12
 */
public class ResultVO extends BaseVO {
    /**
     * 시리얼 버전 아이디
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 처리결과
     */
    public static final String SUCCESS = "success";
    
    /**
     * 메시지
     */
    public static final String MESSAGES = "messages";
    
    /**
     * 디폴트 생성자이다.
     */
    public ResultVO() {
        super();
    }
    
    /**
     * 처리결과를 반환한다.
     * 
     * @return 처리결과
     */
    public boolean getSuccess() {
        return getBoolean(SUCCESS);
    }
    
    /**
     * 메시지를 반환한다.
     * 
     * @return 메시지
     */
    public MessagesVO getMessages() {
        return (MessagesVO) get(MESSAGES);
    }
}