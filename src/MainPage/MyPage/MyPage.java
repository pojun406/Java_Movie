package MainPage.MyPage;

//마이 페이지 ( 비밀번호 변경, 여태 본 영화 목록, 구매내역 )

import DAODTO.Booking.BookingDAO;
import DAODTO.Booking.BookingDTO;
import DAODTO.Member.MemberDTO;
import DAODTO.Member.User;
import MainPage.AdminPage.AdminPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyPage extends JFrame {
    private JPanel MyPagePanel = new JPanel(null);
    private JButton btn_ChBooking;
    private JLabel lb_money;
    private JList list_Watched;
    private JButton btn_Out;
    private User user = User.getInstance();

    public MyPage() {

        JLabel howmuch = new JLabel("우리 영화관에 사용한 총액 : ");
        JLabel Watched = new JLabel("시청 목록");

        if(user.getUser_Pay() < 1){
            lb_money = new JLabel("0 원");
        }else{
            lb_money = new JLabel(user.getUser_Pay()+" 원");
        }
        list_Watched = new JList();

        ArrayList<BookingDTO> watchedList = BookingDAO.getALLList(user.getUID());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (BookingDTO dto : watchedList) {
            listModel.addElement(dto.getMovie_Name());
        }

        list_Watched.setModel(listModel);

        howmuch.setSize(100, 20);
        Watched.setSize(100, 20);
        list_Watched.setSize(400,200);

        btn_ChBooking = new JButton("예매하러가기");
        btn_Out = new JButton("나가기");


        btn_ChBooking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckBookingList ch = new CheckBookingList();
                ch.setVisible(true);
                MyPage.this.setVisible(false);
            }
        });

        btn_Out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminPage ad = new AdminPage();
                ad.setVisible(true);
                MyPage.this.setVisible(false);
            }
        });

        howmuch.setBounds(20, 20, 200, 20);
        lb_money.setBounds(20, 50, 100, 20);
        Watched.setBounds(20, 80, 100, 20);
        list_Watched.setBounds(20, 100, 200, 200);
        btn_ChBooking.setBounds(20, 300, 100, 30);
        btn_Out.setBounds(120, 300, 100, 30);



        setContentPane(MyPagePanel);
        MyPagePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setTitle("상세 페이지");
        setSize(300,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        System.out.println(user.getUser_ID());

        MyPagePanel.add(howmuch);
        MyPagePanel.add(Watched);
        MyPagePanel.add(list_Watched);
        MyPagePanel.add(btn_Out);
        MyPagePanel.add(btn_ChBooking);
        MyPagePanel.add(lb_money);

    }
}
