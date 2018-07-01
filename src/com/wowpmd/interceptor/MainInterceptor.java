package com.wowpmd.interceptor;

import java.net.InetAddress;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kr.framework.config.Config;
import com.wowpmd.common.model.LoginUser;
import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.common.service.ComCodeService;
import com.wowpmd.exception.SessionException;

public class MainInterceptor extends HandlerInterceptorAdapter{

	private static Logger logger = LoggerFactory.getLogger(MainInterceptor.class);

	@Autowired
	Properties config;

	@Autowired
	ComCodeService comCodeService;

	// 2016.06.03 이동엽 [1] 리소스 경로 제외 BEGIN
	/**
	 * 앤트경로매칭
	 */
	private AntPathMatcher antPathMatcher;

	/**
	 * 제외경로매핑
	 */
	private String[] excludeMappings;

	/**
	 * 세션리스경로매핑
	 */
	private String[] sessionlessMappings;

	/**
	 * 앤트경로매칭을 반환한다.
	 *
	 * @return 앤트경로매칭
	 */
	public AntPathMatcher getAntPathMatcher() {
		return antPathMatcher;
	}

	/**
	 * 앤트경로매칭을 설정한다.
	 *
	 * @param antPathMatcher 앤트경로매칭
	 */
	public void setAntPathMatcher(AntPathMatcher antPathMatcher) {
		this.antPathMatcher = antPathMatcher;
	}

	/**
	 * 제외경로매핑을 반환한다.
	 *
	 * @return 제외경로매핑
	 */
	public String[] getExcludeMappings() {
		return excludeMappings;
	}

	/**
	 * 제외경로매핑을 설정한다.
	 *
	 * @param excludeMappings 제외경로매핑
	 */
	public void setExcludeMappings(String[] excludeMappings) {
		this.excludeMappings = excludeMappings;
	}

	/**
	 * 세션리스경로매핑을 반환한다.
	 *
	 * @return 세션리스경로매핑
	 */
	public String[] getSessionlessMappings() {
		return sessionlessMappings;
	}

	/**
	 * 세션리스경로매핑을 설정한다.
	 *
	 * @param sessionlessMappings 세션리스경로매핑
	 */
	public void setSessionlessMappings(String[] sessionlessMappings) {
		this.sessionlessMappings = sessionlessMappings;
	}

	/**
	 * 제외경로여부를 반환한다.
	 *
	 * @param path 경로
	 * @return 제외경로여부
	 */
	private boolean isExcludePath(String path) {
		for (String pattern : excludeMappings) {
			if (antPathMatcher.match(pattern, path)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 세션리스경로여부를 반환한다.
	 *
	 * @param path 경로
	 * @return 세션리스경로여부
	 */
	private boolean isSessionlessPath(String path) {
		for (String pattern : sessionlessMappings) {
			if (antPathMatcher.match(pattern, path)) {
				return true;
			}
		}

		return false;
	}
	// 2016.06.03 이동엽 [1] 리소스 경로 제외 END

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		// 제외경로인 경우
		if (isExcludePath(req.getRequestURI())) {
			return true;
		}

		// 세션리스경로가 아닌 경우
		if (!isSessionlessPath(req.getRequestURI())) {
			HttpSession session = req.getSession(false);

			if(!StringUtils.equals(Config.getString("is.developer"), "Y")) {
				// 세션이 없는 경우
				if (session == null) {
					throw new SessionException("로그인 후 이용할 수 있습니다.");
				}

				// 사용자 세션이 없는 경우
				if (session.getAttribute("userLoginInfo") == null) {
					throw new SessionException("로그인 후 이용할 수 있습니다.");
				}
			}
		}

		HttpSession ses = req.getSession();

		/*
		if (ses.getAttribute("MenuList") == null) {
			Object result = comCodeService.getTopMenu();
			ses.setAttribute("MenuList", result);
		}
		*/

		long currentTime = System.currentTimeMillis();

		req.setAttribute("bTime", currentTime);
		return true;
	}

	@Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

		// 제외경로인 경우
		if (isExcludePath(request.getRequestURI())) {
			return;
		}

		HttpSession ses = request.getSession();
		ParamsVO params = new ParamsVO();
		LoginUser user = (LoginUser)ses.getAttribute("userLoginInfo");
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();

//	    String inAddr = req.getHeader("X-FORWARDED-FOR");
		InetAddress inet = InetAddress.getLocalHost();
//		String inAddr = inet.getHostAddress();
		String inAddr = req.getRemoteAddr();

        long currentTime = System.currentTimeMillis();
        long beginTime = (long)request.getAttribute("bTime");
        long processedTime = currentTime - beginTime;

        if(!StringUtils.equals(Config.getString("is.developer"), "Y")) {
			params.put("stafId", user.getStafId());
			params.put("menuId", request.getRequestURI());
			params.put("ipAddr", inAddr);
			params.put("processTime", processedTime);

//			comCodeService.logMenuInsert(params);
        } else {
			// todo: 테스트 세션 생성 후 삭제
			params.put("stafId", "dev");
			params.put("menuId", request.getRequestURI());
			params.put("ipAddr", inAddr);
			params.put("processTime", processedTime);

//			comCodeService.logMenuInsert(params);
        }

        super.afterCompletion(request, response, handler, ex);
    }

}