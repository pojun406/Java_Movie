package MainPage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import DAODTO.Member.MemberDAO;
import DAODTO.Movie.MovieDAO;
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
        MovieDAO dao = new MovieDAO();

        dao.GETAPI_Poster("영화제목");
    }
}
