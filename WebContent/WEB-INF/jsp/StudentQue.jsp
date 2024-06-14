<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>質問-教科選択画面</title>
  <link rel="stylesheet" type="text/css" href="css/situmon.css">
</head>
<body>
  <header>
    <div class="logo">アプリロゴ</div>

    <input type="checkbox" id="checkbox" class="checkbox">
    <label for="checkbox" class="hamburger">
      <span class="bar bar-top"></span>
      <span class="bar bar-middle"></span>
      <span class="bar bar-bottom"></span>
    </label>
    <nav class="nav-menu">
      <ul>
        <li><a href="#">HOME</a></li>
        <li><a href="#">成績登録</a></li>
        <li><a href="#">質問</a></li>
        <li><a href="#">ログアウト</a></li>
      </ul>
    </nav>
  </header>

  <h2>質問</h2>


  <h3>教科を選択してください</h3>

  <div class="float_left">

    <div class="grad_subject">
      <p>・国語</p>
      <p>・数学</p>
      <p>・英語</p>
      <p>・理科</p>
      <p>・社会</p>
    </div>

    <div class="tokumei">
      <p>教師に匿名質問する</p>
      <img src="img/right.png" alt="">
    </div>

    <div class="situmon_kaisu">
      <p>質問数 ${contentCount}回</p>
      <span>質問履歴を見る</span>
    </div>

    <div class="kaitou_kaisu">
      <p>回答数 ${answerCount}回</p>
      <span>回答履歴を見る</span>
    </div>


  </div>
</body>
</html>