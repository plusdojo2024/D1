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
    <div class="login_logo">
        <img src="img/title.png" alt="">
    </div>

    <img class="people_icon" src="img/people.png" alt="">
    <img class="lock_icon" src="img/lock.png" alt="">

    <div class="login_input">
        <div class="login_text">
            <label>新規アカウント登録</label><br>
            <input id="email" type="text" name="login_id" placeholder="ID">
        </div>
        <div class="login_text">
            <input id="password" type="password" name="password" placeholder="password">
        </div>

        <div class="user_name">
            <label>あなたの氏名を入力して下さい</label><br>
                    <input type="text" placeholder="">
        </div>

        <div class="submit">
            <input id="openModal" type="submit" value="登録"><br>
        </div>
        <span id="error_message"></span>
    </div>

    <section id="modalArea" class="modalArea">
        <div id="modalBg" class="modalBg"></div>
        <div class="modalWrapper">
            <div class="modalContents">
                <div class="modal_title">
                    <h1>新規登録が完了しました</h1>
                </div>
                <div class="modal_choice">
                    <p>ログイン画面に戻る</p>
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
<script src="js/NewLogin.js"></script>

</html>