package MainPage.MyPage;


import DAODTO.Booking.BookingDAO;
import DAODTO.Booking.BookingDTO;
import DAODTO.Member.MemberDAO;
import DAODTO.Member.MemberDTO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CheckBookingList extends JFrame{
    private JPanel CheckBookPanel = new JPanel(null);
    private JList bookingMovieList = new JList();
    private JButton btn_Detail;
    private JButton btn_Out;
    private MemberDTO login;
    private BookingDTO book;
    private MemberDAO dao = new MemberDAO();
    private BookingDAO bokdao = new BookingDAO();

    public CheckBookingList(MemberDTO login){
        BookingDTO bokdto = new BookingDTO();
        this.login = login;
        String UserID = login.getUSER_ID();
        String UID = login.getUID();
        ArrayList<BookingDTO> bookingList = bokdao.getAllReservationInfo(UID);

        if (!bookingList.isEmpty()) {
            book = bookingList.get(0); // 예약 정보 중 첫 번째를 가져옴

            System.out.println(book);
            //...
        } else {
            // 예약 정보가 없을 경우 처리
            System.out.println("예약 내역이 없습니다.");
        }


        String list = dao.getUserWatched(UserID);



        String[] watchedlist;

        JLabel findyourbooking = new JLabel();

        if (list != null) {
            watchedlist = list.split(",");
        } else {
            watchedlist = new String[0];
        }

        if(watchedlist.length > 0){
            findyourbooking = new JLabel("예매 내역을 확인하세요");
        }
        else{
            findyourbooking = new JLabel("예매 내역이 없습니다.");
        }


        ArrayList<BookingDTO> watchedList = BookingDAO.getALLList(login.getUID());

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (BookingDTO dto : watchedList) {
            listModel.addElement(dto.getMovie_Name());
        }

        bookingMovieList.setModel(listModel);

        JScrollPane listScrollPane = new JScrollPane(bookingMovieList);
        listScrollPane.setBounds(10, 10, 270, 300);

        btn_Detail = new JButton("상세 정보");
        btn_Detail.setBounds(10, 320, 130, 30);

        btn_Out = new JButton("나가기");
        btn_Out.setBounds(150, 320, 130, 30);

        findyourbooking.setBounds(10, 10, 270, 30);

        CheckBookPanel.add(listScrollPane);
        CheckBookPanel.add(btn_Detail);
        CheckBookPanel.add(btn_Out);
        CheckBookPanel.add(findyourbooking);

        setContentPane(CheckBookPanel);
        CheckBookPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setTitle("예매 내역 체크");
        setSize(300,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        btn_Detail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CheckBookingDialog dia = new CheckBookingDialog(login);
            }
        });

    }
}
