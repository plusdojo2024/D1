<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>ログイン画面</title>
	<!-- <link rel="stylesheet" type="text/css" href="css/login.css"> -->
	<link rel="stylesheet" type="text/css" href="css/Login.css">
</head>


<body>
    <div class="login_logo">
        <img src="img/logo.png" alt="">
    </div>

    <!-- <form action="result.html"> -->
    <form id="login_form" method="post" action="?">

        <div class="login_input">
            <div class="login_text">
                <input id="email" type="text" name="login_id" placeholder="ID">
            </div>
            <div class="login_text">
                <input id="password" type="password" name="password" placeholder="password">
            </div>

            <div class="submit">
                <input type="submit" value="ログイン" formaction="/D1/LoginServlet"><br>
                <label>初めての方はこちら</label><br>
                <input type = "button" value = "新規登録" onclick = "goToNewRegistration()">

                <!--<input type="submit" value="新規登録" formaction="/D1/NewLoginServlet">-->
            </div>
            <span id="error_message"></span>
        </div>

    </form>
</body>

<script src="js/login_js.js"></script>

<script>
	function goToNewRegistration() {
		window.location.href = '/D1/NewLoginServlet';
}
</script>

</html>