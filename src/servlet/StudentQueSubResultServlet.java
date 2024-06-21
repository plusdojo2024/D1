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
import model.Result;
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
		HttpSession session = request.getSession();
		if (session.getAttribute("login_id") == null) {
			response.sendRedirect("/D1/LoginServlet");
			return;
		}


		request.setCharacterEncoding("UTF-8");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		String login_id=request.getParameter("login_id");
		String date=request.getParameter("date");
		String answer=request.getParameter("answer");

		QuestionDao qDao=new QuestionDao();


		if(qDao.insert(new Question(login_id, date, null, answer, null))) {
			request.setAttribute("result",
					new Result("登録完了","回答を受け付けました！","/D1/StudentQueSubResultServlet"));
		}
		else {
			request.setAttribute("result",
					new Result("登録失敗", "回答を受け付けられませんでした。","/D1/StudentQueSubServlet"));
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StudentQueSubResult.jsp");
		dispatcher.forward(request, response);
	}

}
