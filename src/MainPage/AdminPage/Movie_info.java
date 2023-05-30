package MainPage.AdminPage;

import DAODTO.Booking.BookingDAO;
import DAODTO.Movie.MovieDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Movie_info extends JFrame {
    private JPanel MovieinfoPanel;
    private JButton btn_out;
    private JLabel lb_MovieNm;
    private JLabel Poster_Img;
    private JLabel lb_Selled;
    private JLabel lb_RunningTime;
    private JLabel lb_TheaterEx;
    private JTextPane tpDescription = new JTextPane();
    MovieDAO dao = new MovieDAO();
    BookingDAO bokdao = new BookingDAO();

    public Movie_info(String selectedMovie) {
        String filename = "";
        String Description = "";
        String RunningTime = "";
        List<String> TheaterNums = null;
        String TheaterNum = "";



        Description = dao.getMovieDescription(selectedMovie);
        RunningTime = dao.getMovieRunningTime(selectedMovie);
        TheaterNums = bokdao.getTheaterNum(selectedMovie);

        if(TheaterNums.size() == 0){
            TheaterNum = "현재 할당된 상영관이 없습니다.";
        }else{
            for (int i = 0; i < TheaterNums.size(); i++) {
                TheaterNum += TheaterNums.get(i);
            }
        }

        MovieinfoPanel = new JPanel(null);
        JLabel movie_nm = new JLabel("영화 명 : ");
        JLabel movie_ticket = new JLabel("예매 수 : ");
        JLabel movie_RunningTime = new JLabel("상영 시간 : ");
        JLabel movie_Theater = new JLabel("상영 관 : ");
        JLabel movie_Description = new JLabel("줄거리 : ");

        if (selectedMovie != null) {
            filename = "./img/" + selectedMovie.replaceAll("[^ㄱ-ㅎ가-힣0-9]+", "").replaceAll("\\s+", "") + ".jpg";
        } else {
            filename = "./error/error.jpg";
        }

        Poster_Img = new JLabel(new ImageIcon(filename));

        Poster_Img.setSize(213,304);
        Poster_Img.setLocation(50,70);


        btn_out = new JButton("나가기");
        lb_MovieNm = new JLabel(selectedMovie);
        lb_Selled = new JLabel(bokdao.ReservationALL(selectedMovie) + "장");
        lb_RunningTime = new JLabel(RunningTime + "분");
        lb_TheaterEx = new JLabel(TheaterNum);
        tpDescription.setText(Description);
        tpDescription.setEditable(false);


        movie_nm.setSize(100, 20);
        movie_ticket.setSize(100, 20);
        movie_Theater.setSize(100, 20);
        movie_RunningTime.setSize(100, 20);
        movie_Description.setSize(100, 20);
        btn_out.setSize(100,20);
        lb_MovieNm.setSize(300, 20);
        lb_Selled.setSize(100,20);
        lb_RunningTime.setSize(100,20);
        lb_TheaterEx.setSize(200, 20);
        tpDescription.setSize(250,250);

        int noinfoLabelX = 340;
        int infoLabelX = 400;

        movie_nm.setLocation(noinfoLabelX, 40);
        movie_ticket.setLocation(noinfoLabelX, 80);
        movie_Theater.setLocation(noinfoLabelX, 120);
        movie_RunningTime.setLocation(noinfoLabelX, 160);
        movie_Description.setLocation(noinfoLabelX, 200);
        btn_out.setLocation(50,400);
        lb_MovieNm.setLocation(infoLabelX, 40);
        lb_Selled.setLocation(infoLabelX, 80);
        lb_TheaterEx.setLocation(infoLabelX, 120);
        lb_RunningTime.setLocation(infoLabelX, 160);
        tpDescription.setLocation(infoLabelX, 200);

        setContentPane(MovieinfoPanel);
        MovieinfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        setTitle("영화정보 페이지");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        MovieinfoPanel.add(movie_nm);
        MovieinfoPanel.add(movie_ticket);
        MovieinfoPanel.add(movie_Theater);
        MovieinfoPanel.add(movie_RunningTime);
        MovieinfoPanel.add(movie_Description);
        MovieinfoPanel.add(lb_MovieNm);
        MovieinfoPanel.add(lb_Selled);
        MovieinfoPanel.add(lb_TheaterEx);
        MovieinfoPanel.add(lb_RunningTime);
        MovieinfoPanel.add(tpDescription);
        MovieinfoPanel.add(Poster_Img);
        MovieinfoPanel.add(btn_out);

        btn_out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Movie_Choose info = new Movie_Choose();
                info.setVisible(true);
                Movie_info.this.setVisible(false);
            }
        });

/*
        if (movie != null) {
            String filename = "./img/" + movie.replaceAll("[^ㄱ-ㅎ가-힣0-9]+", "").replaceAll("\\s+", "") + ".jpg";
            Poster_Img = new JLabel(new ImageIcon(filename));
        } else {
            String imagePath = "./error/error.jpg";
            System.out.println(imagePath);
            Poster_Img = new JLabel(new ImageIcon(imagePath));
        }

        lb_MovieNm.setText(movie);

        btn_out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminPage admin = new AdminPage();
                admin.setVisible(true);
                Movie_info.this.setVisible(false);
            }
        });

 */
    }
}
