package com.wowpmd.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.kr.framework.security.Access;
import com.wowpmd.acc.service.AccAccService;
import com.wowpmd.common.model.FileMeta;
import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.common.service.SystemService;
import com.wowpmd.service.BasicService;
import com.wowpmd.util.SoftUtil;
import com.wowpmd.vo.JsonObj;


@Controller
public class BasicController extends DefaultController {

	private static final Logger log  = LoggerFactory.getLogger(BasicController.class);

	private static SoftUtil util = new SoftUtil();

	private LinkedList<FileMeta> files = new LinkedList<FileMeta>();

	private FileMeta fileMeta = null;

	private static String CASH_ACCT_CODE = "111000";//현금 계정 코드

	@Autowired
	private AccAccService accAccService;

	@Autowired
	private BasicService basicService;

	@Autowired
	private SystemService systemService;

	/**
	 * 코드관리
	 * @param paramMap
	 * @param model
	 * @return
	 * @throws Throwable
	 */
	@Access
	@RequestMapping("/basic/code1010")
	public String acnt1030(@RequestParam Map<String, Object> paramMap, Model model) throws Throwable{
		return "/basic/code1010";
	}

	/**
	 * 코드관리 검색
	 * @param request
	 * @param models
	 * @param model
	 * @param referer
	 * @return
	 * @throws IOException
	 */
	/*
	@Access
	@RequestMapping(value="/basic/code1010Search", method={RequestMethod.POST}, headers = "Accept=application/json")
	public String code1010Search(HttpServletRequest request,
					Model models,
					@RequestBody Map<String, Object> model,
					@RequestHeader(value="referer", required=false) final String referer
			) throws IOException {

		ParamsVO params = getParams(request);
		params.add("condition", model.get("condition").toString());
		params.add("searchWord", model.get("searchWord").toString());

		List<Object> list = basicService.code1010Search(params);

//		addObject(models, "total", 1);
//		addObject(models, "page", 1);
//		addObject(models, "records", 6);
		addObject(models, "rows", list);

		return "jsonView";
	}
	*/

	@Access
	@RequestMapping("/basic/code1010Search")
	public String code1010Search(HttpServletRequest request, Model model) throws IOException {

		ParamsVO params = getParams(request);

		Object list = basicService.code1010Search(params);

		addObject(model, list);

		return "/basic/code1010";
	}

	/**
	 * 코드관리 수정
	 * @param request
	 * @param models
	 * @param model
	 * @param id
	 * @param cellName
	 * @param cellValue
	 * @return
	 */
	@RequestMapping("/basic/code1010Update")
	public @ResponseBody String code1010Update (HttpServletRequest request,
					Model models,
					@RequestBody Map<String, Object> model,
					@RequestParam(value = "id") Integer id,
					@RequestParam(value = "cellName") String cellName,
					@RequestParam(value = "cellValue") String cellValue) {

		ParamsVO params = getParams(request);
		params.add("id", model.get("id").toString());
		params.add("cellName", model.get("cellName").toString());
		params.add("cellValue", model.get("cellValue").toString());


		return "SUCCESS";
	}

	/*
	@RequestMapping("/basic/getUserList")
	@ResponseBody
	public JsonObj getJqGridJson (HttpServletRequest request,
			Model models,
			@RequestBody Map<String, Object> model,
			@RequestParam(value="page", required=false, defaultValue="1") String page,
			@RequestParam(value="rows", required=false, defaultValue="") String rows) throws Exception{

		ParamsVO params = getParams(request);
		params.add("regDtm", model.get("regDtm").toString());

		List<Object> list = basicService.code1010Search(params);

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

		HashMap<String, Object> tempMap = new HashMap<String, Object>();
		int size = list.size();

		for(int i=0; i<size; i++) {
			tempMap.put("user_id"		, list.get(i).getUser_id());
			tempMap.put("user_email"	, list.get(i).getUser_email());
			tempMap.put("user_job"	, list.get(i).getUser_job());
			tempMap.put("user_phoneNumber"	, list.get(i).getUser_phoneNumber());

			tempMap.put("user_name"		, list.get(i).getUser_name());

			tempMap.put("user_birth"		, list.get(i).getUser_birth());
			tempMap.put("user_sex"		, list.get(i).getUser_sex());
			tempMap.put("user_snsId"	, list.get(i).getUser_snsId());
			tempMap.put("user_status", list.get(i).getUser_status());
			resultList.add(tempMap);

			tempMap = new HashMap<String, Object>();
		}



		JsonObj jsonObj = new JsonObj();
		//임시
		jsonObj.setRecords(5);
		jsonObj.setTotal(5);
		jsonObj.setPage(5);
		jsonObj.setRows(resultList);

		return jsonObj;
	}
*/


/*
	@Access
	@RequestMapping(value="/basic/code1010Search", method={RequestMethod.GET}, headers = "Accept=application/json")
	public @ResponseBody String code1010Search(HttpServletRequest request, HttpServletResponse response,
			Model models,
			@RequestHeader(value="referer", required=false) final String referer,
			@RequestBody Map<String, Object> model,
//			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
//			@RequestParam(value = "max", required = false, defaultValue = "20") int max,
			@ModelAttribute JqgridVO jqgridVo
			) throws IOException {

		ParamsVO params = getParams(request);

		//		params.add("regDtm", model.get("regDtm").toString());
//		List<Object> list = basicService.code1010Search(params);
//		int startIdx = (page - 1) * max;
//		int endIdx = Math.min(startIdx + max, list.size());

//		addObject(models, list);

        PrintWriter out = null;

        response.setCharacterEncoding("UTF-8");

        String quotZero = request.getParameter("param");
        System.out.println(quotZero);
        quotZero = quotZero.replaceAll("&quot;", "\"");
        System.out.println(quotZero);

        Map<String, Object> castMap = new HashMap<String, Object>();

        castMap = JsonUtil.JsonToMap(quotZero); // quotZero json을 맵으로 변환시킨다.

        jqgridVo.setServiceImplYn((String) castMap.get("serviceImplYn"));

        List<Object> jqGridList = basicService.code1010Search(params);
//        EgovMap jqGridListCnt = jqgridservice.selectJqgridListCnt(jqgridVo);

        HashMap<String, Object> resMap = new HashMap<String, Object>();

//        resMap.put("records", jqGridListCnt.get("toatalTotCnt"));
        resMap.put("records", jqGridList.size());
        resMap.put("rows", jqGridList);
        resMap.put("page", request.getParameter("page"));
        System.out.println("page from request "+request.getParameter("page"));
//        resMap.put("total", jqGridListCnt.get("totalpage"));
        resMap.put("total", jqGridList.size());

        out = response.getWriter();

        out.write(JsonUtil.HashMapToJson(resMap).toString());


        ObjectMapper mapper = new ObjectMapper();
        String value = mapper.writeValueAsString(resMap);

//		return "jsonView";
        return value;
	}
*/
/*
	@RequestMapping("/test")
	public @ResponseBody JsonObj test(
			@RequestParam(value = "page", required=false) String page,
			@RequestParam(value = "rows", required=false) String rows,
			@RequestParam(value = "sidx", required=false) String sidx,
			@RequestParam(value = "sord", required=false) String sord,
			@RequestParam(value = "searchField", required=false) String searchField,
			@RequestParam(value = "searchString", required=false) String searchString) {

		// TODO : 데이터 가져오기 구현..

		JsonObj obj = null;
		obj.setRows(List<Map>);  // list<map>형태의 받아온 데이터를 가공해서 셋하고 리턴
		return obj;
	}
*/

	/*
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

	 *//**
	 * 일반전표
	 * @param paramMap
	 * @param model
	 * @return
	 * @throws Throwable
	 *//*
	@Access
	@RequestMapping("/acc/accAcc/acco4050")
	public String acco4050(@RequestParam Map<String, Object> paramMap, Model model) throws Throwable {

		//addObject(model, "bnkCdList", systemService.getCodeHandler().getCodes("BNK_CD"));
		addObject(model, "hdlDivList", systemService.getCodeHandler().getCodes("HDL_DIV"));

		return "/acc/accacc/acco4050";
	}

	  *//**
	  * 일반전표 조회
	  * @param request
	  * @param model
	  * @return
	  *//*
	@Access
	@RequestMapping("/acc/accAcc/acco4050Search")
	public String acco4050Search(HttpServletRequest request, @RequestBody Map<String, Object> model, Model models, @RequestHeader(value="referer", required=false) final String referer) {
		ParamsVO params = getParams(request);

		params.add("regDtm", model.get("regDtm").toString());
		List<Object> slipList = accAccService.getSlipList(params);
		addObject(models, slipList);

		return "jsonView";
	}


	   *//**
	   * 계정과목 조회
	   * @param request
	   * @param model
	   * @return
	   *//*
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

	    *//**
	    * 계정과목 조회 팝업
	    * @param paramMap
	    * @param model
	    * @return
	    * @throws Throwable
	    *//*
	@Access
	@RequestMapping("/acc/accAcc/acco9001")
	public String acco9001(HttpServletRequest request, @RequestParam Map<String, Object> paramMap, Model model) throws Throwable {
		ParamsVO params = getParams(request);
		PagingVO list = accAccService.getAcctCodeList(params);

		addObject(model, list);
		addObject(model, "params", params);

		return "/popup/acc/acco9001";
	}

	     *//**
	     * 전표 입력
	     * @param request
	     * @param model
	     * @param referer
	     * @return
	     *//*
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



	      *//**
	      * 20160427 채선영
	      * 대출거래내역에서 계좌번호를 입력하고 조회한다.
	      * @param request HTTP 요청
	      * @param model 모델
	      * @return 뷰이름
	      *//*
	@Access
	@RequestMapping("/acc/accAcc/acco4040Search")
	public String acco4040Search(HttpServletRequest request, Model model){

		ParamsVO params = getParams(request);

		Object result = accAccService.acco4040Search(params);

		addObject(model, result);

		return "/acc/accacc/acco4040";
	}

	       *//**
	       * 20160502 채선영
	       * 대출거래내역에서 계좌번호를 입력하고 조회한다.
	       * @param request HTTP 요청
	       * @param model 모델
	       * @return 뷰이름
	       *//*
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

	        *//**
	        * 마감 팝업을 실행
	        * @param request
	        * @param model
	        * @return
	        * @throws Throwable
	        *//*
	@Access
	@RequestMapping("/acc/accAcc/acco9002")
	public String acco9002(HttpServletRequest request, Model model) throws Throwable {
		ParamsVO params = getParams(request);
//		PagingVO list = accAccService.getSlipDlnStatList(params);

//		addObject(model, list);
		addObject(model, "params", params);

		return "/popup/acc/acco9002";
	}

	         *//**
	         * 마감 메뉴
	         * @param request
	         * @param model
	         * @return
	         * @throws Throwable
	         *//*
	@Access
	@RequestMapping("/acc/accAcc/acco4051")
	public String acco4051(HttpServletRequest request, Model model) throws Throwable {
		ParamsVO params = getParams(request);
		addObject(model, "params", params);

		return "/acc/accacc/acco4051";
	}

	          *//**
	          * 미마감된 일자와 건수를 조회
	          * @param request
	          * @param model
	          * @param models
	          * @param referer
	          * @return
	          *//*
	@Access
	@RequestMapping("/acc/accAcc/acco4051Search")
	public String acco4051Search(HttpServletRequest request, @RequestBody Map<String, Object> model, Model models, @RequestHeader(value="referer", required=false) final String referer) {
		ParamsVO params = getParams(request);

		List<Object> slipList = accAccService.getSlipDlnStatList(params);
		addObject(models, slipList);

		return "jsonView";
	}

	           *//**
	           * 선택된 일자의 마감를 실행 - 마감 실행
	           * @param request
	           * @param model
	           * @param models
	           * @param referer
	           * @return
	           *//*
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
	            */
}
