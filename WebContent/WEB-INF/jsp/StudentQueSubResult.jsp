<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>テスト</title>
<link rel="stylesheet" type="text/css" href="css/ScoreRegist.css">
<link rel="stylesheet" type="text/css" href="css/modal.css">
</head>

<body>
	<header>
		<div class="logo">
			<a href="/D1/HomeServlet"><img src="img/logo.png" alt=""></a>
		</div>
	</header>

   <div class="questionssss">

      <!-- 質問 -->
      	<c:forEach var="e" items="${QueList}">
      	<c:if test="${!empty e.content}">
        <div class="chat-box">
            <div class="chat-face">
                <img src="img/asuran.jpg" alt="" width="50" height="50">
            </div>
            <div class="chat-area">
                <div class="chat-hukidashi">
                    <p>質問</p>
			     <p>${e.date}</p><br>
                <p>${e.content}</p>
                </div>
            </div>
        </div>
        </c:if>


    <!-- 回答 -->
    <c:if test="${!empty e.answer}">
        <div class="chat-box">
        <div class="chat-face">
            <img src="img/megane.png" alt="" width="50" height="50">
        </div>
        <div class="chat-area">
            <div class="chat-hukidashi_kaitou">
                <p>回答</p>
                <p>${e.date}</p><br>
                <p>${e.answer}</p>
            </div>
        </div>
    </div>
    </c:if>
    </c:forEach>

    <div class="chat-box">
      <div class="chat-face">
        <img src="img/sin.jpg" alt="" width="50" height="50">
      </div>
      <div class="chat-area">
        <div class="chat-hukidashi">
          <p>シン・アスカ</p>
          <p>俺は間違ったことはしてませんよ！</p>
          <p>あそこの人たちだってあれで助かったんだ！！</p>
        </div>
      </div>
    </div>

    <div class="chat-box">
      <div class="chat-face">
        <img src="img/asuran.jpg" alt="" width="50" height="50">
      </div>
      <div class="chat-area">
        <div class="chat-hukidashi_kaitou">
          <p>アスラン・ザラ</p>
          <p>戦争はヒーローごっこじゃない！</p>
          <p>力を持つ者なら、その力を自覚しろ！</p>
        </div>
      </div>
    </div>

  </div>

  <script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous"></script>
	<script src="js/modal.js"></script>

</body>
</html>