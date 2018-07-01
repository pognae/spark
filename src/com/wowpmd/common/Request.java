package com.wowpmd.common;

import java.util.List;

import com.kr.framework.util.HttpRequestException;

/**
 * 
 * 클래스명: <code>Request</code>
 * 
 * <pre>
 * HttpRequest 인터페이스
 * </pre>
 *
 * @author 이경연
 * @date 2008-02-22
 *
 */
public interface Request {
    
    public final static String GET = "GET";
    
    public final static String POST = "POST";
    
    public static boolean SEND_REDIRECT = true;
    
    public Response execute() throws HttpRequestException;
    
    public void setPost();
    
    public void setGet(); 
    
    public void setMethod(String method);
    
    public void setParams(List<RequestParameter> params);

    public void setResponseCharset(String responseCharset);

    void setMaxConnectionTimeout(int maxConnectionTimeout);
}
