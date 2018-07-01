package com.wowpmd.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;

import com.kr.framework.util.HttpRequestException;
import com.kr.framework.util.Util;


/**
 * 클래스명: <code>ResponseImpl</code>
 * 
 * <pre>
 * Response구현분
 *
 * 잡다한 기능제거
 * QoomSessionServer에서 사용
 * </pre>
 *
 * @date 2008-04-11
 * @author 이경연
 *
 */
public class ResponseImpl implements Response {

    /**
     * 문자셋
     */
    private final String charset;

    /**
     * 컨텐츠 길이
     */
    private final long contentLength;

    /**
     * HttpStatus
     */
    private final int status;

    /**
     * 컨텐츠타입
     */
    private final String contentType;

    /**
     * 리스판스 해더
     */
    private Header[] headers;

    /**
     *
     */
    private final HttpEntity entity;

    /**
     * 생성자
     *
     * @param entity
     * @param headers
     * @param status
     * @throws IOException
     */
    ResponseImpl(HttpEntity entity, Header[] headers, int status) throws IOException {
        this.status = status;
        this.entity = entity;
        this.contentLength = entity.getContentLength();
        this.contentType = getContentType(entity.getContentType().getValue());
        this.charset = EntityUtils.getContentCharSet(entity);
        this.headers = headers;
    }

    /**
     * 생성자
     *
     * @param entity
     * @param headers
     * @param status
     * @throws IOException
     * @throws IllegalStateException
     * @throws IOException
     */
    ResponseImpl(HttpEntity entity, Header[] headers, int status, String charset) {
        this.entity = entity;
        this.status = status;
        this.contentLength = entity.getContentLength();
        this.contentType = getContentType(entity.getContentType().getValue());
        this.charset = charset != null ? charset : EntityUtils.getContentCharSet(entity);
        this.headers = headers;
    }

    public String getContentType() {
        return this.contentType;
    }

    /**
     * <pre>
     * 해더에서 가져온 contentType을 가져와 ';'을 때어내고
     * 실제 컨텐츠타입으로 바꾼다.
     * </pre>
     *
     * @param contentType
     * @return
     */
    private String getContentType(String contentType) {
        if (contentType == null) {
            return null;
        }
        try {
            return Util.getContentType(contentType);
        }
        catch (Exception e) {
            return contentType;
        }
    }

    /**
     * <pre>
     * 현재 리스판스가 redirect면 true를 리턴한다.
     *
     * </pre>
     *
     * @return
     * @see net.flashworld.http.Response#isMove()
     */
    public boolean isMove() {
        return this.getStatus() == HttpStatus.SC_MOVED_PERMANENTLY || this.getStatus() == HttpStatus.SC_MOVED_TEMPORARILY;
    }

    public void setHeaders(Header[] allHeaders) {
        this.headers = allHeaders;
    }

    public Header[] getHeaders() {
        return this.headers;
    }

    public String getCharset() {
        if (this.charset == null) {
            return Constants.DEFAULT_SITE_CHAR_SET;
        }
        return this.charset;
    }

    public long getContentLength() {
        return this.contentLength;
    }

    public int getStatus() {
        return this.status;
    }

    public String getResponseBody() {
        try {
            return EntityUtils.toString(entity, charset);
        } catch (Exception e) {
            throw new HttpRequestException(e);
        }
    }

    public InputStream getInputStream() {
        try {
            return this.entity.getContent();
        } catch (Exception e) {
            throw new HttpRequestException(e);
        }
    }
    
    public byte[] getBytes() {
        try {
            return EntityUtils.toByteArray(entity);
        } catch (IOException e) {
            throw new HttpRequestException(e);
        }
    }
}
