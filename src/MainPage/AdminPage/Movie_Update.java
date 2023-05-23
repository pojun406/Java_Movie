package MainPage.AdminPage;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Movie_Update extends JFrame{
    private JPanel MovieUpdatePanel;
    private JList list_Movie;
    private JList list_Theater;
    private JList list_Time;
    private JButton btn_OK;

    public Movie_Update() {
        setContentPane(MovieUpdatePanel);
        MovieUpdatePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setTitle("영화 업데이트 페이지");
        setSize(460,560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        list_Theater.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });
        list_Time.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

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
    }
}
