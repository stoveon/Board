<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제 폼</title>
</head>
<body style="text-align: center;">
<article>
<section>
	<form:form method="post" commandName="boardDto">
<input type="hidden" name="num" value="${boardDto.num}"/>
<input type="hidden" name="ref" value="${boardDto.ref}"/>
<input type="hidden" name="step" value="${boardDto.step}"/>
<input type="hidden" name="depth" value="${boardDto.depth}"/>
	<table border="1" style="margin: auto;">
		<tr>
		<td><form:label path="title">제목</form:label></td>
		<td colspan="3" style="text-align: left; text-indent: 10px">
		<input type="text" name="title" value="${boardDto.title}" size="25"/></td>
	</tr>
	<tr>
		<td><form:label path="checkPass">비밀번호</form:label></td>
		<td colspan="3" style="text-align: left; text-indent: 10px">
		<input type="password" name="checkPass" size="25"/>${msg}</td>
	</tr>
	<tr>
		<td><form:label path="writer">작성자</form:label></td><td>${boardDto.writer}</td>
		<td><form:label path="readcount">조회수</form:label></td><td>${boardDto.readcount}</td>
	</tr>
	<tr>
		<td colspan="4"><form:textarea rows="7" cols="40" path="content" value="${boardDto.content}"/></td>
	</tr>
	<tr>
	<td colspan="4">
		<input type="submit" value="삭제">&nbsp;&nbsp;
		<input type="button" onClick="location.href='<c:url value="/board/list" />'" value="목록"/>
	</td>
	</tr>
	</table>
	</form:form>
</section>
</article>
</body>
</html>