<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/27
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/inc.jsp"%>
<html>
<head>
    <title>edit page</title>
</head>
<body>
<c:if test="${sessionScope.username eq null}">
    <c:redirect url="index.jsp"/>
</c:if>
<h1>edit page</h1>
${sessionScope.username}
<hr>
<a href="${ctx}/user?action=logout">LOG OUT</a>
<hr>
<h2>EDIT BOOK</h2>
<form action="${ctx}/book" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="${sessionScope.book.id}">
    <input type="text" name="title" placeholder="TITLE" value="${sessionScope.book.title}"><br>
    <input type="submit" value="SAVE">
</form>
</body>
</html>
