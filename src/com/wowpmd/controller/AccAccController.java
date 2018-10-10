package com.wowpmd.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
//import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kr.framework.config.Config;
import com.kr.framework.security.Access;
import com.wowpmd.acc.service.AccAccService;
import com.wowpmd.common.model.FileMeta;
import com.wowpmd.common.model.LoginUser;
import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.common.service.SystemService;
import com.wowpmd.util.SoftUtil;
import com.wowpmd.vo.PagingVO;


@Controller
public class AccAccController extends DefaultController {

	private static final Logger log  = LoggerFactory.getLogger(AccAccController.class);

	private static SoftUtil util = new SoftUtil();

	private LinkedList<FileMeta> files = new LinkedList<FileMeta>();

	private FileMeta fileMeta = null;

	private static String CASH_ACCT_CODE = "111000";//현금 계정 코드

	@Autowired
    private AccAccService accAccService;

	@Autowired
	private SystemService systemService;

	// 일마감
	@Access
	@RequestMapping("/acc/accAcc/acco0010")
	public String acco0010(@RequestParam Map<String, Object> paramMap, Model model) throws Throwable{

		return "/acc/accacc/acco0010";
	}

	// 전표총괄표
	@Access
	@RequestMapping("/acc/accAcc/acco4010")
	public String acco4010(@RequestParam Map<String, Object> paramMap, Model model) throws Throwable{

		return "/acc/accacc/acco4010";
	}

	// 대차대조표
	@Access
	@RequestMapping("/acc/accAcc/acco4020")
	public String acco4020(@RequestParam Map<String, Object> paramMap, Model model) throws Throwable{

		return "/acc/accacc/acco4020";
	}

	// 손익계산서
	@Access
	@RequestMapping("/acc/accAcc/acco4030")
	public String acco4030(@RequestParam Map<String, Object> paramMap, Model model) throws Throwable{

		return "/acc/accacc/acco4030";
	}

	// 일일거래명세
	@Access
	@RequestMapping("/acc/accAcc/acco4040")
	public String acco4040(@RequestParam Map<String, Object> paramMap, Model model) throws Throwable{

		return "/acc/accacc/acco4040";
	}

	/**
	 * 일반전표
	 * @param paramMap
	 * @param model
	 * @return
	 * @throws Throwable
	 */
	@Access
	@RequestMapping("/acc/accAcc/acco4050")
	public String acco4050(@RequestParam Map<String, Object> paramMap, Model model) throws Throwable {

		//addObject(model, "bnkCdList", systemService.getCodeHandler().getCodes("BNK_CD"));
		addObject(model, "hdlDivList", systemService.getCodeHandler().getCodes("HDL_DIV"));

		return "/acc/accacc/acco4050";
	}

	/**
	 * 일반전표 조회
	 * @param request
	 * @param model
	 * @return
	 */
	@Access
	@RequestMapping("/acc/accAcc/acco4050Search")
	public String acco4050Search(HttpServletRequest request, @RequestBody Map<String, Object> model, Model models, @RequestHeader(value="referer", required=false) final String referer) {
		ParamsVO params = getParams(request);

		params.add("regDtm", model.get("regDtm").toString());
		List<Object> slipList = accAccService.getSlipList(params);
		addObject(models, slipList);

		return "jsonView";
	}


	/**
	 * 계정과목 조회
	 * @param request
	 * @param model
	 * @return
	 */
	@Access
	@RequestMapping("/acc/accAcc/getAcctCode")
	public String getAcctCode(HttpServletRequest request, Model model) {

		ParamsVO params = getParams(request);

		List<Object> list = accAccService.getAcctCode(params);
		Map<String, String> result = new HashMap<String, String>();

		if(list == null) {
			result.put("cnt", "0");
		} else if(list.size() == 0) {
			result.put("cnt", "0");
		} else if(list.size() == 1) {
			result = (HashMap)list.get(0);
		} else if(list.size() > 1) {
			result = (HashMap)list.get(0);
		}

		addObject(model, result);

		return "jsonView";
	}

	/**
	 * 계정과목 조회 팝업
	 * @param paramMap
	 * @param model
	 * @return
	 * @throws Throwable
	 */
	@Access
	@RequestMapping("/acc/accAcc/acco9001")
	public String acco9001(HttpServletRequest request, @RequestParam Map<String, Object> paramMap, Model model) throws Throwable {
		ParamsVO params = getParams(request);
		PagingVO list = accAccService.getAcctCodeList(params);

		addObject(model, list);
		addObject(model, "params", params);

		return "/popup/acc/acco9001";
	}

	/**
	 * 전표 입력
	 * @param request
	 * @param model
	 * @param referer
	 * @return
	 */
	@Access
	@RequestMapping(value="/acc/accAcc/acco4050Insert", method={RequestMethod.POST}, headers = "Accept=application/json")
	public String acco4050Insert(HttpServletRequest request, @RequestBody Map<String, Object> model, Model models, @RequestHeader(value="referer", required=false) final String referer) {
		ParamsVO params = getParams(request);
		LoginUser loginUser = getLoginUser();

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			List list = (ArrayList) model.get("list");
			for(int i=0; i<list.size(); i++) {
				map = (Map<String, Object>) list.get(i);

				if(!StringUtils.isEmpty(map.get("slipNo").toString())) {
					continue;
				}

				if(StringUtils.isEmpty(map.get("crtAcctCode").toString()) && StringUtils.equals(map.get("crtHdlAmt").toString(), "0") &&
						StringUtils.isEmpty(map.get("dbtAcctCode").toString()) && StringUtils.equals(map.get("dbtHdlAmt").toString(), "0")) {
					continue;
				}

				if(StringUtils.equals(map.get("useYn").toString(), "N")) {
					if(accAccService.updateSlipDel(map) > 0) {
						continue;
					} else {
						throw new Exception();
					}
				}

				if(StringUtils.equals(map.get("procIo").toString(), "01")) {
					if(StringUtils.isEmpty(map.get("crtAcctName").toString())) {
						map.put("crtAcctCode", CASH_ACCT_CODE);
					}
				} else if(StringUtils.equals(map.get("procIo").toString(), "02")) {
					if(StringUtils.isEmpty(map.get("dbtAcctName").toString())) {
						map.put("dbtAcctCode", CASH_ACCT_CODE);
					}
				} else {
//					throw new Exception();
				}

				map.put("stafId", loginUser.getUserId());
				accAccService.insertSlip(map);
			}

			params.add("regDtm", model.get("regDtm").toString());
			List<Object> slipList = accAccService.getSlipList(params);
			addObject(models, slipList);

//			addObject(models, "result", "success");

		} catch(Exception e) {
			e.printStackTrace();
		}

		return "jsonView";
	}

	@Access
	@RequestMapping(value="/acc/accAcc/acco4050Delete", method={RequestMethod.POST}, headers = "Accept=application/json")
	public String acco4050Delete(HttpServletRequest request, @RequestBody Map<String, Object> model, Model models, @RequestHeader(value="referer", required=false) final String referer) {
		ParamsVO params = getParams(request);
		LoginUser loginUser = getLoginUser();

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			List list = (ArrayList) model.get("list");
			for(int i=0; i<list.size(); i++) {
				map = (Map<String, Object>) list.get(i);

				if(accAccService.updateSlipDel(map) > 0) {
					continue;
				} else {
					throw new Exception();
				}
			}

			params.add("regDtm", model.get("regDtm").toString());
			List<Object> slipList = accAccService.getSlipList(params);
			addObject(models, slipList);
		} catch(Exception e) {
			e.printStackTrace();
		}

		return "jsonView";
	}



	/**
     * 20160427 채선영
     * 대출거래내역에서 계좌번호를 입력하고 조회한다.
     * @param request HTTP 요청
     * @param model 모델
     * @return 뷰이름
     */
	@Access
	@RequestMapping("/acc/accAcc/acco4040Search")
	public String acco4040Search(HttpServletRequest request, Model model){

		ParamsVO params = getParams(request);

		Object result = accAccService.acco4040Search(params);

		addObject(model, result);

		return "/acc/accacc/acco4040";
	}

	/**
     * 20160502 채선영
     * 대출거래내역에서 계좌번호를 입력하고 조회한다.
     * @param request HTTP 요청
     * @param model 모델
     * @return 뷰이름
     */
	@Access
	@RequestMapping("/acc/accAcc/acco4010Search")
	public String acco4010Search(HttpServletRequest request, Model model){

		ParamsVO params = getParams(request);

		Object result = accAccService.acco4010Search(params);

		addObject(model, result);

		return "/acc/accacc/acco4010";
	}

	@RequestMapping("/acc/accAcc/acco4020Search")
	public String acco4020Search(HttpServletRequest request, Model model) {

		ParamsVO params = getParams(request);

		Object asset = accAccService.getAssetList(params);
		model.addAttribute("asset", asset);

		Object bond = accAccService.getBondList(params);
		model.addAttribute("bond", bond);

		Object capital = accAccService.getCapitalList(params);
		model.addAttribute("capital", capital);

		return "/acc/accacc/acco4020";
	}

	/**
	 * 마감 팝업을 실행
	 * @param request
	 * @param model
	 * @return
	 * @throws Throwable
	 */
	@Access
	@RequestMapping("/acc/accAcc/acco9002")
	public String acco9002(HttpServletRequest request, Model model) throws Throwable {
		ParamsVO params = getParams(request);
//		PagingVO list = accAccService.getSlipDlnStatList(params);

//		addObject(model, list);
		addObject(model, "params", params);

		return "/popup/acc/acco9002";
	}

	/**
	 * 마감 메뉴
	 * @param request
	 * @param model
	 * @return
	 * @throws Throwable
	 */
	@Access
	@RequestMapping("/acc/accAcc/acco4051")
	public String acco4051(HttpServletRequest request, Model model) throws Throwable {
		ParamsVO params = getParams(request);
		addObject(model, "params", params);

		return "/acc/accacc/acco4051";
	}

	/**
	 * 미마감된 일자와 건수를 조회
	 * @param request
	 * @param model
	 * @param models
	 * @param referer
	 * @return
	 */
	@Access
	@RequestMapping("/acc/accAcc/acco4051Search")
	public String acco4051Search(HttpServletRequest request, @RequestBody Map<String, Object> model, Model models, @RequestHeader(value="referer", required=false) final String referer) {
		ParamsVO params = getParams(request);

		List<Object> slipList = accAccService.getSlipDlnStatList(params);
		addObject(models, slipList);

		return "jsonView";
	}

	/**
	 * 선택된 일자의 마감를 실행 - 마감 실행
	 * @param request
	 * @param model
	 * @param models
	 * @param referer
	 * @return
	 */
	@Access
	@RequestMapping(value="/acc/accAcc/acco4051Update", method={RequestMethod.POST}, headers = "Accept=application/json")
	public String acco4051Update(HttpServletRequest request, @RequestBody Map<String, Object> model, Model models, @RequestHeader(value="referer", required=false) final String referer) {
		ParamsVO params = getParams(request);
		LoginUser loginUser = getLoginUser();

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			List list = (ArrayList) model.get("list");
			for(int i=0; i<list.size(); i++) {
				map = (Map<String, Object>) list.get(i);
				map.put("stafId", loginUser.getUserId());

				if(accAccService.updateClosed(map) > 0) {
					continue;
				} else {
					throw new Exception();
				}
			}

			List<Object> slipList = accAccService.getSlipDlnStatList(params);
			addObject(models, slipList);
		} catch(Exception e) {
			e.printStackTrace();
		}

		return "jsonView";
	}
}
