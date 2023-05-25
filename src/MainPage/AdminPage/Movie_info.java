package MainPage.AdminPage;

import DAODTO.Movie.MovieDAO;
import DAODTO.Movie.MovieDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Movie_info extends JFrame {
    private JPanel MovieinfoPanel;
    private JButton btn_out;
    private JLabel lb_MovieNm;
    private JLabel Poster_Img;
    private JLabel lb_Selled;
    private JLabel lb_dayUser;
    private JLabel lb_allUser;
    private JLabel lb_profit;

    String movie = "";

    public Movie_info(String selectedMovie) {
        movie = selectedMovie;
        setContentPane(MovieinfoPanel);
        MovieinfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        setTitle("영화정보 페이지");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        lb_MovieNm.setText(movie);

        btn_out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminPage admin = new AdminPage();
                admin.setVisible(true);
                Movie_info.this.setVisible(false);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
// 이미지 파일 경로 생성
        if (movie != null) {
            // 이미지 파일 경로 생성
            String imagePath = "./img/" + movie.replaceAll("[^ㄱ-ㅎ가-힣0-9]+", "").replaceAll("\\s+", "") + ".jpg";

            // 이미지 파일 존재 여부 확인
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                // 이미지 파일이 존재하는 경우 이미지를 JLabel에 설정
                ImageIcon imageIcon = new ImageIcon(imagePath);
                Poster_Img = new JLabel(imageIcon);
            } else {
                // 이미지 파일이 존재하지 않는 경우 기본 이미지 또는 알림 메시지를 설정
                Poster_Img = new JLabel("이미지 없음");
            }
        } else {
            // movie가 null인 경우 기본 이미지 또는 알림 메시지를 설정
            Poster_Img = new JLabel("이미지 없음");
        }
    }
}
