package com.wowpmd.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kr.framework.web.BaseController;
import com.wowpmd.common.model.LoginUser;
import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.common.service.SystemService;
import com.wowpmd.common.service.UserService;


@Controller
public class MainController extends DefaultController {

	private static final Logger log = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private SystemService systemService;

	@RequestMapping(value="/")
	public ModelAndView login(LoginUser user, HttpServletResponse response, ModelMap model) throws Throwable {

		return new ModelAndView("/index");
	}

	@RequestMapping(value="/navbar")
	public ModelAndView navbar(HttpServletRequest request, HttpServletResponse response, Model model) throws Throwable {
		ParamsVO params = getParams(request);

		params.add("level", "1");
//		addObject(model, "menuList", systemService.getMenuList(params));

		return new ModelAndView("/navbar");
	}

	@RequestMapping(value="/navbar/{subMenu}")
	public ModelAndView subMenu(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable("subMenu") String subMenu) throws Throwable {
		ParamsVO params = getParams(request);

		if(StringUtils.equals(subMenu, "thirdMenu")) {
			params.put("level", "3");
			params.put("upperId", params.getString("upperSubId"));
		}

//		addObject(model, "dropDownMenuList", systemService.getMenuList(params));

		return new ModelAndView("/" + subMenu);
	}

	@RequestMapping(value="/breadcrumb")
	public ModelAndView breadcrumb(LoginUser user, HttpServletResponse response, ModelMap model) throws Throwable {
		return new ModelAndView("/breadcrumb");
	}

	@RequestMapping(value="/index")
	public String index(LoginUser user, ModelMap model) throws Throwable {
		String userId = "";
		String viewId = "/index";
		LoginUser loginUser = getLoginUser();

		if(loginUser == null) {
			//쿠키를 불러옴
			try{
				Cookie[] ck = this.getRequest().getCookies();
				if(ck!=null){
					for(int i=0; i<ck.length; i++){
						//쿠키에 userId가 있으면 아이디저장도 체크
						if(ck[i].getName().equals("userId")){
							userId = ck[i].getValue();
							user.setUserId(userId);
							user.setSaveId(true);
						}
					}
				}
			}catch(Exception e){
				System.out.println("cookie error");
			}

			model.addAttribute("userLoginForm", user);
		} else {
			viewId = "/comm/main/main";
		}

        return viewId;
	}

	@RequestMapping("/comm/main")
	public String main(HttpServletRequest request, Model model) {
		return "/comm/main/main";
	}

	@RequestMapping("/comm/leftMenu")
	public String leftMenu(HttpServletRequest request, Model model) {
		return "/comm/leftMenu";
	}

	@RequestMapping("/comm/top")
	public String top(HttpServletRequest request, Model model) {
		return "/comm/top";
	}

	@RequestMapping("/comm/style")
	public String style(HttpServletRequest request, Model model) {
		return "/comm/style";
	}

	@RequestMapping("/comm/script")
	public String script(HttpServletRequest request, Model model) {
		return "/comm/script";
	}

	@RequestMapping("/comm/footer")
	public String footer(HttpServletRequest request, Model model) {
		return "/comm/footer";
	}

}