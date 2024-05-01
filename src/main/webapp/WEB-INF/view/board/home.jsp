<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h3>게시물 목록</h3>

<table>
    <thead>
    <tr>
        <th>#</th>
        <th>제목</th>
        <th>작성자</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${boardList}" var="board">
        <%--todo 제목을 클릭하면 해당 게시물 보는 화면으로 이동 하도록 수정--%>
        <tr>
            <td>${board.id}</td>
            <td>${board.title}</td>
            <td>${board.writer}</td>
        </tr>
    </c:forEach>
    </tbody>

</table>

</body>
</html>
