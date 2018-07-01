package com.wowpmd.common;

import org.apache.http.HttpVersion;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

/**
 * 클래스명: <code>BaseParameterFactory</code>
 * 
 * <pre>
 * Http Parameter클래스 
 * Request의 BaseParameter를 관장한다.
 * 특정한 일이없으면 기본파라매터를 사용하면됨      
 * </pre>
 * 
 * @date 2008-04-11
 * @author 이경연
 *
 */
public class BaseParameterFactory {

    /**
     * <pre>
     * default 파라매터
     * </pre> 
     *
     * @return
     */
    public static HttpParams getDefaulParameter() {
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, "UTF-8");
        HttpProtocolParams.setUserAgent(params, "HttpComponents/1.1");
        HttpProtocolParams.setUseExpectContinue(params, true);
        return params;
    }

    /**
     * <pre>
     * http V1.0파라매터 
     * </pre> 
     *
     * @return
     */
    public static HttpParams getParameterVersion1_0() {
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_0);
        HttpProtocolParams.setContentCharset(params, "UTF-8");
        HttpProtocolParams.setUserAgent(params, "HttpComponents/1.1");
        HttpProtocolParams.setUseExpectContinue(params, true);
        return params;
    }

    /**
     * <pre>
     * charset을 변경
     * </pre> 
     *
     * @param charset
     * @return
     */
    public static HttpParams getParameter(String charset) {
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, charset);
        HttpProtocolParams.setUserAgent(params, "HttpComponents/1.1");
        HttpProtocolParams.setUseExpectContinue(params, true);
        return params;
    }

    /**
     * <pre>
     * charset/agent를 변경
     * </pre> 
     *
     * @param charset
     * @param agent
     * @return
     */
    public static HttpParams getParameter(String charset, String agent) {
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, charset);
        HttpProtocolParams.setUserAgent(params, agent);
        HttpProtocolParams.setUseExpectContinue(params, true);
        return params;
    }

    /**
     * <pre>
     * charset/agent를 변경/expectContinue 값을 변경
     * </pre> 
     *
     * @param charset
     * @param agent
     * @param expectContinue
     * @return
     */
    public static HttpParams getParameter(String charset, String agent, boolean expectContinue) {
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, charset);
        HttpProtocolParams.setUserAgent(params, agent);
        HttpProtocolParams.setUseExpectContinue(params, expectContinue);
        return params;
    }
}
