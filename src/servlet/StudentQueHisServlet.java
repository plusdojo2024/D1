package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginUser;
import model.Result;

/**
 * Servlet implementation class StudentQueHisServlet
 */
@WebServlet("/StudentQueHisServlet")
public class StudentQueHisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = null;
		List<Double> avgScores = new ArrayList<>();//リストで取得したい時に使う
        request.setCharacterEncoding("UTF-8");

        //login_idをjspから何とか取得したい！方法は模索中…
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


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StudentQueHis.jsp");
		dispatcher.forward(request, response);
        }
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//		HttpSession session = request.getSession();
//		if (session.getAttribute("id") == null) {
//			response.sendRedirect("/D1/LoginServlet");
//			return;
//		}

						// 結果ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StudentQueHis.jsp");
			dispatcher.forward(request, response);

	}

}
