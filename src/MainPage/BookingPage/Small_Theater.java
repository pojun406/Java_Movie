package MainPage.BookingPage;

import DAODTO.Movie.MovieDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Small_Theater extends JFrame{
    private JComponent ui = null;
    private JToggleButton[] seats = new JToggleButton[40];
    private JTextArea selectedSeats = new JTextArea(1, 40);
    JFrame f = new JFrame("좌석 선택 페이지");
    MovieDAO dao = new MovieDAO();

    public Small_Theater() {

        if (ui!=null) return;

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
                StringBuilder sb = new StringBuilder();
                for (int ii = 0; ii < seats.length; ii++) {
                    JToggleButton tb = seats[ii];
                    if (tb.isSelected()) {
                        sb.append(tb.getText());
                        sb.append(", ");
                    }
                }
                String s = sb.toString();
                if (s.length() > 0) {
                    s = s.substring(0, s.length() - 2);
                }
                selectedSeats.setText(s);
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
    }
    public static void main(String[] args) { // test main
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Small_Theater();
            }
        });
    }
}
