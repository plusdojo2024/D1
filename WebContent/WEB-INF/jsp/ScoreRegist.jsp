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
</head>

<body>
  <header>
    <div class="logo">
			<img src="img/logo.png" alt="">
		</div>
  </header>

  <h2>成績登録　各教科</h2>


  <h3>戻る</h3>
<div class="seiseki_touroku">
  <p>点数登録</p><br>
  <input type="text">
  <img src="img/score.png" width="70" height="70"><br>
  <p>日付登録</p>
  <input type="date"><br>
</div>


<div class="seiseki_okuru">
  <input id="openModal" type="submit" value="登録">
</div>

<div class="not_100">
<p>100点満点ではない場合</p>
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
        <p>OK</p>
      </div>
    </div>
    <div id="closeModal" class="closeModal">
      ×
    </div>
  </div>
</section>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"
  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
<script src="js/modal.js"></script>

</body>
</html>