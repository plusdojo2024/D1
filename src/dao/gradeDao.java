package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class gradeDao {
	// ログインできるならtrueを返す
		public boolean isLoginOK(User user) {
			Connection conn = null;
			boolean loginResult = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D1", "sa", "");

				// SELECT文を準備する
				String sql = "SELECT COUNT(*) FROM USER WHERE login_id = ? AND Date = ? AND subject = ? AND score = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				pStmt.setString(1, user.getLogin_id());
				pStmt.setString(2, user.getDate());
				pStmt.setString(3, user.getSubject());
				pStmt.setString(4, user.getScore());

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
		public boolean insert(User card) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D1", "sa", "");

				// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
				String sql = "INSERT INTO USER VALUES (?, ?, ?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				if (card.getLogin_id() != null && !card.getLogin_id().equals("")) {
					pStmt.setString(1, card.getLogin_id());
				}
				else {
					pStmt.setString(1, "（未設定）");
				}
				if (card.getUser_name() != null && !card.getUser_name().equals("")) {
					pStmt.setString(2, card.getUser_name());
				}
				else {
					pStmt.setString(2, "（未設定）");
				}
				if (card.getPassword() != null && !card.getPassword().equals("")) {
					pStmt.setString(3, card.getPassword());
				}
				else {
					pStmt.setString(3, "（未設定）");
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