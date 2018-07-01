<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta name="selection" content="acco4051"/>
<script type="text/javascript">
$(function() {
	initComp();
	// 마스크를 바인딩한다.
    bindMask();
    // 이벤트를 바인딩한다.
    bindEvent();
});

window.onload = function() {
// 	acco4051Search();
// 	setTimeout(function() {}, 0);
}

function initComp() {
	acco4051Search();
}

function bindMask() {

}

/**
 * 이벤트를 바인딩한다.
 */
function bindEvent() {
    $(".acco4051-select-button").click(function(event) {
    	acco4051Search();
    });

    $(".acco4051-closed-button").click(function(event) {
    	var gc = grid.getCheckedRowList();
    	if(gc.length > 0) {
    		if(confirm("선택된 일자를 마감하시겠습니까?")) {
    	    	var gridList = grid.getCheckedRowList();
    			var params = {};
    			params.list = gridList;

    		    jQuery.ajax({
    		        headers: {'Accept':'application/json', 'Content-Type':'application/json; charset=utf-8'},
    		        type: 'POST',
    		        url: '<c:url value="/acc/accAcc/acco4051Update"/>',
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
    		alert("마감일자를 선택하세요.");
    		return;
    	}
    });
}

function gridInsertCallback(data) {
// 	console.log(JSON.stringify(data));
	grid.setRowList(data.data);
}

function acco4051Search() {
	var params = {};

    jQuery.ajax({
        headers: {'Accept':'application/json', 'Content-Type':'application/json; charset=utf-8'},
        type: 'POST',
        url: '<c:url value="/acc/accAcc/acco4051Search"/>',
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
        		<li>[4051] 전표 마감</li>
        	</ul>
        </div>

		<div id="cont_table1">
			<table class="bordered">
				<colgroup>
					<col width="" />
				</colgroup>
				<thead>
					<tr>
						<th></th>
					</tr>
				</thead>
				<tbody class="">
					<tr>
						<td class="bordered_right">
							<a href="#" class="btn btn11 acco4051-select-button">
								<span style="text-align:right;">조회</span>
							</a>
							<a href="#" class="btn btn13 acco4051-closed-button">
								<span>마감</span>
							</a>
						</td>
					</tr>
				</tbody>
			</table>
		</div>

		<div id="cont_table">

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
    columnModelList : [
        {
            "title" : "<b>발생일자</b>",
            "columnName" : "regDtm",
            "align" : "center",
            "width" : 100
        },
    	{
            "title" : "미마감건수",
            "columnName" : "cnt",
            "width" : 110,
            "align" : "right",
            "editOption" : {
                type: 'normal'
            }
        }
    ],
    footer: {
        height: 27,
        columnContent: {
        	regDtm: {
                template: function(valueMap) {
                    return valueMap.cnt + "건";
                }
            },
            cnt: {
                template: function(valueMap) {
                	return "총 " + String(valueMap.sum).replace(/(\d)(?=(\d{3})+$)/gi, "$1,") + "건";
                }
            }
        }
    }
});
</script>
		</div>
	</div>
</div>
</form>

</body>
</html>