package MainPage.MyPage;

//마이 페이지 ( 비밀번호 변경, 여태 본 영화 목록, 구매내역 )

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPage {
    private JPanel MyPagefield;
    private JButton btn_ChBooking;
    private JLabel lb_howmuchpayed;
    private JLabel lb_money;
    private JLabel lb_Watched;
    private JList list_Watched;

    public MyPage() {


        btn_ChBooking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
