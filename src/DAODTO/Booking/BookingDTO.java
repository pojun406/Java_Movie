package DAODTO.Booking;

import java.util.ArrayList;

public class BookingDTO {
    //예약 ID, 사용자 ID, 영화 정보, 좌석 정보, 결제 정보, 예약 일시

    private String Reservation_Num;
    private String UID;
    private String Movie_Name;
    private String Payment_Num;
    private String Payment_Method;
    private String Movie_Num;
    private String Schedule;
    private String Theater_Num;
    private String SeatNum;
    private int Price;
    private ArrayList<String> watchedList; // 영화 목록


    public BookingDTO(){
        super();
    }

    public String getReservation_Num() {
        return Reservation_Num;
    }

    public void setReservation_Num(String reservation_Num) {
        Reservation_Num = reservation_Num;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getMovie_Name() {
        return Movie_Name;
    }

    public void setMovie_Name(String movie_Name) {
        Movie_Name = movie_Name;
    }

    public String getPayment_Num() {
        return Payment_Num;
    }

    public void setPayment_Num(String payment_Num) {
        Payment_Num = payment_Num;
    }

    public String getPayment_Method() {
        return Payment_Method;
    }

    public void setPayment_Method(String payment_Method) {
        Payment_Method = payment_Method;
    }

    public String getMovie_Num() {
        return Movie_Num;
    }

    public void setMovie_Num(String movie_Num) {
        Movie_Num = movie_Num;
    }

    public String getSchedule() {
        return Schedule;
    }

    public void setSchedule(String schedule) {
        Schedule = schedule;
    }

    public String getTheater_Num() {
        return Theater_Num;
    }

    public void setTheater_Num(String theater_Num) {
        Theater_Num = theater_Num;
    }

    public String getSeatNum() {
        return SeatNum;
    }

    public void setSeatNum(String seatNum) {
        SeatNum = seatNum;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }
    public ArrayList<String> getWatchedList() {
        return watchedList;
    }

    public void addMovieToWatchedList(String movieName) {
        watchedList.add(movieName);
    }
}
