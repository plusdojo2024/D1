<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>テスト</title>
<link rel="stylesheet" type="text/css" href="css/StudentQueSubResult.css">
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
                <img src="img/que_icon.svg" alt="" width="50" height="50">
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



<div id="que_result"></div>
	<div class="que">
		<button id="openModal" class="qbutton">回答を作成する</button>

		<img class="qbutton_img" src="img/pen.png" alt="" width="25"
			height="25">
	</div>

	<form id="Form" action="/D1/StudentQueSubResultServlet" method="post">
	<section id="modalArea" class="modalArea">
		<div id="modalBg" class="modalBg"></div>
		<div class="modalWrapper">
			<div class="modalContents">
					<div class="modal_maintitle">
						<h1>回答を入力してください</h1>
					</div>
					<div class="modal_title">
						<textarea name="answer" rows="7" cols="85"></textarea>
					</div>
					<div class="modal_choice">
						<p id="modal_cancel">戻る</p>
						<p id="modal_ok">OK</p>
					</div>
			</div>
			<div id="closeModal" class="closeModal">×</div>
		</div>
	</section>
	</form>


  <script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous"></script>
	<script src="script/modal_input.js"></script>

</body>
</html>