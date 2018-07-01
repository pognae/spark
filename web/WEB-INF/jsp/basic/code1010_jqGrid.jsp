<html>
<head>
<meta name="selection" content="code1010"/>
<style>
table {
	font-size:98%
}
</style>

</head>

<body>
<div class="code1010">
  	<div class="page-title">

    	<div class="row">
    		<div class="col-md-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>코드관리 </h2>
                    	<div class="clearfix"></div>
					</div>


					<div class="x_content">

						<div class="col-md-4 col-sm-4">
							<div class="form-group">
	                        	<label class="control-label col-md-4 col-sm-4 col-xs-12">코드명</label>
								<div class="col-md-8 col-sm-8 col-xs-12">
	                          		<input type="text" id="search" class="form-control" placeholder="">
	                        	</div>
	                      	</div>
                      	</div>

						<div class="col-md-4 col-sm-4">
                      	</div>

						<div class="col-md-4 col-sm-4">
							<a href="#" class="btn btn-primary pull-right code1010-add-btn">추가</a>
							<a href="#" class="btn btn-primary pull-right code1010-search-btn">검색</a>
						</div>

						<div class="col-md-12" style="margin-left:0px">
						    <div class="row">
						    	<div>
								    <table id="jqGrid"></table>
								    <div id="jqGridPager"></div>
						    	</div>
						    </div>
						</div>

<script>
	//console.log(document.body.scrollWidth);
	var width = document.body.scrollWidth - 341;
	//console.log(width);
	//$.jgrid.defaults.width = 780;
	$.jgrid.defaults.width = width;
	$.jgrid.defaults.responsive = true;
	//$.jgrid.defaults.styleUI = 'Bootstrap';
</script>

					</div>
				</div>
    		</div>
    	</div>
    </div>
</div>


<script type="text/javascript">
var params = {};
params.condition = $("#search").val();
params.searchWord = $("#search").val();

$(function() {
	initComp();
	// 마스크를 바인딩한다.
    bindMask();
    // 이벤트를 바인딩한다.
    bindEvent();

	$("#jqGrid").jqGrid({
		datatype: "local",
        colModel: [
            { label: '기준코드명', name: 'cdIdNm', index:'cdIdNm', key: true, width: 150, align:"center" },
            { label: '코드명', name: 'cdNm', index:'cdNm', width: 150, align:"center", editable:true },
            { label: '코드', name: 'cdVldVal', index:'cdVldVal', key: true, width: 150, align:"center" },
            { label: '적용시작일자', name: 'apclStrDt', index:'apclStrDt', width: 150, align:"center" },
            { label: '적용종료일자', name: 'apclEndDt', index:'apclEndDt', width: 150, align:"center", editable:true },
            { label: '사용여부', name: 'useYn', index:'useYn', width: 75, align:"center", editable:true }
        ],
		viewrecords: true,
        height: 600,//'auto',
        rowNum: 10000,
        rownumbers:true,
        cellEdit : true,
        cellsubmit:'remote',
        cellurl:'<c:url value="/basic/code1010Update"/>',
        beforeSubmitCell : function(rowid, name, value) {
			return {"id":rowid, "cellName":name, "cellValue":value}
       	},
       	afterSubmitCell : function(res) {    // 변경 후
            var aResult = $.parseJSON(res.responseText);
            var userMsg = "";
            if((aResult.msg == "success")) {
                userMsg = "데이터가 변경되었습니다.";
            }
            return [(aResult.msg == "success") ? true : false, userMsg];
        },
        jsonReader: {
            repeatitems: false,
            root: function (obj) { return obj.rows; },
            page: function (obj) { return 1; },
            total: function (obj) { return 1; },
            records: function (obj) { return obj.rows.length; }
        }
    });

	fetchGridData();
});

function initComp() {
    //  텍스트필드에 디폴트 값을 설정한다.

}

function bindMask() {
    // 적용시작일자 텍스트필드에 날짜 마스크를 바인딩한다.
    //$("#bascDt").setMask({ mask:"9999-19-39" });

    // 적용시작일자 텍스트필드에 날짜 선택기를 바인딩한다.
    //$("#bascDt").datepicker();
}

/**
 * 이벤트를 바인딩한다.
 */
function bindEvent() {
    $(".code1010-search-btn").click(function(event) {
    	$('#jqGrid').jqGrid('clearGridData');
    	fetchGridData();
    });

    $(".code1010-add-btn").click(function(event) {
    	addRow();
    });
}

function fetchGridData() {
	jQuery.ajax({
	    headers: {'Accept':'application/json', 'Content-Type':'application/json; charset=utf-8'},
	    type: 'POST',
	    url: '<c:url value="/basic/code1010Search"/>',
	    dataType: 'json',
	    data: JSON.stringify(params),
	    success:function(result) {
	    	if(result != null) {
		    	$("#jqGrid").setGridParam({data:result.rows}).trigger("reloadGrid");
	    	}
	    },
	    error:function(e) {
	    	callBackError(e);
	    }
	});
}

function addRow() {
	jQuery("#jqGrid").jqGrid('addRow', {
    	rowID : 'AAA',          	//중복되지 않게 rowid설정
        initdata : {},
        position :"last",           //first, last
        useDefValues : false,
        useFormatter : false,
        addRowParams : {extraparam:{}}
	});
}
</script>

</body>
</html>