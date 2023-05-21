package MainPage.LoginPage;

import MainPage.LoginPage.MemberDTO;
import DataBase.DBConnect;
import org.w3c.dom.ls.LSOutput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MemberDAO{
    public boolean Login(MemberDTO dto){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = new DBConnect().getConn();
            String query = "SELECT * FROM user WHERE User_ID = ? AND User_PW = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, dto.getUSER_ID());
            pstmt.setString(2, dto.getUSER_PW());
            rs = pstmt.executeQuery();

            return rs.next(); // 성공시 true, 실패시 false
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean CheckID(MemberDTO dto) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DBConnect().getConn();
            String query = "SELECT COUNT(*) FROM user WHERE User_ID = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, dto.getUSER_ID());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count == 0; // 중복되지 않은 경우 true 반환
            } else {
                return false; // 결과가 없는 경우 중복으로 간주하여 false 반환
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean Join(MemberDTO dto) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DBConnect().getConn();

            Statement stmt = conn.createStatement();
            String MAX_UID = "SELECT MAX(UID) FROM user";

            try (ResultSet rs = stmt.executeQuery(MAX_UID)) {
                if (rs.next()) {
                    int maxUID = rs.getInt(1);
                    int newUID = maxUID + 1;
                    System.out.println("새로운 UID: " + newUID);
                    dto.setUID(newUID);
                }
            }

            String query = "INSERT INTO user (UID, User_ID, User_PW, User_Name, User_CallNum) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, dto.getUID());
            pstmt.setString(2, dto.getUSER_ID());
            pstmt.setString(3, dto.getUSER_PW());
            pstmt.setString(4, dto.getUSER_Name());
            pstmt.setString(5, dto.getUSER_CallNum());
            int result = pstmt.executeUpdate();

            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
