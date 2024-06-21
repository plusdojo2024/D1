package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
//		List<Double> avgScores = new ArrayList<>();//リストで取得したい時に使う
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
				    contentCount++;
				}

				request.setAttribute("contentCount", contentCount);//質問数


				// answerカラム（Questionテーブル）のデータを取得するループ
				//　answerとcontentが同じ行数とは限らないのでループを分けました。
				String sql2 = "SELECT answer FROM Question WHERE login_id = ?";
				PreparedStatement st2 = conn.prepareStatement(sql2);
				st2.setString(1, login_id);
				ResultSet res2 = st2.executeQuery();

				int answerCount=0;
				while (res2.next()) {
				    answerCount++;
				}
				request.setAttribute("answerCount", answerCount);//質問回答数

				//このクエリですべての教科の平均スコアが取得される
				String sql3 = "SELECT AVG(score) AS avg_score FROM Grade WHERE login_id = ?";
				PreparedStatement st3 = conn.prepareStatement(sql3);
				st3.setString(1, login_id);
				ResultSet res3 = st3.executeQuery();
				double avgScore = 0;

				if (res3.next()) { // 結果セットが空でない場合にのみ処理を実行
				    avgScore = res3.getDouble("avg_score");
				}

				request.setAttribute("avgScore", avgScore);//最高の平均スコアを持つ科目の平均点数


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




	        PreparedStatement stmt1 = null;
	        ResultSet rs1 = null;

	        PreparedStatement stmt2 = null;
	        ResultSet rs2 = null;

	        try {

	        	Class.forName("org.h2.Driver");
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D1", "sa", "");


	            // 年と月ごとに全科目の平均スコアを取得するSQLクエリ
	            String sql = "SELECT YEAR(Date) AS year, MONTH(Date) AS month, AVG(score) AS averageScore "
	                         +"FROM Grade WHERE login_id = ?"
	                         +"GROUP BY YEAR(Date), MONTH(Date)"
	                         +"ORDER BY year ASC, month ASC";
	            stmt1 = conn.prepareStatement(sql);
	            stmt1.setString(1, login_id);
	            rs1 = stmt1.executeQuery();

	            // 年と月ごとの平均スコアを格納するリスト
	            List<Map<String, Object>> averageScoresList = new ArrayList<>();

	            while (rs1.next()) {
	                int year = rs1.getInt("year");
	                int month = rs1.getInt("month");
	                double averageScore = rs1.getDouble("averageScore");

	                Map<String, Object> entry = new HashMap<>();
	                entry.put("year", year);
	                entry.put("month", month);
	                entry.put("averageScore", averageScore);
	                averageScoresList.add(entry);
	            }

	            request.setAttribute("averageScores",  averageScoresList);

	         // 年と月ごとに国語の平均スコアを取得するSQLクエリ
	            String sql2 = "SELECT YEAR(Date) AS year, MONTH(Date) AS month, AVG(score) AS averageScore "
	                         +"FROM Grade WHERE login_id = ? AND subject = '国語'"
	                         +"GROUP BY YEAR(Date), MONTH(Date)"
	                         +"ORDER BY year ASC, month ASC";
	            stmt2 = conn.prepareStatement(sql2);
	            stmt2.setString(1, login_id);
	            rs2 = stmt2.executeQuery();

	            // 年と月ごとの平均スコアを格納するリスト
	            List<Map<String, Object>> japaneseAvgScoresList = new ArrayList<>();

	            while (rs2.next()) {
	                int year = rs2.getInt("year");
	                int month = rs2.getInt("month");
	                double japaneseAvgScore = rs2.getDouble("japaneseAvgScore");

	                Map<String, Object> entry = new HashMap<>();
	                entry.put("year", year);
	                entry.put("month", month);
	                entry.put("japaneseAvgScore", japaneseAvgScore);
	                japaneseAvgScoresList.add(entry);
	            }

	            request.setAttribute("japaneseAvgScores",  japaneseAvgScoresList);

	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (rs1 != null) rs1.close();
	                if (stmt1 != null) stmt1.close();
	                if (rs2 != null) rs2.close();
	                if (stmt2 != null) stmt2.close();
	                if (conn != null) conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }



        // ホームページにフォワードする
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Home.jsp");
        dispatcher.forward(request, response);
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		request.setCharacterEncoding("UTF-8");
//		String login_id = request.getParameter("login_id");
//		String user_name = request.getParameter("user_name");
//		String password = request.getParameter("password");


		// ホームページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Home.jsp");
		dispatcher.forward(request, response);
	}
}