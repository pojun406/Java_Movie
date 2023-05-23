package MainPage.AdminPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User_Manage extends JFrame {
    private JPanel ManagePanel;
    private JList list_User;
    private JList list_Watched;
    private JButton btn_Out;

    public User_Manage() {
        setContentPane(ManagePanel);
        ManagePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setTitle("고객 관리 페이지");
        setSize(460,260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        btn_Out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminPage admin = new AdminPage();
                admin.setVisible(true);
                User_Manage.this.setVisible(false);
            }
        });
    }
}
