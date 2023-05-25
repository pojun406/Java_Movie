package MainPage.AdminPage;

import DAODTO.Movie.MovieDAO;
import DAODTO.Movie.MovieDTO;

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

    MovieDAO dao = new MovieDAO();

    public Movie_info(String selectedMovie) {
        setContentPane(MovieinfoPanel);
        MovieinfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        dao.ChoiceTitle(selectedMovie);
        dao.infoTitle();

        setTitle("영화정보 페이지");
        setSize(460, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        MovieDTO info = new MovieDTO();

        String title_info = info.getTitle();
        String genre_info = info.getGenre();
        String director_info = info.getDirector();
        String Actor_info = info.getCast();
        String Running_info = info.getRunning_Time();
        String Description_info = info.getDescription();

        System.out.println(title_info);

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
