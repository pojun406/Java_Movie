package MainPage.LoginPage;

import DAODTO.Member.MemberDAO;
import DAODTO.Member.MemberDTO;

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

        MemberDTO member = new MemberDTO();
        btn_Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.setVisible(true);
                FindID.this.setVisible(false);
            }
        });
        btn_FindID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MemberDAO dao = new MemberDAO();

                if(txt_Name.getText().equals("") || txt_PhNum.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "빈칸이 있습니다.");
                }
                else{
                    member.setUSER_Name(txt_Name.getText());
                    member.setUSER_CallNum(txt_PhNum.getText());

                    String Find = dao.FindID(member);

                    if(Find != ""){
                        JOptionPane.showMessageDialog(null, "아이디는 '" + dao.FindID(member) + "'입니다.");
                    }else{
                        JOptionPane.showMessageDialog(null, "아이디를 찾지 못했습니다.");
                    }
                }
            }
        });
    }
}
