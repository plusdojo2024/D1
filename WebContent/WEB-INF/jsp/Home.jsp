<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/Home.css">
</head>
<body>
	<header>
		<div class="logo">
			<img src="img/logo.png" alt="">
		</div>
		<input type="checkbox" id="checkbox" class="checkbox"> <label
			for="checkbox" class="hamburger"> <span class="bar bar-top"></span>
			<span class="bar bar-middle"></span> <span class="bar bar-bottom"></span>
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
		<div class="head_bar"></div>

		<nav class="your_grade">
			<h1>あなたの現在の成績</h1>
		</nav>

		<script>
		const array1 = [
		  <c:forEach var="item" items="${test1}">
		     ${item},
		  </c:forEach>];
		</script>


		<!-- 変更開始 -->
		<canvas id="myLineChart"></canvas>
		<div class="graph_js">
		    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>
			<script src="script/Line_Chart.js" alt=""></script>
		<!-- ここまで -->

		</div>

		<div class="bottom_content">

			<div class="daily_task">

				<h1>デイリータスク</h1>

				<ul>
					<li>質問をする</li>
					<li>質問を２回する</li>
					<li>質問に回答する</li>
					<li>全てのタスクをクリアする</li>
				</ul>

				<div class="task_bar">
					<img src="img/task_bar.png" alt="">
				</div>

			</div>



		<div class="bottom_right">
			<div class="bottom_right1">

				<div class="score">
					<span>平均点</span><span class="font_L">${requestScope.subjectScores}点</span>
				</div>

				<div class="score">
					<span>得意科目</span><span class="font_L">${requestScope.subject}</span>
				</div>
			</div>

			<div class="bottom_right2">
				<div class="score">
					<span>質問数</span><span class="font_L">${requestScope.contentCount}回</span>
				</div>

				<div class="score">
					<span>回答数</span><span class="font_L">${requestScope.answerCount}回</span>
				</div>
			</div>
		</div>
    </div>
  </main>


<table border="1">
    <thead>
        <tr>
            <th>Year-Month</th>
            <th>Average Score</th>
        </tr>
    </thead>
    <tbody>
        <!-- averageScores のデータを表示 -->
        <c:forEach var="entry" items="${averageScores}">
            <tr>
                <td>${entry.key}</td>
                <td>${entry.value}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>


</body>
</html>