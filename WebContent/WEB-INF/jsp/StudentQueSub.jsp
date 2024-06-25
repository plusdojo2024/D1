<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>質問画面</title>
<link rel="stylesheet" type="text/css" href="css/StudentQueSub.css">
<link rel="stylesheet" type="text/css" href="css/modal_input.css">
</head>

<body>
	<header>
		<div class="logo">
			<a href="/D1/HomeServlet"><img src="img/logo.png" alt=""></a>
		</div>
	</header>

	<h2>質問を作成する</h2>

	<div class="back">
		<a href="/D1/StudentQueServlet"><img src="img/sirusi.png" alt=""></a>
	</div>

	<div class="questionssss">

		<div class="chat-box">
			<div class="chat-face">
				<img src="img/IMG_8183.png" alt="" width="50" height="50">
			</div>
			<div class="chat-area">
				<div class="chat-hukidashi">
					<br>
					<p>何か困っていることはあるか？</p>
					<br>
				</div>
			</div>

		</div>

		<div class="chat-box">

			<div class="chat-area">
				<div class="chat-hukidashi_kaitou">
					<br>
					<p>気になることがあれば質問してみよう！</p>
					<br>
				</div>
			</div>
			<div class="chat-face_ans">
				<img src="img/IMG_8184.png" alt="" width="50" height="50">
			</div>
		</div>

		<div class="chat-box">
			<div class="chat-face">
				<img src="img/IMG_8183.png" alt="" width="50" height="50">
			</div>
			<div class="chat-area">
				<div class="chat-hukidashi">
					<br>
					<p>簡単に解決するかもしれんからな！ﾊﾊｯ</p>
					<br>
				</div>
			</div>
		</div>
		<div class="chat-facer_ans">
			<img src="img/IMG_8184.png" alt="" width="50" height="50">
		</div>
	</div>

	<div id="que_result"></div>
	<div class="que">
		<button id="openModal" class="qbutton">質問を作成する</button>

	</div>

	<form id="Form" action="/D1/StudentQueSubServlet" method="post">
		<section id="modalArea" class="modalArea">
			<div id="modalBg" class="modalBg"></div>
			<div class="modalWrapper">
				<div class="modalContents">
					<div class="modal_maintitle">
						<h1>質問を入力してください</h1>
						<select id="subject_select" name="subject">
							<option value="" selected disabled>科目を選択</option>
							<option value="国語">国語</option>
							<option value="数学">数学</option>
							<option value="英語">英語</option>
							<option value="理科">理科</option>
							<option value="社会">社会</option>
						</select>
					</div>
					<div class="modal_title">
						<textarea name="content" rows="7" cols="58" required></textarea>
					</div>
					<div class="modal_choice">
						<p id="modal_cancel">戻る</p>
						<p id="modal_ok">OK</p>
					</div>
				</div>
				<div id="closeModal" class="closeModal">×</div>
			</div>
		</section>
		<input type="hidden" id="submissionDateTime" name="date">
	</form>


</body>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="script/modal_input.js"></script>
<script>
	document.getElementById('Form').addEventListener('submit', function(event) {
		var currentDateTime = new Date().toISOString(); // 現在の日時をISO形式で取得
		document.getElementById('submissionDateTime').value = currentDateTime; // 隠し入力フィールドに設定
	});
</script>
</html>