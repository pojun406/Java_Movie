package LoginPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Join extends JFrame{

    private JPanel JoinPanel;
    private JButton btn_Exit;
    private JButton btn_Join;
    private JButton btn_PWcheck;
    private JButton btn_IDcheck;
    private JLabel lb_PW;
    private JLabel lb_PhNum;
    private JLabel lb_Name;
    private JLabel lb_ID;
    private JLabel lb_PW_check;
    private JTextField txt_Name;
    private JTextField txt_PhNum;
    private JTextField txt_ID;
    private JPasswordField pw_PW;
    private JPasswordField pw_PWch;

    public Join(){
        setContentPane(JoinPanel);
        JoinPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setTitle("회원가입 페이지");
        setSize(460,260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        //---------------------------------------------------------------------------------
        btn_Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.setVisible(true);
                Join.this.setVisible(false);
            }
        });
        //-------------------------------------------------------------------------------------
        btn_PWcheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Password = "";
                String Password_Check = "";

                char[] pw = pw_PW.getPassword();
                char[] pw_ch = pw_PWch.getPassword();

                for(char cha : pw){
                    Character.toString(cha);
                    Password += (Password.equals("")? ""+cha+"" : ""+cha+"");
                }

                for(char cha : pw_ch){
                    Character.toString(cha);
                    Password_Check += (Password_Check.equals("")? ""+cha+"" : ""+cha+"");
                }
//                System.out.println(Password); // 비밀번호칸 확인
//                System.out.println(Password_Check); // 비밀번호 확인칸 확인

                if(Password.equals(Password_Check)) {
                    if(Password_Check != ""){
                        JOptionPane.showMessageDialog(null, "HelloWorld");
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Check TextBox");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Wrong");
                }
            }
        });
        //---------------------------------------------------------------------------------------
        btn_Join.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}