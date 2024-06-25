package servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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


		/*		request.setCharacterEncoding("UTF-8");

				String date = request.getParameter("date");
				String login_id = request.getParameter("login_id");
				String content = request.getParameter("content");
				String answer = request.getParameter("answer");
				String subject = request.getParameter("subject");

				AllQuestionDao QDao = new AllQuestionDao();
				List<Question> QueList = QDao.select(new Question(login_id, date, content, answer, subject));

				request.setAttribute("QueList", QueList);
		*/
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StudentQueSubResult.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		LoginUser loginUser = (LoginUser) session.getAttribute("login_id");

		String login_id = loginUser.getId();

		LocalDateTime date1 = LocalDateTime.now();
		DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String date = date1.format(dtformat);

		String answer=request.getParameter("answer");
		String subject=request.getParameter("subject");
		String reaction = request.getParameter("reaction");

		if(login_id.isEmpty() || answer.isEmpty() || subject.isEmpty()) {
			request.setAttribute("result",
			new Result("登録失敗！", "全ての項目を入力してください。", "/D1/StudentQueSubResultServlet"));
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/Result.jsp");
			dispatcher.forward(request, response);
			return;
		}

		QuestionDao qDao=new QuestionDao();



		if(qDao.insert(new Question(login_id, date, null, answer, subject,reaction))) {
			request.setAttribute("result",
					new Result("登録完了","回答を受け付けました！","/D1/StudentQueSubResultServlet"));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Result.jsp");
			dispatcher.forward(request, response);
		}
		else {
			request.setAttribute("result",
					new Result("登録失敗", "回答を受け付けられませんでした。","/D1/StudentQueSubResultServlet"));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Result.jsp");
			dispatcher.forward(request, response);
		}

	}

}
