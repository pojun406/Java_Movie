package MainPage.MyPage;

import DAODTO.Booking.BookingDAO;
import DAODTO.Member.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ticket_info extends JFrame {
    private JLabel lb_UserName;
    private JLabel lb_MovieName;
    private JLabel lb_Seat;
    private JLabel lb_showDate;
    private JLabel lb_theater;
    private JButton btn_check;
    private JPanel detailPanel = new JPanel(null);
    BookingDAO bokdao = new BookingDAO();
    User user = User.getInstance();

    public Ticket_info(String resnum) {
        String ID = user.getUser_ID();
        String title = "";
        String seat = "";
        String schedule = "";
        String theater = "";

        // Get the list of reservation details
        java.util.ArrayList<String> details = bokdao.getlisttodetail(resnum);
        for (String detail : details) {
            if (detail.startsWith("영화 제목 : ")) {
                title = detail;
            } else if (detail.startsWith("좌석 : ")) {
                seat += detail + ", ";
            } else if (detail.startsWith("상영 시간 : ")) {
                schedule = detail;
            } else if (detail.startsWith("상영관 : ")) {
                theater = detail;
            }
        }

        // Remove the trailing comma and space from the seat string
        seat = seat.substring(0, seat.length() - 2);

        // Create and configure the JLabels
        lb_UserName = new JLabel("유저 아이디 : " + ID);
        lb_MovieName = new JLabel(title);
        lb_Seat = new JLabel(seat);
        lb_showDate = new JLabel(schedule);
        lb_theater = new JLabel(theater);
        btn_check = new JButton("확인");

        // Set the positions and sizes of the JLabels and JButton
        lb_UserName.setBounds(20, 20, 500, 20);
        lb_MovieName.setBounds(20, 50, 500, 20);
        lb_Seat.setBounds(20, 80, 500, 20);
        lb_showDate.setBounds(20, 110, 500, 20);
        lb_theater.setBounds(20, 140, 500, 20);
        btn_check.setBounds(20, 170, 100, 30);

        // Add the JLabels and JButton to the detailPanel
        detailPanel.add(lb_UserName);
        detailPanel.add(lb_MovieName);
        detailPanel.add(lb_Seat);
        detailPanel.add(lb_showDate);
        detailPanel.add(lb_theater);
        detailPanel.add(btn_check);

        // Set the content pane and other frame properties
        setContentPane(detailPanel);
        setTitle("예매 정보");
        setSize(300, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        btn_check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainPage mp = new MainPage();
                mp.setVisible(true);
                Ticket_info.this.setVisible(false);
            }
        });
    }
}
