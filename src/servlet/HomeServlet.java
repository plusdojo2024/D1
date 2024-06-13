package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // もしもログインしていなかったらログインサーブレットにリダイレクトする
//        HttpSession session = request.getSession();
//        if (session.getAttribute("login_id") == null) {
//            response.sendRedirect("/D1/LoginServlet");
//            return;
//        }

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String login_id = request.getParameter("login_id");
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		String date = request.getParameter("date");
		String content = request.getParameter("content");
		String answer = request.getParameter("answer");
		String subject = request.getParameter("subject");
		String time = request.getParameter("time");
		String score = request.getParameter("score");

		//パラメータをリクエスト属性として設定する
		request.setAttribute("login_id", login_id);
		request.setAttribute("user_name", user_name);
		request.setAttribute("password", password);
		request.setAttribute("date", date);
		request.setAttribute("content", content);
		request.setAttribute("answer", answer);
		request.setAttribute("subject", subject);
		request.setAttribute("time", time);
		request.setAttribute("score", score);




        // ホームページにフォワードする
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Home.jsp");
        dispatcher.forward(request, response);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
//		HttpSession session = request.getSession();
//		if (session.getAttribute("login_id") == null) {
//			response.sendRedirect("/D1/LoginServlet");
//			return;
//		}

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String login_id = request.getParameter("login_id");
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		String date = request.getParameter("date");
		String content = request.getParameter("content");
		String answer = request.getParameter("answer");
		String subject = request.getParameter("subject");
		String time = request.getParameter("time");
		String score = request.getParameter("score");



		// ホームページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Home.jsp");
		dispatcher.forward(request, response);
	}
}
