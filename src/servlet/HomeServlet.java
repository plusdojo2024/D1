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
		HttpSession session = request.getSession();
		if (session.getAttribute("login_id") == null) {
			response.sendRedirect("/D1/LoginServlet");
			return;
		}

		Connection conn = null;
//		List<Double> avgScores = new ArrayList<>();//リストで取得したい時に使う


        //login_idをjspから何とか取得したい！方法は模索中…
//        String login_id = (String) session.getAttribute("login_id");
        LoginUser loginUser = (LoginUser) session.getAttribute("login_id");
        request.setCharacterEncoding("UTF-8");
        String login_id = loginUser.getId();


        if (login_id != null) {

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D1", "sa", "");
				//SQLのクエリ（Homeサーブレットはすべてのテーブルからデータを取得するのですべて結合してから取得）
				String sql = "SELECT content FROM Question WHERE login_id = ? AND content != '（未設定）'";
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
				String sql2 = "SELECT answer FROM Question WHERE login_id = ? AND answer != '（未設定）'";
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


				String sl = "SELECT user_name FROM User WHERE login_id = ?";
				PreparedStatement s = conn.prepareStatement(sl);
				s.setString(1, login_id);
				ResultSet r = s.executeQuery();
				while (r.next()) {
					String user_name = r.getString("user_name");
					request.setAttribute("user_name", user_name);
				}



			 } catch(SQLException e) {
		            e.printStackTrace();
		    }catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    }




	        PreparedStatement stmt1 = null;
	        ResultSet rs1 = null;

	        PreparedStatement stmt2 = null;
	        ResultSet rs2 = null;

	        PreparedStatement stmt3 = null;
	        ResultSet rs3 = null;

	        PreparedStatement stmt4 = null;
	        ResultSet rs4 = null;

	        PreparedStatement stmt5 = null;
	        ResultSet rs5 = null;

	        PreparedStatement stmt6 = null;
	        ResultSet rs6 = null;

	        try {

	        	Class.forName("org.h2.Driver");
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D1", "sa", "");


	            // 年と月ごとに全科目の平均スコアを取得するSQLクエリ
	            String sql5 = "SELECT YEAR(Date) AS year, MONTH(Date) AS month, AVG(score) AS averageScore "
	                         +"FROM Grade WHERE login_id = ?"
	                         +"GROUP BY YEAR(Date), MONTH(Date)"
	                         +"ORDER BY year ASC, month ASC";
	            stmt1 = conn.prepareStatement(sql5);
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
	            String sql6 = "SELECT YEAR(Date) AS year, MONTH(Date) AS month, AVG(score) AS japaneseAvgScore "
	                         +"FROM Grade WHERE login_id = ? AND subject = '国語'"
	                         +"GROUP BY YEAR(Date), MONTH(Date)"
	                         +"ORDER BY year ASC, month ASC";
	            stmt2 = conn.prepareStatement(sql6);
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


	         // 年と月ごとに数学の平均スコアを取得するSQLクエリ
	            String sql7 = "SELECT YEAR(Date) AS year, MONTH(Date) AS month, AVG(score) AS mathAvgScore "
	                         +"FROM Grade WHERE login_id = ? AND subject = '数学'"
	                         +"GROUP BY YEAR(Date), MONTH(Date)"
	                         +"ORDER BY year ASC, month ASC";
	            stmt3 = conn.prepareStatement(sql7);
	            stmt3.setString(1, login_id);
	            rs3 = stmt3.executeQuery();

	            // 年と月ごとの平均スコアを格納するリスト
	            List<Map<String, Object>> mathAvgScoresList = new ArrayList<>();

	            while (rs3.next()) {
	                int year = rs3.getInt("year");
	                int month = rs3.getInt("month");
	                double mathAvgScore = rs3.getDouble("mathAvgScore");

	                Map<String, Object> entry = new HashMap<>();
	                entry.put("year", year);
	                entry.put("month", month);
	                entry.put("mathAvgScore", mathAvgScore);
	                mathAvgScoresList.add(entry);
	            }

	            request.setAttribute("mathAvgScores",  mathAvgScoresList);


	         // 年と月ごとに理科の平均スコアを取得するSQLクエリ
	            String sql8 = "SELECT YEAR(Date) AS year, MONTH(Date) AS month, AVG(score) AS scienceAvgScore "
	                         +"FROM Grade WHERE login_id = ? AND subject = '理科'"
	                         +"GROUP BY YEAR(Date), MONTH(Date)"
	                         +"ORDER BY year ASC, month ASC";
	            stmt4 = conn.prepareStatement(sql8);
	            stmt4.setString(1, login_id);
	            rs4 = stmt4.executeQuery();

	            // 年と月ごとの平均スコアを格納するリスト
	            List<Map<String, Object>> scienceAvgScoresList = new ArrayList<>();

	            while (rs4.next()) {
	                int year = rs4.getInt("year");
	                int month = rs4.getInt("month");
	                double scienceAvgScore = rs4.getDouble("scienceAvgScore");

	                Map<String, Object> entry = new HashMap<>();
	                entry.put("year", year);
	                entry.put("month", month);
	                entry.put("scienceAvgScore", scienceAvgScore);
	                scienceAvgScoresList.add(entry);
	            }

	            request.setAttribute("scienceAvgScores",  scienceAvgScoresList);


	         // 年と月ごとに社会の平均スコアを取得するSQLクエリ
	            String sql9 = "SELECT YEAR(Date) AS year, MONTH(Date) AS month, AVG(score) AS societyAvgScore "
	                         +"FROM Grade WHERE login_id = ? AND subject = '社会'"
	                         +"GROUP BY YEAR(Date), MONTH(Date)"
	                         +"ORDER BY year ASC, month ASC";
	            stmt5 = conn.prepareStatement(sql9);
	            stmt5.setString(1, login_id);
	            rs5 = stmt5.executeQuery();

	            // 年と月ごとの平均スコアを格納するリスト
	            List<Map<String, Object>> societyAvgScoresList = new ArrayList<>();

	            while (rs5.next()) {
	                int year = rs5.getInt("year");
	                int month = rs5.getInt("month");
	                double societyAvgScore = rs5.getDouble("societyAvgScore");

	                Map<String, Object> entry = new HashMap<>();
	                entry.put("year", year);
	                entry.put("month", month);
	                entry.put("societyAvgScore", societyAvgScore);
	                societyAvgScoresList.add(entry);
	            }

	            request.setAttribute("societyAvgScores",  societyAvgScoresList);


	         // 年と月ごとに英語の平均スコアを取得するSQLクエリ
	            String sql10 = "SELECT YEAR(Date) AS year, MONTH(Date) AS month, AVG(score) AS englishAvgScore "
	                         +"FROM Grade WHERE login_id = ? AND subject = '英語'"
	                         +"GROUP BY YEAR(Date), MONTH(Date)"
	                         +"ORDER BY year ASC, month ASC";
	            stmt6 = conn.prepareStatement(sql10);
	            stmt6.setString(1, login_id);
	            rs6 = stmt6.executeQuery();

	            // 年と月ごとの平均スコアを格納するリスト
	            List<Map<String, Object>> englishAvgScoresList = new ArrayList<>();

	            while (rs6.next()) {
	                int year = rs6.getInt("year");
	                int month = rs6.getInt("month");
	                double englishAvgScore = rs6.getDouble("englishAvgScore");

	                Map<String, Object> entry = new HashMap<>();
	                entry.put("year", year);
	                entry.put("month", month);
	                entry.put("englishAvgScore", englishAvgScore);
	                englishAvgScoresList.add(entry);
	            }

	            request.setAttribute("englishAvgScores",  englishAvgScoresList);





	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (rs1 != null) rs1.close();
	                if (stmt1 != null) stmt1.close();
	                if (rs2 != null) rs2.close();
	                if (stmt2 != null) stmt2.close();
	                if (rs3 != null) rs3.close();
	                if (stmt3 != null) stmt3.close();
	                if (rs4 != null) rs4.close();
	                if (stmt4 != null) stmt4.close();
	                if (rs5 != null) rs5.close();
	                if (stmt5 != null) stmt5.close();
	                if (rs6 != null) rs6.close();
	                if (stmt6 != null) stmt6.close();
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
        // もしもログインしていなかったらログインサーブレットにリダイレクトする
        HttpSession session = request.getSession();
        if (session.getAttribute("login_id") == null) {
            response.sendRedirect("/D1/LoginServlet");
            return;
        }

		request.setCharacterEncoding("UTF-8");
//		String login_id = request.getParameter("login_id");
//		String user_name = request.getParameter("user_name");
//		String password = request.getParameter("password");


		// ホームページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Home.jsp");
		dispatcher.forward(request, response);
	}
}