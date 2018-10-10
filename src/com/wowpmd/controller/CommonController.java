package com.wowpmd.controller;

import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
//import org.springframework.web.servlet.view.json.MappingJacksonJsonView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.kr.framework.config.Config;
import com.kr.framework.security.Access;
import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.common.service.ComCodeService;
import com.wowpmd.common.service.SystemService;
import com.wowpmd.common.service.UserService;
import com.wowpmd.vo.ResultVO;


@Controller
public class CommonController extends DefaultController {

	private static final Logger log = LoggerFactory.getLogger(CommonController.class);

	private final String jsExtension = "js";

	@Autowired
	private ComCodeService comCodeService;

	@Autowired
	private UserService userService;

	@Autowired
    private SystemService systemService;

//	@Access
    @RequestMapping("/script/{scriptId}")
    public String script(ModelMap model, @PathVariable("scriptId") String scriptId, String serviceYn) {
        model.addAttribute("imageServer", Config.getImageServerHost());
        model.addAttribute("uploadServer", Config.getUploadServerHost());
//        model.addAttribute("isLogin", super.getLoginUser() == null ? false : true);
        model.addAttribute("isDeveloper", Config.getString("is.developer"));
        model.addAttribute("serverHost", Config.getServerHost());
        model.addAttribute("serverHostForSSL", Config.getServerHostForSSL());
        model.addAttribute("onlyServerHost", Config.getOnlyServerHost());
//        debug(String.format("script/%s.%s", scriptId, jsExtension));
        return String.format("/script/%s.%s", scriptId, jsExtension);
    }

	@Access
	@RequestMapping("/common/comCode/searchView")
	public String getTopMenu(HttpServletRequest request, Model model) {
		ParamsVO params = getParams(request);
		debug("Request parameters: " + params);

		Object result = comCodeService.getTopMenu1(params);
        debug("Request parameters: " + result);

        addObject(model, result);

        return "jsonView";
	}

	/**
	 * for Grid 자바스크립트 공통코드
	 * @param request
	 * @param model
	 * @return
	 */
	@Access
	@RequestMapping("/common/code/gridCodeSearch")
	public String codeSearch(HttpServletRequest request, Model model) {
		ParamsVO params = getParams(request);
		debug("Request parameters: " + params);

		Object result = getComCode(params.getString("code"));
		debug("Request parameters: " + result);

		addObject(model, result);

		return "jsonView";
	}

	/**
	 * 20160523 정민승
	 * 비밀번호 변경
	 * @param request HTTP 요청
	 * @param model
	 * @return
	 */
	@Access
	@RequestMapping("/popup/com/common/comp0001Update")
	public String comp0001Update(HttpServletRequest request, Model model) throws Throwable {

		ParamsVO params = getParams(request);
		String passNumb = request.getParameter("newPassWd");

        // SHA-256 암호화
        MessageDigest sh = MessageDigest.getInstance("SHA-256");
        sh.update(passNumb.getBytes());
        byte byteData[] = sh.digest();

        StringBuffer sb = new StringBuffer();
        for(int i=0; i<byteData.length; i++) {
            sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
        }

        String userPwd = sb.toString();
        params.put("userPwd", userPwd);

        ResultVO result = userService.comp0001Update(params);

		return alertAndForward("/index", result.getMessages().getMessage());
	}

	@Access
	@RequestMapping("/imageSave")
	public void imageSave(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String data = request.getParameter("data");
		String apcNo = request.getParameter("apcNo");
        data = data.replaceAll("data:image/png;base64,", "");
        byte[] file = Base64.decodeBase64(data);
        ByteArrayInputStream is = new ByteArrayInputStream(file);

        response.setContentType("image/png");
        response.setHeader("Content-Disposition", "attachment; filename="+apcNo+".png");
        IOUtils.copy(is, response.getOutputStream());
        response.flushBuffer();

        return;
	}

    @Access
    @RequestMapping("/common/system/codeReloadHandler")
    public View systemReloadHandler(ModelMap model, String reloadCode) {
        boolean save = true;
        List<String> errorMessages = new ArrayList<String>();

        if(StringUtils.isEmpty(reloadCode)) {
            save = false;
            errorMessages.add("리로드를 실패 했습니다.");
        } else if(StringUtils.equals(reloadCode, "code")) {
            this.systemService.reloadCodeHandler();        // 코드 리로드
        }

        model.addAttribute(this.errorMessageKey, errorMessages);
        model.addAttribute("save", save);
//        return new MappingJacksonJsonView();
        return new MappingJackson2JsonView();
    }
}
