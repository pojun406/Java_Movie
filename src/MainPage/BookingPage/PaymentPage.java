package MainPage.BookingPage;

import DAODTO.Booking.BookingDTO;
import DAODTO.Member.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentPage extends JFrame{
    private JPanel PaymentPanel;
    private JButton btn_Cash;
    private JButton btn_Card;
    private JLabel lb_WhatPay;
    private BookingDTO dto;  // BookingDTO 정보를 저장할 필드
    private String[] selectedSeats;  // 선택된 좌석 정보를 저장할 필드
    private User user = User.getInstance();


    public PaymentPage(BookingDTO dto, String[] selectedSeats) {
        this.dto = dto;
        this.selectedSeats = selectedSeats;
        user.setUser_Pay(10000);

        setContentPane(PaymentPanel);
        PaymentPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setTitle("결제 페이지");
        setSize(500,220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        btn_Cash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Payed = "cash";
                Booking_Detail bok = new Booking_Detail(selectedSeats, dto, Payed);
                PaymentPage.this.setVisible(false);
            }
        });
        btn_Card.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Payed = "Card";
                Booking_Detail bok = new Booking_Detail(selectedSeats, dto, Payed);
                PaymentPage.this.setVisible(false);
            }
        });
    }
}
