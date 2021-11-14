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
			<div id="content">
				<div class="blog-content">
					<h4>${post2.title }</h4>
					<p>
						${post2.contents }
					<p>
					
				</div>
				<ul class="blog-list">
				<c:forEach items="${post }" var="p">
					<li>
						<a href="${pageContext.request.contextPath }/${authUser.getId()}/${p.category_no}/${p.no }">${p.title }</a> 
						<span><a href="${pageContext.request.contextPath }/${authUser.getId() }/admin/post/delete/${p.category_no }/${p.no }" 
		      							class="del" 
		      							style="background: url(${pageContext.request.contextPath}/assets/images/delete.jpg) no-repeat 0 0 / 9px ">삭제</a></span> 
		      			<span>${p.reg_date }</span> 
					</li>
				</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath }/${blogbyId.logo }">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${category }" var="cgy">
					<li><a href="${pageContext.request.contextPath }/${authUser.getId() }/${cgy.no}">${cgy.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		
	<c:import url="/WEB-INF/views/blog/includes/footer.jsp" />			
	</div>
</body>
</html>