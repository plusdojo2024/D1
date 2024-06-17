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
        List<String> cardList = new ArrayList<>();//リストで取得したい時に使う（HomeServletでは未使用）
        request.setCharacterEncoding("UTF-8");

        //login_idをjspから何とか取得したい！方法は模索中…
        HttpSession session = request.getSession();
        String login_id = (String) session.getAttribute("login_id");
        if (login_id != null) {

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

				int contentCount=0;
				// contentカラム（Questionテーブル）のデータを取得するループ
				while (res.next()) {
				    String content = res.getString("content");
				    contentCount++;
				}
				request.setAttribute("contentCount", contentCount);//質問数

				// answerカラム（Questionテーブル）のデータを取得するループ
				//answerとcontentが同じ行数とは限らないのでループを分けました。
				res.beforeFirst();// ResultSetを最初の行の前に移動する（ゼロクリアみたいなもの）
				int answerCount=0;
				while (res.next()) {
				    String answer = res.getString("answer");
				    answerCount++;
				}
				request.setAttribute("answerCount", answerCount);//質問回答数

				//このクエリですべての教科の平均スコアが取得される
				String sql2 = "SELECT AVG(score) AS avg_score FROM Grade WHERE login_id = ?";
				PreparedStatement st2 = conn.prepareStatement(sql2);
				st2.setString(1, "login_id");
				ResultSet res2 = st2.executeQuery();
				res2.beforeFirst();

				double avgScore = 0;

				if (res2.next()) { // 結果セットが空でない場合にのみ処理を実行
				    avgScore = res2.getDouble("avg_score");
				}

				request.setAttribute("avgScore", avgScore);//最高の平均スコアを持つ科目の平均点数



				//このクエリで最高の平均スコアを持つ科目が取得される
				String sql3 = "SELECT subject, AVG(score) AS avg_score FROM Grade "
						+ "WHERE login_id = ? "
						+ "GROUP BY subject ORDER BY avg_score DESC LIMIT 1";
				PreparedStatement st3 = conn.prepareStatement(sql3);
				ResultSet res3 = st3.executeQuery();
				res3.beforeFirst();
				String subject = null;

				if (res3.next()) { // 結果セットが空でない場合にのみ処理を実行
				    subject = res3.getString("subject");
				}

				request.setAttribute("subject", subject);//最高の平均スコアを持つ科目


			 } catch(SQLException e) {
		            e.printStackTrace();
		    }catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    }
			int[] test1 = {10,20,30,40,50,60,70,80,90,100,80,10};
			request.setAttribute("test1", test1);//質問回答数

        // ホームページにフォワードする
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Home.jsp");
        dispatcher.forward(request, response);
	}
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
//
//		// リクエストパラメータを取得する
//		request.setCharacterEncoding("UTF-8");
//		String login_id = request.getParameter("login_id");
//		String user_name = request.getParameter("user_name");
//		String password = request.getParameter("password");
//		String date = request.getParameter("date");
//		String content = request.getParameter("content");
//		String answer = request.getParameter("answer");
//		String subject = request.getParameter("subject");
//		String time = request.getParameter("time");
//		String score = request.getParameter("score");
//
//
//
//		// ホームページにフォワードする
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Home.jsp");
//		dispatcher.forward(request, response);
	}
}
