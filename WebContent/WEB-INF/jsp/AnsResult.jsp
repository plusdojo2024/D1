<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>回答履歴</title>
<link rel="stylesheet" type="text/css" href="css/AnsResult.css">
<link rel="stylesheet" type="text/css" href="css/modal.css">
</head>

<body>
	<header>
		<div class="logo">
			<img src="img/logo.png" alt="">
		</div>
	</header>

	<h2>あなたの回答履歴</h2>

	<form method="post" action="/D1/AnsResultServlet">
			<input type="text" name="login_id">
			<input type="submit" value="検索">
	</form>

	<ul class="Que-area">
		<c:forEach var="e" items="${QueList}">

			<li>${e.login_id}/${e.date}/${e.content}/${e.answer}/${e.subject}</li>

		</c:forEach>
	</ul>





	<section id="modalArea" class="modalArea">
		<div id="modalBg" class="modalBg"></div>
		<div class="modalWrapper">
			<div class="modalContents">
				<div class="modal_title">
					<h1>この内容で送信してよろしいですか？</h1>
				</div>
				<div class="modal_choice">
					<p id="modal_cancel">戻る</p>
					<p>OK</p>
				</div>
			</div>
			<div id="closeModal" class="closeModal">×</div>
		</div>
	</section>

</body>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="js/modal.js"></script>
<script src="/D1/script/StudentQue.js"></script>

</html>