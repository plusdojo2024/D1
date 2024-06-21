<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>質問-教科選択画面</title>
  <link rel="stylesheet" type="text/css" href="css/StudentQue.css">
</head>
<body>
  <header>
     <div class="logo">
			<a href="/D1/HomeServlet"><img src="img/logo.png" alt=""></a>
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
    <div class="head_bar"></div>

  <h2>質問</h2>

  <div class="grid_area">
  <p><a href="/D1/StudentQueSubServlet">質問する</a></p>
  <p><a href="/D1/StudentQueSubResultServlet">みんなの質問一覧を見る</a></p>
  <p><a href="/D1/QueResultServlet">自分の質問履歴を見る</a></p>
  <p><a href="/D1/AnsResultServlet">自分の回答履歴を見る</a></p>
  </div>

</body>
</html>