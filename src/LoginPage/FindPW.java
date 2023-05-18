package LoginPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindPW extends JFrame{
    private JPanel FindPWPanel;
    private JLabel lb_Name;
    private JLabel lb_PhNum;
    private JLabel lb_ID;
    private JTextField txt_Name;
    private JTextField txt_PhNum;
    private JTextField txt_ID;
    private JButton btn_Exit;
    private JButton btn_FindPW;

    public FindPW(){
        setContentPane(FindPWPanel);
        FindPWPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setTitle("비밀번호 찾기 페이지");
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
                FindPW.this.setVisible(false);
            }
        });
    }
}
