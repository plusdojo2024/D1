package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginUser;

/**
 * Servlet implementation class StudentQueSubServlet
 */
@WebServlet("/QueResultServlet")
public class QueResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QueResultServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
        // もしもログインしていなかったらログインサーブレットにリダイレクトする
      HttpSession session = request.getSession();
      if (session.getAttribute("login_id") == null) {
          response.sendRedirect("/D1/LoginServlet");
          return;
      }

				Connection conn = null;

				request.setCharacterEncoding("UTF-8");

				LoginUser loginUser = (LoginUser) session.getAttribute("login_id");
				String login_id = loginUser.getId();

				ArrayList<String> contentCount = new ArrayList<>();

		if (login_id != null) {

			try {

				Class.forName("org.h2.Driver");


				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D1", "sa", "");




				String sql = "SELECT content FROM Question WHERE login_id = ?";
				PreparedStatement st = conn.prepareStatement(sql);
				st.setString(1, login_id);
				ResultSet res = st.executeQuery();

				// contentカラム（Questionテーブル）のデータを取得するループ
				while (res.next()) {
				contentCount.add(res.getString("content"));
				}

				request.setAttribute("contentCount", contentCount);//質問数
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		// ログインページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/QueResult.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		//		request.setCharacterEncoding("UTF-8");
		//		String login_id = request.getParameter("login_id");
		//		String date = request.getParameter("date");
		//		String content = request.getParameter("content");
		//		String answer = request.getParameter("answer");
		//		String subject = request.getParameter("subject");
		//
		//
		//		// 検索処理を行う
		//		QuestionDao QDao = new QuestionDao();
		//		List<Question> QueList = QDao.select(new Question(login_id, date, content,answer,subject));
		//
		//		// 検索結果をリクエストスコープに格納する
		//		request.setAttribute("QueList", QueList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/QueResult.jsp");
		dispatcher.forward(request, response);
	}

}
