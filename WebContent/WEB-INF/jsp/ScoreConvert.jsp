<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>成績登録例外画面</title>
<link rel="stylesheet" type="text/css" href="css/ScoreConvert.css">
<link rel="stylesheet" type="text/css" href="css/modal.css">
</head>

<body>
	<header>
		<div class="logo">
			<a href="/D1/HomeServlet"><img src="img/logo.png" alt="ロゴ"></a>
		</div>
	</header>

	<h2>成績登録 各教科</h2>

	<div class="back">
		<a href="/D1/ScoreRegistServlet"><img src="img/sirusi.png" alt=""></a>
	</div>


	<div class="reigai_mes">
		<p>問題数と正解数から100点満点に計算し直します</p>
	</div>

	<div class="seiseki_touroku">
		<form id = "Form" method = "post" action = "/D1/ScoreConvertRegistServlet">
			<p>正解数　</p>
			<p>問題数　</p>
			<br>

			<input id="correct" type="text" name="correct">
			<input id="total" type="text" name="total"> <br><br>

			<p>教科選択</p>
			<p id="seiseki_date">日付</p>
			<br>

			<select name = "subject">
				<option value = "" selected disabled>科目を選択してください</option>
				<option value = "国語">国語</option>
				<option value = "数学">数学</option>
				<option value = "英語">英語</option>
				<option value = "理科">理科</option>
				<option value = "社会">社会</option>
			</select>

			<input type="date" name = "date"><br><br>

			<div class="seiseki_okuru">
				<input id="openModal" type="button" value="登録">
			</div>
		</form>
	</div>


	<section id="modalArea" class="modalArea">
		<div id="modalBg" class="modalBg"></div>
		<div class="modalWrapper">
			<div class="modalContents">
				<div class="modal_title">
					<h1>この内容で登録してよろしいですか？</h1>
				</div>
				<div class="modal_choice">
					<p id="modal_cancel">戻る</p>
					<p id="modal_ok">OK</p>
				</div>
			</div>
			<div id="closeModal" class="closeModal">×</div>
		</div>
	</section>

</body>

<footer>
</footer>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>
<script src="script/modal.js"></script>

</html>