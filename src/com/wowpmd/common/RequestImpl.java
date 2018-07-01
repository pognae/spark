package com.wowpmd.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import com.kr.framework.util.HttpRequestException;
import com.kr.framework.util.Url;

/**
 * 클래스명: <code>RequestImpl</code>
 * 
 * <pre>
 * HttpRequest구현 클래스
 * 잡다한 기능제거
 * httpConnection의 레퍼클래스
 * </pre>
 *
 * @date 2008-04-11
 * @author 이경연
 *
 */
public class RequestImpl implements Request {
    
    private int maxConnectionTimeout = Constants.MAX_CONNECTION_TIMEOUT; 

    final Log log = LogFactory.getLog(RequestImpl.class);

    /**
     * httpMethod 기본값(GET)
     */
    private String method = "GET";

    /**
     * 보내게될 URL
     */
    private Url url = null;

    /**
     * 파라매터
     */
    private List<RequestParameter> params = null;

    /**
     * 기본문자셋
     */
    private String charset = HTTP.UTF_8;

    /**
     * Response Charset
     */
    private String responseCharset;

    /**
     * 생성자
     *
     * @param url
     */
    public RequestImpl(Url url) {
        this.url = url;
    }

    /**
     * 생성자
     *
     * @param url
     * @param charset
     */
    public RequestImpl(Url url, String charset) {
        this.charset = charset;
        this.url = url;
    }

    /**
     * 생성자
     *
     * @param url
     * @param timeout
     */
    public RequestImpl(Url url, int timeout) {
        this.url = url;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    /**
     * <pre>
     * 리퀘스트보낸 후 해당 reponse를
     * net.flashworld.http.Response로 바꾸어 리턴한다.
     *
     *
     * </pre>
     *
     * @return
     * @throws HttpRequestException
     * @see net.flashworld.http.Request#execute()
     */
    public Response execute() throws HttpRequestException {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpParams baseParams = BaseParameterFactory.getDefaulParameter();
        HttpConnectionParams.setConnectionTimeout(baseParams, this.maxConnectionTimeout);
        httpclient.setParams(baseParams);
        ResponseImpl response = null;

        List <NameValuePair> nvps = null;
        if ("POST".equals(method)) {
            if (this.params != null) {
                nvps = new ArrayList <NameValuePair>();
                for (int i = 0; i < this.params.size(); i++) {
                    RequestParameter parameter = params.get(i);
                    nvps.add(new BasicNameValuePair(parameter.getName(), parameter.getValue()));
                }
            }
        }

        try {
            
            if (GET.equals(method)) {
                HttpGet httpget = new HttpGet(this.url.toString());
                HttpResponse httpResponse = httpclient.execute(httpget);
                response = new ResponseImpl(httpResponse.getEntity(), httpResponse.getAllHeaders(), httpResponse.getStatusLine().getStatusCode(), this.responseCharset);
                response.setHeaders(httpResponse.getAllHeaders());
            }
            else {
                HttpPost httpPost = new HttpPost(this.url.toString());
                if (nvps != null) {
                    httpPost.setEntity(new UrlEncodedFormEntity(nvps, charset));
                }
                HttpResponse httpResponse = httpclient.execute(httpPost);
                response = new ResponseImpl(httpResponse.getEntity(), httpResponse.getAllHeaders(), httpResponse.getStatusLine().getStatusCode(), this.responseCharset);
                response.setHeaders(httpResponse.getAllHeaders());
            }
        } catch (Exception e) {
            throw new HttpRequestException(e.getMessage());
        }

        return response;
    }


    public Url getLinkUrl() {
        return this.url;
    }

    public void setGet() {
        this.method = GET;
    }

    public void setPost() {
        this.method = POST;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setParams(List<RequestParameter> params) {
        this.params = params;
    }

    @Override
    public void setResponseCharset(String responseCharset) {
        this.responseCharset = responseCharset;
    }

    public int getMaxConnectionTimeout() {
        return maxConnectionTimeout;
    }

    @Override
    public void setMaxConnectionTimeout(int maxConnectionTimeout) {
        this.maxConnectionTimeout = maxConnectionTimeout;
    }
}
