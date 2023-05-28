package MainPage.BookingPage;

//예매내역확인 페이지 (몇관, 몇장, 시트위치등)

import DAODTO.Booking.BookingDAO;
import DAODTO.Booking.BookingDTO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckBookingDialog extends JFrame {
    private JPanel CheckBookingPanel;
    private JLabel MovieName;
    private JButton btn_Out;
    private JButton btn_Next;
    private BookingDAO bokdao = new BookingDAO();
    private BookingDTO dto = new BookingDTO();

    public CheckBookingDialog(String title, String[] Schedule, String Theater_Num) {

        dto.setMovie_Name(title);
        dto.setTheater_Num(Theater_Num);

        CheckBookingPanel = new JPanel();
        CheckBookingPanel.setLayout(null);

        btn_Next = new JButton("다음");
        btn_Next.setBounds(20,120,100,20);
        CheckBookingPanel.add(btn_Next);

        btn_Out = new JButton("나가기");
        btn_Out.setBounds(150,120,100,20);
        CheckBookingPanel.add(btn_Out);

        // MovieName 레이블
        MovieName = new JLabel("영화 제목: " + title);
        MovieName.setBounds(20, 20, 200, 20);
        CheckBookingPanel.add(MovieName);

        // TheaterNum 레이블
        JLabel theaterLabel = new JLabel("상영관: " + Theater_Num);
        theaterLabel.setBounds(20, 50, 200, 20);
        CheckBookingPanel.add(theaterLabel);

        // Schedule 콤보 박스
        JLabel scheduleLabel = new JLabel("상영 시간: ");
        scheduleLabel.setBounds(20, 80, 100, 20);
        CheckBookingPanel.add(scheduleLabel);

        JComboBox<String> scheduleComboBox = new JComboBox<>(Schedule);
        scheduleComboBox.setBounds(100, 80, 150, 20);
        CheckBookingPanel.add(scheduleComboBox);

        for (int i = 0; i < Schedule.length; i++) {
            System.out.println("상영시간 : " + Schedule[i]);
        }
        System.out.println(title);
        System.out.println(Schedule);
        System.out.println(Theater_Num);

        setContentPane(CheckBookingPanel);

        CheckBookingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setTitle("영화 선택");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        btn_Next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedSchedule = (String) scheduleComboBox.getSelectedItem();
                dto.setSchedule(selectedSchedule);
                if(selectedSchedule != null){
                    if(bokdao.Theater_Size(Theater_Num) == 1){
                        big_Theater bigT = new big_Theater(dto);
                        CheckBookingDialog.this.setVisible(false);
                    }
                    else{
                        Small_Theater smallT = new Small_Theater(dto);
                        smallT.setVisible(true);
                        CheckBookingDialog.this.setVisible(false);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"시간을 선택하십쇼");
                }
            }
        });
    }
}
