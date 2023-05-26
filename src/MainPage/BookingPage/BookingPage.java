package MainPage.BookingPage;

import DAODTO.Booking.BookingDAO;
import DAODTO.Movie.MovieDAO;
import MainPage.AdminPage.Movie_info;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BookingPage extends JFrame{
    public String selectedMovie = "";
    public String selectedTheater = "";
    private JList<String> list_Movie;
    private JList<String> list_Theater;
    private JButton btn_Next;
    private JPanel choicePanel;
    MovieDAO moviedao = new MovieDAO();
    BookingDAO bookingdao = new BookingDAO();

    List<String> movieList = new ArrayList<>();
    List<String> theaterList = new ArrayList<>();

    DefaultListModel<String> MovieModel = new DefaultListModel<>();
    DefaultListModel<String> TheaterModel = new DefaultListModel<>();

    public BookingPage(){
        btn_Next = new JButton("다음");

        movieList = moviedao.getTitle();
        theaterList = bookingdao.getTheater();

        for (String title : movieList) {
            MovieModel.addElement(title);
        }

        for (String theater : theaterList) {
            TheaterModel.addElement(theater);
        }

        list_Movie = new JList<>(MovieModel);
        list_Theater = new JList<>(TheaterModel);

        choicePanel = new JPanel();
        setContentPane(choicePanel);
        choicePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setTitle("예매 페이지");
        setSize(460, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JScrollPane scrollMovie = new JScrollPane(list_Movie);
        JScrollPane scrollTheater = new JScrollPane(list_Theater);
        choicePanel.add(scrollMovie);
        choicePanel.add(scrollTheater);
        add(btn_Next);


        btn_Next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean chmovie = moviedao.ChoiceTitle(list_Movie.getSelectedValue());
                boolean chtheater = bookingdao.ChoiceTheater(list_Theater.getSelectedValue());
                if(chmovie && chtheater){
                    selectedMovie = list_Movie.getSelectedValue();
                    selectedTheater = list_Theater.getSelectedValue();
                    moviedao.ChoiceTitle(selectedMovie);
                    bookingdao.ChoiceTheater(selectedTheater);
                    if(bookingdao.Theater_Size(selectedTheater) == 1){
                        big_Theater bigT = new big_Theater(selectedMovie);
                        BookingPage.this.setVisible(false);
                    }
                    else{
                        Small_Theater smallT = new Small_Theater(selectedMovie);
                        BookingPage.this.setVisible(false);
                    }
                    System.out.println("선택한 영화 : " + selectedMovie);
                    System.out.println("선택한 관 : " + selectedTheater);
                }
                else{
                    JOptionPane.showMessageDialog(null,"선택안함");
                }
            }
        });
    }


}
