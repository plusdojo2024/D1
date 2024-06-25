package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QuestionDao;
import model.LoginUser;
import model.Question;

/**
 * Servlet implementation class StudentQueSubServlet
 */
@WebServlet("/AnsResultServlet")
public class AnsResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AnsResultServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("login_id") == null) {
			response.sendRedirect("/D1/LoginServlet");
			return;
		}

		LoginUser loginUser = (LoginUser) session.getAttribute("login_id");

		request.setCharacterEncoding("UTF-8");
		String login_id = loginUser.getId();
		String date = request.getParameter("date");
		String content = request.getParameter("content");
		String answer = request.getParameter("answer");
		String subject = request.getParameter("subject");
		String reaction = request.getParameter("reaction");

		QuestionDao QDao = new QuestionDao();
		List<Question> QueList = QDao.select(new Question(login_id, date, content, answer, subject,reaction));

		request.setAttribute("QueList", QueList);

		// ログインページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/AnsResult.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("login_id") == null) {
			response.sendRedirect("/D1/LoginServlet");
			return;
		}



		LoginUser loginUser = (LoginUser) session.getAttribute("login_id");

		request.setCharacterEncoding("UTF-8");
		String login_id = loginUser.getId();
		String date = request.getParameter("date");
		String content = request.getParameter("content");
		String answer = request.getParameter("answer");
		String subject = request.getParameter("subject");
		String reaction = request.getParameter("reaction");

		QuestionDao QDao = new QuestionDao();
		List<Question> QueList = QDao.select(new Question(login_id, date, content, answer, subject,reaction));

		request.setAttribute("QueList", QueList);

		// ログインページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/AnsResult.jsp");
		dispatcher.forward(request, response);
	}

}
