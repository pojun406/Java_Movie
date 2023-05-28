package DAODTO.Booking;

import DAODTO.Member.User;
import DataBase.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BookingDAO {
    public void insertReservation(String reservationNum, String UID, String title, String schedule, String theaterNum, String seatList, int Price, String Payed) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DBConnect().getConn();
            String query = "INSERT INTO reservations (Reservation_Num, UID, Movie_Title, Schedule, Theater_Num, Seat_Num, Price, Payment_Method) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, reservationNum);
            pstmt.setString(2, UID);
            pstmt.setString(3, title);
            pstmt.setString(4, schedule);
            pstmt.setString(5, theaterNum);
            pstmt.setString(6, seatList);
            pstmt.setInt(7, Price);
            pstmt.setString(8,Payed);
            pstmt.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public String getReservationNum(){
        String Reservation_Num = "";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
            String date = dateFormat.format(new Date());
            conn = new DBConnect().getConn();
            String query = "SELECT MAX(Reservation_Num) FROM reservations";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            if(rs.next()){
                String maxReservationNumber = rs.getString(1);
                if (maxReservationNumber != null) {
                    // 다음 예약 번호 계산
                    int sequenceNumber = Integer.parseInt(maxReservationNumber.substring(8)) + 1;
                    String uid = User.getInstance().getUID();
                    Reservation_Num = date + uid + String.format("%02d", sequenceNumber);
                } else {
                    // 예약 번호가 없을 경우 초기값 설정
                    Reservation_Num = date + User.getInstance().getUID() + "01";
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Reservation_Num;
    }
    public String getMovieTitle(String movieNum) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String movieTitle = "";

        try {
            conn = new DBConnect().getConn();
            String query = "SELECT Movie_Title FROM movie WHERE Movie_Num = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, movieNum);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                movieTitle = rs.getString("Movie_Title");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return movieTitle;
    }
    public ArrayList<BookingDTO> getMovieSchedule(){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<BookingDTO> canwatchmovieList = new ArrayList<>();
        try {
            conn = new DBConnect().getConn();
            String query = "SELECT * FROM movie_schedule";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            while(rs.next()){
                BookingDTO booking = new BookingDTO();
                booking.setSchedule(rs.getString("Schedule"));
                booking.setMovie_Num(rs.getString("Movie_Num"));
                booking.setTheater_Num(rs.getString("Theater_Num"));

                String Movie_Num = booking.getMovie_Num();
                String Title = getMovieTitle(Movie_Num);
                booking.setMovie_Name(Title);

                canwatchmovieList.add(booking);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return canwatchmovieList;
    }


    public ArrayList<BookingDTO> getAllReservationInfo(String Reservation_Num) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<BookingDTO> watchedList = new ArrayList<>();

        try {
            conn = new DBConnect().getConn();
            String query = "SELECT * FROM reservations WHERE Reservation_Num = ?";
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
