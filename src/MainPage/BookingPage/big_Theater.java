package MainPage.BookingPage;

import DAODTO.Booking.BookingDTO;
import DAODTO.Booking.Seat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class big_Theater extends JFrame {
    Seat seat = new Seat("",false);
    String movie;
    String Schedule;
    private JLabel posterImg;
    private JComponent ui = null;
    private JToggleButton[] seats = new JToggleButton[80];
    private BookingDTO dto;
    JFrame f = new JFrame("좌석 선택 페이지");
    private List<Seat> seatList = new ArrayList<>();

    public big_Theater(BookingDTO dto) {
        this.dto = dto;

        movie = dto.getMovie_Name();
        Schedule = dto.getSchedule();

        if (ui != null) return;

        init_UI();

        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationByPlatform(true);
        f.setContentPane(ui);
        f.pack();
        f.setMinimumSize(f.getSize());
        f.setVisible(true);
    }

    public void init_UI() {
        ui = new JPanel(new BorderLayout(4, 4));
        ui.setBorder(new EmptyBorder(4, 4, 4, 4));

        posterImg = new JLabel();

        if (movie != null) {
            // Create the image file path
            String imagePath = "./img/" + movie.replaceAll("[^ㄱ-ㅎ가-힣0-9]+", "").replaceAll("\\s+", "") + ".jpg";

            // Check if the image file exists
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                // If the image file exists, set the image to the JLabel
                ImageIcon imageIcon = new ImageIcon(imagePath);
                posterImg.setIcon(imageIcon);
            } else {
                // If the image file does not exist, set a default image or display a notification message
                posterImg.setText("이미지 없음");
            }
        } else {
            // If the movie is null, set a default image or display a notification message
            posterImg.setText("이미지 없음");
        }

        JPanel cinemaFloor = new JPanel(new BorderLayout(40, 0));
        ui.add(cinemaFloor, BorderLayout.CENTER);
        JPanel leftStall = new JPanel(new GridLayout(0, 2, 2, 2));
        JPanel centerStall = new JPanel(new GridLayout(0, 4, 2, 2));
        JPanel rightStall = new JPanel(new GridLayout(0, 2, 2, 2));

        cinemaFloor.add(leftStall, BorderLayout.WEST);
        cinemaFloor.add(centerStall, BorderLayout.CENTER);
        cinemaFloor.add(rightStall, BorderLayout.EAST);

        ActionListener seatSelectionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JToggleButton selectedSeat = (JToggleButton) e.getSource();
                String seatText = selectedSeat.getText();

                for (int i = 0; i < seats.length; i++) {
                    if (selectedSeat == seats[i]) {
                        Seat seat = seatList.get(i);
                        seat.setSeatNumber(seatText);
                        seat.setSelected(selectedSeat.isSelected());
                        break;
                    }
                }
            }
        };

        for (int ii = 0; ii < seats.length; ii++) {
            JToggleButton tb = new JToggleButton("S-" + (ii + 1));
            tb.addActionListener(seatSelectionListener);
            seats[ii] = tb;
            int colIndex = ii % 8;
            if (colIndex < 2) {
                leftStall.add(tb);
            } else if (colIndex < 6) {
                centerStall.add(tb);
            } else {
                rightStall.add(tb);
            }

            // Create Seat object and add to seatList
            Seat seat = new Seat("S-" + ii + 1, false);
            seatList.add(seat);
        }

        JButton nextButton = new JButton("다음");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> selectedSeatNumbers = new ArrayList<>();
                for (Seat seat : seatList) {
                    if (seat.isSelected()) {
                        selectedSeatNumbers.add(seat.getSeatNumber());
                    }
                }

                if (selectedSeatNumbers.isEmpty()) {
                    JOptionPane.showMessageDialog(big_Theater.this, "선택된 좌석이 없습니다.", "경고", JOptionPane.WARNING_MESSAGE);
                } else {
                    String[] selectedSeatsArray = selectedSeatNumbers.toArray(new String[0]);
                    //Booking_Detail bok = new Booking_Detail(selectedSeatsArray, dto);
                    //bok.setVisible(true);
                    PaymentPage paymentPage = new PaymentPage(dto, selectedSeatsArray);
                    paymentPage.setVisible(true);
                    f.setVisible(false);
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(nextButton);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(cinemaFloor, BorderLayout.CENTER);
        contentPanel.add(posterImg, BorderLayout.WEST);
        ui.add(contentPanel, BorderLayout.CENTER);
        ui.add(buttonPanel, BorderLayout.PAGE_END);
    }
}
