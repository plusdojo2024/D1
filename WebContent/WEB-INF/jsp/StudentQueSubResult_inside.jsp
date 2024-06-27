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
						<p>【質問】名前:${e.login_id}　　科目:${e.subject}　　${e.date}</p>
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
						<p>【回答】名前:${e.login_id}　　科目:${e.subject}　　${e.date}</p>
						<br>
						<p>${e.answer}</p>
					</div>
				</div>
				<c:if test="${e.reaction == '0'}">
					<div class="reaction_plus">

							<%-- <input type="hidden" name="date" value="${e.date}"> <input class="btn_update margin" type="submit" value=""> --%>
							<nav>

								<ul>
									<li class="has-child"><a href="#"><img src="img/reaction_plus.png" alt="" width="40"height="40"></a>
										<ul>
											<li><form action="/D1/StudentQueSubResult_ReactionServlet" method="post"><a href="#"><input type="hidden" name="date" value="${e.date}"><input type="hidden" name="reaction" value="1"><input class="btn_update1" type="submit" value=""></a></form></li>
											<li><form action="/D1/StudentQueSubResult_ReactionServlet" method="post"><a href="#"><input type="hidden" name="date" value="${e.date}"><input type="hidden" name="reaction" value="2"><input class="btn_update2" type="submit" value=""></a></form></li>
											<li><form action="/D1/StudentQueSubResult_ReactionServlet" method="post"><a href="#"><input type="hidden" name="date" value="${e.date}"><input type="hidden" name="reaction" value="3"><input class="btn_update3" type="submit" value=""></a></form></li>
											<li><form action="/D1/StudentQueSubResult_ReactionServlet" method="post"><a href="#"><input type="hidden" name="date" value="${e.date}"><input type="hidden" name="reaction" value="4"><input class="btn_update4" type="submit" value=""></a></form></li>
											<li><form action="/D1/StudentQueSubResult_ReactionServlet" method="post"><a href="#"><input type="hidden" name="date" value="${e.date}"><input type="hidden" name="reaction" value="5"><input class="btn_update5" type="submit" value=""></a></form></li>
										</ul>
									</li>
								</ul>

							</nav>
					</div>
				</c:if>

				<c:if test="${e.reaction == '1'}">
					<div class="reaction">
						<img class="margin" src="img/reaction1.png" alt="" width="70"height="70">
					</div>
				</c:if>

				<c:if test="${e.reaction == '2'}">
					<div class="reaction">
						<img class="margin" src="img/reaction2.png" alt="" width="70"height="70">
					</div>
				</c:if>

				<c:if test="${e.reaction == '3'}">
					<div class="reaction">
						<img class="margin" src="img/reaction3.png" alt="" width="70"height="70">
					</div>
				</c:if>

				<c:if test="${e.reaction == '4'}">
					<div class="reaction">
						<img class="margin" src="img/reaction4.png" alt="" width="70"height="70">
					</div>
				</c:if>

				<c:if test="${e.reaction == '5'}">
					<div class="reaction">
						<img class="margin" src="img/reaction5.png" alt="" width="70"height="70">
					</div>
				</c:if>
			</div>
		</c:if>
	</c:forEach>

</div>