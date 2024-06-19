package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
 * Servlet implementation class ScoreRegistServlet
 */
@WebServlet("/ScoreRegistServlet")
public class ScoreRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ScoreRegist.jsp");
		dispatcher.forward(request, response);
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Connection conn = null;
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
        LoginUser loginUser = (LoginUser) session.getAttribute("login_id");
        String login_id = loginUser.getId();
		String subject = request.getParameter("subject");
		String date = request.getParameter("date");
		//int型は一旦string型でパラメーターを受け取ってそれをint型に直す必要がある
		String scoreString = request.getParameter("score");
		int score = Integer.parseInt(scoreString);

		if(login_id != null) {
			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D1", "sa", "");
				//SQLのクエリ
				String sql = "SELECT date FROM Grade WHERE login_id = ?";
				PreparedStatement st = conn.prepareStatement(sql);
				st.setString(1, login_id);

				String sql2 = "SELECT subject FROM Grade WHERE login_id = ?";
				PreparedStatement st2 = conn.prepareStatement(sql2);
				st2.setString(1, login_id);

				String sql3 = "SELECT grade FROM Grade WHERE login_id = ?";
				PreparedStatement st3 = conn.prepareStatement(sql3);
				st3.setString(1, login_id);

			}catch(SQLException e) {
				e.printStackTrace();
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		gradeDao gDao = new gradeDao();

		if (gDao.insert(new grade(login_id,date,subject,score))) {	// 登録成功

			request.setAttribute("result",
			new Result("登録成功！", "成績を登録しました。", "/D1/HomeServlet"));
		}
		else {												// 登録失敗
			request.setAttribute("result",
			new Result("登録失敗！", "成績を登録できませんでした。", "/D1/ScoreRegistServlet"));
		}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Result.jsp");
		dispatcher.forward(request, response);

	}
}


