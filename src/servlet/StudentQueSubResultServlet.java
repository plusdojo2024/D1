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
 * Servlet implementation class StudentQueSubServlet
 */
@WebServlet("/StudentQueSubResultServlet")
public class StudentQueSubResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
        request.setCharacterEncoding("UTF-8");
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D1", "sa", "");
			//SQLのクエリ（Homeサーブレットはすべてのテーブルからデータを取得するのですべて結合してから取得）
			String sql = "SELECT user_name FROM User";
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			List<String> names = new ArrayList<>();
			List<String> contents = new ArrayList<>();
			List<String> answers = new ArrayList<>();

			while (res.next()) {
			    String userName = res.getString("user_name");
			    names.add(userName);
			}
			request.setAttribute("names", names);

			String sql2 = "SELECT content FROM Question";
			PreparedStatement st2 = conn.prepareStatement(sql2);
			ResultSet res2 = st2.executeQuery();

			while (res2.next()) {
				String content = res2.getString("content");
				contents.add(content);
			}

			request.setAttribute("contents", contents);


			// answerカラム（Questionテーブル）のデータを取得するループ
			//　answerとcontentが同じ行数とは限らないのでループを分けました。
			String sql3 = "SELECT answer FROM Question";
			PreparedStatement st3 = conn.prepareStatement(sql3);
			ResultSet res3 = st3.executeQuery();

			while (res3.next()) {
		        String answer = res3.getString("answer");
		        answers.add(answer);
			}
			request.setAttribute("answers", answers);
			  st.close();
			    res.close();
			    st2.close();
			    res2.close();
			    st3.close();
			    res3.close();
			    conn.close();


		 } catch(SQLException e) {
	            e.printStackTrace();
	    }catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StudentQueSubResult.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StudentQueSubResult.jsp");
		dispatcher.forward(request, response);
	}

}
