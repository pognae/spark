/*
 * @(#)BaseVO.java 1.0 2016/04/12
 * 
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wowpmd.exception.SystemException;

/**
 * 기본 VO 클래스이다.
 * 
 * @author 이동엽
 * @version 1.0 2016/04/12
 */
public class BaseVO extends HashMap<Object, Object> {
    /**
     * 시리얼 버전 아이디
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 로그
     */
    protected Log log;
    
    /**
     * 디폴트 생성자이다.
     */
    protected BaseVO() {
        super();
        
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
    
    /**
     * 논리값을 반환한다.
     * 
     * @param key 키
     * @return 논리값
     */
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }
    
    /**
     * 논리값을 반환한다.
     * 
     * @param key 키
     * @param defaultValue 디폴트 논리값
     * @return 논리값
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        Object value = get(key);
        
        if (value instanceof Object) {
            return value.toString().equalsIgnoreCase("true");
        }
        
        return defaultValue;
    }
    
    /**
     * 논리값 배열을 반환한다.
     * 
     * @param key 키
     * @return 논리값 배열
     */
    public boolean[] getBooleanArray(String key) {
        Object value = get(key);
        
        if (value instanceof Object[]) {
            Object[] values = (Object[]) value;
            
            boolean[] booleanValues = new boolean[values.length];
            
            for (int i = 0; i < values.length; i++) {
                booleanValues[i] = values[i].toString().equalsIgnoreCase("true");
            }
            
            return booleanValues;
        }
        
        if (value instanceof Object) {
            return new boolean[] { value.toString().equalsIgnoreCase("true") };
        }
        
        return new boolean[0];
    }
    
    /**
     * 정수값을 반환한다.
     * 
     * @param key 키
     * @return 정수값
     */
    public int getInt(String key) {
        return getInt(key, 0);
    }
    
    /**
     * 정수값을 반환한다.
     * 
     * @param key 키
     * @param defaultValue 디폴트 정수값
     * @return 정수값
     */
    public int getInt(String key, int defaultValue) {
        Object value = get(key);
        
        if (value instanceof Object) {
            return Double.valueOf(value.toString().replaceAll("[^\\-0-9\\.]", "")).intValue();
        }
        
        return defaultValue;
    }
    
    /**
     * 정수값 배열을 반환한다.
     * 
     * @param key 키
     * @return 정수값 배열
     */
    public int[] getIntArray(String key) {
        Object value = get(key);
        
        if (value instanceof Object[]) {
            Object[] values = (Object[]) value;
            
            int[] intValues = new int[values.length];
            
            for (int i = 0; i < values.length; i++) {
                intValues[i] = Double.valueOf(values[i].toString().replaceAll("[^\\-0-9\\.]", "")).intValue();
            }
            
            return intValues;
        }
        
        if (value instanceof Object) {
            return new int[] { Double.valueOf(value.toString().replaceAll("[^\\-0-9\\.]", "")).intValue() };
        }
        
        return new int[0];
    }
    
    /**
     * 정수값을 반환한다.
     * 
     * @param key 키
     * @return 정수값
     */
    public long getLong(String key) {
        return getLong(key, 0L);
    }
    
    /**
     * 정수값을 반환한다.
     * 
     * @param key 키
     * @param defaultValue 디폴트 정수값
     * @return 정수값
     */
    public long getLong(String key, long defaultValue) {
        Object value = get(key);
        
        if (value instanceof Object) {
            return Double.valueOf(value.toString().replaceAll("[^\\-0-9\\.]", "")).longValue();
        }
        
        return defaultValue;
    }
    
    /**
     * 정수값 배열을 반환한다.
     * 
     * @param key 키
     * @return 정수값 배열
     */
    public long[] getLongArray(String key) {
        Object value = get(key);
        
        if (value instanceof Object[]) {
            Object[] values = (Object[]) value;
            
            long[] longValues = new long[values.length];
            
            for (int i = 0; i < values.length; i++) {
                longValues[i] = Double.valueOf(values[i].toString().replaceAll("[^\\-0-9\\.]", "")).longValue();
            }
            
            return longValues;
        }
        
        if (value instanceof Object) {
            return new long[] { Double.valueOf(value.toString().replaceAll("[^\\-0-9\\.]", "")).longValue() };
        }
        
        return new long[0];
    }
    
    /**
     * 실수값을 반환한다.
     * 
     * @param key 키
     * @return 실수값
     */
    public float getFloat(String key) {
        return getFloat(key, 0F);
    }
    
    /**
     * 실수값을 반환한다.
     * 
     * @param key 키
     * @param defaultValue 디폴트 실수값
     * @return 실수값
     */
    public float getFloat(String key, float defaultValue) {
        Object value = get(key);
        
        if (value instanceof Object) {
            return Double.valueOf(value.toString().replaceAll("[^\\-0-9\\.]", "")).floatValue();
        }
        
        return defaultValue;
    }
    
    /**
     * 실수값 배열을 반환한다.
     * 
     * @param key 키
     * @return 실수값 배열
     */
    public float[] getFloatArray(String key) {
        Object value = get(key);
        
        if (value instanceof Object[]) {
            Object[] values = (Object[]) value;
            
            float[] floatValues = new float[values.length];
            
            for (int i = 0; i < values.length; i++) {
                floatValues[i] = Double.valueOf(values[i].toString().replaceAll("[^\\-0-9\\.]", "")).floatValue();
            }
            
            return floatValues;
        }
        
        if (value instanceof Object) {
            return new float[] { Double.valueOf(value.toString().replaceAll("[^\\-0-9\\.]", "")).floatValue() };
        }
        
        return new float[0];
    }
    
    /**
     * 실수값을 반환한다.
     * 
     * @param key 키
     * @return 실수값
     */
    public double getDouble(String key) {
        return getDouble(key, 0D);
    }
    
    /**
     * 실수값을 반환한다.
     * 
     * @param key 키
     * @param defaultValue 디폴트 실수값
     * @return 실수값
     */
    public double getDouble(String key, double defaultValue) {
        Object value = get(key);
        
        if (value instanceof Object) {
            return Double.valueOf(value.toString().replaceAll("[^\\-0-9\\.]", "")).doubleValue();
        }
        
        return defaultValue;
    }
    
    /**
     * 실수값 배열을 반환한다.
     * 
     * @param key 키
     * @return 실수값 배열
     */
    public double[] getDoubleArray(String key) {
        Object value = get(key);
        
        if (value instanceof Object[]) {
            Object[] values = (Object[]) value;
            
            double[] doubleValues = new double[values.length];
            
            for (int i = 0; i < values.length; i++) {
                doubleValues[i] = Double.valueOf(values[i].toString().replaceAll("[^\\-0-9\\.]", "")).doubleValue();
            }
            
            return doubleValues;
        }
        
        if (value instanceof Object) {
            return new double[] { Double.valueOf(value.toString().replaceAll("[^\\-0-9\\.]", "")).doubleValue() };
        }
        
        return new double[0];
    }
    
    /**
     * 문자열을 반환한다.
     * 
     * @param key 키
     * @return 문자열
     */
    public String getString(String key) {
        return getString(key, "");
    }
    
    /**
     * 문자열을 반환한다.
     * 
     * @param key 키
     * @param defaultValue 디폴트 문자열
     * @return 문자열
     */
    public String getString(String key, String defaultValue) {
        Object value = get(key);
        
        if (value instanceof Object) {
            return value.toString();
        }
        
        return defaultValue;
    }
    
    /**
     * 문자열 배열을 반환한다.
     * 
     * @param key 키
     * @return 문자열 배열
     */
    public String[] getStringArray(String key) {
        Object value = get(key);
        
        if (value instanceof Object[]) {
            Object[] values = (Object[]) value;
            
            String[] stringValues = new String[values.length];
            
            for (int i = 0; i < values.length; i++) {
                stringValues[i] = values[i].toString();
            }
            
            return stringValues;
        }
        
        if (value instanceof Object) {
            return new String[] { value.toString() };
        }
        
        return new String[0];
    }
    
    /**
     * 캘린더를 반환한다.
     * 
     * @param key 키
     * @param pattern 패턴
     * @return 캘린더
     */
    public Calendar getCalendar(String key) {
        Object value = get(key);
        
        if (value instanceof Object) {
            switch (value.toString().length()) {
                case 8:
                    return getCalendar(key, "yyyyMMdd", Calendar.getInstance());
                case 10:
                    return getCalendar(key, "yyyy-MM-dd", Calendar.getInstance());
            }
        }
        
        return Calendar.getInstance();
    }
    
    /**
     * 캘린더를 반환한다.
     * 
     * @param key 키
     * @param pattern 패턴
     * @return 캘린더
     */
    public Calendar getCalendar(String key, String pattern) {
        return getCalendar(key, pattern, Calendar.getInstance());
    }
    
    /**
     * 캘린더를 반환한다.
     * 
     * @param key 키
     * @param pattern 패턴
     * @return 캘린더
     */
    public Calendar getCalendar(String key, String pattern, Calendar defaultValue) {
        Object value = get(key);
        
        if (value instanceof Object) {
            try {
                Calendar calendar = Calendar.getInstance();
                
                calendar.setTime(new SimpleDateFormat(pattern).parse(value.toString()));
                
                return calendar;
            }
            catch (ParseException pe) {
                throw new SystemException(pe.getMessage());
            }
        }
        
        return defaultValue;
    }
}