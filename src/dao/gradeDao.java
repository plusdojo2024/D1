package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.grade;

public class gradeDao {
	// ログインできるならtrueを返す
		public boolean isLoginOK(grade user) {
			Connection conn = null;
			boolean loginResult = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D1", "sa", "");

				// SELECT文を準備する
				String sql = "SELECT COUNT(*) FROM USER WHERE login_id = ? AND date = ? AND time = ? AND subject = ? AND score = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, user.getLogin_id());
				pStmt.setDate(2, new java.sql.Date(user.getDate().getTime()));
				pStmt.setTime(3, user.getTime());
				pStmt.setString(4, user.getSubject());
				pStmt.setInt(5, user.getScore());

				// SELECT文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// ユーザーIDとパスワードが一致するユーザーがいたかどうかをチェックする
				rs.next();
				if (rs.getInt("COUNT(*)") == 1) {
					loginResult = true;
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
				loginResult = false;
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				loginResult = false;
			}
			finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
						loginResult = false;
					}
				}
			}

			// 結果を返す
			return loginResult;
		}

		// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
		public boolean insert(grade card) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D1", "sa", "");

				// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
				String sql = "INSERT INTO USER VALUES (?, ?, ?, ?, ?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				if (card.getLogin_id() != null && !card.getLogin_id().equals("")) {
					pStmt.setString(1, card.getLogin_id());
				}
				else {
					pStmt.setString(1, "（未設定）");
				}
				if (card.getDate() != null && !card.getDate().equals("")) {
					pStmt.setDate(2, new java.sql.Date(card.getDate().getTime()));
				}
				else {
					pStmt.setString(2, "（未設定）");
				}
				if (card.getTime() != null && !card.getTime().equals("")) {
					pStmt.setTime(3, card.getTime());
				}
				else {
					pStmt.setString(3, "（未設定）");
				}
				if (card.getSubject() != null && !card.getSubject().equals("")) {
					pStmt.setString(4, card.getSubject());
				}
				else {
					pStmt.setString(4, "（未設定）");
				}
				if (card.getScore() != 0) { //int型はnullにならないから0で判定
					pStmt.setInt(5, card.getScore());
				}
				else {
					pStmt.setInt(5, 0);
				}

				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					result = true;
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			// 結果を返す
			return result;
		}
	}
