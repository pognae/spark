package com.wowpmd.common;

/**
 * 클래스명: <code>Constants</code>
 * 
 * <pre>
 * http페키지에서 사용하기위한 상수클래스 
 * 필요할때 추가하자
 * </pre>
 *
 * @date 2008-04-11
 * @author 이경연
 *
 */
public interface Constants {

    /**
     * 컨낵션 타임아웃시간(4초) 
     */
    public final static int MAX_CONNECTION_TIMEOUT = 4000; // 4초 
    
    /**
     * 기본버퍼사이즈  
     */
    public static final int DEFAULT_BUFFER_SIZE = 4096;
    
    /**
     * 기본 리트라이 횟수  
     */
    public static final int MAX_RETRY_COUNT = 3;
    
    /**
     * 디폴트 문자셋 
     */
    public static final String DEFAULT_SITE_CHAR_SET = "UTF-8";
    
    public final static String ERROR_MESSAGE = "errorMessages";

}
