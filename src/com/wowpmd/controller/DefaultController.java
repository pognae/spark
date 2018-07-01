/*
 * @(#)BaseController.java 1.0 2016/04/12
 *
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kr.framework.config.Config;
import com.kr.framework.config.Constants;
import com.wowpmd.common.model.LoginUser;
import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.common.service.ComCodeService;
import com.wowpmd.constants.ModelAttribute;
import com.wowpmd.constants.RequestAttribute;
import com.wowpmd.vo.MessagesVO;
import com.wowpmd.vo.PagingVO;
import com.wowpmd.vo.ResultVO;

/**
 * 기본 컨트롤러 클래스이다.
 *
 * @author 이동엽
 * @version 1.0 2016/04/12
 */
public class DefaultController extends com.kr.framework.web.BaseController {
    /**
     * 로그
     */
    protected Log log;

    /**
     * 공통코드를 관리하는 서비스
     */
    @Autowired
    protected ComCodeService comCodeService;

    protected final String errorMessageKey = Constants.ERROR_MESSAGE;

    protected final String USER_SESSION_KEY = "userLoginInfo";

    /**
     * 디폴트 생성자이다.
     */
    protected DefaultController() {
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
     * 파마메터를 반환한다.
     *
     * @return 파라메터
     */
    protected ParamsVO getParams() {
        return getParams(getRequest(), true);
    }

    /**
     * 파라메터를 반환한다.
     *
     * @param request HTTP 요청
     * @return 파라메터
     */
    protected ParamsVO getParams(HttpServletRequest request) {
        return getParams(request, true);
    }

    /**
     * 파라메터를 반환한다.
     *
     * @param session 세션정보
     * @return 파라메터
     */
    protected ParamsVO getParams(boolean session) {
        return getParams(getRequest(), session);
    }

    /**
     * 파라메터를 반환한다.
     *
     * @param request HTTP 요청
     * @param session 세션정보
     * @return 파라메터
     */
    protected ParamsVO getParams(HttpServletRequest request, boolean session) {
        ParamsVO params = new ParamsVO();

        // 텍스트 파라메터를 추가한다.
        addTextParameter(params, request);

        if (request instanceof MultipartHttpServletRequest) {
            // 파일 파라메터를 추가한다.
            addFileParameter(params, (MultipartHttpServletRequest) request);
        }

        if (session) {
            // 사용자 파라메터를 추가한다.
            addUserParameter(params, request);
        }

        return params;
    }

    /**
     * 텍스트 파라메터를 추가한다.
     *
     * @param params 파라메터
     * @param request HTTP 요청
     */
    @SuppressWarnings("unchecked")
    private void addTextParameter(ParamsVO params, HttpServletRequest request) {
		Enumeration<String> enumeration = request.getParameterNames();

        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();

            params.add(getParameterName(name), getTextParameter(request, name));
        }
    }

    /**
     * 파일 파라메터를 추가한다.
     *
     * @param params 파라메터
     * @param request HTTP 요청
     */
    private void addFileParameter(ParamsVO params, MultipartHttpServletRequest request) {
        Iterator<String> iterator = request.getFileNames();

        while (iterator.hasNext()) {
            String name = iterator.next();

            params.add(getParameterName(name), getFileParameter(request, name));
        }
    }

    /**
     * 사용자 파라메터를 추가한다.
     *
     * @param params 파라메터
     * @param request HTTP 요청
     */
    private void addUserParameter(ParamsVO params, HttpServletRequest request) {
        // Nothing to do.
    }

    /**
     * 파라메터 이름을 반환한다.
     *
     * @param name 파라메터 이름
     * @return 파라메터 이름
     */
    private String getParameterName(String name) {
        if (name.endsWith("[]")) {
            return name.substring(0, name.lastIndexOf("[]"));
        }

        return name;
    }

    /**
     * 텍스트 파라메터를 반환한다.
     *
     * @param request HTTP 요청
     * @param name 파라메터 이름
     * @return 텍스트 파라메터
     */
    private String[] getTextParameter(HttpServletRequest request, String name) {
        return request.getParameterValues(name);
    }

    /**
     * 파일 파라메터를 반환한다.
     *
     * @param request HTTP 요청
     * @param name 파라메터 이름
     * @return 파일 파라메터
     */
    private MultipartFile[] getFileParameter(MultipartHttpServletRequest request, String name) {
        Object[] source = request.getFiles(name).toArray();

        MultipartFile[] destination = new MultipartFile[source.length];

        System.arraycopy(source, 0, destination, 0, destination.length);

        return destination;
    }

    /**
     * 모델에 객체를 추가한다.
     *
     * @param model 모델
     * @param object 객체
     */
    protected void addObject(Model model, Object object) {
        addObject(model, null, object);
    }

    /**
     * 모델에 객체를 추가한다.
     *
     * @param model 모델
     * @param name 이름
     * @param object 객체
     */
    protected void addObject(Model model, String name, Object object) {
        if (object instanceof ResultVO) {
            addResult(model, name, (ResultVO) object);
        } else {
            addData(model, name, object);
        }
    }

    /**
     * 모델에 결과를 추가한다.
     *
     * @param model 모델
     * @param result 결과
     */
    private void addResult(Model model, String name, ResultVO result) {
        if (result.getSuccess()) {
            if (name != null) {
                model.addAttribute(name, result.getMessages());
            }
            else {
                model.addAttribute(ModelAttribute.SUCCESS, result.getMessages());
            }
        }
        else {
            model.addAttribute(ModelAttribute.ERROR, result.getMessages());
        }
    }

    /**
     * 모델에 데이터를 추가한다.
     *
     * @param model 모델
     * @param data 데이터
     */
    private void addData(Model model, String name, Object data) {
        if (data instanceof PagingVO) {
            PagingVO paging = (PagingVO) data;

            model.addAttribute(ModelAttribute.PAGE, paging.getPage());
            model.addAttribute(ModelAttribute.ROWS, paging.getRows());

            if (name != null) {
                model.addAttribute(name, paging.getData());
            }
            else {
                model.addAttribute(ModelAttribute.DATA, paging.getData());
            }

            model.addAttribute(ModelAttribute.COUNT, paging.getCount());
            model.addAttribute(ModelAttribute.TOTAL, paging.getTotal());
            model.addAttribute(ModelAttribute.PAGES, paging.getPages());
        }
        else {
            if (name != null) {
                model.addAttribute(name, data);
            }
            else {
                model.addAttribute(ModelAttribute.DATA, data);
            }
        }
    }

    /**
     * 공통코드를 검색한다.
     *
     * @param cdIdNm 코드ID명
     * @return 검색결과
     */
    protected List<Object> getComCode(String cdIdNm) {
        return getComCode(cdIdNm, null, null);
    }

    /**
     * 공통코드를 검색한다.
     *
     * @param cdIdNm 코드ID명
     * @param defaultName 디폴트 이름
     * @return 검색결과
     */
    protected List<Object> getComCode(String cdIdNm, String defaultName) {
        return getComCode(cdIdNm, "", defaultName);
    }

    /**
     * 공통코드를 검색한다.
     *
     * @param cdIdNm 코드ID명
     * @param defaultCode 디폴트 코드
     * @param defaultName 디폴트 이름
     * @return 검색결과
     */
    protected List<Object> getComCode(String cdIdNm, String defaultCode, String defaultName) {
        ParamsVO params = new ParamsVO();

        params.put("cdIdNm", cdIdNm);

        if (defaultName != null) {
            if (defaultCode != null) {
                params.put(ComCodeService.DEFAULT_CODE, defaultCode);
                params.put(ComCodeService.DEFAULT_NAME, defaultName);
            }
            else {
                params.put(ComCodeService.DEFAULT_CODE, "");
                params.put(ComCodeService.DEFAULT_NAME, defaultName);
            }
        }

        return getComCode(params);
    }

    /**
     * 공통코드를 검색한다.
     *
     * @param params 파라메터
     * @return 검색결과
     */
    protected List<Object> getComCode(ParamsVO params) {
        // 공통코드를 검색한다.
        return comCodeService.getComCode(params);
    }

    /**
     * 메시지를 보여주고 화면을 이동한다.
     *
     * @param url URL
     * @param message 메시지
     * @return 뷰이름
     */
    protected String alertAndRedirect(String url, String message) {
        return alertAndRedirect(url, message, null);
    }

    /**
     * 메시지를 보여주고 화면을 이동한다.
     *
     * @param url URL
     * @param message 메시지
     * @return 뷰이름
     */
    protected String alertAndRedirect(String url, String message, ResultVO result) {
        getRequest().setAttribute(RequestAttribute.ACTION,     url);
        getRequest().setAttribute(RequestAttribute.MESSAGE,    message);
        getRequest().setAttribute(RequestAttribute.PARAMETERS, getRedirectParameters(result));

        return "/sys/view/alertAndRedirect";
    }

    /**
     * 리다이렉트 파라메터를 반환한다.
     *
     * @param result 처리결과
     * @return 파라메터
     */
    private List<ParamsVO> getRedirectParameters(ResultVO result) {
        List<ParamsVO> parameters = new ArrayList<ParamsVO>();

        if (result != null) {
            MessagesVO messages = result.getMessages();

            Iterator<Object> iterator = messages.keySet().iterator();

            while (iterator.hasNext()) {
                String key = (String) iterator.next();

                ParamsVO param = new ParamsVO();

                param.put("key",   key);
                param.put("value", messages.getStringArray(key));

                parameters.add(param);
            }
        }

        return parameters;
    }

    /**
     * 메시지를 보여주고 화면을 이동한다.
     *
     * @param url URL
     * @param message 메시지
     * @return 뷰이름
     */
    protected String alertAndForward(String url, String message) {
        getRequest().setAttribute(RequestAttribute.ACTION,  url);
        getRequest().setAttribute(RequestAttribute.MESSAGE, message);

        return "/sys/view/alertAndForward";
    }

    public HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }

    public HttpSession getSession() {
        return this.getRequest().getSession();
    }

    /**
     * <pre>
     * 회원정보를 가져온다.
     * </pre>
     * @return
     */
    public LoginUser getLoginUser() {
        HttpServletRequest request = this.getRequest();
        return getLoginUser(request);
    }

    /**
     * <pre>
     * 회원정보를 가져온다.
     * </pre>
     * @param request
     * @return
     */
    public LoginUser getLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        LoginUser loginUser = (LoginUser) session.getAttribute(USER_SESSION_KEY);

		if(StringUtils.equals(Config.getString("is.developer"), "Y")) {
			if(loginUser == null) {
				loginUser = new LoginUser();
				loginUser.setUserId("dev");
				session.setAttribute(USER_SESSION_KEY, loginUser);
			}
		}

        return loginUser;
    }

	public boolean isNull(String str) {
		return ("".equals(fixNull(str)));
	}

	public String fixNull(String str) {
		if ((str == null) || ("".equals(str))) {
			return "";
		}
		return str;
	}
}