<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script>
var onTabClicked = function(){
	console.log("click" + this.innerText);
	//unselect
	var lisSelected = document.getElementsByClassName("selected");
	
	(lisSelected.length == 1) && (lisSelected[0].className = ''); // if문에서 이걸로 변형
	//&& 앞의 내용이 true여야 뒤의 내용 실행

	
	//select
	this.className = "selected";
	
}


window.onload = function(){
	console.log("load load");
	
	var ul = document.getElementsByClassName("admin-menu")[0];
	console.log(ul);
	
	var liTabs = ul.getElementsByTagName("li");
	console.log(liTabs);
	
	
	for(var i = 0; i < liTabs.length; i++){
		liTabs[i].onclick = onTabClicked; //함수는 상단에 정의
		
	}
}
</script>



	<ul class="admin-menu">
		<li><a href="${pageContext.request.contextPath }/${authUser.getId() }/admin" >기본설정</a></li>
		<li><a href="${pageContext.request.contextPath }/${authUser.getId() }/admin/category">카테고리</a></li>
		<li><a href="${pageContext.request.contextPath }/${authUser.getId() }/admin/write">글작성</a></li>

	</ul>

