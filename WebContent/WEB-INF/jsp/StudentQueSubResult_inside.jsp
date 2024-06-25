<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<meta charset="UTF-8">

	<div class="questionssss">

		<!-- 質問 -->
		<c:forEach var="e" items="${QueList}">
			<c:if test="${!empty e.content and e.content != '（未設定）'}">
				<div class="chat-box">
					<div class="chat-face">
						<img src="img/que_icon.svg" alt="" width="50" height="50">
					</div>
					<div class="chat-area">
						<div class="chat-hukidashi">
							<p>【質問】${e.date} 名前：${e.login_id}</p>
							<br>
							<p>${e.content}</p>
						</div>
					</div>
				</div>
			</c:if>


			<!-- 回答 -->
			<c:if test="${!empty e.answer and e.answer != '（未設定）'}">
				<div class="chat-box">
					<div class="chat-face">
						<img src="img/megane.png" alt="" width="50" height="50">
					</div>
					<div class="chat-area">
						<div class="chat-hukidashi_kaitou">
							<p>【回答】${e.date} 名前：${e.login_id}</p>
							<br>
							<p>${e.answer}</p>
						</div>
					</div>
					<c:if test="${e.reaction == '0'}">
					<div class="reaction_plus">
						<!-- <img src="img/reaction_plus.png" alt="" width="50" height="50"> -->
						<form action="/D1/StudentQueSubResult_ReactionServlet" method="post">
						<input type="hidden" name="date" value="${e.date}">
						<input class="btn_update" type="submit" value="リアクション" <%-- onclick="onclick_reaction(${e.date}) --%>">
						</form>
					</div>
					</c:if>
					<c:if test="${e.reaction == '1'}">
					<div class="reaction">
						<img src="img/reaction.png" alt="" width="50" height="50">
					</div>
					</c:if>
				</div>
			</c:if>
		</c:forEach>

	</div>