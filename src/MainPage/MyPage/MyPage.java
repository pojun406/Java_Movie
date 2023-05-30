package MainPage.MyPage;

//마이 페이지 ( 비밀번호 변경, 여태 본 영화 목록, 구매내역 )

import DAODTO.Booking.BookingDAO;
import DAODTO.Booking.BookingDTO;
import DAODTO.Member.MemberDTO;
import DAODTO.Member.User;
import MainPage.AdminPage.AdminPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyPage extends JFrame {
    private JPanel MyPagePanel = new JPanel(null);
    private JButton btn_ChBooking;
    private JLabel lb_money;
    private JList list_Watched;
    private JButton btn_Out;
    private User user = User.getInstance();
    private BookingDAO dao = new BookingDAO();

    public MyPage() {

        JLabel howmuch = new JLabel("우리 영화관에 사용한 총액 : ");
        JLabel Watched = new JLabel("시청 목록");



        if(dao.PayedALL(user.getUID()) < 1){
            lb_money = new JLabel("0 원");
        }else{
            lb_money = new JLabel(dao.PayedALL(user.getUID())+" 원");
        }
        list_Watched = new JList();

        ArrayList<BookingDTO> watchedList = BookingDAO.getALLList(user.getUID());
        DefaultListModel<String> listModel = new DefaultListModel<>();
        String prevReservationNum = ""; // 이전 예약 번호를 저장할 변수

        for (BookingDTO dto : watchedList) {
            String currentReservationNum = dto.getReservation_Num();
            System.out.println("마페이지 커런트넘"+ currentReservationNum);
            // 이전 예약 번호와 현재 예약 번호가 다르면 Movie_Name을 추가
            if (!currentReservationNum.equals(prevReservationNum)) {
                listModel.addElement("예약번호 : "+currentReservationNum+" | " +dto.getMovie_Name() + "  | 상영시간 : " + dto.getSchedule());
            }

            prevReservationNum = currentReservationNum;
        }

        list_Watched.setModel(listModel);

        howmuch.setSize(100, 20);
        Watched.setSize(100, 20);
        list_Watched.setSize(700,200);

        btn_ChBooking = new JButton("예매 확인");
        btn_Out = new JButton("나가기");


        btn_ChBooking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list_Watched != null) {
                    String selectedItem = list_Watched.getSelectedValue().toString();
                    if (selectedItem != null) {

                        String[] splitItem = selectedItem.split(" \\| "); // "영화 제목 | 상영시간" 형식에서 분리
                        String resnum = splitItem[0].substring(6).trim(); // 예약번호
                        String movieTitle = splitItem[1].trim(); // 영화 제목
                        String schedule = splitItem[2].substring(6).trim(); // 상영시간

                        // Reservation_Num 추출
                        String reservationNum = null;
                        for (BookingDTO dto : watchedList) {
                            if (dto.getMovie_Name().equals(movieTitle) && dto.getSchedule().equals(schedule) && dto.getReservation_Num().equals(resnum)) {
                                reservationNum = dto.getReservation_Num();
                                break;
                            }
                        }

                        if (reservationNum != null) {
                            Ticket_info ti = new Ticket_info(reservationNum);
                            ti.setVisible(true);
                            MyPage.this.setVisible(false);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "선택된 예매목록이 없습니다.");
                    }
                }
            }
        });

        btn_Out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainPage ad = new MainPage();
                ad.setVisible(true);
                MyPage.this.setVisible(false);
            }
        });

        howmuch.setBounds(20, 20, 200, 20);
        lb_money.setBounds(20, 50, 100, 20);
        Watched.setBounds(20, 80, 100, 20);
        list_Watched.setBounds(20, 100, 380, 200);
        btn_ChBooking.setBounds(20, 300, 100, 30);
        btn_Out.setBounds(220, 300, 100, 30);



        setContentPane(MyPagePanel);
        MyPagePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setTitle("상세 페이지");
        setSize(440,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        System.out.println(user.getUser_ID());

        MyPagePanel.add(howmuch);
        MyPagePanel.add(Watched);
        MyPagePanel.add(list_Watched);
        MyPagePanel.add(btn_Out);
        MyPagePanel.add(btn_ChBooking);
        MyPagePanel.add(lb_money);

    }
}
