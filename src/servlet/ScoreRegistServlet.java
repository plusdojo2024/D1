package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.gradeDao;
import model.LoginUser;
import model.Result;
import model.grade;

/**
 * Servlet implementation class ScoreRegistServlet
 */
@WebServlet("/ScoreRegistServlet")
public class ScoreRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		if (session.getAttribute("login_id") == null) {
			response.sendRedirect("/D1/LoginServlet");
			return;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ScoreRegist.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		if (session.getAttribute("login_id") == null) {
			response.sendRedirect("/D1/LoginServlet");
			return;
		}

		request.setCharacterEncoding("UTF-8");

		LoginUser loginUser = (LoginUser) session.getAttribute("login_id");
		String login_id = loginUser.getId();
		String subject = request.getParameter("subject");
		String date = request.getParameter("date");
		//int型は一旦string型でパラメーターを受け取ってそれをint型に直す必要がある
		String scoreString = request.getParameter("score");

		if(login_id.isEmpty() || date.isEmpty() || scoreString.isEmpty() || subject.isEmpty()) {
			request.setAttribute("result",
			new Result("登録失敗！", "全ての項目を入力してください。", "/D1/ScoreRegistServlet"));
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/Result.jsp");
			dispatcher.forward(request, response);
			return;
		}

		//int型に直す
		int score = Integer.parseInt(scoreString);

		gradeDao gDao = new gradeDao();

		if (gDao.insert(new grade(login_id, date, subject, score))) { // 登録成功

			request.setAttribute("result",
					new Result("登録成功！", "成績を登録しました。", "/D1/ScoreRegistServlet"));
		} else { // 登録失敗
			request.setAttribute("result",
					new Result("登録失敗！", "成績を登録できませんでした。", "/WEB-INF/jsp/Result.jsp"));
		}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Result.jsp");
		dispatcher.forward(request, response);

	}
}
