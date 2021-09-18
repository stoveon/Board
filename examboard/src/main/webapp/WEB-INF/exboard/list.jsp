<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
</head>
<body style="text-align: center;">
<h2>게시판</h2>
<h4>(전체 글 : ${totalCount})</h4>
<article>
<section>
<c:if test="${totalCount == 0}">
<table border="1" style="margin: auto;">
	<tr>
		<td>등록된 글이 없습니다.</td>
	</tr>
</table>

</c:if>
<c:if test="${totalCount > 0}">
<table id="listTable" border="1" style="margin: auto;">
	<tr>
		<th id="num" name="num">번호</th>
		<th id="title" name="title">제목</th>
		<th id="writer" name="writer">글쓴이</th>
		<th id="regdate" name="regdate">작성일</th>
		<th id="readcount" name="readcount">조회수</th>
	</tr>
	<c:forEach items="${articleList}" var="boardDto">
	<tr>
	<td>${boardDto.rnum}</td>
	<td><a href="<c:url value="/board/detail/${boardDto.num}" />">${boardDto.title}</a></td>
	<td>${boardDto.writer}</td>
	<td><fmt:formatDate value="${boardDto.regdate}" pattern="yyyy-MM-dd" /></td>
	<td>${boardDto.readcount}</td>
	</tr>
	</c:forEach>
</table>

<div style="margin: 10px;">
	<c:if test="${pageNum > pageBlock}">
		<button class= "btn-prev" onclick="<c:url value="/board/list?pageNum=${pageNum-1}" />">이전</button>
	</c:if>

	<c:forEach var="i" begin="${startPaging}" end="${endPaging}">
		<a href="<c:url value="/board/list?pageNum=${i}"/>" >[${i}]</a>
	</c:forEach>

	
	<c:if test="${endPage==pageNum}">
		<button class= "btn-next" onclick="alert('다음 페이지가 없습니다.');">다음</button>
	</c:if>
	<c:if test="${endPage>pageNum}">
		<button class= "btn-next" onclick="<c:url value="/board/list?pageNum=${pageNum+1}" />">다음</button>
	</c:if>
</div>	
</c:if>
<div>
<%-- 	<input type="button" value="index로 이동" onclick="loaction.href='<c:url value="/index"/>'" /> --%>
	<input type="button" value="글쓰기" onclick="location.href='<c:url value="/board/write"/>'" />
</div>
</section>
</article>
</body>
</html>