package MainPage.LoginPage;

import MainPage.AdminPage.AdminPage;
import DAODTO.Member.MemberDAO;
import DAODTO.Member.MemberDTO;
import MainPage.BookingPage.BookingPage;

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

        MemberDTO member = new MemberDTO();


        btn_Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Admin Page 진입 -----------------------
                String Password = "";
                char[] pw = txt_PW.getPassword();
                for(char cha : pw){
                    Character.toString(cha);
                    Password += (Password.equals("")? ""+cha+"" : ""+cha+"");
                }

                member.setUSER_ID(txt_ID.getText());
                member.setUSER_PW(Password);

                MemberDAO dao = new MemberDAO();
                boolean login = dao.Login(member);
                if(login){
                    if(member.getUID() == 0 && "admin".equals(member.getUSER_ID())){
                        AdminPage adminpage = new AdminPage();
                        adminpage.setVisible(true);
                        Login.this.setVisible(false);
                    }
                    else{
                        BookingPage bookpage = new BookingPage();
                        bookpage.setVisible(true);
                        Login.this.setVisible(false);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "아이디 / 비밀번호를 확인하세요");
                }
                    
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

