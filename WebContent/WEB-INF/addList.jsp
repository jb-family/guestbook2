<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<form action="./gbc" method="get">
		<table border="1">
			<tr>
				<td>이름<input type="text" name="name"></td>
				<td>비밀번호<input type="password" name="password"></td>	
			</tr>
			<tr>
				<td colspan="2"><textarea name="content" style="width:500px; height:200px;"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><button type="submit">확인</button></td>
			</tr>
		</table>
			<input type=hidden name="action" value="add">
		<br>
	</form>
	<c:forEach items="${gList}" var="gList" varStatus="status">
		<table border="1">
				<tr>
					<td style="width:30px;">${gList.no}</td>
					<td style="width:100px;">${gList.name}</td>
					<td style="width:270px;">${gList.regDate}</td>	
					<td><a href ="./gbc?action=deleteForm&no=${gList.no}">삭제</a></td>
				</tr>
				<tr>
					<td rowspan="2" colspan="4">첫번째 방명록내용<br>
						${gList.content}
					</td>	
			</table>
			<br>
	</c:forEach>
	
	

						
</body>
</html>