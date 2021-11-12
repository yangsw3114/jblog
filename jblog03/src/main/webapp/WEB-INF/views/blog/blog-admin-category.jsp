<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
$(function(){
	$("#category-name-check").click(function(){
		
		var categoryname = $("#categoryname").val();
		if(categoryname == ""){ return; }
		console.log(categoryname);		
		
		var flagSubmit = true;
		var form = document.getElementById("categoryadd");
		
		form.onsubmit = function(){
			return flagSubmit;
		}		
		
		$.ajax({
			url: "${pageContext.request.contextPath }/category/api/checkcategoryname?categoryname=" + categoryname,
			type: "get",
			dataType: "json",
			async:false, // 동기식 즉 절차적으로 처리한다는 뜻
			error: function(xhr, status, e){
				console.log(status, e);
			},
			success: function(response){
				console.log(response);
				if(response.result != "success"){
					console.error(response.message);
					return;
				}
				
				if(response.data){
					flagSubmit = false;
					alert("존재하는 카테고리명입니다.");
					$("#categoryname").val("").focus();
					
					return;
				}
			}
		});
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
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		<c:forEach items="${category }" var="vo" varStatus="status">
		      			<tr>
		      				<td>${status.count }</td>
		      				<td>${vo.name }</td>
		      				<td>${vo.count }</td>
		      				<td>${vo.desc }</td>      				
		      				<td>
		      					<c:choose>
		      						<c:when test="${not empty authUser && authUser.id == vo.blog_id}">
		      							<a href="${pageContext.request.contextPath }/${authUser.getId() }/admin/category/delete/${vo.no }" 
		      							class="del" 
		      							style="background: url(${pageContext.request.contextPath}/assets/images/delete.jpg) no-repeat 0 0 / 15px">삭제</a>
		      						</c:when>
		      						<c:otherwise>
		      							&nbsp;
		      						</c:otherwise>
		      					</c:choose>
		      				</td>
		      			</tr>
		      		</c:forEach>
				  
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      			<form id="categoryadd" class="add-form" method="post" 
      			action="${pageContext.request.contextPath }/${authUser.getId() }/admin/category" > 
	      			<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input id="categoryname" type="text" name="name" /></td>			  
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input type="text" name="desc"></td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><input id="category-name-check" type="submit" value="카테고리 추가"></td>
			      		</tr>      		      		
			      	</table> 
      			</form>

			</div>
		</div>
		<c:import url="/WEB-INF/views/blog/includes/footer.jsp" />	
	</div>
</body>
</html>