package MainPage.MyPage;

import DAODTO.Member.MemberDTO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JFrame{
    private JPanel MainPanel;
    private JButton btn_goBook;
    private JButton btn_goMyPage;
    private MemberDTO login;

    public MainPage(MemberDTO login) {
        this.login = login;

        setContentPane(MainPanel);
        MainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setTitle("메인 페이지");
        setSize(460, 160);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        btn_goBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckBookingList chbok = new CheckBookingList(login);
                chbok.setVisible(true);
                MainPage.this.setVisible(false);
            }
        });
        btn_goMyPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyPage mp = new MyPage(login);
                mp.setVisible(true);
                MainPage.this.setVisible(false);
            }
        });
    }
}
