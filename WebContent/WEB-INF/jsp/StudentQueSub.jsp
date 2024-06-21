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
			<img src="img/logo.png" alt="">
		</div>
	</header>

	<h2>質問を作成する</h2>

	<div id="que_result"></div>
	<div class="que">
		<button id="openModal" class="qbutton">質問を作成する</button>

		<img class="qbutton_img" src="img/pen.png" alt="" width="25"
			height="25">
	</div>

	<form id="Form" action="/D1/StudentQueSubServlet" method="post">
	<section id="modalArea" class="modalArea">
		<div id="modalBg" class="modalBg"></div>
		<div class="modalWrapper">
			<div class="modalContents">
					<div class="modal_maintitle">
						<h1>質問内容を入力してください</h1>
					</div>
					<div class="modal_title">
						<textarea name="content" rows="7" cols="85"></textarea>
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

	<section id="modalArea_B" class="modalArea_B">
		<div id="modalBg_B" class="modalBg_B"></div>
		<div class="modalWrapper_B">
			<div class="modalContents_B">
				<div class="modal_maintitle_B">
					<h1>回答内容を入力してください</h1>
				</div>
				<div class="modal_title_B">

					<textarea name="input" rows="7" cols="85"></textarea>
				</div>
				<div class="modal_choice_B">
					<p id="modal_cancel_B">戻る</p>
					<p id="modal_ok">OK</p>
				</div>
			</div>
			<div id="closeModal_B" class="closeModal_B">×</div>
		</div>
	</section>

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