package LoginPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{
    private JPanel LoginPanel;
    private JTextField txt_ID;
    private JButton btn_Login;
    private JPasswordField txt_PW;
    private JButton btn_FindID;
    private JButton btn_FindPW;
    private JButton btn_Join;
    private JLabel lb_ID;
    private JLabel lb_PW;

    public Login() {

        setContentPane(LoginPanel);
        LoginPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setTitle("로그인 페이지");
        setSize(460,160);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);


        btn_Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"HelloWorld");
            }
        });
        btn_FindID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FindID findid = new FindID();
                findid.setVisible(true);
                Login.this.setVisible(false);
            }
        });
        btn_FindPW.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FindPW findpw = new FindPW();
                findpw.setVisible(true);
                Login.this.setVisible(false);
            }
        });
        btn_Join.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Join join = new Join();
                join.setVisible(true);
                Login.this.setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        Login login = new Login();
        login.setVisible(true);
    }
}

