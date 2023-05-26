package MainPage.AdminPage;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;import java.awt.event.ComponentAdapter;

public class Movie_Update extends JFrame{
    private JPanel MovieUpdatePanel;
    private JList list_Movie;
    private JList list_Theater;
    private JList list_Time;
    private JButton btn_OK;

    public Movie_Update() {
        list_Movie = new JList();
        list_Theater = new JList();
        list_Time = new JList();

        list_Movie.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_Theater.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list_Time.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setContentPane(MovieUpdatePanel);
        MovieUpdatePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setTitle("영화 업데이트 페이지");
        setSize(460,560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        list_Movie.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(list_Movie.getSelectedValue() != null){

                }
            }
        });

        list_Theater.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(list_Movie.getSelectedValue() != null){

                }

            }
        });
        list_Time.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(list_Theater.getSelectedValue() != null){

                }
            }
        });
        btn_OK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminPage admin = new AdminPage();
                admin.setVisible(true);
                Movie_Update.this.setVisible(false);
            }
        });

    list_Movie.addComponentListener(new ComponentAdapter() { } );}
}
