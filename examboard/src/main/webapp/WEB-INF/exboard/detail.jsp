<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 페이지</title>
</head>
<body style="text-align: center;">
<article>
<section>
<table border="1" style="margin: auto;">
	<tr>
		<td>제목</td>
		<td colspan="3">${boardDto.title}</td>
	</tr>
	<tr>
		<td>작성일</td>
		<td colspan="3"><fmt:formatDate value="${boardDto.regdate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
	</tr>
	<tr>
		<td>작성자</td><td>${boardDto.writer}</td>
		<td>조회수</td><td>${boardDto.readcount}</td>
	</tr>
	<tr>
		<td>내용</td>
		<td colspan="3">${boardDto.content}</td>
	</tr>
	<tr>
		<td colspan="2"><a href="<c:url value="/board/detail/${boardDto.num}?type=before" />">이전글</a></td>
		<td colspan="2"><a href="<c:url value="/board/detail/${boardDto.num}?type=after" />">다음글</a></td>
	</tr>
</table>
<div style="margin: 10px;">
	<form method="post" action="<c:url value="/board/write" />">
		<input type="hidden" name="num" value="${boardDto.num}"/>
		<input type="hidden" name="ref" value="${boardDto.ref}"/>
		<input type="hidden" name="step" value="${boardDto.step}"/>
		<input type="hidden" name="depth" value="${boardDto.depth}"/>
		<input type="submit" value="답글">
		
		<input type="button" onclick="location.href='<c:url value="/board/update/${boardDto.num}'" />" value="수정"/>

		<input type="button" onclick="location.href='<c:url value="/board/delete/${boardDto.num}'" />" value="삭제"/>

		<input type="button" onclick="location.href='<c:url value="/board/list" />'" value="목록"/>
	</form>
</div>
</section>
</article>
</body>
</html>