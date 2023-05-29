package MainPage.BookingPage;

import DAODTO.Booking.BookingDAO;
import DAODTO.Booking.BookingDTO;
import DAODTO.Member.User;
import DAODTO.Movie.MovieDAO;
import MainPage.MyPage.MyPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Booking_Detail extends JFrame {
    private JLabel lb_UserName;
    private JLabel lb_MovieName;
    private JLabel lb_Seat;
    private JLabel lb_showDate;
    private JLabel lb_theater;
    private JButton btn_check;
    private JLabel posterImg;
    private JPanel detailPanel = new JPanel(null);
    BookingDAO bokdao = new BookingDAO();
    User user = User.getInstance();

    public Booking_Detail(String[] seat, BookingDTO dto, String Payed) { // Payed는 Payment_Method 원툴임
        int ticketCount = seat.length;
        String Reservation_Num = bokdao.getReservationNum();
        String UID = user.getUID();
        String Title = dto.getMovie_Name();
        String schedule_Time = dto.getSchedule();
        String theater_Num = dto.getTheater_Num();
        int Price = 10000 * ticketCount;
        //user.setUser_Pay(Price);
        String ID = user.getUser_ID();

        bokdao.insertReservation(Reservation_Num, UID,Title,schedule_Time,theater_Num,seat,Price,Payed);

        setTitle("상세 페이지");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        detailPanel.setLayout(null);

        // 사용자명 레이블
        lb_UserName = new JLabel("사용자명: " + ID);
        lb_UserName.setBounds(20, 20, 360, 30);
        detailPanel.add(lb_UserName);

        // 영화명 레이블
        lb_MovieName = new JLabel("선택한 영화명: " + dto.getMovie_Name());
        lb_MovieName.setBounds(20, 60, 360, 30);
        detailPanel.add(lb_MovieName);

        // 좌석 레이블
        lb_Seat = new JLabel("선택한 좌석: " + String.join(", ", seat));
        lb_Seat.setBounds(20, 100, 360, 30);
        detailPanel.add(lb_Seat);

        // 상영일자 레이블
        lb_showDate = new JLabel("상영일자: " + schedule_Time);
        lb_showDate.setBounds(20, 140, 360, 30);
        detailPanel.add(lb_showDate);

        // 상영관 레이블
        lb_theater = new JLabel("상영관: " + theater_Num);
        lb_theater.setBounds(20, 180, 360, 30);
        detailPanel.add(lb_theater);

        // "확인" 버튼
        btn_check = new JButton("확인");
        btn_check.setBounds(150, 220, 100, 30);
        detailPanel.add(btn_check);

        btn_check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyPage my = new MyPage();
                my.setVisible(true);
                Booking_Detail.this.setVisible(false);
            }
        });

        setContentPane(detailPanel);
        setVisible(true);
    }
}
