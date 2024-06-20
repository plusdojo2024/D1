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
@WebServlet("/StudentQueSubResultServlet")
public class StudentQueSubResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StudentQueSubResult.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
        HttpSession session = request.getSession();
		LoginUser loginUser = (LoginUser) session.getAttribute("login_id");


		String login_id = loginUser.getId();
		String date = request.getParameter("date");
		String content = request.getParameter("content");
		String answer = request.getParameter("answer");
		String subject = request.getParameter("subject");

		QuestionDao QDao = new QuestionDao();
		List<Question> QueList = QDao.select(new Question(login_id, date, content, answer, subject));

		request.setAttribute("QueList", QueList);



		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StudentQueSubResult.jsp");
		dispatcher.forward(request, response);
	}

}
