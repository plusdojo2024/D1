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
@WebServlet("/ScoreConvertRegistServlet")
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method stub

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ScoreConvert.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");

		//      String login_id = (String) session.getAttribute("login_id");
		HttpSession session = request.getSession();
		LoginUser loginUser = (LoginUser) session.getAttribute("login_id");
		String login_id = loginUser.getId();

		// JSP から送られた正答数と問題数を取得
		int correct = Integer.parseInt(request.getParameter("correct"));
		int total = Integer.parseInt(request.getParameter("total"));

		if(login_id.isEmpty() || correct==0 || total==0) {
			request.setAttribute("result",
			new Result("登録失敗！", "全ての項目を入力してください。", "/D1/StudentQueSubResultServlet"));
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/Result.jsp");
			dispatcher.forward(request, response);
			return;
		}


		// 100点満点に換算
		double percentage = ((double) correct / total) * 100;

		String date = request.getParameter("date");
		String subject = request.getParameter("subject");
		int score = (int) percentage;

//		System.out.println("Received login_id: " + login_id);
//		System.out.println("Received date: " + date);
//		System.out.println("Received score: " + score);
//		System.out.println("Received subject: " + subject);

		if(login_id.isEmpty() || score==0 || date.isEmpty()|| subject.isEmpty()) {
			request.setAttribute("result",
			new Result("登録失敗！", "全ての項目を入力してください。", "/D1/StudentQueSubResultServlet"));
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/Result.jsp");
			dispatcher.forward(request, response);
			return;
		}


		//登録処理を行う
		gradeDao gDao = new gradeDao();

		if (gDao.insert(new grade(login_id, date, subject, score))) { // 登録成功

			request.setAttribute("result",
					new Result("登録成功！", "点数を登録しました。", "/WEB-INF/jsp/Result.jsp"));
		} else { // 登録失敗
			request.setAttribute("result",
					new Result("登録失敗！", "点数を登録できませんでした。", "/WEB-INF/jsp/Result.jsp"));
		}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Result.jsp");
		dispatcher.forward(request, response);

	}

}
