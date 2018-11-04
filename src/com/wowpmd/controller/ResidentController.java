package com.wowpmd.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kr.framework.security.Access;
import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.common.service.SystemService;
import com.wowpmd.service.HouseService;
import com.wowpmd.service.ResidentService;
import com.wowpmd.util.Utils;
import com.wowpmd.vo.ResultVO;


@Controller
public class ResidentController extends DefaultController {

	private static final Logger log  = LoggerFactory.getLogger(ResidentController.class);

	@Autowired
	private ResidentService residentService;

	@Autowired
	private HouseService houseService;

	@Autowired
	private SystemService systemService;

	@Access
	@RequestMapping("/resident/rsd1010")
	public String rsd1010(HttpServletRequest request, Model model) throws Throwable{

		ParamsVO params = getParams(request);
		addObject(model, "useYn", systemService.getCodeHandler().getCodes("USE_YN"));
		addObject(model, "resideBeginDt", Utils.getYyyymmdd());

		return "/resident/rsd1010";
	}

	@Access
	@RequestMapping("/resident/rsd1010Search")
	public String rsd1010Search(HttpServletRequest request, Model model) throws IOException {

		ParamsVO params = getParams(request);
		Object list = residentService.rsd1010Search(params);

		addObject(model, list);
		addObject(model, "useYn", systemService.getCodeHandler().getCodes("USE_YN"));
		addObject(model, "resideBeginDt", Utils.getYyyymmdd());

		return "/resident/rsd1010";
	}

	@Access
	@RequestMapping("/resident/rsd1010/insert")
	public String rsd1010Insert(HttpServletRequest request, @RequestParam Map<String, Object> paramMap, Model model) throws Throwable {

		ParamsVO params = getParams(request);
		ResultVO result = residentService.insertResident(params);

		return alertAndForward("/resident/rsd1010Search", result.getMessages().getMessage());
	}

	@Access
	@RequestMapping("/popup/rsd1011")
	public String bsc1021(HttpServletRequest request, @RequestParam Map<String, Object> paramMap, Model model) throws Throwable {

		ParamsVO params = getParams(request);
		Object list = houseService.rsd1020Search(params);

		addObject(model, list);

		return "/popup/rsd1011";
	}

	@Access
	@RequestMapping("/resident/rsd1020")
	public String rsd1020(HttpServletRequest request, Model model) throws Throwable{

		ParamsVO params = getParams(request);
		addObject(model, "useYn", systemService.getCodeHandler().getCodes("USE_YN"));

		return "/resident/rsd1020";
	}

	@Access
	@RequestMapping("/resident/rsd1020Search")
	public String rsd1020Search(HttpServletRequest request, Model model) throws IOException {

		ParamsVO params = getParams(request);
		Object list = houseService.rsd1020Search(params);

		addObject(model, list);
		addObject(model, "useYn", systemService.getCodeHandler().getCodes("USE_YN"));

		return "/resident/rsd1020";
	}

	@Access
	@RequestMapping("/resident/rsd1020/insert")
	public String rsd1020Insert(HttpServletRequest request, @RequestParam Map<String, Object> paramMap, Model model) throws Throwable {

		ParamsVO params = getParams(request);
		String gnrSn = params.getString("gnrSn");

		ResultVO result = null;

		if(StringUtils.isEmpty(gnrSn)) {
			result = houseService.insertHouse(params);
		} else {
			//수정
			result = houseService.updateHouse(params);
		}

		return alertAndForward("/resident/rsd1020Search", result.getMessages().getMessage());
	}



	@Access
	@RequestMapping("/resident/rsd1030")
	public String rsd1030(HttpServletRequest request, Model model) throws Throwable{

		ParamsVO params = getParams(request);
		addObject(model, "useYn", systemService.getCodeHandler().getCodes("USE_YN"));
		addObject(model, "resideBeginDt", Utils.getYyyymmdd());

		return "/resident/rsd1030";
	}

	@Access
	@RequestMapping("/resident/rsd1030Search")
	public String rsd1030Search(HttpServletRequest request, Model model) throws IOException {

		ParamsVO params = getParams(request);
		Object list = residentService.rsd1030Search(params);

		addObject(model, list);
		addObject(model, "useYn", systemService.getCodeHandler().getCodes("USE_YN"));
		addObject(model, "resideBeginDt", Utils.getYyyymmdd());

		return "/resident/rsd1030";
	}

	@Access
	@RequestMapping("/resident/rsd1030/insert")
	public String rsd1030Insert(HttpServletRequest request, @RequestParam Map<String, Object> paramMap, Model model) throws Throwable {

		ParamsVO params = getParams(request);
		ResultVO result = residentService.insertVehicle(params);

		return alertAndForward("/resident/rsd1030Search", result.getMessages().getMessage());
	}

	@Access
	@RequestMapping("/popup/rsd1031")
	public String rsd1031(HttpServletRequest request, @RequestParam Map<String, Object> paramMap, Model model) throws Throwable {

		ParamsVO params = getParams(request);
		Object list = houseService.rsd1020Search(params);

		addObject(model, list);

		return "/popup/rsd1031";
	}
}
