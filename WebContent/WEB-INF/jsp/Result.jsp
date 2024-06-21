<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>-Result-</title>
<!-- 仮：スタイルシート-->
<link rel="stylesheet" href="/D1/css/Result.css">
</head>
<body>
  <header>
    <div class="logo">
			<img src="img/logo.png" alt="">
	</div>
  </header>

  <div class="head_bar"></div>

<h2>${result.title}</h2>
<hr>
<p>${result.message}</p>
<a class="btn" href="${result.backTo}">戻る</a>
</body>
</html>
