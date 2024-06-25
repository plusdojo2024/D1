<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新規登録画面</title>
    <!-- <link rel="stylesheet" type="text/css" href="css/login.css"> -->
    <link rel="shortcut icon" href="images/icon2.ico" type="image/vnd.microsoft.icon">
    <link rel="stylesheet" type="text/css" href="css/NewLogin.css">
</head>

<body>
	<form id = "registform" method = "post" action = "/D1/NewLoginServlet">
	    <div class="logo">
	        <img src="img/logo_blue.png" alt="">
	    </div>

	    <div class="login_input">
	        <div class="login_text">
	            <label>新規アカウント登録</label><br>
	            <input id="email" type="text" name="login_id" placeholder="ID" required pattern="[A-Za-z0-9]*" title="英数字のみ入力してください(16文字以内)" maxlength="16">
	        </div>
	        <div class="login_text">
	            <input id="password" type="password" name="password" placeholder="password" required pattern="[A-Za-z0-9]*" title="英数字のみ入力してください(16文字以内)" maxlength="16">
	        </div>

	        <div class="user_name">
	            <label>あなたの氏名を入力して下さい</label><br>
	            <input type="text" name = "user_name" placeholder="氏名" required maxlength = "100">
	        </div>

	        <div class="submit">
	            <input id="openModal" type="button" value="登録"><br>
	        </div>
	        <span id="error_message"></span>
	    </div>
	</form>

    <section id="modalArea" class="modalArea" style = "display: none;">
        <div id="modalBg" class="modalBg"></div>
        <div class="modalWrapper">
            <div class="modalContents">
                <div class="modal_title">
                    <h1>この内容で登録してよろしいですか？</h1>
                </div>
                <div class="modal_choice">
                	<p id="modal_cancel">戻る</p>
                    <p id = "modal_ok">OK</p>
                </div>
            </div>
            <div id="closeModal" class="closeModal">
                ×
            </div>
        </div>
    </section>
</body>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
    integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
<script src="script/NewLogin.js"></script>
<script>
	//英数字のみ許可
	document.getElementById('email').addEventListener('input', function(e)){
		e.target.value = e.target.replace(/[^A-Za-z0-9]/g, '');
	});

	document.getElemntById('password').addEventListener('input', function(e)){
		e.target.value = e.target.replace(/[^A-Za-z0-9]/g, '');
	});
</script>
</html>