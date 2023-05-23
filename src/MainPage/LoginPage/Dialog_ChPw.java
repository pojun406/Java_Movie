package MainPage.LoginPage;

import DAODTO.Member.MemberDAO;
import DAODTO.Member.MemberDTO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dialog_ChPw extends JDialog{
    private JButton btn_Ch;
    private JButton btn_Exit;
    private JPasswordField pw_pw;
    private JPasswordField pw_pwch;
    boolean PW_Checks = false;
    String Password = "";
    String Password_Check = "";

    public Dialog_ChPw() {
        MemberDTO member = new MemberDTO();

        btn_Ch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] pw = pw_pw.getPassword();
                char[] pw_ch = pw_pwch.getPassword();
                Password = "";
                Password_Check = "";

                for(char cha : pw){
                    Character.toString(cha);
                    Password += (Password.equals("")? ""+cha+"" : ""+cha+"");
                }

                for(char cha : pw_ch){
                    Character.toString(cha);
                    Password_Check += (Password_Check.equals("")? ""+cha+"" : ""+cha+"");
                }
                if(Password.equals(Password_Check)) {
                    if(Password_Check != ""){
                        JOptionPane.showMessageDialog(null, "확인완료");
                        PW_Checks = true;
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"안썼잖아");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"틀림");
                }

                if(PW_Checks){
                    member.setUSER_PW(Password);

                    MemberDAO dao = new MemberDAO();

                    boolean success = dao.Ch_PW(member);

                    if(success){
                        JOptionPane.showMessageDialog(null, "변경완료");
                    }
                }
                else{

                }
            }
        });
        btn_Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
