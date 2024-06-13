package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QuestionDao;

/**
 * Servlet implementation class StudentQueServlet
 */
@WebServlet("/StudentQueServlet")
public class StudentQueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		QuestionDao questionDAO = new QuestionDao();
		String subject = questionDAO.getSubject();

		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("login_id");
		String content = request.getParameter("content");
		String answer = request.getParameter("answer");
		String subject = request.getParameter("subject");

		int contentCount = content.trim().split("\\s+").length;
		int answerCount = answer.trim().split("\\s+").length;

		request.setAttribute("login_id", id);
		request.setAttribute("subject", subject);

		request.setAttribute("contentCount", contentCount);
		request.setAttribute("answerCount", answerCount);


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StudentQueServlet.jsp");
		dispatcher.forward(request, response);
	}

}
