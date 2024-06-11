<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
          <li><a href="/D1/HomeServlet">HOME</a></li>
          <li><a href="/D1/ScoreRegistServlet">成績登録</a></li>
          <li><a href="/D1/StudentQueServlet">質問</a></li>

          <!-- ログアウト一旦保留(ここから) -->
          <li><a href="#">ログアウト</a></li>
          <!-- ここまで -->

        </ul>
      </nav>
    </header>
    <main>
      <nav class="your_grade">
        <h1>あなたの現在の成績</h1>
      </nav>
    </main>
</body>
</html>