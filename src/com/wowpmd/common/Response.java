package com.wowpmd.common;

import java.io.InputStream;

import org.apache.http.Header;

/**
 * 
 * 클래스명: <code>Response</code>
 * 
 * <pre>
 * HttpResponse 인터페이스
 * </pre>
 *
 * @author 이경연
 * @date 2008-04-11
 *
 */
public interface Response {

    public int getStatus();

    public long getContentLength();

    public String getContentType();

    public String getResponseBody();

    public String getCharset();

    public boolean isMove();

    public Header[] getHeaders();

    public InputStream getInputStream();
    
    byte[] getBytes();
}
