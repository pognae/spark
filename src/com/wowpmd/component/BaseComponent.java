/*
 * @(#)BaseComponent.java 1.0 2016/04/12
 * 
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 기본 컴포넌트 클래스이다.
 * 
 * @author 이동엽
 * @version 1.0 2016/04/12
 */
public class BaseComponent {
    /**
     * 로그
     */
    protected Log log;
    
    /**
     * 디폴트 생성자이다.
     */
    protected BaseComponent() {
        // 로그를 생성한다.
        log = LogFactory.getLog(getClass());
    }
    
    /**
     * 로그를 기록한다.
     * 
     * @param message 메시지
     */
    protected void trace(Object message) {
        if (log.isTraceEnabled()) {
            log.trace(message);
        }
    }
    
    /**
     * 로그를 기록한다.
     * 
     * @param message 메시지
     * @param throwable 발생오류
     */
    protected void trace(Object message, Throwable throwable) {
        if (log.isTraceEnabled()) {
            log.trace(message, throwable);
        }
    }
    
    /**
     * 로그를 기록한다.
     * 
     * @param message 메시지
     */
    protected void debug(Object message) {
        if (log.isDebugEnabled()) {
            log.debug(message);
        }
    }
    
    /**
     * 로그를 기록한다.
     * 
     * @param message 메시지
     * @param throwable 발생오류
     */
    protected void debug(Object message, Throwable throwable) {
        if (log.isDebugEnabled()) {
            log.debug(message, throwable);
        }
    }
    
    /**
     * 로그를 기록한다.
     * 
     * @param message 메시지
     */
    protected void info(Object message) {
        if (log.isInfoEnabled()) {
            log.info(message);
        }
    }
    
    /**
     * 로그를 기록한다.
     * 
     * @param message 메시지
     * @param throwable 발생오류
     */
    protected void info(Object message, Throwable throwable) {
        if (log.isInfoEnabled()) {
            log.info(message, throwable);
        }
    }
    
    /**
     * 로그를 기록한다.
     * 
     * @param message 메시지
     */
    protected void warn(Object message) {
        if (log.isWarnEnabled()) {
            log.warn(message);
        }
    }
    
    /**
     * 로그를 기록한다.
     * 
     * @param message 메시지
     * @param throwable 발생오류
     */
    protected void warn(Object message, Throwable throwable) {
        if (log.isWarnEnabled()) {
            log.warn(message, throwable);
        }
    }
    
    /**
     * 로그를 기록한다.
     * 
     * @param message 메시지
     */
    protected void error(Object message) {
        if (log.isErrorEnabled()) {
            log.error(message);
        }
    }
    
    /**
     * 로그를 기록한다.
     * 
     * @param message 메시지
     * @param throwable 발생오류
     */
    protected void error(Object message, Throwable throwable) {
        if (log.isErrorEnabled()) {
            log.error(message, throwable);
        }
    }
    
    /**
     * 로그를 기록한다.
     * 
     * @param message 메시지
     */
    protected void fatal(Object message) {
        if (log.isFatalEnabled()) {
            log.fatal(message);
        }
    }
    
    /**
     * 로그를 기록한다.
     * 
     * @param message 메시지
     * @param throwable 발생오류
     */
    protected void fatal(Object message, Throwable throwable) {
        if (log.isFatalEnabled()) {
            log.fatal(message, throwable);
        }
    }
}