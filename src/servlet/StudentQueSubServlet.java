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
@WebServlet("/StudentQueSubServlet")
public class StudentQueSubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentQueSubServlet() {
        super();
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

        // もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("login_id") == null) {
			response.sendRedirect("/D1/LoginServlet");
			return;
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StudentQueSub.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

        // もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("login_id") == null) {
			response.sendRedirect("/D1/LoginServlet");
			return;
		}

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		LoginUser loginUser = (LoginUser) session.getAttribute("login_id");

		String login_id = loginUser.getId();

        LocalDateTime date1 = LocalDateTime.now();
		DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String date = date1.format(dtformat);

		String content=request.getParameter("content");
		String answer=request.getParameter("answer");
		String subject=request.getParameter("subject");
		String reaction = request.getParameter("reaction");
//		System.out.println("Received content: " + content);
//		System.out.println("Received content: " + login_id);
//		System.out.println("Received content: " + date);
//		System.out.println("Received content: " + subject);
//		System.out.println("Received content: " + answer);

		if(login_id.isEmpty() || content.isEmpty()|| subject.isEmpty()) {
			request.setAttribute("result",
			new Result("登録失敗！", "全ての項目を入力してください。", "/D1/StudentQueSubServlet"));
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/Result.jsp");
			dispatcher.forward(request, response);
			return;
		}


		QuestionDao qDao=new QuestionDao();


		if(qDao.insert(new Question(login_id, date, content, answer, subject,reaction))) {
			request.setAttribute("result",
					new Result("登録完了","質問を受け付けました！","/D1/StudentQueSubServlet"));
		}
		else {
			request.setAttribute("result",
					new Result("登録失敗", "質問を受け付けられませんでした。","/D1/StudentQueSubServlet"));
		}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Result.jsp");
		dispatcher.forward(request, response);


	}

}
