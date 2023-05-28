package MainPage.AdminPage;

import DAODTO.Member.MemberDAO;

import javax.swing.*;
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
    private JLabel lb_Payed;

    public User_Manage() {
        btn_Out = new JButton("나가기");
        btn_Out.setBounds(30, 170, 150, 30);
        btn_Check = new JButton("조회하기");
        btn_Check.setBounds(180, 170, 150, 30);
        lb_Payed = new JLabel("총 지불액");
        lb_Payed.setBounds(350,170,100,30);

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
        ManagePanel.setLayout(null);
        ManagePanel.add(userlist); // User_List를 서쪽에 배치
        userlist.setBounds(30,20,100,150);
        ManagePanel.add(watched); // User_Watched를 중앙에 배치
        watched.setBounds(130,20,300,150);
        ManagePanel.add(btn_Out); // 버튼을 북쪽에 배치
        ManagePanel.add(btn_Check); // 버튼을 남쪽에 배치
        ManagePanel.add(lb_Payed); // lb_Payed를 동쪽에 배치


        btn_Check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedUser = User_List.getSelectedValue(); // 선택된 사용자

                if (selectedUser != null) {
                    String[] watched = null;
                    String watchedList = dao.getUserWatched(selectedUser); // 선택된 사용자의 시청 목록 조회
                    if(watchedList != null){
                        watched = watchedList.split(",");
                    }else{
                        JOptionPane.showMessageDialog(null, "나가");
                    }

                    DefaultListModel<String> watchedListModel = new DefaultListModel<>();
                    for (String watchedMovie : watched) {
                        watchedListModel.addElement(watchedMovie);
                    }
                    User_Watched.setModel(watchedListModel); // User_Watched에 시청 목록 표시

                    int totalAmount = dao.getUserTotalPay(selectedUser); // 선택된 사용자의 총 결제 금액 조회
                    lb_Payed.setText(totalAmount + "원"); // lb_Payed에 결제 금액 표시
                }
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
