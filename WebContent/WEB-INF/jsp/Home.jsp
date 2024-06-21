<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<li><a href="/D1/LogoutServlet">ログアウト</a></li>
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
			  <c:forEach var="entry" items="${averageScores}">
			  	${entry.averageScore},
			  </c:forEach>];
		const array2 = [
			<c:forEach var="entry" items="${averageScores}">
		  	  '${entry.year}-${entry.month}',
		    </c:forEach>];
		</script>

		<div class="summary">
			<!-- 変更開始 -->
			<canvas id="myLineChart"></canvas>
			<div class="graph_js">
				<script
					src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>
				<script src="script/Line_Chart.js"></script>
				<!-- ここまで -->

			</div>



			<div class="bottom_right">

				<div class="score">
					<span>平均点</span><span class="font_L">${requestScope.avgScore}点</span>
				</div>

				<div class="score">
					<span>得意科目</span><span class="font_L">${requestScope.subject}</span>
				</div>

				<div class="score">
					<span>質問数</span><span class="font_L">${requestScope.contentCount}回</span>
				</div>

				<div class="score">
					<span>回答数</span><span class="font_L">${requestScope.answerCount}回</span>
				</div>
			</div>
		</div>
	</main>

</body>
<footer>
<div class="fot_img">
<img src="img/fot_logo.png" alt="">
</div>
</footer>

</html>