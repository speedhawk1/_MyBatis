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
    <title>home page</title>
    <script>
        function del() {
            return confirm("DELETE?")
        }
    </script>
</head>
<body>
<c:if test="${sessionScope.username eq null}">
    <c:redirect url="index.jsp"/>
</c:if>
<h1>home page</h1>
${sessionScope.username}
<hr>
<a href="${ctx}/user?action=logout">LOG OUT</a>
<hr>
<h2>ADD BOOK</h2>
<form action="${ctx}/book" method="post">
    <input type="hidden" name="action" value="create">
    <input type="text" name="title" placeholder="TITLE"><br>
    <input type="submit" value="ADD">
</form>
<hr>
<h2>BOOK LIST</h2>
<table border="1">
    <tr>
        <th>INDEX</th>
        <th>TITLE</th>
        <th colspan="2">OPERATIONS</th>
    </tr>
    <c:forEach var="book" items="${sessionScope.books}" varStatus="vs">
        <tr>
            <td>${vs.count}</td>
            <td>${book.title}</td>
            <td><a href="${ctx}/book?action=search&id=${book.id}">MODIFY</a></td>
            <td><a href="${ctx}/book?action=remove&id=${book.id}" onclick="return del()">REMOVE</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
