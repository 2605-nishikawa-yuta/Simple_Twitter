<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="./css/style.css" rel="stylesheet" type="text/css">
<title>つぶやき編集</title>
</head>
<body>
    <c:if test="${ not empty errorMessages }">
        <div class="errorMessages">
            <ul>
                <c:forEach items="${errorMessages}" var="errorMessage">
                    <li><c:out value="${errorMessage}" /></li>
                </c:forEach>
            </ul>
        </div>
    </c:if>

    <form action="edit" method="post">

        <input type="hidden" name="id" value="${message.id}">

        つぶやき<br />

        <textarea name="text" cols="100" rows="5" class="tweet-box">${message.text}</textarea>

        <br/>
        <input type="submit" name="update" value="更新">
        <a href="./">戻る</a>
    </form>

    <div class="copyright">Copyright(c)yuta nishikawa</div>
</body>
</html>