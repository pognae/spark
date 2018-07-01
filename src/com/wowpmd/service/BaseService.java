/*
 * @(#)BaseService.java 1.0 2016/04/12
 * 
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.service;

import com.wowpmd.component.BaseComponent;
import com.wowpmd.vo.MessagesVO;
import com.wowpmd.vo.ResultVO;

/**
 * 기본 서비스 클래스이다.
 * 
 * @author 이동엽
 * @version 1.0 2016/04/12
 */
public class BaseService extends BaseComponent {
    /**
     * 디폴트 생성자이다.
     */
    protected BaseService() {
        super();
    }
    
    /**
     * 처리결과를 반환한다.
     * 
     * @param message 메시지
     * @return 처리결과
     */
    protected ResultVO success(String message) {
        MessagesVO messages = new MessagesVO();
        
        messages.put(MessagesVO.MESSAGE, message);
        
        return success(messages);
    }
    
    /**
     * 처리결과를 반환한다.
     * 
     * @param messages 메시지
     * @return 처리결과
     */
    protected ResultVO success(MessagesVO messages) {
        ResultVO result = new ResultVO();
        
        result.put(ResultVO.SUCCESS,  true);
        result.put(ResultVO.MESSAGES, messages);
        
        return result;
    }
    
    /**
     * 처리결과를 반환한다.
     * 
     * @param message 메시지
     * @return 처리결과
     */
    protected ResultVO failure(String message) {
        MessagesVO messages = new MessagesVO();
        
        messages.put(MessagesVO.MESSAGE, message);
        
        return failure(messages);
    }
    
    /**
     * 처리결과를 반환한다.
     * 
     * @param messages 메시지
     * @return 처리결과
     */
    protected ResultVO failure(MessagesVO messages) {
        ResultVO result = new ResultVO();
        
        result.put(ResultVO.SUCCESS,  false);
        result.put(ResultVO.MESSAGES, messages);
        
        return result;
    }
}