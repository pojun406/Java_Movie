package MainPage.MyPage;


import DAODTO.Booking.BookingDAO;
import DAODTO.Booking.BookingDTO;
import MainPage.BookingPage.CheckBookingDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CheckBookingList extends JFrame{
    private JPanel CheckBookPanel = new JPanel(null);
    private JList bookingMovieList = new JList();
    private JButton btn_Detail;
    private JButton btn_Out;
    private BookingDAO bokdao = new BookingDAO();

    public CheckBookingList(){
        ArrayList<BookingDTO> MovieList = bokdao.getMovieSchedule();
        String TheaterNum = "";
        String[] Schedule = null;

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (BookingDTO dto : MovieList) {
            listModel.addElement(dto.getMovie_Name());
        }
        bookingMovieList.setModel(listModel);

        JScrollPane listScrollPane = new JScrollPane(bookingMovieList);
        listScrollPane.setBounds(10, 10, 270, 300);

        btn_Detail = new JButton("상세 정보");
        btn_Detail.setBounds(10, 320, 130, 30);

        btn_Out = new JButton("나가기");
        btn_Out.setBounds(150, 320, 130, 30);

        CheckBookPanel.add(listScrollPane);
        CheckBookPanel.add(btn_Detail);
        CheckBookPanel.add(btn_Out);

        setContentPane(CheckBookPanel);
        CheckBookPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setTitle("예매 화면");
        setSize(300,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        btn_Detail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedMovie = bookingMovieList.getSelectedValue().toString();
                BookingDTO selectedMovieDTO = null;

                // 선택된 영화명과 일치하는 DTO 찾기
                for (BookingDTO dto : MovieList) {
                    if (dto.getMovie_Name().equals(selectedMovie)) {
                        selectedMovieDTO = dto;
                        break;
                    }
                }
                if (selectedMovieDTO != null) {
                    String[] Scadule = selectedMovieDTO.getSchedule().split(",");
                    CheckBookingDialog dia = new CheckBookingDialog(selectedMovieDTO.getMovie_Name(), Scadule, selectedMovieDTO.getTheater_Num());
                    dia.setVisible(true);
                    CheckBookingList.this.setVisible(false);
                }
            }
        });

        btn_Out.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainPage main = new MainPage();
                main.setVisible(true);
                CheckBookingList.this.setVisible(false);
            }
        });

    }
}
