package DAODTO.Member;

import DataBase.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO{
    public List<String> getUserWatched(String user) {
    List<String> userWatched = new ArrayList<>();

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        conn = new DBConnect().getConn();

        String query = "SELECT User_Watch_list FROM user WHERE user_id = ?";
        pstmt = conn.prepareStatement(query);
        pstmt.setString(1, user);
        rs = pstmt.executeQuery();

        if (rs.next()) {
            String watched = rs.getString("User_Watch_list");
            userWatched.add(watched);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    return userWatched;
}

    // User가 지불한 총 금액을 가져오는 함수
    public int getUserTotalPay(String user) {
        int totalPay = 0;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DBConnect().getConn();

            String query = "SELECT User_Pay FROM user WHERE user_id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                totalPay = rs.getInt("User_Pay");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return totalPay;
    }


/*
    public List<String> getUserWatched(String User){
        List<String> User_Watched = new ArrayList<>();;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DBConnect().getConn();

            String query = "SELECT User_Watch_list FROM user WHERE user_id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, User);
            rs = pstmt.executeQuery();

            if(rs.next()){
                String userWatched = rs.getString("User_Watch_list");
                int paid = rs.getInt("User_Pay");

                User_Watched.add(userWatched);
                User_Watched.add(String.valueOf(paid));
            }
            return User_Watched;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return User_Watched;
    }


 */
    public List<String> getUser() {
        List<String> Users = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DBConnect().getConn();

            String query = "SELECT User_ID FROM user";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String UserID = rs.getString("User_ID");
                Users.add(UserID);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Users;
    }

    public boolean Ch_PW(MemberDTO dto){
        Connection conn = null;
        PreparedStatement pstmt = null;

        try{
            conn = new DBConnect().getConn();
            String query = "UPDATE user SET User_PW = ? WHERE User_ID = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, dto.getUSER_PW());
            pstmt.setString(2, dto.getUSER_ID());
            pstmt.executeUpdate();
            System.out.println(pstmt.executeUpdate());
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean FindPW(MemberDTO dto){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            conn = new DBConnect().getConn();
            String query = "SELECT * FROM user " +
                    "WHERE User_Name = ? AND User_CallNum = ? AND User_ID = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, dto.getUSER_Name());
            pstmt.setString(2, dto.getUSER_CallNum());
            pstmt.setString(3, dto.getUSER_ID());
            rs = pstmt.executeQuery();

            return rs.next();
        }catch (Exception e){
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
    public String FindID(MemberDTO dto){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String ID = "";

        try{
            conn = new DBConnect().getConn();
            String query = "SELECT * FROM user WHERE User_Name = ? AND User_CallNum = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, dto.getUSER_Name());
            pstmt.setString(2, dto.getUSER_CallNum());
            rs = pstmt.executeQuery();

            if(rs.next()){
                ID = rs.getString("User_ID");
            }
            return ID;
        }catch (Exception e){
            e.printStackTrace();
            return "";
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
