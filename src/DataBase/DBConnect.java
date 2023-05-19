package DataBase;

import java.sql.*;

public class DBConnect {
    public static void main(String[] args) {
        Connection con = null;

        String url = "jdbc:mariadb://localhost:3306/";
        String database = "Cinema";
        String user_name = "root";
        String user_password = "root";

        try{
            Class.forName("org.mariadb.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.err.println("드라이버 로딩 오류 : " + e.getMessage());
            e.printStackTrace();
        }

        try{
            con = DriverManager.getConnection(url + database
                    , user_name,user_password);
            System.out.println("연결 성공");
        }catch (SQLException e) {
            System.err.println("에러 내용 : " + e.getMessage());
            e.printStackTrace();
        }

        try{
            if(con != null) con.close();
        }catch (SQLException e) {}
    }
}