<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String contextRoot = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="stylesheet" href="<%=contextRoot%>/css/default.css">
<title>:::SPARK:::</title>

<script language="javascript">
$(function() {
	$(".checkId").bind("click", function(event) {
		if($("#saveId").is(":checked")){
			$("#saveId").prop("checked",false);
		}else{
			$("#saveId").prop('checked',true);
		}
	});
});

function actSubmit(frm, url) {
	frm.action = url;
	frm.method = 'post';
	frm.submit();
}

/* function fn_onload()
{
	actSubmit(document.frm, "/comm/main")
}
*/

function fn_onload() {

	var userId = document.getElementById("userId");
	var userPw = document.getElementById("userPw");

	if(userId.value == '') {
		alert("아이디를 입력하세요.");
		userId.focus();
		return false;
	}

	if(userPw.value == '') {
		alert("비밀번호를 입력하세요.");
		userPw.focus();
		return false;
	}

	if(userId.value != '' & userPw.value != '') {
		//document.getElementById("frm").submit();

		actSubmit(document.frm, "/login")
	}

}

/*  if (navigator.userAgent.indexOf("MSIE") > -1 || navigator.userAgent.indexOf("Trident") !=-1) {
		window.location.href = "./xp_install/x_installAX.jsp";
	} else {
		window.location.href = "./xp_install/x_installPlugin.jsp";
	}

} */

// 엔터키 자동로그인 이벤트
function submitEvent(){
  	if (event.keyCode == 13) {
		// 엔터 키값은 13 입니다.
  		fn_onload();
  	}
  	return false;
}

function idPwdChk(key, passNumb, pwTmp) {
	if (key == "1") {
		alert("없는 아이디입니다.");
		return;
	} else if (passNumb != pwTmp) {
		alert("비밀번호가 틀렸습니다.");
		return;
	}
}

function imgRefresh(){
    $("#captchaImg").attr("src", "/captcha?id=" + Math.random());
}

$(function() {
	var ck = document.cookie;
	if(ck != null){
		$("#userId").val(getCookie("userId"));
		if(getCookie("isSaveId")){
			$("#saveId").attr("checked",true);
		}
	}
});

idPwdChk('${key}', '${passNumb}', '${pwTmp}');
</script>

<c:if test="${!empty exception.message}">
<script type="text/javascript">
(function() {
	alert("<c:out value="${exception.message}" />");
})();
</script>
</c:if>

</head>

<body style="background:#FFFFFF;">
<form name="frm" id="frm" action="<%=contextRoot%>/login" method="POST">
    <div class="outer">
        <div class="inner">
            <div class="login-wrapper">
			    <div class="login-box">
			        <p class="input-box"><input onkeypress="submitEvent()" type="text" id="userId" name="userId" placeholder="아이디" value="wowpmd"/></p>
			        <p class="input-box"><input onkeypress="submitEvent()" type="password" id="userPw" name="userPw" placeholder="패스워드" value="00514"/></p>
					<!--
					<img src="/captcha" id="captchaImg" alt="captcha img">
					<input type="text" placeholder="보안문자를 입력하세요" name="captcha">
					<a onclick="imgRefresh()" id="refreshBtn">새로고침</a>
					 -->
					<input type="button" id="" name="" class="btn-login" onclick="fn_onload()"/>
			    </div>
                <p class="word-idsave">
                	<input type="checkbox" id="saveId" name="saveId"/><label for="saveId">아이디저장</label>
				</p>
        	</div>
        </div>
    </div>
	<!-- login -->
</form>
</body>
</html>