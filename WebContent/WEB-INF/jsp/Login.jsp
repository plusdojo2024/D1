<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>ログイン画面</title>
	<!-- <link rel="stylesheet" type="text/css" href="css/login.css"> -->
	<link rel="shortcut icon" href="images/icon2.ico" type="image/vnd.microsoft.icon">
	<link rel="stylesheet" type="text/css" href="css/Login.css">
</head>


<body>
    <div class="login_logo">
        <img src="img/logo.png" alt="">
    </div>

    <img class="people_icon" src="img/people.png" alt="">
    <img class="lock_icon" src="img/lock.png" alt="">

    <!-- <form action="result.html"> -->
    <form id="login_form" method="post" action="/D1/LoginServlet">

        <div class="login_input">
            <div class="login_text">
                <input id="email" type="text" name="id" placeholder="ID">
            </div>
            <div class="login_text">
                <input id="password" type="password" name="pass" placeholder="password">
            </div>

            <div class="submit">
                <input type="submit" value="login"><br>
                <label>初めての方はこちら</label><br>
                <input type="submit" value="新規登録">
            </div>
            <span id="error_message"></span>
        </div>

    </form>
</body>

<script src="js/login_js.js"></script>

</html>