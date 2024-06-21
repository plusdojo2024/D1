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
			<a href="/D1/HomeServlet"><img src="img/logo.png" alt=""></a>
		</div>
	</header>


	<h2>あなたの質問履歴</h2>

<div class="back_icon">
	<a href="/D1/StudentQueServlet"><img src="img/sirusi.png"></a>
</div>

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

	<div class="que_title">
		<p>質問履歴</p>

		<form method="post" action="/D1/QueResultServlet">
			<!-- <form id="search_form"> -->
			<div class="subject_pul">
			<select name="subject" id="subject_select">
				<option value="">教科選択</option>
				<option value="国語">国語</option>
				<option value="数学">数学</option>
				<option value="英語">英語</option>
				<option value="理科">理科</option>
				<option value="社会">社会</option>
			</select>
			<input type="submit" value="絞り込み">
			</div>
		</form>

	</div>

	<div class="que_area">
		<c:forEach var="e" items="${QueList}">




			<c:if test="${empty e.answer}">
				<div class="que_content">

					<p>${e.date}　　${e.subject}</p>
					<h3>${e.content}</h3>
				</div>
			</c:if>
		</c:forEach>
	</div>





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

<footer>
<div class="fot_img">
<img src="img/fot_logo.png" alt="">
</div>
</footer>

</body>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="js/modal.js"></script>
<script src="/D1/script/StudentQue.js"></script>

</html>