package MainPage.BookingPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservationPage extends JFrame {
    private JLabel labelMovie;
    private JLabel labelTheater;
    private JLabel labelTime;
    private JComboBox<String> comboBoxMovie;
    private JComboBox<String> comboBoxTheater;
    private JComboBox<String> comboBoxTime;
    private JButton buttonReserve;

    public ReservationPage() {
        setTitle("영화 예매");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // UI 컴포넌트 초기화
        labelMovie = new JLabel("영화 선택:");
        labelTheater = new JLabel("영화관 선택:");
        labelTime = new JLabel("시간대 선택:");
        comboBoxMovie = new JComboBox<>();
        comboBoxTheater = new JComboBox<>();
        comboBoxTime = new JComboBox<>();
        buttonReserve = new JButton("예매하기");

        // UI 컴포넌트를 프레임에 추가
        add(labelMovie);
        add(comboBoxMovie);
        add(labelTheater);
        add(comboBoxTheater);
        add(labelTime);
        add(comboBoxTime);
        add(buttonReserve);

        // 버튼 클릭 이벤트 리스너 등록
        buttonReserve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedMovie = (String) comboBoxMovie.getSelectedItem();
                String selectedTheater = (String) comboBoxTheater.getSelectedItem();
                String selectedTime = (String) comboBoxTime.getSelectedItem();

                if (selectedMovie == null || selectedTheater == null || selectedTime == null) {
                    JOptionPane.showMessageDialog(null, "영화, 영화관, 시간대를 선택해주세요.");
                } else {
                    // 예매 정보를 이용하여 예매 처리 로직 작성
                    // 예매 정보: selectedMovie, selectedTheater, selectedTime
                    // TODO: 예매 처리 로직 구현
                    JOptionPane.showMessageDialog(null, "예매가 완료되었습니다.");
                }
            }
        });

        pack();
        setLocationRelativeTo(null); // 화면 중앙에 위치
    }

    // 예매 페이지 실행 메서드
    public void run() {
        setVisible(true);
    }

    public static void main(String[] args) {
        ReservationPage reservationPage = new ReservationPage();
        reservationPage.run();
    }
}
