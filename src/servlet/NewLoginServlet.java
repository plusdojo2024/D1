package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import model.Result;
import model.User;

/**
 * Servlet implementation class NewLoginServlet
 */
@WebServlet("/NewLoginServlet")
public class NewLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
//    public NewLoginServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/NewLogin.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");

		String login_id = request.getParameter("login_id");
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");

		if(login_id.isEmpty() || user_name.isEmpty() || password.isEmpty()) {
			request.setAttribute("result",
			new Result("登録失敗！", "全ての項目を入力してください。", "/D1/NewLoginServlet"));
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/Result.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// 登録処理を行う
		UserDao uDao = new UserDao();

		if(uDao.exists(login_id)) {
			request.setAttribute("result",
			new Result("登録失敗！", "このIDはすでに使用済みです。", "/D1/NewLoginServlet"));
			RequestDispatcher dispacher = request.getRequestDispatcher("WEB-INF/jsp/Result.jsp");
			dispacher.forward(request, response);
		}else {

			if (uDao.insert(new User(login_id, user_name, password))) {	// 登録成功
				request.setAttribute("result",
				new Result("登録成功！", "ユーザー登録に成功しました。", "/D1/HomeServlet"));
			}
			else {												// 登録失敗
				request.setAttribute("result",
				new Result("登録失敗！", "ユーザー登録に失敗しました。", "/D1/NewLoginServlet"));
			}

		}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp");
		dispatcher.forward(request, response);

	}

}
