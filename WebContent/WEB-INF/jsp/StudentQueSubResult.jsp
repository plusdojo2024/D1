<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>テスト</title>
<link rel="stylesheet" type="text/css"
	href="css/StudentQueSubResult.css">
<link rel="stylesheet" type="text/css" href="css/modal_input.css">
</head>

<body>
	<header>
		<div class="logo">
			<a href="/D1/HomeServlet"><img src="img/logo.png" alt=""></a>
		</div>
	</header>

	<h2>質問回答一覧</h2>

	<div class="back">
		<a href="/D1/StudentQueServlet"><img src="img/sirusi.png" alt=""></a>
	</div>

	<div class="questionssss">

		<!-- 質問 -->
		<c:forEach var="e" items="${QueList}">
			<c:if test="${!empty e.content and e.content != '（未設定）'}">
				<div class="chat-box">
					<div class="chat-face">
						<img src="img/que_icon.svg" alt="" width="50" height="50">
					</div>
					<div class="chat-area">
						<div class="chat-hukidashi">
							<p>【質問】${e.date} 名前：${e.login_id}</p>
							<br>
							<p>${e.content}</p>
						</div>
					</div>
				</div>
			</c:if>


			<!-- 回答 -->
			<c:if test="${!empty e.answer and e.answer != '（未設定）'}">
				<div class="chat-box">
					<div class="chat-face">
						<img src="img/megane.png" alt="" width="50" height="50">
					</div>
					<div class="chat-area">
						<div class="chat-hukidashi_kaitou">
							<p>【回答】${e.date} 名前：${e.login_id}</p>
							<br>
							<p>${e.answer}</p>
						</div>
					</div>
				</div>
			</c:if>
		</c:forEach>

	</div>


	<div class="que">
		<button id="openModal" class="qbutton">回答を作成する</button>
	</div>

	<form id="Form" action="/D1/StudentQueSubResultServlet" method="post">
		<section id="modalArea" class="modalArea">
			<div id="modalBg" class="modalBg"></div>
			<div class="modalWrapper">
				<div class="modalContents">
					<div class="modal_maintitle">
						<h1>回答を入力してください</h1>
					</div>
					<div class="modal_title">
						<textarea name="answer" rows="7" cols="58" required></textarea>
							<select id="subject_select" name = "subject">
								<option value = "" selected disabled>科目を選択してください</option>
								<option value = "国語">国語</option>
								<option value = "数学">数学</option>
								<option value = "英語">英語</option>
								<option value = "理科">理科</option>
								<option value = "社会">社会</option>
							</select>
					</div>
					<div class="modal_choice">
						<p id="modal_cancel">戻る</p>
						<p id="modal_ok">OK</p>
					</div>
				</div>
				<div id="closeModal" class="closeModal">×</div>
			</div>
		</section>
	</form>


	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous"></script>
	<script src="script/modal_input.js"></script>
	<footer>
		<div class="fot_img">
			<img src="img/fot_logo.png" alt="">
		</div>
	</footer>

</body>


</html>