package com.wowpmd.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kr.framework.web.BaseController;
import com.wowpmd.common.model.LoginUser;
import com.wowpmd.common.service.UserService;


@Controller
public class LoginController extends DefaultController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	// 쿠키 저장 기간
    private final int maxAge = 60 * 60 * 24 * 7; // 일주일
    
    protected final String USER_SESSION_KEY = "userLoginInfo";

	@Autowired
	private UserService userService;

	@SuppressWarnings("null")
	@RequestMapping(value="/login")
	public ModelAndView login(LoginUser user, HttpServletResponse response, HttpServletRequest request, ModelMap model) throws Throwable {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/main");
        HttpSession session = request.getSession();
        String pwTmp = request.getParameter("userPw");

        // SHA-256 암호화
        MessageDigest sh = MessageDigest.getInstance("SHA-256");
        sh.update(pwTmp.getBytes());
        byte byteData[] = sh.digest();

        StringBuffer sb = new StringBuffer();
        for(int i=0; i<byteData.length; i++) {
            sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
        }

        String userPwd = sb.toString();
        user.setPassNumb(userPwd);

		LoginUser loginUser = userService.findByUserIdAndPassword(user);
		LoginUser lstCttDt = userService.lstCttDtSearch(user);

		String lstCttDt1 = "0";
		if(lstCttDt != null){
			lstCttDt1 = lstCttDt.getLstCttDt();
		}

		int lstCttDt2 =  Integer.valueOf(lstCttDt1);

        if (loginUser != null) {
    		loginUser.setUserPw(userPwd);

    		//비번바꾸기
    		if (lstCttDt2 >= 1) {
    			model.addAttribute("lstCttDt", "1");
    			model.addAttribute("pwTmp", pwTmp);

    			session.setAttribute(USER_SESSION_KEY, loginUser);
    			mav.setViewName("/popup/com/common/comp0001");
    		}

        	if (lstCttDt2 < 1 && loginUser.getUserPw().equals(loginUser.getPassNumb())) {

        		saveCookieById(user, response, request);

	            userService.lstCttDtUpdate(user);

	            Cookie exCk = new Cookie("exUserId", request.getParameter("userId"));
	            exCk.setMaxAge(maxAge);
	            exCk.setPath("/");
	            response.addCookie(exCk);
	            exCk = new Cookie("exUserPw", request.getParameter("userPw"));
	            exCk.setMaxAge(maxAge);
	            exCk.setPath("/");
	            response.addCookie(exCk);

	            session.setAttribute(USER_SESSION_KEY, loginUser);
	            mav.setViewName("/comm/main/main");
        	} else if (lstCttDt2 < 1 && !loginUser.getUserPw().equals(loginUser.getPassNumb())) {
        		saveCookieById(user, response, request);

        		model.addAttribute("pwTmp", userPwd);
        		model.addAttribute("passNumb", loginUser.getPassNumb());
        		mav.setViewName("/index");
        	}
        } else {
        	model.addAttribute("key", "1");
        	mav.setViewName("/index");
        }
        return mav;
	}

	@RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("userLoginInfo", null);
        return "redirect:/index";
    }

	private void saveCookieById(LoginUser user, HttpServletResponse response, HttpServletRequest request) {
        if (user.isSaveId()) {
        	//쿠키생성
    		Cookie c = new Cookie("userId", this.getRequest().getParameter("userId"));
    		//쿠키 기간 (1주일)
    		c.setMaxAge(maxAge);
    		//쿠키 적용 경로
    		c.setPath("/");
    		//쿠키 추가
    		response.addCookie(c);

    		c = new Cookie("isSaveId", "true");
    		c.setMaxAge(maxAge);
    		//쿠키 적용 경로
    		c.setPath("/");
    		//쿠키 추가
    		response.addCookie(c);
        } else {
        	//쿠키삭제
        	Cookie[] ck = request.getCookies();
        	if(ck!=null){
        		for(int i=0; i<ck.length; i++){
        			// JSESSIONID는 세션정보
        			if(!ck[i].getName().equals("JSESSIONID")){
		        		ck[i].setMaxAge(0);
		        		response.addCookie(ck[i]);
        			}
	        	}
        	}
        }
    }

	public static void main(String[] args) throws Exception {
		String password = "00514";

		// SHA-256 암호화
        MessageDigest sh = MessageDigest.getInstance("SHA-256");
        sh.update(password.getBytes());
        byte byteData[] = sh.digest();

        StringBuffer sb = new StringBuffer();
        for(int i=0; i<byteData.length; i++) {
            sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
        }

        System.out.println("password:" + sb.toString());
	}
}