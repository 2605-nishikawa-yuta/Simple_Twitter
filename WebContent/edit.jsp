<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %> <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>つぶやき編集</title>
</head>
<body>
<form action="edit" method="post">

    <input type="hidden" name="id" value="${message.id}">

    いま、どうしてる？（編集モード）<br />

    <textarea name="text" cols="100" rows="5" class="tweet-box">${message.text}</textarea>

    <br/>
    <input type="submit" name="update" value="更新">
</form>
</body>
</html>