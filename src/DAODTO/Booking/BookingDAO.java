package DAODTO.Booking;

import DAODTO.Member.User;
import DataBase.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BookingDAO {
    public int ReservationALL(String title){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int resall = 0;
        try{
            conn = new DBConnect().getConn();
            String query = "SELECT COUNT(*) FROM reservations WHERE Movie_Title = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, title);
            rs = pstmt.executeQuery();

            if (rs.next()) { // 결과 집합에 다음 행이 있는지 확인
                resall = rs.getInt(1);
            }
        } catch (Exception e) {
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
        return resall;
    }
    public int PayedALL(String UID) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int Pay = 0;
        try {
            conn = new DBConnect().getConn();
            String query = "SELECT Price FROM reservations WHERE UID = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, UID);
            rs = pstmt.executeQuery();
            while (rs.next()){
                int price = rs.getInt("Price");
                Pay += price;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Pay;
    }
    public ArrayList<String> getlisttodetail(String resnum) {
        ArrayList<String> list = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DBConnect().getConn();
            String sql = "SELECT * FROM reservations WHERE Reservation_Num = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, resnum);
            rs = pstmt.executeQuery();

            String title = null;
            String schedule = null;
            String theaterNum = null;
            List<String> seatList = new ArrayList<>();

            while (rs.next()) {
                String reservationNum = rs.getString("Reservation_Num");
                title = rs.getString("Movie_Title");
                schedule = rs.getString("Schedule");
                theaterNum = rs.getString("Theater_Num");
                String seatNum = rs.getString("Seat_Num");
                int price = rs.getInt("Price");
                String payed = rs.getString("Payment_Method");

                // Collect seat numbers in a list
                if (seatNum != null && !seatNum.isEmpty()) {
                    seatList.add(seatNum);
                }
            }

            // Add the details to the list
            list.add("영화 제목 : " + title);
            if (!seatList.isEmpty()) {
                list.add("좌석 : "+String.join(", ", seatList));
            }
            list.add("상영 시간 : " + schedule);
            list.add("상영관 : " + theaterNum);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    public static ArrayList<BookingDTO> getALLList(String UID) {
        ArrayList<BookingDTO> bookingList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DBConnect().getConn();
            String sql = "SELECT * FROM reservations WHERE uid = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, UID);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String reservationNum = rs.getString("Reservation_Num");
                String title = rs.getString("Movie_Title");
                String schedule = rs.getString("Schedule");
                String Theater_Num = rs.getString("Theater_Num");
                String seatNum = rs.getString("seat_num");
                int Price = rs.getInt("Price");
                String Payed = rs.getString("Payment_Method");

                BookingDTO dto = new BookingDTO();
                dto.setReservation_Num(reservationNum);
                dto.setMovie_Name(title);
                dto.setSchedule(schedule);
                dto.setTheater_Num(Theater_Num);
                dto.setSeatNum(seatNum);
                dto.setPrice(Price);
                dto.setPayment_Method(Payed);

                bookingList.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return bookingList;
    }

    public boolean isSeatReserved(String seatNum, String Title, String TheaterNum, String Schedule) {
        String[] SeatCount = seatNum.split(", ");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = new DBConnect().getConn();
            String query = "SELECT Seat_Num FROM reservations WHERE Seat_Num = ? " +
                    "AND Movie_Title = ? AND Theater_Num = ? AND Schedule = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, seatNum);
            pstmt.setString(2, Title);
            pstmt.setString(3, TheaterNum);
            pstmt.setString(4, Schedule);
            rs = pstmt.executeQuery();

            return rs.next(); // ResultSet에 결과가 있으면 중복된 좌석이 존재하는 것이므로 true 반환

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
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
                System.out.println(maxReservationNumber);
                String uid = User.getInstance().getUID();
                if (maxReservationNumber == null) {
                    Reservation_Num = date + uid + "01";
                } else {
                    // 다음 예약 번호 계산
                    int sequenceNumber = Integer.parseInt(maxReservationNumber.substring(8)) + 1;
                    Reservation_Num = date + uid + String.format("%02d", sequenceNumber);
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
    public void insertReservation(String resnum, String UID, String title, String schedule, String theaterNum, String[] seatList, int Price, String Payed) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DBConnect().getConn();
            if(seatList.length < 2){
                String query = "INSERT INTO reservations (Reservation_Num ,UID, Movie_Title, Schedule, Theater_Num, Seat_Num, Price, Payment_Method) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1, resnum);
                pstmt.setString(2, UID);
                pstmt.setString(3, title);
                pstmt.setString(4, schedule);
                pstmt.setString(5, theaterNum);
                pstmt.setString(6, seatList[0]);
                pstmt.setInt(7, Price);
                pstmt.setString(8,Payed);
                pstmt.executeUpdate();
            }
            else{
                for (int i = 0; i < seatList.length; i++) {
                    String query = "INSERT INTO reservations (Reservation_Num ,UID, Movie_Title, Schedule, Theater_Num, Seat_Num, Price, Payment_Method) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    pstmt = conn.prepareStatement(query);
                    pstmt.setString(1, resnum);
                    pstmt.setString(2, UID);
                    pstmt.setString(3, title);
                    pstmt.setString(4, schedule);
                    pstmt.setString(5, theaterNum);
                    pstmt.setString(6, seatList[i]);
                    pstmt.setInt(7, Price);
                    pstmt.setString(8,Payed);
                    pstmt.executeUpdate();
                }
            }

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
