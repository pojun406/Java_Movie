package DAODTO.Booking;

import DataBase.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookingDAO {
    public ArrayList<BookingDTO> getAllReservationInfo(String Reservation_Num) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<BookingDTO> watchedList = new ArrayList<>();

        try {
            conn = new DBConnect().getConn();
            String query = "SELECT * FROM reservations_detail WHERE Reservation_Num = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, Reservation_Num);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BookingDTO booking = new BookingDTO();
                booking.setMovie_Num(rs.getString("Movie_Num"));
                booking.setSchedule(rs.getString("Schedule"));
                booking.setTheater_Num(rs.getString("Theater_Num"));
                booking.setSeatNum(rs.getString("Seat_Num"));
                booking.setPrice(rs.getInt("Price"));

                watchedList.add(booking);
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
        return watchedList;
    }

    public static ArrayList<BookingDTO> getALLList(String UID) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<BookingDTO> watchedList = new ArrayList<>();

        try {
            conn = new DBConnect().getConn();
            String query = "SELECT * FROM reservations WHERE UID = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, String.valueOf(UID));
            rs = pstmt.executeQuery();

            while (rs.next()) {
                BookingDTO booking = new BookingDTO();
                booking.setReservation_Num(rs.getString("Reservation_Num"));
                booking.setUID(rs.getString("UID"));
                booking.setMovie_Name(rs.getString("Movie_Name"));
                booking.setPayment_Num(rs.getString("Payment_Num"));
                booking.setPayment_Method(rs.getString("Payment_Method"));
                booking.setMovie_Num(rs.getString("Movie_Num"));
                booking.setSchedule(rs.getString("Schedule"));
                booking.setTheater_Num(rs.getString("Theater_Num"));
                booking.setSeatNum(rs.getString("SeatNum"));
                booking.setPrice(rs.getInt("Price"));

                watchedList.add(booking);
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

        return watchedList;
    }

    public List<String> getTheaterNum(String title) {
        List<String> theaterNumbers = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = new DBConnect().getConn();
            String query = "SELECT Movie_Num FROM movie WHERE Movie_Title = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, title);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                String movieNum = rs.getString("Movie_Num");
                String newQuery = "SELECT Theater_Num FROM movie_schedule WHERE Movie_Num = ?";
                pstmt = conn.prepareStatement(newQuery);
                pstmt.setString(1, movieNum);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    String theaterNum = rs.getString("Theater_Num");
                    theaterNumbers.add(theaterNum);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return theaterNumbers;
    }

    public boolean Schedule_have() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = new DBConnect().getConn();
            String query = "SELECT COUNT(*) < 8 AS Result FROM (SELECT DISTINCT Theater_Num FROM movie_schedule) AS T";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            rs.next();
            boolean result = rs.getBoolean("Result");

            if (result) {
                query = "SELECT COUNT(*) < 8 AS Result FROM theater WHERE Theater_Num NOT IN (SELECT DISTINCT Theater_Num FROM movie_schedule)";
                pstmt = conn.prepareStatement(query);
                rs = pstmt.executeQuery();
                rs.next();
                result = rs.getBoolean("Result");
            }

            pstmt.close();
            return result;
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

    public boolean AddSchedule(String schedule, String MovieNum, String TheaterNum) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = new DBConnect().getConn();
            String query = "INSERT INTO movie_schedule (Schedule, Movie_Num, Theater_Num) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, schedule);
            pstmt.setString(2, MovieNum);
            pstmt.setString(3, TheaterNum);
            pstmt.executeUpdate();
            pstmt.close();
            return true;
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
/*
    public boolean BookingDetail(){
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

        }

 */

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
