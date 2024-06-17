package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginUser;
import model.ScoreDate;



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
		List<ScoreDate> scoreDateList = new ArrayList<>();//リストで取得したい時に使う
        request.setCharacterEncoding("UTF-8");

        //login_idをjspから何とか取得したい！方法は模索中…
//        String login_id = (String) session.getAttribute("login_id");
        HttpSession session = request.getSession();
        LoginUser loginUser = (LoginUser) session.getAttribute("login_id");
        String login_id = loginUser.getId();
        if (login_id != null) {

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D1", "sa", "");
				//SQLのクエリ（Homeサーブレットはすべてのテーブルからデータを取得するのですべて結合してから取得）
				String sql = "SELECT content FROM Question WHERE login_id = ?";
				PreparedStatement st = conn.prepareStatement(sql);
				st.setString(1, login_id);
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
				String sql2 = "SELECT answer FROM Question WHERE login_id = ?";
				PreparedStatement st2 = conn.prepareStatement(sql2);
				st2.setString(1, login_id);
				ResultSet res2 = st2.executeQuery();


				int answerCount=0;
				while (res2.next()) {
				    String answer = res2.getString("answer");
				    answerCount++;
				}
				request.setAttribute("answerCount", answerCount);//質問回答数

				//このクエリですべての教科の平均スコアが取得される

				// SQLクエリ
				String sql5 = "SELECT subject, score, date FROM Grade WHERE login_id = ?";
				PreparedStatement st5 = conn.prepareStatement(sql5);
				st5.setString(1, login_id);
				ResultSet rs5 = st5.executeQuery();

				// スコアデータを保持するリスト
				List<ScoreDate> scoreDateList1 = new ArrayList<>();

				// ResultSetからデータを取得してScoreDateオブジェクトを作成しリストに追加
				while (rs5.next()) {
				    String subject = rs5.getString("subject");
				    int score = rs5.getInt("score");
				    java.sql.Date date = rs5.getDate("date");

				    // LocalDateに変換して年と月を取得
				    LocalDate localDate = date.toLocalDate();
				    Year year = Year.of(localDate.getYear());
				    Month month = localDate.getMonth();

				    // ScoreDateオブジェクトを作成し、リストに追加
				    ScoreDate scoreData = new ScoreDate(subject, score, date, year, month);
				    scoreDateList1.add(scoreData);
				}

				// 月次平均スコアを計算するためのリスト
				List<Double> avgScores = new ArrayList<>();

				// 各月の平均スコアを計算
				for (Month month : Month.values()) {
				    double sum = 0;
				    int count = 0;

				    for (ScoreDate scoreDate : scoreDateList1) {
				        if (scoreDate.getYear().getValue() == 2020 && scoreDate.getMonth() == month) {
				            sum += scoreDate.getScore();
				            count++;
				        }
				    }

				    if (count > 0) {
				        double avg = sum / count;
				        avgScores.add(avg);
				    } else {
				        avgScores.add(0.0); // データがない場合は平均点0とする
				    }
				}

				// 年と月ごとの平均スコアを取得するSQLクエリ
				String avgScoreSql = "SELECT YEAR(date) AS year, MONTH(date) AS month, AVG(score) AS avg_score "
				        + "FROM Grade WHERE login_id = ? "
				        + "GROUP BY YEAR(date), MONTH(date)";
				PreparedStatement avgStmt = conn.prepareStatement(avgScoreSql);
				avgStmt.setString(1, login_id);

				ResultSet avgRs = avgStmt.executeQuery();

				// 年と月ごとの平均スコアを格納するマップ
				Map<String, Double> avgScoresMap = new HashMap<>();

				// 結果セットからデータを取得し、マップに格納
				while (avgRs.next()) {
				    int avgYear = avgRs.getInt("year");
				    int avgMonth = avgRs.getInt("month");
				    double avgScore = avgRs.getDouble("avg_score");

				    String key = avgYear + "-" + String.format("%02d", avgMonth); // 年-月の形式でキーを作成
				    avgScoresMap.put(key, avgScore);
				}

				// JSPにデータを渡すためにリクエストスコープにセット
				request.setAttribute("scoreDateList", scoreDateList1);
				request.setAttribute("avgScoresMap", avgScoresMap);




				//このクエリで最高の平均スコアを持つ科目が取得される
				String sql4 = "SELECT subject, AVG(score) AS avg_score FROM Grade "
						+ "WHERE login_id = ? "
						+ "GROUP BY subject ORDER BY avg_score DESC LIMIT 1";
				PreparedStatement st4 = conn.prepareStatement(sql4);
				st4.setString(1, login_id);
				ResultSet res4 = st4.executeQuery();
				String subject = null;

				if (res4.next()) { // 結果セットが空でない場合にのみ処理を実行
				    subject = res4.getString("subject");
				}

				request.setAttribute("subject", subject);//最高の平均スコアを持つ科目


			 } catch(SQLException e) {
		            e.printStackTrace();
		    }catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    }

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
