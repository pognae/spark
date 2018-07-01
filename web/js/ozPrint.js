var filename = "";
var param = "";
var cbovalue = "";

//var formName = ;
/*function fnOpen(requNumb,requCust) {
	param = "";
	param += "resultChkData2=";
  	param += requNumb;
  	param += requCust;
} */  

function fnCboValue(value) {
	cbovalue = value;
	if(cbovalue == "10" && insCode2 != "08"){
		filename = "FIVS_RFFM";
	}else if(cbovalue == "10" && insCode2 == "08"){
		filename = "FIVS_RFFM_CHA";
	}else if(cbovalue == "20"){
		filename = "RDRG_MVIN_APFR";
	}else if(cbovalue == "30" && insCode2 != "06"){
		filename = "FIVS_RPTR2";
	}else if(cbovalue == "30" && insCode2 == "06"){
		filename = "FIVS_RPTR_GND2";
	}
	
}
  
function ozPrint(filename, param) {
	if(filename == ""){
		alert("출력할 리포트를 선택하세요.");
		return;
	}
	if(param == ""){
		alert("출력할 데이터를 선택하세요.");
		return;
	}
	//var url = "ozsvc::/ozrviewer/view.jsp?filename=" + filename
	var url = "http://110.10.0.35:8082/oz/ozrviewer/view.jsp?filename=" + filename
	//var url = "http://110.10.0.32/oz/ozrviewer/view.jsp?filename=" + filename
	if(param != '') {
		url += '&param=' + param;
	}
		
	window.open(url);
}