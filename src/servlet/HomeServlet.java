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



/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // もしもログインしていなかったらログインサーブレットにリダイレクトする
//        HttpSession session = request.getSession();
//        if (session.getAttribute("login_id") == null) {
//            response.sendRedirect("/D1/LoginServlet");
//            return;
//        }

		Connection conn = null;
        List<String> cardList = new ArrayList<>();
        request.setCharacterEncoding("UTF-8");
        String loginId = request.getParameter("login_id");//login_idをjspから何とか取得したい！方法は模索中…
			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D1", "sa", "");
				//SQLのクエリ（Homeサーブレットはすべてのテーブルからデータを取得するのですべて結合してから取得）
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


				res.beforeFirst(); // ResultSetを最初の行の前に移動する
				// scoreカラム（Gradeテーブル）のデータを取得するループ
				while (res.next()) {
				    int score = res.getInt("score");

				}


				res.beforeFirst();
				// date,content,subjectカラム（Questionテーブル）のデータを取得するループ
				while (res.next()) {
				    String date = res.getString("date");
				    String subject = res.getString("subject");
				}

				res.beforeFirst();
				int contentCount=0;
				// contentカラム（Questionテーブル）のデータを取得するループ
				while (res.next()) {
				    String content = res.getString("content");
				    contentCount++;
				}
				request.setAttribute("contentCount", contentCount);

				// answerカラム（Questionテーブル）のデータを取得するループ
				//　answerとcontentが同じ行数とは限らないのでループを分けました。
				res.beforeFirst();
				int answerCount=0;
				while (res.next()) {
				    String answer = res.getString("answer");
				    answerCount++;
				}
				request.setAttribute("answerCount", answerCount);

			 } catch(SQLException e) {
		            e.printStackTrace();
		    }catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    }


		//パラメータをリクエスト属性として設定する
		request.setAttribute("login_id", login_id);
		request.setAttribute("user_name", user_name);
		request.setAttribute("password", password);
		request.setAttribute("date", date);
		request.setAttribute("content", content);
		request.setAttribute("answer", answer);
		request.setAttribute("subject", subject);
		request.setAttribute("score", score);




        // ホームページにフォワードする
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Home.jsp");
        dispatcher.forward(request, response);
			}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
//		HttpSession session = request.getSession();
//		if (session.getAttribute("login_id") == null) {
//			response.sendRedirect("/D1/LoginServlet");
//			return;
//		}

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String login_id = request.getParameter("login_id");
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		String date = request.getParameter("date");
		String content = request.getParameter("content");
		String answer = request.getParameter("answer");
		String subject = request.getParameter("subject");
		String time = request.getParameter("time");
		String score = request.getParameter("score");



		// ホームページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Home.jsp");
		dispatcher.forward(request, response);
	}
}
