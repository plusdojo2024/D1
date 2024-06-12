package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QuestionDao;
import model.Question;
import model.Result;

/**
 * Servlet implementation class StudentQueHisServlet
 */
@WebServlet("/StudentQueHisServlet")
public class StudentQueHisServlet extends HttpServlet {
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
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/D1/LoginServlet");
			return;
		}

			request.setCharacterEncoding("UTF-8");
			String id = request.getParameter("login_id");
			String content = request.getParameter("content");
			String answer = request.getParameter("answer");


			QuestionDao qDao = new QuestionDao();


			if (qDao.insert(new Question(id,null,null,content,answer,null))) {

			}
			else {
				request.setAttribute("result",new Result("取得失敗！", "データを取得できませんでした。", "/D1/HomeServlet"));
			}

			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StudentQueHis.jsp");
			dispatcher.forward(request, response);

	}

}
