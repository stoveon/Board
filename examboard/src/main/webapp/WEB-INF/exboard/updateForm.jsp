<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정 폼</title>
</head>
<body style="text-align: center;">
<article>
<section>
	<form:form method="post" commandName="boardDto">
	<input type="hidden" value="${boardDto.num}" name="num">
	<table border="1" style="margin: auto;">
		<tr>
		<td>제목</td>
		<td colspan="3"><form:input path="title" value="${boardDto.title}" size="25"/></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td colspan="3"><form:password path="checkPass" size="25"/>${msg}</td>
	</tr>
	<tr>
		<td>작성자</td><td>${boardDto.writer}</td>
		<td>조회수</td><td>${boardDto.readcount}</td>
	</tr>
	<tr>
		<td colspan="4"><textarea rows="7" cols="40" name="content">${boardDto.content}</textarea></td>
	</tr>
	<tr>
	<td colspan="4">
		<input type="submit" value="수정">&nbsp;&nbsp;
		<input type="button" value="목록" onClick="javascript:location.href='${pageContext.request.contextPath}/board/list'">
	</td>
	</tr>
	</table>
	</form:form>
</section>
</article>
</body>
</html>