package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QuestionDao;
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StudentQueSub.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String login_id=request.getParameter("login_id");
		String date=request.getParameter("date");
		String content=request.getParameter("content");
		String answer=request.getParameter("answer");
		String subject=request.getParameter("subject");
		System.out.println("Received content: " + content);
		QuestionDao qDao=new QuestionDao();


		if(qDao.insert(new Question(login_id, date, content, answer, subject))) {
			request.setAttribute("result",
					new Result("登録完了","質問又は回答を受け付けました！","/D1/StudentQueSubResultServlet"));
		}
		else {
			request.setAttribute("result",
					new Result("登録失敗", "質問又は回答を受け付けられませんでした。","/D1/StudentQueSubServlet"));
		}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(request, response);


	}

}
