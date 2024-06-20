package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.grade;

public class gradeDao {
	// ログインできるならtrueを返す
    public List<grade> select(grade card) {
        Connection conn = null;
        List<grade> cardList = new ArrayList<grade>();

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D1", "sa", "");

				// SELECT文を準備する
				String sql = "SELECT COUNT * FROM Grade WHERE login_id LIKE ? AND date LIKE ? AND subject LIKE ? AND score LIKE ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				 if (card.getLogin_id() != null ) {
		                pStmt.setString(1, "%" + card.getLogin_id() + "%");
		            }
		            else {
		                pStmt.setString(1, "%");
		            }
		            if (card.getDate() != null ) {
		                pStmt.setString(2, "%" + card.getDate() + "%");
		            }
		            else {
		                pStmt.setString(2, "%");
		            }
		            if (card.getSubject() != null ) {
		                pStmt.setString(3, "%" + card.getSubject() + "%");
		            }
		            else {
		                pStmt.setString(3, "%");
		            }
		            if (card.getScore() != 0 ) {
		                pStmt.setString(4, "%" + card.getScore() + "%");
		            }
		            else {
		                pStmt.setString(4, "%");
		            }


				// SELECT文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				while (rs.next()) {
	                grade record = new grade(
	                rs.getString("login_id"),
	                rs.getString("date"),
	                rs.getString("subject"),
	                rs.getInt("score")
	                );
	                cardList.add(record);
	            }
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
	            cardList = null;
	        }
	        catch (ClassNotFoundException e) {
	            e.printStackTrace();
	            cardList = null;
	        }
	        finally {
	            // データベースを切断
	            if (conn != null) {
	                try {
	                    conn.close();
	                }
	                catch (SQLException e) {
	                    e.printStackTrace();
	                    cardList = null;
	                }
	            }
	        }
	        // 結果を返す
	        return cardList;
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
				String sql = "INSERT INTO Grade VALUES (?, ?, ?, ?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				if (card.getLogin_id() != null && !card.getLogin_id().equals("")) {
					pStmt.setString(1, card.getLogin_id());
				}
				else {
					pStmt.setString(1, "（未設定）");
				}
				if (card.getDate() != null && !card.getDate().equals("")) {
					pStmt.setString(2, card.getDate());
				}
				else {
					pStmt.setString(2, "（未設定）");
				}
				if (card.getSubject() != null && !card.getSubject().equals("")) {
					pStmt.setString(3, card.getSubject());
				}
				else {
					pStmt.setString(3, "（未設定）");
				}
				if (card.getScore() != 0) { //int型はnullにならないから0で判定
					pStmt.setInt(4, card.getScore());
				}
				else {
					pStmt.setInt(4, 0);
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

		public boolean update(grade card) {
	        Connection conn = null;
	        boolean result = false;
	        try {
	            // JDBCドライバを読み込む
	            Class.forName("org.h2.Driver");
	            // データベースに接続する
	            conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D1", "sa", "");
	            // SQL文を準備する
	            String sql = "UPDATE Grade SET login_id=?, date=?, subject=?, score=?";
	            PreparedStatement pStmt = conn.prepareStatement(sql);
	            // SQL文を完成させる
	            if (card.getLogin_id() != null && !card.getLogin_id().equals("")) {
					pStmt.setString(1, card.getLogin_id());
				}
				else {
					pStmt.setString(1, "（未設定）");
				}
				if (card.getDate() != null && !card.getDate().equals("")) {
					pStmt.setString(2, card.getDate());
				}
				else {
					pStmt.setString(2, "（未設定）");
				}
				if (card.getSubject() != null && !card.getSubject().equals("")) {
					pStmt.setString(3, card.getSubject());
				}
				else {
					pStmt.setString(3, "（未設定）");
				}
				if (card.getScore() != 0) { //int型はnullにならないから0で判定
					pStmt.setInt(4, card.getScore());
				}
				else {
					pStmt.setInt(4, 0);
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
