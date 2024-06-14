package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Question;
public class QuestionDao{
    // 引数paramで検索項目を指定し、検索結果のリストを返す
    public List<Question> select(Question card) {
        Connection conn = null;
        List<Question> cardList = new ArrayList<Question>();
        try {
            // JDBCドライバを読み込む
            Class.forName("org.h2.Driver");
            // データベースに接続する
            conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D1", "sa", "");
            // SQL文を準備する
            String sql = "SELECT * FROM Question WHERE login_id LIKE ? AND date LIKE ? AND content LIKE ? AND answer LIKE ? AND subject LIKE ?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            // SQL文を完成させる
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
            if (card.getContent() != null ) {
                pStmt.setString(3, "%" + card.getContent() + "%");
            }
            else {
                pStmt.setString(3, "%");
            }
            if (card.getAnswer() != null ) {
                pStmt.setString(4, "%" + card.getAnswer() + "%");
            }
            else {
                pStmt.setString(4, "%");
            }
            if (card.getSubject() != null ) {
                pStmt.setString(5, "%" + card.getSubject() + "%");
            }
            else {
                pStmt.setString(5, "%");
            }

            // SQL文を実行し、結果表を取得する
            ResultSet rs = pStmt.executeQuery();
            // 結果表をコレクションにコピーする
            while (rs.next()) {
                Question record = new Question(
                rs.getString("login_id"),
                rs.getString("date"),
                rs.getString("content"),
                rs.getString("answer"),
                rs.getString("subject")
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
    public boolean insert(Question card) {
        Connection conn = null;
        boolean result = false;
        try {
            // JDBCドライバを読み込む
            Class.forName("org.h2.Driver");
            // データベースに接続する
            conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D1", "sa", "");
            // SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
            String sql = "INSERT INTO Question VALUES (?, ?, ?, ?, ?)";
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
            if (card.getContent() != null && !card.getContent().equals("")) {
                pStmt.setString(3, card.getContent());
            }
            else {
                pStmt.setString(3, "（未設定）");
            }
            if (card.getAnswer() != null && !card.getAnswer().equals("")) {
                pStmt.setString(4, card.getAnswer());
            }
            else {
                pStmt.setString(4, "（未設定）");
            }
            if (card.getSubject() != null && !card.getSubject().equals("")) {
                pStmt.setString(5, card.getSubject());
            }
            else {
                pStmt.setString(5, "（未設定）");
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
    // 引数cardで指定されたレコードを更新し、成功したらtrueを返す
    public boolean update(Question card) {
        Connection conn = null;
        boolean result = false;
        try {
            // JDBCドライバを読み込む
            Class.forName("org.h2.Driver");
            // データベースに接続する
            conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/D1", "sa", "");
            // SQL文を準備する
            String sql = "UPDATE Question SET login_id=?, date=?, content=?, answer=?, subject=?";
            PreparedStatement pStmt = conn.prepareStatement(sql);
            // SQL文を完成させる
            if (card.getLogin_id() != null && !card.getLogin_id().equals("")) {
                pStmt.setString(1, card.getLogin_id());
            }
            else {
                pStmt.setString(1, null);
            }
            if (card.getDate() != null && !card.getDate().equals("")) {
                pStmt.setString(2, card.getDate());
            }
            else {
                pStmt.setString(2, null);
            }
            if (card.getContent() != null && !card.getContent().equals("")) {
                pStmt.setString(3, card.getContent());
            }
            else {
                pStmt.setString(3, null);
            }
            if (card.getAnswer() != null && !card.getAnswer().equals("")) {
                pStmt.setString(4, card.getAnswer());
            }
            else {
                pStmt.setString(4, null);
            }
            if (card.getSubject() != null && !card.getSubject().equals("")) {
                pStmt.setString(5, card.getSubject());
            }
            else {
                pStmt.setString(5, null);
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