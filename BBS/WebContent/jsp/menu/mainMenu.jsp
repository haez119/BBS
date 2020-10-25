<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<!-- 스타일 시트 링크 걸기 -->
	<!-- 최상위 (프로젝트폴더) 폴더 이름을 / 붙여서 걸면 절대경로 -->
    <link rel= "stylesheet" href="/BBS/css/mainmenu.css"> 
</head>
<body>
	<div align="center">
		<div id='menu'>
			<nav id="topMenu" >
				<ul>
					<c:if test="${id eq null }">
						<li><a class="menuLink" href="loginForm.do">로그인</a></li>
						<li>|</li>
					</c:if>
					
					<li><a class="menuLink" href="#">공지사항</a></li>
					<li>|</li>
					<li><a class="menuLink" href="#">게시판</a></li>
					
					<!-- 아이디가 존재하면 => 로그인을 했으면 -->
					<!-- 아이디가 존재하지 않으면 회원가입을 보여줌 -->
					<c:if test="${id eq null }"> 
						<li>|</li>
						<li><a class="menuLink" href="memberForm.do">회원가입</a></li>
					</c:if>
					
					<c:if test="${author eq 'admin' }">
						<li>|</li>	
						<li><a class="menuLink" href="memberList.do">회원관리</a></li>
					</c:if>
					<li>|</li>
					<li><a class="menuLink" href="logout.do">로그아웃</a></li>
				</ul>
			</nav>
		</div>
	</div>
</body>
</html>