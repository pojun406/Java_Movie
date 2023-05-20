package MainPage.BookingPage;

import javax.swing.*;

public class PaymentPage extends JFrame{
    private JPanel PaymentPanel;
    private JButton btn_Cash;
    private JButton btn_Card;
    private JLabel lb_WhatPay;

    public PaymentPage() {
        setContentPane(PaymentPanel);
        PaymentPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setTitle("결제 페이지");
        setSize(350,220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
