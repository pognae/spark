<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta name="selection" content="acco4050"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="/images/favicon.ico" type="/image/ico" />

<title>SPARK | </title>

<!-- Bootstrap -->
<link href="/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Font Awesome -->
<link href="/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">

<!-- NProgress -->
<link href="/vendors/nprogress/nprogress.css" rel="stylesheet">

<!-- iCheck -->
<link href="/vendors/iCheck/skins/flat/green.css" rel="stylesheet">

<!-- bootstrap-progressbar -->
<link href="/vendors/bootstrap-progressbar/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">

<!-- JQVMap -->
<link href="/vendors/jqvmap/dist/jqvmap.min.css" rel="stylesheet"/>

<!-- bootstrap-daterangepicker -->
<link href="/vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

<!-- Custom Theme Style -->
<link href="/build/css/custom.min.css" rel="stylesheet">

</head>

	<body class="nav-md">
		<div class="container body">
			<div class="main_container">
				<div class="col-md-3 left_col">
					<div class="left_col scroll-view">
						<div class="navbar nav_title" style="border: 0;">
							<a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>SPARK</span></a>
						</div>

						<div class="clearfix"></div>

						<!-- menu profile quick info -->
						<!--
						<div class="profile clearfix">
							<div class="profile_pic">
								<img src="/images/img.jpg" alt="..." class="img-circle profile_img">
							</div>
							<div class="profile_info">
								<span>Welcome,</span>
								<h2>John Doe</h2>
							</div>
						</div>
						 -->
						<!-- /menu profile quick info -->

						<br />

						<!-- sidebar menu -->
						<c:import url="/comm/leftMenu"/>
						<!-- /sidebar menu -->

					</div>
				</div>

				<!-- top navigation -->
				<div class="top_nav">
					<div class="nav_menu">
						<nav>
							<div class="nav toggle">
								<a id="menu_toggle"><i class="fa fa-bars"></i></a>
							</div>

							<ul class="nav navbar-nav navbar-right">
								<li class="">
									<a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
										<!-- 
										<img src="/images/img.jpg" alt="">
										 -->
										관리자
										<span class=" fa fa-angle-down"></span>
									</a>
									<ul class="dropdown-menu dropdown-usermenu pull-right">
										<li><a href="javascript:;"> 회원정보</a></li>
										<li><a href="javascript:;">Help</a></li>
										<li><a href="login.html"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
									</ul>
								</li>
								<!-- 
								<li role="presentation" class="dropdown">
									<a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">
										<i class="fa fa-envelope-o"></i>
										<span class="badge bg-green">6</span>
									</a>
									<ul id="menu1" class="dropdown-menu list-unstyled msg_list" role="menu">
										<li>
											<a>
												<span class="image"><img src="/images/img.jpg" alt="Profile Image" /></span>
												<span>
													<span>John Smith</span>
													<span class="time">3 mins ago</span>
												</span>
												<span class="message">
													Film festivals used to be do-or-die moments for movie makers. They were where...
												</span>
											</a>
										</li>
									</ul>
								</li>
								 -->
							</ul>
						</nav>
					</div>
				</div>
				<!-- /top navigation -->

				<!-- page content -->
				<div class="right_col" role="main">

<script type="text/javascript">
$(function() {
	initComp();
	// 마스크를 바인딩한다.
    bindMask();
    // 이벤트를 바인딩한다.
    bindEvent();

	$('#regDtm').datepicker({
		  showon: 'button'
			//,inline: true
     	, buttonText: 'Show Date'
     	, buttonImageOnly: true
     	, buttonImage:'/images/btn_icon_calendar.gif'
		, showButtonPanel: true
		});
	$('#regDtm').val(
		$.datepicker.formatDate('yy-mm-dd', new Date())
	);
});

function initComp() {
    //  텍스트필드에 디폴트 값을 설정한다.
    if (com.wow.fn.isEmpty($("#bascDt").val())) {
        $("#bascDt").val(com.wow.fn.formatDate(new Date()));
    }
}

function bindMask() {
    // 적용시작일자 텍스트필드에 날짜 마스크를 바인딩한다.
    $("#bascDt").setMask({ mask:"9999-19-39" });

    // 적용시작일자 텍스트필드에 날짜 선택기를 바인딩한다.
    $("#bascDt").datepicker();
}

/**
 * 이벤트를 바인딩한다.
 */
function bindEvent() {
    $(".acco4050-update-button").click(function(event) {
    	var gridList = grid.getRowList();

    	if(dbtHdlTtlAmt != crtHdlTtlAmt) {
            alert("차/대변의 합계 금액이 일치하여 합니다.");
            return;
    	}

    	if(gridList.length == 0) {
    		alert("등록할 전표를 입력하세요.");
    		return;
    	}

    	for(var i=0; i<gridList.length; i++) {
    		var crtName = grid.getValue(i, "crtAcctName");
    		var dbtName = grid.getValue(i, "dbtAcctName");
    		var crtAmt = grid.getValue(i, "crtHdlAmt");
    		var dbtAmt = grid.getValue(i, "dbtHdlAmt");
    		var procId = grid.getValue(i, "procIo");

    		if(crtAmt == 0 || crtAmt == "") {
    			alert("대변 금액을 입력하세요.");
    			return;
    		}

    		if(dbtAmt == 0 || dbtAmt == "") {
    			alert("차변 금액을 입력하세요.");
    			return;
    		}

    		if(procId == "01") {
    			if(dbtName == "") {
    				alert("차변 계정을 선택하세요.");
    				return;
    			}
    		}

    		if(procId == "02") {
    			if(crtName == "") {
    				alert("대변 계정을 선택하세요.");
    				return;
    			}
    		}

    		if(procId == "03") {
    			if(crtName == "") {
    				alert("대변 계정을 선택하세요.");
    				return;
    			}

    			if(dbtName == "") {
    				alert("차변 계정을 선택하세요.");
    				return;
    			}
    		}
    	}

		var params = {};
		params.list = gridList;
		params.regDtm = $("#regDtm").val();
		console.log(JSON.stringify(params));

	    jQuery.ajax({
	        headers: {'Accept':'application/json', 'Content-Type':'application/json; charset=utf-8'},
	        type: 'POST',
	        url: '<c:url value="/acc/accAcc/acco4050Insert"/>',
	        dataType: 'json',
	        data: JSON.stringify(params),
	        success:function(result) {
// 	        	console.log(JSON.stringify(result));
	        	gridInsertCallback(result);
	        },
	        error:function(e){
	        	callBackError(e);
	        }
	    });
    });

    $(".acco4050-select-button").click(function(event) {
    	acco4050Search();
    });

    $(".grid-add-button").click(function(event) {
		grid.appendRow(gridData, "focus:true");
    });

    $(".acco4050-closed-button").click(function(event) {
    	/*
    	var path = "/acc/accAcc/acco9002";
    	centerwin(path, "1050", "650", "no");
    	*/
    	location.href("/acc/accAcc/acco4051");
    });

    $(".grid-del-button").click(function(event) {
    	var gc = grid.getCheckedRowList();
    	if(gc.length > 0) {
    		console.log(grid.getCheckedRowList());
			//grid.setValue(grid.getSelectedRowKey(), "useYn", "N");
    		//grid.removeCheckedRows("true");
    		if(confirm("선택된 전표를 삭제하시겠습니까?")) {
    	    	var gridList = grid.getCheckedRowList();
    			var params = {};
    			params.list = gridList;
    			params.regDtm = $("#regDtm").val();

    		    jQuery.ajax({
    		        headers: {'Accept':'application/json', 'Content-Type':'application/json; charset=utf-8'},
    		        type: 'POST',
    		        url: '<c:url value="/acc/accAcc/acco4050Delete"/>',
    		        dataType: 'json',
    		        data: JSON.stringify(params),
    		        success:function(result) {
    		        	gridInsertCallback(result);
    		        },
    		        error:function(e){
    		        	callBackError(e);
    		        }
    		    });
    		}
    	} else {
    		alert("삭제할 전표를 선택하세요.");
    	}
    });
}

function gridInsertCallback(data) {
// 	console.log(JSON.stringify(data));
	grid.setRowList(data.data);
// 	grid.setRowList(gridData);
}

function acco4050Search() {
    if (com.wow.fn.isEmpty($("#regDtm").val())) {
        alert("조회일자를 입력하여 주십시오.");
        $("#regDtm").focus();
        return;
    }

	var params = {};
	params.regDtm = $("#regDtm").val();

    jQuery.ajax({
        headers: {'Accept':'application/json', 'Content-Type':'application/json; charset=utf-8'},
        type: 'POST',
        url: '<c:url value="/acc/accAcc/acco4050Search"/>',
        dataType: 'json',
        data: JSON.stringify(params),
        success:function(result) {
        	gridInsertCallback(result);
        },
        error:function(e){
        	callBackError(e);
        }
    });
}

function getAcctCode(col, params) {
 	rowKey = params.rowKey;
 	selectCol = col;

    doPost({
    	url:"/acc/accAcc/getAcctCode",
    	before:function(options) {
    		return {
    			searchText: params.value
    		}
    	},
    	after:getAcctCodeCallBack
    });
}

var rowKey = "";
var selectCol = "";
function getAcctCodeCallBack(data) {

	if(data.cnt == 1) {
		if(selectCol == "crt") {
			grid.setValue(rowKey, "crtAcctCode", data.acctCode);
			grid.setValue(rowKey, "crtAcctName", data.acctAbbr);
		} else if(selectCol == "dbt") {
			grid.setValue(rowKey, "dbtAcctCode", data.acctCode);
			grid.setValue(rowKey, "dbtAcctName", data.acctAbbr);
		}

		rowKey = "";
		selectCol = "";
	} else {
		if(selectCol == "crt") {
			popup(grid.getValue(rowKey, "crtAcctName"));
		} else if(selectCol == "dbt") {
			popup(grid.getValue(rowKey, "dbtAcctName"));
		}
	}
}

function popup(data) {
	var path = "/acc/accAcc/acco9001?searchText=" + data;	//팝업창에 출력될 페이지 URL
	//centerwin(path, "1050", "650", "no");
    openWindow(path, "acco9001", {
        width:1050,
        height:650
    }, {
        callback:"popupCallBack"
    });
}

function popupCallBack(data) {
	if(selectCol == "crt") {
		grid.setValue(rowKey, "crtAcctCode", data.acctCode);
		grid.setValue(rowKey, "crtAcctName", data.acctAbbr);
		grid.focus(rowKey, "crtHdlAmt");
	} else if(selectCol == "dbt") {
		grid.setValue(rowKey, "dbtAcctCode", data.acctCode);
		grid.setValue(rowKey, "dbtAcctName", data.acctAbbr);
		grid.focus(rowKey, "dbtHdlAmt");
	}

	rowKey = "";
	selectCol = "";
}

/*
function comCodeDataCallback(data) {
	smprocioData = data;
}
*/

var gridData = [
	{
		"regDtm":getToday(),
		"slipNo":"",
		"procIo":"03",
		"dbtAcctCode":"",
		"dbtAcctName":"",
		"dbtHdlAmt":"0",
		"crtAcctCode":"",
		"crtAcctName":"",
		"crtHdlAmt":"0",
		"trAccNo":"",
		"dest":"",
		"accNo":"",
		"useYn":""
	}
];
</script>
</head>

<body>
<form id="frm" name="frm" method="post">
<input type="hidden" id="page" name="page" value="<c:out value="${param.page}" default="1" />" />
<input type="hidden" id="rows" name="rows" value="<c:out value="${param.rows}" default="5" />" />

<div class="Content">
    <div class="">
        <div id="cont_title">
        	<ul>
        		<li>[4050] 일반전표</li>
        	</ul>
        </div>

		<div id="cont_table1">
			<table class="bordered">
				<colgroup>
					<col width="12.5%" />
					<col width="57.5%" />
					<col width="30%" />
				</colgroup>
				<thead>
					<tr>
						<th></th>
						<th class="borderless"></th>
						<th></th>
					</tr>
				</thead>
				<tbody class="">
					<tr>
						<td><span class="essential"><span>*</span></span>조회일자</td>
						<td class="borderless">
				            <input type="text" name="regDtm" id="regDtm" size="15" class="text" value="${regDtm}" style="vertical-align:top"/>
						</td>
						<td class="bordered_right">
							<a href="#" class="btn btn11 acco4050-select-button">
								<span style="text-align:right;">조회</span>
							</a>
							<a href="#" class="btn btn12 acco4050-update-button">
								<span>등록 및 변경</span>
							</a>
							<a href="#" class="btn btn13 acco4050-closed-button">
								<span>마감</span>
							</a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>

		<div id="cont_table">

			<div class="table_maintitle_r" style="height: 12px;">
				<a href="#" class="btn btn12 grid-add-button"><span>추가</span></a>
				<a href="#" class="btn btn12 grid-del-button"><span>삭제</span></a>
			</div>

			<div class="code-html">
				<div id="grid"></div>
			</div>

<script type="text/javascript" class="code-js">
var grid = new tui.Grid({
    el: $('#grid'),
    selectType: 'checkbox',
    columnFixIndex: 5,
    resizeHandle: {},
    displayRowCount: 24,
    headerHeight: 52,
    columnMerge : [
        {
            columnName : "mergeColumn1",
            title : "차변",
            columnNameList : ["dbtAcctName", "dbtHdlAmt"]
        },
        {
            columnName : "mergeColumn2",
            title : "대변",
            columnNameList : ["crtAcctName", "crtHdlAmt"]
        }
    ],
    columnModelList : [
        {
            "title" : "<b>발생일자</b>",
            "columnName" : "regDtm",
            "align" : "center",
            "width" : 100
        },
    	{
            "title" : "전표번호",
            "columnName" : "slipNo",
            "width" : 110,
            "editOption" : {
                type: 'normal'
            }
        },
    	{
            "title" : "구분",
            "columnName" : "procIo",
            "width" : 60,
            "align" : "center",
            "relationList" : [
                {
                    "columnList" : ["dbtAcctName"],
	                "isEditable": function(columnValue, rowData) {
                        if (rowData["procIo"] == "01") {
                            return true;
                        } else if (rowData["procIo"] == "02") {
                            return false;
                        } else {
                        	return true;
                        }
	                }
                },
            	{
                    "columnList" : ["crtAcctName"],
	                "isEditable": function(columnValue, rowData) {
                        if (rowData["procIo"] == "01") {
                            return false;
                        } else if (rowData["procIo"] == "02") {
                            return true;
                        } else {
                        	return true;
                        }
	                }
                }
            ],
            "editOption" : {
                "type" : 'select',
                "list" : [
					<c:forEach items="${hdlDivList}" var="item" varStatus="status">
						{"value" : "${item.codeValue}", "text" : "${item.codeName}"}<c:if test="${!status.last}">,</c:if>
					</c:forEach>
                ],
                "changeBeforeCallback" : function(changeEvent){

                }
            }
        },
        {
            "title" : "계정코드",
            "columnName" : "dbtAcctCode",
            "width" : 100,
            "align" : "right",
            "isHidden" : true,
            "editOption" : {
                type: 'text'
            }
        },
        {
            "title" : "<b>계정과목</b>",
            "columnName" : "dbtAcctName",
            "width" : 140,
            "align" : "left",
            "editOption" : {
                type: 'text',
                "singleClickEdit" : "true",
                "changeAfterCallback" : function(e) {
                    if(rowKey === "") {
                    	getAcctCode('dbt', e);
                    }
                }
            }
        },
        {
            "title" : "<b>금액</b>",
            "columnName" : "dbtHdlAmt",
            "width" : 100,
            "align" : "right",
            "isRequired" : true,
            "editOption" : {
                "type" : 'text'
            },
//             "afterContent" : " 원",
            "formatter" : function(columnValue) {
                var sValue = String(columnValue) || "0";
//                 return sValue.replace(/(\d)(?=(\d{3})+$)/gi, "$1,")+" 원";
                return sValue.replace(/(\d)(?=(\d{3})+$)/gi, "$1,");
            },
        },
        {
            "title" : "계정코드",
            "columnName" : "crtAcctCode",
            "width" : 100,
            "align" : "right",
            "isHidden" : true,
            "editOption" : {
                type: 'text'
            }
        },
        {
            "title" : "<b>계정과목</b>",
            "columnName" : "crtAcctName",
            "width" : 140,
            "align" : "left",
            "editOption" : {
                type: 'text',
                "singleClickEdit" : "true",
                "changeBeforeCallback" : function(e) {
                    if(rowKey === "") {
                    	getAcctCode('crt', e);
                    }
                }
            }
        },
        {
            "title" : "<b>금액</b>",
            "columnName" : "crtHdlAmt",
            "width" : 100,
            "align" : "right",
            "isRequired" : true,
            "editOption" : {
                "type" : 'text'
            },
//             "afterContent" : " 원",
            "formatter" : function(columnValue) {
                var sValue = String(columnValue) || "0";
//                 return sValue.replace(/(\d)(?=(\d{3})+$)/gi, "$1,")+" 원";
                return sValue.replace(/(\d)(?=(\d{3})+$)/gi, "$1,");
            },
        },
        {
            "title" : "<b>대체계좌</b>",
            "width" : 90,
            "align" : "left",
            "columnName" : "trAccNo",
            "editOption" : {
                "type" : 'text'
            }
        },
        {
            "title" : "<b>적요</b>",
            "width" : 150,
            "align" : "left",
            "columnName" : "dest",
            "editOption" : {
                "type" : 'text'
            }
        },
        {
            "title" : "<b>계좌번호</b>",
            "width" : 150,
            "isHidden" : true,
            "align" : "left",
            "columnName" : "accNo",
            "editOption" : {
                "type" : 'text'
            }
        },
        {
            "title" : "<b>삭제여부</b>",
            "width" : 150,
            "isHidden" : true,
            "align" : "left",
            "columnName" : "useYn",
            "editOption" : {
                "type" : 'text'
            }
        }
    ],
    footer: {
        height: 27,
//         align : right,
        columnContent: {
        	slipNo: {
                template: function(valueMap) {
                    return valueMap.cnt + "건";
                }
            },
            dbtHdlAmt: {
//         		"align" : "right",
                template: function(valueMap) {
                	dbtHdlTtlAmt = valueMap.sum;
                	return String(dbtHdlTtlAmt).replace(/(\d)(?=(\d{3})+$)/gi, "$1,");
                }
            },
            crtHdlAmt: {
                template: function(valueMap) {
                	crtHdlTtlAmt = valueMap.sum;
                	return String(crtHdlTtlAmt).replace(/(\d)(?=(\d{3})+$)/gi, "$1,");
                }
            }
        }
    }
});

grid.setRowList(gridData);
var dbtHdlTtlAmt = 0;
var crtHdlTtlAmt = 0;
</script>
		</div>
	</div>
</div>
</form>

				</div>
				<!-- /page content -->

				<!-- footer content -->
				<footer>
					<div class="pull-right">
						SPARK - Made by <a href="https://www.wowpmd.co.kr">WOWPMD</a>
					</div>
					<div class="clearfix"></div>
				</footer>
				<!-- /footer content -->
				
			</div>
		</div>

		<!-- jQuery -->
		<script src="/vendors/jquery/dist/jquery.min.js"></script>
		<!-- Bootstrap -->
		<script src="/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
		<!-- FastClick -->
		<script src="/vendors/fastclick/lib/fastclick.js"></script>
		<!-- NProgress -->
		<script src="/vendors/nprogress/nprogress.js"></script>
		<!-- Chart.js -->
		<script src="/vendors/Chart.js/dist/Chart.min.js"></script>
		<!-- gauge.js -->
		<script src="/vendors/gauge.js/dist/gauge.min.js"></script>
		<!-- bootstrap-progressbar -->
		<script src="/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
		<!-- iCheck -->
		<script src="/vendors/iCheck/icheck.min.js"></script>
		<!-- Skycons -->
		<script src="/vendors/skycons/skycons.js"></script>
		<!-- Flot -->
		<script src="/vendors/Flot/jquery.flot.js"></script>
		<script src="/vendors/Flot/jquery.flot.pie.js"></script>
		<script src="/vendors/Flot/jquery.flot.time.js"></script>
		<script src="/vendors/Flot/jquery.flot.stack.js"></script>
		<script src="/vendors/Flot/jquery.flot.resize.js"></script>
		<!-- Flot plugins -->
		<script src="/vendors/flot.orderbars/js/jquery.flot.orderBars.js"></script>
		<script src="/vendors/flot-spline/js/jquery.flot.spline.min.js"></script>
		<script src="/vendors/flot.curvedlines/curvedLines.js"></script>
		<!-- DateJS -->
		<script src="/vendors/DateJS/build/date.js"></script>
		<!-- JQVMap -->
		<script src="/vendors/jqvmap/dist/jquery.vmap.js"></script>
		<script src="/vendors/jqvmap/dist/maps/jquery.vmap.world.js"></script>
		<script src="/vendors/jqvmap/examples/js/jquery.vmap.sampledata.js"></script>
		<!-- bootstrap-daterangepicker -->
		<script src="/vendors/moment/min/moment.min.js"></script>
		<script src="/vendors/bootstrap-daterangepicker/daterangepicker.js"></script>

		<!-- Custom Theme Scripts -->
		<!-- 
		<script src="/build/js/custom.min.js"></script>
		 -->
		<script src="/build/js/custom.js?v=<%=System.currentTimeMillis()%>"></script>

	</body>
</html>