package MainPage.AdminPage;

import DAODTO.Movie.MovieDAO;
import DAODTO.Movie.MovieDTO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Movie_Choose extends JFrame {

    private JList<String> list_Movie;
    private JButton btn_exit;
    private JButton btn_show;
    private JPanel chosePanel;
    MovieDAO dao = new MovieDAO();
    public String selectedMovie = "";
    DefaultListModel<String> listModel = new DefaultListModel<>();
    List<String> movieList = new ArrayList<>();

    public Movie_Choose() {
        btn_exit = new JButton("나가기");
        btn_show = new JButton("세부정보 보기");

        btn_show.setBounds(20, 5, 70, 30);
        btn_exit.setBounds(440, 5, 70, 30);

        movieList = dao.getTitle();

        for (String title : movieList) {
            listModel.addElement(title);
        }

        list_Movie = new JList<>(listModel);

        chosePanel = new JPanel();
        setContentPane(chosePanel);
        chosePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setTitle("영화선택 페이지");
        setSize(460, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JScrollPane scrollPane = new JScrollPane(list_Movie);
        chosePanel.add(scrollPane, BorderLayout.CENTER);
        add(btn_show);
        add(btn_exit);

        btn_show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean success = dao.ChoiceTitle(list_Movie.getSelectedValue());
                if(success){
                    selectedMovie = list_Movie.getSelectedValue();
                    dao.ChoiceTitle(selectedMovie);

                    Movie_info info = new Movie_info(selectedMovie);
                    info.setVisible(true);
                    setVisible(false);
                }
            }
        });
        setVisible(true);

        list_Movie.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        btn_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminPage admin = new AdminPage();
                admin.setVisible(true);
                Movie_Choose.this.setVisible(false);
            }
        });
    }
}