<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>質問履歴</title>
<link rel="stylesheet" type="text/css" href="css/QueResult.css">
<link rel="stylesheet" type="text/css" href="css/modal.css">
</head>

<body>
	<header>
		<div class="logo">
			<img src="img/logo.png" alt="">
		</div>
	</header>

	<h2>あなたの質問履歴</h2>

<form method="post" action="/D1/QueResultServlet">
<!-- <form id="search_form"> -->
<select name="subject" id="subject_select">
<option value="">教科選択</option>
<option value="国語">国語</option>
<option value="数学">数学</option>
<option value="英語">英語</option>
<option value="理科">理科</option>
<option value="社会">社会</option>
</select>
<input type="submit" value="絞り込み">
</form>

<!-- <script>
function onclick_search() {
	const data = new FormData(document.getElementById('search_form'));
	var subject_form = document.getElementById('search_form').value;
	const subjectSelect = document.getElementById('subject_select');
	 subjectSelect.value = subject_form;


	fetch('http://localhost:8080/D1/QueResultServlet', {
		method: 'post', body: data
	})
}
</script>
 -->
	<ul class="Que-area">
		<c:forEach var="e" items="${QueList}">

			<li>${e.date} / ${e.content} / ${e.subject}</li>

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