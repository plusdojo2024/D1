package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QuestionDao;
import dao.UserDao;
import dao.gradeDao;
import model.User;
//import model.grade;
//import model.Question;


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

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D1", "sa", "");
				Statement st = conn.createStatement();
				 //PreparedStatement statement = conn.prepareStatement(下のクエリ);エラーがでたら追加
				//SQLのクエリ（Homeサーブレットはすべてのテーブルからデータを取得するのですべて結合してから取得）
				ResultSet res = st.executeQuery("SELECT User.login_id, User.user_name, User.password, "
						+ "Grade.score, Question.date, Question.content, Question.answer, Question.subject "
						+ "FROM User INNER JOIN Grade ON login_id = Grade.login_id "
						+ "INNER JOIN Question ON User.login_id = Question.login_id");

				// login_id,user_name,passwordカラム（Userテーブル）のデータを取得するループ
				//以下のwhile処理はResultSetが次の行に移動し、その行が存在する限り実行するので行が統一されているカラム同士を分けてる
				while (res.next()) {
				    String loginId = res.getString("login_id");
				    String userName = res.getString("user_name");
				    String password = res.getString("password");

				}

				// scoreカラム（Gradeテーブル）のデータを取得するループ
				res.beforeFirst(); // ResultSetを最初の行の前に移動する
				while (res.next()) {
				    int score = res.getInt("score");

				}

				// date,content,subjectカラム（Questionテーブル）のデータを取得するループ
				res.beforeFirst();
				while (res.next()) {
				    String date = res.getString("date");
				    String content = res.getString("content");
				    String subject = res.getString("subject");
				}

				// answerカラム（Questionテーブル）のデータを取得するループ
				//　answerとcontentが同じ行数とは限らないのでループを分けました。
				res.beforeFirst();
				while (res.next()) {
				    String answer = res.getString("answer");
				}

			 } catch(SQLException e) {
		            e.printStackTrace();
		    }

		    request.setAttribute("books", books);


				// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
//		String login_id = request.getParameter("login_id");
		String user_name = request.getParameter("user_name");
		String password = request.getParameter("password");
		String date = request.getParameter("date");
		String content = request.getParameter("content");
		String answer = request.getParameter("answer");
		String subject = request.getParameter("subject");
		String score = request.getParameter("score");


		//データベースからデータを取得する
		UserDao userDao = new UserDao();
		String login_id = User.getLogin_id();

		gradeDao gradeDao = new gradeDao();

		QuestionDao questionDao = new QuestionDao();


		//パラメータをリクエスト属性として設定する
		request.setAttribute("login_id", login_id);
		request.setAttribute("user_name", user_name);
		request.setAttribute("password", password);
		request.setAttribute("date", date);
		request.setAttribute("content", content);
		request.setAttribute("answer", answer);
		request.setAttribute("subject", subject);
		request.setAttribute("score", score);

		//質問内容や質問回答の合計数をカウントする
//		int contentCount = content != null ? content.split(",").length : 0;
//		int answerCount = answer != null ? answer.split(",").length : 0;
//		request.setAttribute("contentCount", contentCount);		//質問数
//		request.setAttribute("answerCount", answerCount);		//質問回答数


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


		// ホームページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Home.jsp");
		dispatcher.forward(request, response);
	}
}
