<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>成績登録画面</title>
<link rel="stylesheet" type="text/css" href="css/ScoreRegist.css">
<link rel="stylesheet" type="text/css" href="css/modal.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<body>
	<header>
		<div class="logo">
			<a href="/D1/HomeServlet"><img src="img/logo.png" alt="ロゴ"></a>
		</div>
		<input type="checkbox" id="checkbox" class="checkbox">
    <label for="checkbox" class="hamburger">
      <span class="bar bar-top"></span>
      <span class="bar bar-middle"></span>
      <span class="bar bar-bottom"></span>
    </label>
    <nav class="nav-menu">
      <ul>
        <li><a href="/D1/HomeServlet">HOME</a></li>
        <li><a href="/D1/ScoreRegistServlet">成績登録</a></li>
        <li><a href="/D1/StudentQueServlet">質問</a></li>
        <li><a href="/D1/LogoutServlet">ログアウト</a></li>
      </ul>
    </nav>
	</header>

	<h2>成績登録 各教科</h2>

	<div class="seiseki_touroku">
		<form id = "Form" action="/D1/ScoreRegistServlet" method="post">
			<p>教科登録</p>
			<br>
			<select id="subject_select" name = "subject">
				<option value = "" selected disabled>科目を選択してください</option>
				<option value = "国語">国語</option>
				<option value = "数学">数学</option>
				<option value = "英語">英語</option>
				<option value = "理科">理科</option>
				<option value = "社会">社会</option>
			</select>

			<br><br>

			<p>点数登録</p>
			<br>
			<input type="text" id="score" name="score" maxlength="3"value="0"required>
			<!-- <img src="img/score.png" width="70" height="70"> --><br><br>

			<p>日付登録</p>
			<br>
			<input type="date" id="date" name="date" required><br><br>
			<div class = "seiseki_okuru">
				<input id="openModal" type="button" value="登録">
			</div>
		</form>
	</div>


	<div class="not_100">
		<img src="img/Q.svg" width="15" height="15"><br>
		<p>
			<a href="/D1/ScoreConvertRegistServlet">100点満点ではない場合</a>
		</p>
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
					<p id = "modal_ok">OK</p>
				</div>
			</div>
			<div id="closeModal" class="closeModal">×</div>
		</div>
	</section>

	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous"></script>
	<script src="script/modal.js"></script>

</body>
</html>