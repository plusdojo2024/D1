package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.gradeDao;
import model.LoginUser;
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




		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ScoreConvert.jsp");
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

//      String login_id = (String) session.getAttribute("login_id");
      HttpSession session = request.getSession();
      LoginUser loginUser = (LoginUser) session.getAttribute("login_id");
      String login_id = loginUser.getId();


		// JSP から送られた正答数と問題数を取得
        int correct = Integer.parseInt(request.getParameter("correct"));
        int total = Integer.parseInt(request.getParameter("total"));

        // 100点満点に換算
        double percentage = ((double) correct / total) * 100;

		String subject = request.getParameter("subject");
		int score = (int) percentage;

		//登録処理を行う
		gradeDao gDao = new gradeDao();

		if (gDao.insert(new grade(login_id, null, subject, score))) {	// 登録成功

			request.setAttribute("result",
			new Result("登録成功！", "点数を登録しました。", "/D1/ScoreConvertRegistServlet"));
		}
		else {												// 登録失敗
			request.setAttribute("result",
			new Result("登録失敗！", "点数を登録できませんでした。", "/D1/ScoreConvertRegistServlet"));
		}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Result.jsp");
		dispatcher.forward(request, response);

	}

}
