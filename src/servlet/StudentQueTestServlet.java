package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AllQuestionDao;
import model.Question;
/**
 * Servlet implementation class StudentQueSubServlet
 */
@WebServlet("/StudentQueTestServlet")
public class StudentQueTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする

		request.setCharacterEncoding("UTF-8");
		////////質問回数割り出し////////////////////////////////////////////////////////////////////////////////////////
		String login_id = request.getParameter("login_id");
		String date = request.getParameter("date");
		String content = request.getParameter("content");
		String answer = request.getParameter("answer");
		String subject = request.getParameter("subject");

		AllQuestionDao QDao = new AllQuestionDao();
		List<Question> QueList = QDao.select(new Question(login_id, date, content, answer, subject));

			int Count = QueList.size();

			request.setAttribute("Count", Count);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StudentQueSubResult.jsp");
			dispatcher.forward(request, response);
	}

}
