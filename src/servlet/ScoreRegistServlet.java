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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/D1/LoginServlet");
			return;
	}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Home.jsp");
		dispatcher.forward(request, response);
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		// もしもログインしていなかったらログインサーブレットにリダイレクトするh
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/D1/LoginServlet");
			return;
		}

			request.setCharacterEncoding("UTF-8");
			//int型は一旦string型でパラメーターを受け取ってそれをint型に直す必要がある
			String scoreString = request.getParameter("score");
			int score = Integer.parseInt(scoreString);
			// 改造（ここまで）

			// 登録処理を行う
			gradeDao gDao = new gradeDao();
			// 改造（ここから）
			if (gDao.insert(new grade(null,null,null,null,score))) {	// 登録成功
				// 改造（ここまで）
				request.setAttribute("result",
				new Result("登録成功！", "成績を登録しました。", "/D1/HomeServlet"));
			}
			else {												// 登録失敗
				request.setAttribute("result",
				new Result("登録失敗！", "成績を登録できませんでした。", "/D1/HomeServlet"));
			}

			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
			dispatcher.forward(request, response);

		}
	}


