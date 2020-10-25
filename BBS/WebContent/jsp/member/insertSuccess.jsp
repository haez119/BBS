<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 성공</title>
</head>
<body>
	<jsp:include page="../menu/mainMenu.jsp" />
	<hr>
	<div align="center">
	<!-- 서버가 만든 객체 그대로 쓸거니까 param (내가 새로 만든거 아니고) -->
		<div><h1>${param.id }님 가입을 축하합니다.</h1></div>
		<div>
			<table border="1">
				<tr>
					<th width="150"> 아이디 </th>
					<td width="500">${param.id }</td>
				</tr>
				<tr>
					<th width="150"> 이름 </th>
					<td>${param.name }</td>
				</tr>
				<tr>
					<th width="150"> 주  소 </th>
					<td>${param.address }</td>
				</tr>
				<tr>
					<th width="150"> 전화번호 </th>
					<td>${param.tel }</td>
				</tr>
				<tr>
					<th width="150"> 가입일자 </th>
					<td>${param.enterdate }</td>
				</tr>
			</table>

		</div>
	</div>
</body>
</html>