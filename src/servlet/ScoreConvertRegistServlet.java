package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.gradeDao;
import model.Result;
import model.grade;

/**
 * Servlet implementation class ScoreConvertServlet
 */
@WebServlet("/ScoreConvertServlet")
public class ScoreConvertRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScoreConvertRegistServlet() {
        super();
        // TODO Auto-generated constructor stub
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

		//100点満点換算をするうえで本来の満点得点を記録する必要は？

		// JSP から送られた正答数と問題数を取得
        int correct = Integer.parseInt(request.getParameter("correctAnswers"));
        int total = Integer.parseInt(request.getParameter("totalQuestions"));

        // 正答率を計算して100点満点に換算
        double percentage = ((double) correct / total) * 100;

		String subject = request.getParameter("subject");
		int score = (int) percentage;

		//登録処理を行う
		// 未解決：insertのエラー、Resultの中身最後、フォワード先
		gradeDao gDao = new gradeDao();

		if (gDao.insert(new grade(subject, score))) {	// 登録成功

			request.setAttribute("result",
			new Result("登録成功！", "点数を登録しました。", "/D1/MenuServlet"));
		}
		else {												// 登録失敗
			request.setAttribute("result",
			new Result("登録失敗！", "点数を登録できませんでした。", "/D1/MenuServlet"));
		}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(request, response);

	}

}
