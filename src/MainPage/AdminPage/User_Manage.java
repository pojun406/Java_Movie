package MainPage.AdminPage;

import DAODTO.Member.MemberDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class User_Manage extends JFrame {
    MemberDAO dao = new MemberDAO();
    private JList<String> User_List;
    private JList<String> User_Watched;
    private JPanel ManagePanel;

    List<String> UserList = new ArrayList<>();
    String Watched = "";

    DefaultListModel<String> listModel = new DefaultListModel<>();

    private JButton btn_Out;
    private JButton btn_Check;

    public User_Manage() {
        btn_Out = new JButton("나가기");
        btn_Out.setBounds(20, 5, 70, 30);
        btn_Check = new JButton("조회하기");
        btn_Check.setBounds(20, 20, 70, 30);

        UserList = dao.getUser();

        for (String user : UserList) {
            listModel.addElement(user);
        }

        User_List = new JList<>(listModel);
        User_Watched = new JList<>();

        Watched = dao.getUserWatched(User_List.getSelectedValue());
        System.out.println(Watched);

        ManagePanel = new JPanel();

        setContentPane(ManagePanel);
        ManagePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setTitle("고객 관리 페이지");
        setSize(460, 260);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        JScrollPane userlist = new JScrollPane(User_List);
        JScrollPane watched = new JScrollPane(User_Watched);
        ManagePanel.add(userlist, BorderLayout.CENTER);
        ManagePanel.add(watched, BorderLayout.CENTER);
        add(btn_Out);
        add(btn_Check);

        btn_Check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] watched_list = watched.toString().split(",");


            }
        });


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
