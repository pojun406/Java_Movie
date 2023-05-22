package MainPage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import DataBase.DBConnect;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class test {
    public static void main(String[] args) {
        String key = "6fec5f3e4ac7862b9846d39babec533c";

        LocalDate now = LocalDate.now();
        LocalDate yesterday = now.minusDays(1);

        String year = String.valueOf(yesterday.getYear());
        String monthValue = String.format("%02d", yesterday.getMonthValue());
        String dayOfMonth = String.valueOf(yesterday.getDayOfMonth());

        String targetDt = year + monthValue + dayOfMonth;

        System.out.println(targetDt);
    }
}
