package DAODTO.Booking;

import DataBase.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    // 결제, 영화관 시트정보, 예약정보 여기서 다 관리할예정

    public int Theater_Size(String theater) {
        int size = -1;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DBConnect().getConn();

            String query = "SELECT Theater_Size FROM theater WHERE Theater_Num = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, theater);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                String theaterSize = rs.getString("Theater_Size");
                if (theaterSize.equals("big")) {
                    size = 1;
                } else {
                    size = 2;
                }
            }
            return size;

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
        return size;
    }

    public List<String> getTheater() {
        List<String> Theater = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DBConnect().getConn();

            String query = "SELECT Theater_Num FROM theater";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String movieName = rs.getString("Theater_Num");
                Theater.add(movieName);
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
        return Theater;
    }

    public boolean ChoiceTheater(String theater) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DBConnect().getConn();
            String query = "SELECT Theater_Num FROM theater WHERE Theater_Num = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, theater);
            rs = pstmt.executeQuery();

            return rs.next();
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





    /*public int Seat_count(){
        int seat_count = 0;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DBConnect().getConn();

            String query = "SELECT Total_Seat FROM theater WHERE Theater_Num = ?";
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
    }*/
}
