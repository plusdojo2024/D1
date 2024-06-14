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


    private String getNowDate() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return dateFormatter.format(date);
    }

    private String getNowTime() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return timeFormatter.format(date);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");

		//ログインIDの取得方法？？？

		String content = request.getParameter("content");
		String subject = request.getParameter("subject");

	    String date = getNowDate() + getNowTime();


		// 登録処理を行う
		QuestionDao qDao = new QuestionDao();

		//insertエラー
		if (qDao.insert(new Question(content, subject, date, time))) {	// 登録成功
			request.setAttribute("result",
			new Result("登録成功！", "質問を登録しました。", "/D1/StudentQueHisServlet"));
		}
		else {												// 登録失敗
			request.setAttribute("result",
			new Result("登録失敗！", "質問の登録に失敗しました。", "/D1/StudentQueSubServlet"));
		}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(request, response);


	}

}
