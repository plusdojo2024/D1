package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import model.Bc;
import model.Result;

/**
 * Servlet implementation class ScoreRegistServlet
 */
@WebServlet("/ScoreRegistServlet")
public class ScoreRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScoreRegistServlet() {
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
		HttpSession session = request.getSession();
		if (session.getAttribute("id") == null) {
			response.sendRedirect("/simpleBC/LoginServlet");
			return;

			// リクエストパラメータを取得する
			request.setCharacterEncoding("UTF-8");
			// 改造（ここから）
			String company = request.getParameter("company");
			String department = request.getParameter("department");
			String position = request.getParameter("position");
			String name = request.getParameter("name");
			// 改造（ここまで）

			// 登録処理を行う
			UserDao UserDao = new UserDao();
			// 改造（ここから）
			if (UserDao.insert(new Bc(0, company, department, position, name))) {	// 登録成功
				// 改造（ここまで）
				request.setAttribute("result",
				new Result("登録成功！", "レコードを登録しました。", "/simpleBC/MenuServlet"));
			}
			else {												// 登録失敗
				request.setAttribute("result",
				new Result("登録失敗！", "レコードを登録できませんでした。", "/simpleBC/MenuServlet"));
			}

			// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
			dispatcher.forward(request, response);
		}
	}


}
