package AdminPage;

import DAODTO.MovieDAO;
import DAODTO.MovieDTO;
import MainPage.LoginPage.Join;
import MainPage.LoginPage.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends JFrame{
    private JPanel AdminPanel;
    private JButton btn_Movieinfo;
    private JButton btn_UserManage;
    private JButton btn_MovieUpdate;
    private JButton btn_out;

    public AdminPage() {

        setContentPane(AdminPanel);
        AdminPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setTitle("관리자 페이지");
        setSize(460,260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        MovieDTO moviedto = new MovieDTO();

        btn_Movieinfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Movie_info info = new Movie_info();
                info.setVisible(true);
                AdminPage.this.setVisible(false);
            }
        });
        btn_UserManage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User_Manage mana = new User_Manage();
                mana.setVisible(true);
                AdminPage.this.setVisible(false);
            }
        });
        btn_MovieUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MovieDAO moviedao = new MovieDAO();
                boolean success_GET = moviedao.GetAPI_MovieCD();
                boolean success_Get_Detail = moviedao.GET_MOVIEDetail();

                if(success_GET){
                    if(success_Get_Detail){
                        Movie_Update update = new Movie_Update();
                        update.setVisible(true);
                        AdminPage.this.setVisible(false);
                    }
                    else{
                        System.out.println("세부정보 불러오기 실패");
                    }
                }
                else{
                    System.out.println("영화정보 불러오기 실패");
                }
            }
        });
        btn_out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.setVisible(true);
                AdminPage.this.setVisible(false);
            }
        });
    }
}
