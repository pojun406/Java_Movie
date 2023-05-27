package MainPage.AdminPage;

import DAODTO.Booking.BookingDAO;
import DAODTO.Movie.MovieDAO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;import java.awt.event.ComponentAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Movie_Update extends JFrame{
    private JPanel MovieUpdatePanel;
    private JList<String> list_Movie;
    private JList<String> list_Theater;
    private JList<String> list_Time;
    private JButton btn_OK;
    private JButton btn_out;

    MovieDAO movdao = new MovieDAO();
    BookingDAO bokdao = new BookingDAO();

    public String selectedMovie = "";
    public String selectedTheater = "";

    DefaultListModel<String> movie_list = new DefaultListModel<>();
    DefaultListModel<String> theater_list = new DefaultListModel<>();

    List<String> movieList = new ArrayList<>();
    List<String> theaterList = new ArrayList<>();
    List<String> timeList = new ArrayList<>();

    public void remove_movie(int index) {
        if(index<0) {
            if(movie_list.size()==0) return;	//아무것도 저장되어 있지 않으면 return
            index=0;	//그 이상이면 가장 상위 list index
        }

        movie_list.remove(index);
    }

    public void remove_theater(int index) {
        if(index<0) {
            if(theater_list.size()==0) return;	//아무것도 저장되어 있지 않으면 return
            index=0;	//그 이상이면 가장 상위 list index
        }

        theater_list.remove(index);
    }

    public Movie_Update() {
        btn_OK = new JButton("설정완료");
        btn_out = new JButton("나가기");

        btn_OK.setBounds(20, 500, 200, 30);
        btn_out.setBounds(440, 500, 200, 30);

        list_Movie = new JList();
        list_Theater = new JList();
        list_Time = new JList();

        list_Movie.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_Theater.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_Time.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        MovieUpdatePanel = new JPanel();
        setContentPane(MovieUpdatePanel);
        MovieUpdatePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setTitle("영화 업데이트 페이지");
        setSize(460,360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        movieList = movdao.getTitle();
        theaterList = bokdao.getTheater();

        for (String title : movieList) {
            movie_list.addElement(title);
        }

        for (String theater : theaterList){
            theater_list.addElement(theater);
        }

        list_Movie = new JList<>(movie_list);
        list_Theater = new JList<>(theater_list);

        timeList.add("09:00");
        for (int i = 10; i <= 24; i++) {
            String time = String.format("%02d:00", i);
            timeList.add(time);
        }

        list_Time = new JList<>(timeList.toArray(new String[0]));

        JScrollPane movPane = new JScrollPane(list_Movie);
        JScrollPane thePane = new JScrollPane(list_Theater);
        JScrollPane timePane = new JScrollPane(list_Time);

        MovieUpdatePanel.add(movPane, BorderLayout.CENTER);
        MovieUpdatePanel.add(thePane, BorderLayout.CENTER);
        MovieUpdatePanel.add(timePane, BorderLayout.CENTER);

        add(btn_OK);
        add(btn_out);

        btn_OK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedMovie = list_Movie.getSelectedValue();
                selectedTheater = list_Theater.getSelectedValue();
                List<String> selectedTimeList = list_Time.getSelectedValuesList();

                // Schedule에 추가할 데이터 설정
                String schedule = String.join(",", selectedTimeList);
                String movieNum = movdao.getMovieNum(selectedMovie);
                String theaterNum = selectedTheater;

                // dao.AddSchedule() 호출하여 DB에 추가
                boolean success = bokdao.AddSchedule(schedule, movieNum, theaterNum);
                if (success) {
                    JOptionPane.showMessageDialog(null, "추가완료");

                    int Selected_M = list_Movie.getSelectedIndex();
                    remove_movie(Selected_M);
                    int Selected_T = list_Theater.getSelectedIndex();
                    remove_theater(Selected_T);
                } else {
                    if (selectedMovie == null) {
                        JOptionPane.showMessageDialog(null, "영화를 선택해주세요");
                    } else if (selectedTheater == null) {
                        JOptionPane.showMessageDialog(null, "영화관을 선택해주세요");
                    } else if (selectedTimeList == null) {
                        JOptionPane.showMessageDialog(null, "시간대를 선택해주세요");
                    } else {
                        JOptionPane.showMessageDialog(null, "영화와 영화관, 시간대의 선택을 확인해주세요");
                    }
                }
            }
        });



        btn_out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> selectedTheaters = list_Theater.getSelectedValuesList();

                // DB에 있는 전체 상영관 정보 가져오기
                List<String> allTheaters = bokdao.getTheater();

                // 선택된 영화관 정보와 DB에 있는 전체 상영관 정보 비교
                boolean hasMissingTheater = false;
                for (String selectedTheater : selectedTheaters) {
                    if (!allTheaters.contains(selectedTheater)) {
                        hasMissingTheater = true;
                        break;
                    }
                }

                if (hasMissingTheater) {
                    JOptionPane.showMessageDialog(null, "DB에 존재하지 않는 영화관이 선택되었습니다.");
                } else {
                    if (bokdao.Schedule_have()) {
                        JOptionPane.showMessageDialog(null, "영화를 모두 업데이트 완료했습니다.");
                        AdminPage admin = new AdminPage();
                        admin.setVisible(true);
                        Movie_Update.this.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "영화를 지정하지 않은 상영관이 존재합니다.");
                    }
                }
            }

        });


    }
}