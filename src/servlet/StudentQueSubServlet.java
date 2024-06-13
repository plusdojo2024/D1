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
		response.getWriter().append("Served at: ").append(request.getContextPath());

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

		String content = request.getParameter("content");
		String subject = request.getParameter("subject");

		// 登録処理を行う
		QuestionDao qDao = new QuestionDao();

		if (qDao.insert(new Question(content, subject))) {	// 登録成功
			request.setAttribute("result",
			new Result("登録成功！", "質問を登録しました。", "/D1/StudentQueHisServlet"));
		}
		else {												// 登録失敗
			request.setAttribute("result",
			new Result("登録失敗！", "会員登録に失敗しました。", "/D1/StudentQueServlet"));
		}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(request, response);


	}

}
