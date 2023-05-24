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
    private JPanel chpwPanel;
    boolean PW_Checks = false;
    String Password = "";
    String Password_Check = "";

    public Dialog_ChPw(String UserID) {
        MemberDTO member = new MemberDTO();
        setContentPane(chpwPanel);
        setTitle("비밀번호 변경");
        setSize(200,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        btn_Ch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] pw = pw_pw.getPassword();
                char[] pw_ch = pw_pwch.getPassword();

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
                        PW_Checks = true;
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"안썼잖아");
                        PW_Checks = false;
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"틀림");
                    PW_Checks = false;
                }

                if(PW_Checks){

                    member.setUSER_PW(Password);
                    member.setUSER_ID(UserID);

                    MemberDAO dao = new MemberDAO();
                    boolean success = dao.Ch_PW(member);

                    if(success){
                        JOptionPane.showMessageDialog(null, "변경완료");
                        System.out.println(Password);
                        System.out.println(UserID);
                        setVisible(false);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "변경실패");
                    setVisible(false);
                }
            }
        });
        btn_Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }
}
