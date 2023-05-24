package MainPage.AdminPage;

import DAODTO.Movie.MovieDAO;
import DAODTO.Movie.MovieDTO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;
import java.util.List;

public class Movie_Choose extends JFrame{
    private JList list_Movie;
    private JButton btn_exit;
    private JButton btn_show;
    private JPanel chosePanel;
    MovieDAO dao = new MovieDAO();
    MovieDTO dto = new MovieDTO();

    DefaultListModel listModel = new DefaultListModel();
    String[] alltitle = dao.getTitleAll();
    public Movie_Choose() {
        setContentPane(chosePanel);
        chosePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setTitle("영화선택 페이지");
        setSize(460,260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        list_Movie.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        list_Movie.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                alltitle = dao.getTitleAll();
                System.out.println(alltitle);
            }
        });
    }
}
