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

import dao.AllQuestionDao;
import model.Question;
/**
 * Servlet implementation class StudentQueSubServlet
 */
@WebServlet("/StudentQueSubResult_insideServlet")
public class StudentQueSubResult_insideServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (session.getAttribute("login_id") == null) {
			response.sendRedirect("/D1/LoginServlet");
			return;
		}


		request.setCharacterEncoding("UTF-8");

		String date = request.getParameter("date");
		String login_id = request.getParameter("login_id");
		String content = request.getParameter("content");
		String answer = request.getParameter("answer");
		String subject = request.getParameter("subject");

		AllQuestionDao QDao = new AllQuestionDao();
		List<Question> QueList = QDao.select(new Question(login_id, date, content, answer, subject));

		request.setAttribute("QueList", QueList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StudentQueSubResult_inside.jsp");
		dispatcher.forward(request, response);

	}

}
