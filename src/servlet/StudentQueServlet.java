package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class StudentQueServlet
 */
@WebServlet("/StudentQueServlet")
public class StudentQueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Connection conn = null;
		List<String> cardList = new ArrayList<>();
		request.setCharacterEncoding("UTF-8");
		//login_idをjspから何とか取得したい！方法は模索中…
        HttpSession session = request.getSession();
        String login_id = (String) session.getAttribute("login_id");

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D1", "sa", "");
				//SQLのクエリ（StudentQueServletサーブレットはすべてのテーブルからデータを取得するのですべて結合してから取得）
				String sql = "SELECT User.login_id, User.user_name, User.password, "
						+ "Grade.score, Question.date, Question.content, Question.answer, Question.subject "
						+ "FROM User INNER JOIN Grade ON login_id = Grade.login_id "
						+ "INNER JOIN Question ON User.login_id = Question.login_id"
						+ "WHERE User.login_id = ?";
				PreparedStatement st = conn.prepareStatement(sql);
				st.setString(1, "login_id");
				ResultSet res = st.executeQuery();

				// login_id,user_name,passwordカラム（Userテーブル）のデータを取得するループ
				//以下のwhile処理はResultSetが次の行に移動し、その行が存在する限り実行するので行が統一されているカラム同士を分けてる
				//user_nameとpasswordは使わないのでコメントアウト
//				while (res.next()) {
//				    String userName = res.getString("user_name");
//				    String password = res.getString("password");
//				}

				int contentCount=0;
				// contentカラム（Questionテーブル）のデータを取得するループ
				while (res.next()) {
				    String content = res.getString("content");
				    contentCount++;
				}
				request.setAttribute("contentCount", contentCount);//質問数

				// answerカラム（Questionテーブル）のデータを取得するループ
				// answerとcontentが同じ行数とは限らないのでループを分けました。
				res.beforeFirst();// ResultSetを最初の行の前に移動する（ゼロクリアみたいなもの）
				int answerCount=0;
				while (res.next()) {
				    String answer = res.getString("answer");
				    answerCount++;
				}
				request.setAttribute("answerCount", answerCount);//質問回答数

			}catch(SQLException e) {
				e.printStackTrace();
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}

//		doGet(request, response);
//
//		QuestionDao questionDao = new QuestionDao();
//
//        String content = questionDao.getContent();
//        String answer = questionDao.getAnswer();
//        String subject = questionDao.getSubject();
//
//
//		request.setCharacterEncoding("UTF-8");
//		String id = request.getParameter("login_id");
//		String content = request.getParameter("content");
//		String answer = request.getParameter("answer");
//		String subject = request.getParameter("subject");
//
//		int contentCount = content.trim().split("\\s+").length;
//		int answerCount = answer.trim().split("\\s+").length;
//
//		request.setAttribute("login_id", id);
//		request.setAttribute("subject", subject);
//
//		request.setAttribute("contentCount", contentCount);
//		request.setAttribute("answerCount", answerCount);


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StudentQueServlet.jsp");
		dispatcher.forward(request, response);
	}

}
