package MainPage.LoginPage;

import MainPage.LoginPage.MemberDTO;
import DataBase.DBConnect;
import org.w3c.dom.ls.LSOutput;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MemberDAO{
    public boolean Join(MemberDTO dto) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = new DBConnect().getConn();
            String query = "select * from user";
            pstmt = conn.prepareStatement(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
