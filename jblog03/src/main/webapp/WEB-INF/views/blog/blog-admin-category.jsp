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
		      		<c:forEach items="${category }" var="vo">
		      			<tr>
		      				<td>${vo.no }</td>
		      				<td>${vo.name }</td>
		      				<td>포스트수랑삭제버튼활성화는 id연동해야이용가능</td>
		      				<td>${vo.desc }</td>
		      				<td><img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></td>
		      			</tr>
		      		</c:forEach>
				  
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      			<form class="add-form" method="post" action="${pageContext.request.contextPath }/${authUser.getId() }/admin/category/add">
	      			<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input type="text" name="name"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input type="text" name="desc"></td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<td><input type="submit" value="카테고리 추가"></td>
			      		</tr>      		      		
			      	</table> 
      			</form>

			</div>
		</div>
		<c:import url="/WEB-INF/views/blog/includes/footer.jsp" />	
	</div>
</body>
</html>