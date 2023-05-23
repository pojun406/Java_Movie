package MainPage.AdminPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Movie_info extends JFrame {
    private JPanel MovieinfoPanel;
    private JPanel pan_Poster;
    private JButton btn_out;
    private JLabel lb_MovieNm;
    private JLabel lb_Selled;
    private JLabel lb_dayUser;
    private JLabel lb_allUser;
    private JLabel lb_profit;

    public Movie_info(){
        setContentPane(MovieinfoPanel);
        MovieinfoPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setTitle("영화정보 페이지");
        setSize(460,260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        btn_out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminPage admin = new AdminPage();
                admin.setVisible(true);
                Movie_info.this.setVisible(false);
            }
        });
    }
}
