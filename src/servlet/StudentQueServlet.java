package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class StudentQueServlet
 */
@WebServlet("/StudentQueServlet")
public class StudentQueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*Connection conn = null;
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
				String sql = "SELECT Question.content, Question.answer, Question.subject "
						+ "FROM Qustion WHERE User.login_id = ?";
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
				//　answerとcontentが同じ行数とは限らないのでループを分けました。
				res.beforeFirst();// ResultSetを最初の行の前に移動する（ゼロクリアみたいなもの）
				int answerCount=0;
				while (res.next()) {
				    String answer = res.getString("answer");
				    answerCount++;
				}
				request.setAttribute("answerCount", answerCount);//質問回答数


			 } catch(SQLException e) {
		            e.printStackTrace();
		    }catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    }



	}*/
		// 質問選択ページにフォワードする
	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StudentQue.jsp");
        dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/StudentQueServlet.jsp");
		dispatcher.forward(request, response);
	}

}
