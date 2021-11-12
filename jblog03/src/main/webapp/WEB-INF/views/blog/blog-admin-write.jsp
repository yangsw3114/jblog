<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
var selectBoxChange = function(value){
	console.log("Select Box's Category Name_no :" + value);
	$("#cgy").val(value);
} //밑에 click이벤트랑 중복되지만 onchange="selectBoxChange(this.value); 온체인지라는 속성이 있다는것도 알기위해 남겨둠

window.addEventListener("load", function(){
	document.getElementById("postadd").addEventListener('click',function(event){
		document.getElementById("cgy").value = document.getElementById('selectBox').value;
		//카테고리 미선택시 selectbox의 기본탭이 category_no값으로 들어간다.
		if(document.getElementById("title").value == ""){ //제목 미입력시 팝업창 출력
			alert("제목을 입력하세요...");
			event.preventDefault();
			document.getElementById("title").focus();
		}
	});
	
	document.getElementById("selectBox").addEventListener('click',function(){

		
		
	});
});



</script>
</head>
<body>
	<div id="container">
	<c:import url="/WEB-INF/views/blog/includes/header.jsp" />
	
		<div id="wrapper">
			<div id="content" class="full-screen">
			<c:import url="/WEB-INF/views/blog/includes/adminmenu.jsp" />
				<form action="" method="post">
			      	<table class="admin-cat-write">
			      		<tr>
			      			<td class="t">제목</td>
			      			<td>
			      				<input id="title" type="text" size="60" name="title">
				      			<select id="selectBox" name="category" onchange="selectBoxChange(this.value);">
				      				<c:forEach items="${categorySelectBox }" var="CGYname">
				      					<option value="${CGYname.no }">${CGYname.name }</option>
				      				</c:forEach>
				      			</select>
				      		</td>
			      		</tr>
			      		<tr>
			      			<td class="t">내용</td>		      			
			      			<td><textarea name="contents"></textarea></td>
			      			<td><input id="cgy" type="hidden" name="category_no"></td>
			      			
			      		</tr>
			      		<tr>
			      			<td>&nbsp;</td>
			      			<td class="s"><input id="postadd" type="submit" value="포스트하기"></td>
			      		</tr>
			      	</table>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/blog/includes/footer.jsp" />	
	</div>
</body>
</html>