package LoginPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindID extends JFrame {
    private JPanel FindIDPanel;
    private JLabel lb_Name;
    private JLabel lb_PhNum;
    private JButton btn_FindID;
    private JButton btn_Exit;
    private JTextField txt_Name;
    private JTextField txt_PhNum;

    public FindID(){
        setContentPane(FindIDPanel);
        FindIDPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setTitle("아이디 찾기 페이지");
        setSize(350,220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        btn_Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.setVisible(true);
                FindID.this.setVisible(false);
            }
        });
    }
}
