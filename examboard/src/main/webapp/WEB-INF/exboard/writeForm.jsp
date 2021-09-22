<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등록 페이지</title>
</head>
<body style="text-align: center;">
<section>
<form:form method="post" commandName="boardDto">
<form:hidden path="num" value="${boardDto.num}"/>
<form:hidden path="ref" value="${boardDto.ref}"/>
<form:hidden path="step" value="${boardDto.step}"/>
<form:hidden path="depth" value="${boardDto.depth}"/>

<table border="1" style="margin: auto;">
	<tr>
		<td><form:label path="title">제목</form:label></td>
		<c:set var="checkRe" value="${empty checkRe? false : checkRe}"/>
		<c:if test="${checkRe == true}">
			<td colspan="3" style="text-align: left; text-indent: 10px;">
			<form:input path="title" value="[답글] " size="25"/><br>
			<form:errors path="title"/></td>
		</c:if>
		<c:if test="${checkRe == false}">
			<td colspan="3" style="text-align: left; text-indent: 10px;">
			<form:input path="title" size="25"/><br>
			<form:errors path="title"/></td>
		</c:if>
	</tr>
	<tr>
		<td><form:label path="writer">작성자</form:label></td>
		<td colspan="3" style="text-align: left; text-indent: 10px;">
		<form:input path="writer" size="25"/><br>
		<form:errors path="writer"/></td>
	</tr>
	<tr>
		<td><form:label path="pass">비밀번호</form:label></td>
		<td colspan="3" style="text-align: left; text-indent: 10px;">
		<form:password path="pass" size="25"/><br>
		<form:errors path="pass"/></td>
	</tr>
	<tr>
		<td colspan="4"><form:textarea path="content" cols="40" rows="7" placeholder="내용 입력"/>
		<form:errors path="content"/></td>
	</tr>
	<tr>
		<td colspan="4">
			<input type="submit" value="등록"/>
			<input type="button" onClick="location.href='<c:url value="/board/list" />'" value="목록"/>
		</td>
	</tr>
</table>
</form:form>
</section>
</body>
</html>