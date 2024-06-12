<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 仮：スタイルシート-->
<link rel="stylesheet" href="/D1/css/style.css">
</head>
<body>
<h1>${result.title}</h1>
<hr>
<p>${result.message}</p>
<a class="btn" href="${result.backTo}">戻る</a>
</body>
</html>
