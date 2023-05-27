package MainPage.BookingPage;

import DAODTO.Booking.BookingDAO;
import DAODTO.Movie.MovieDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Booking_Detail extends JFrame{
    private JLabel lb_UserName;
    private JLabel lb_MovieName;
    private JLabel lb_Seat;
    private JLabel lb_showDate;
    private JLabel lb_theater;
    private JButton btn_check;
    private JLabel Poster_Img;
    private JPanel DetailPanel;

    MovieDAO movdao = new MovieDAO();
    BookingDAO bokdao = new BookingDAO();

    public Booking_Detail(String[] Seat, String title) {
        String User_Name = "";
        String Scedule_Time = "";
        String Theater_Num = "";




        setContentPane(DetailPanel);
        DetailPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setTitle("상세 페이지");
        setSize(700,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        btn_check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
