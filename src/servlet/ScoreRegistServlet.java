package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
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

			// リクエストパラメータを取得する
			request.setCharacterEncoding("UTF-8");
			// 改造（ここから）
			String company = request.getParameter("score");

			// 改造（ここまで）

			// 登録処理を行う
			UserDao UDao = new UserDao();
			// 改造（ここから）
			if (UDao.insert(new grade(score))) {	// 登録成功
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


}
