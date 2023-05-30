package MainPage.LoginPage;

import DAODTO.Member.MemberDAO;
import DAODTO.Member.MemberDTO;

import javax.swing.*;
import java.awt.*;
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
    public String ID;

    public FindPW(){
        setContentPane(FindPWPanel);
        FindPWPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setTitle("비밀번호 찾기 페이지");
        setSize(450,220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        MemberDTO member = new MemberDTO();
        btn_Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.setVisible(true);
                FindPW.this.setVisible(false);

            }
        });
        btn_FindPW.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MemberDAO dao = new MemberDAO();
                ID = txt_ID.getText();

                if(txt_Name.getText().equals("") ||
                        txt_PhNum.getText().equals("") ||
                        txt_ID.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "빈칸이 있습니다.");
                }else{
                    member.setUSER_Name(txt_Name.getText());
                    member.setUSER_CallNum(txt_PhNum.getText());
                    member.setUSER_ID(txt_ID.getText());

                    if(dao.FindPW(member)){
                        Dialog_ChPw chpw = new Dialog_ChPw(ID);
                        chpw.setVisible(true);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "사용자가 없습니다.");
                    }
                }
            }
        });
    }
}
