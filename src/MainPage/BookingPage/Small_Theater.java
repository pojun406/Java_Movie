package MainPage.BookingPage;

import DAODTO.Movie.MovieDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Small_Theater extends JFrame{
    String movie;
    private JLabel posterImg;
    private JComponent ui = null;
    private JToggleButton[] seats = new JToggleButton[40];
    private JTextArea selectedSeats = new JTextArea(1, 40);
    JFrame f = new JFrame("좌석 선택 페이지");
    private java.util.List<String> selectedSeatList = new ArrayList<>();

    public Small_Theater(String movieName) {
        movie = movieName;

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

        selectedSeats.setLineWrap(true);
        selectedSeats.setWrapStyleWord(true);
        selectedSeats.setEditable(false);
        ui.add(new JScrollPane(selectedSeats), BorderLayout.PAGE_END);

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
                if (selectedSeat.isSelected()) {
                    selectedSeatList.add(seatText);
                    System.out.println(selectedSeatList);
                } else {
                    selectedSeatList.remove(seatText);
                    System.out.println(selectedSeatList);
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
        }

        JButton nextButton = new JButton("다음");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSeatInfo(selectedSeatList);
                Booking_Detail bok = new Booking_Detail(setSeatInfo(selectedSeatList), movie);
                bok.setVisible(true);
                Small_Theater.this.setVisible(false);
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

    public String[] setSeatInfo(List<String> seatinfo){
        String info = "";
        String[] a = null;
        if(seatinfo == null){
            JOptionPane.showMessageDialog(null,"선택하십쇼");
        }
        else{
            for (int i = 0; i < seatinfo.size(); i++) {
                info += seatinfo.get(i) + ",";
            }
            System.out.println(info);
            a = info.split(",");
            for (int i = 0; i < a.length; i++) {
                System.out.println(a[i]);
            }
        }
        return a;
    }


}
