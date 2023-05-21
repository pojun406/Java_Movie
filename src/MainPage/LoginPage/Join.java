package MainPage.LoginPage;

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


boolean PW_Checks = false;
boolean ID_Checks = false;
String Password = "";
String Password_Check = "";

public Join(){
    setContentPane(JoinPanel);
    JoinPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    setTitle("회원가입 페이지");
    setSize(460,260);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(true);

    MemberDTO member = new MemberDTO();

    //---------------------------------------------------------------------------------
    btn_IDcheck.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            member.setUSER_ID(txt_ID.getText());

            MemberDAO dao = new MemberDAO();
            boolean checked = dao.CheckID(member);

            if(checked){
                System.out.println("가입가능");
                JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.");
                ID_Checks = true;
            }
            else{
                System.out.println("아이디 중복됨");
                JOptionPane.showMessageDialog(null, "이미 사용중인 아이디입니다.");
            }
        }
    });
    //-------------------------------------------------------------------------------
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
        }
    });
    //---------------------------------------------------------------------------------------
    btn_Join.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String Callnum = txt_PhNum.getText();
            if(txt_Name.getText().equals("") || txt_PhNum.getText().equals("")){
                JOptionPane.showMessageDialog(null, "빈칸을 확인해주세요");
            }
            else{
                if(PW_Checks == true && ID_Checks == true){
                    if(Callnum.matches("\\d+")) {
                        member.setUSER_ID(txt_ID.getText());
                        member.setUSER_PW(Password);
                        member.setUSER_Name(txt_Name.getText());
                        member.setUSER_CallNum(txt_PhNum.getText());

                        MemberDAO dao = new MemberDAO();
                        boolean success = dao.Join(member);

                        if (success) {
                            JOptionPane.showMessageDialog(null, "가입완료");

                            Login login = new Login();
                            login.setVisible(true);
                            Join.this.setVisible(false);
                        } else {
                            System.out.println("실패");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "전화번호는 숫자만 입력해주세요");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "아이디 / 비밀번호를 확인해주세요");
                }
            }
        }
    });
}
}