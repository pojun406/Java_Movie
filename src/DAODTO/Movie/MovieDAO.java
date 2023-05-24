package DAODTO.Movie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import DataBase.DBConnect;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {
    //영화제목, 장르, 감독, 출연배우, 상영시간, 줄거리등
    public String getTitle(int i){
        String title = "";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            return null;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean GETAPI_Poster(String title) {
        MovieDAO dao = new MovieDAO();
        Connection conn = null;
        PreparedStatement pstmt = null;

        String key = "U46C5834J59T322L0028";

        try {
            conn = new DBConnect().getConn();
            String add_other = "UPDATE movie_detail SET description = ?, Poster_URL = ? WHERE Movie_Title = ?";

            pstmt = conn.prepareStatement(add_other);

            String encoding = URLEncoder.encode(title);


            URL url = new URL("https://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp?collection=kmdb_new2&ServiceKey="
                    + key + "&detail=Y&query=" + encoding);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                String plots = "plots";
                String posters = "posters";
                int targetplot = -1;
                int targetposter = -1;

                String posterimg = null;
                String plotText = null;

                // JSON 파싱
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(response.toString());
                JSONArray dataArray = (JSONArray) jsonObject.get("Data");
                JSONObject result = (JSONObject) dataArray.get(0);
                JSONArray resultArray = (JSONArray) result.get("Result");// 여기서는 2023년만
                for (Object obj : resultArray) {
                    JSONObject resultobj = (JSONObject) obj;
                    String mod = (String) resultobj.get("modDate");
                    String year = mod.substring(0, 3);
                    JSONObject plotsArray = (JSONObject) resultArray.get(0); // 여기서 poster도 가져와야함 포스터가 비어있는건 prodYear이 많은새끼들 뿐임
                    //----------------------plot 가져오는 부분
                    JSONObject objplots = (JSONObject) plotsArray.get("plots");
                    JSONArray plotArray = (JSONArray) objplots.get("plot");
                    JSONObject firstPlot = (JSONObject) plotArray.get(0);
                    plotText = (String) firstPlot.get("plotText");
                    //----------------------poster 가져오는 부분
                    String objposters = (String) plotsArray.get("posters");
                    String[] poster = objposters.split("\\|");
                    posterimg = poster[0];
                }
                pstmt.setString(1, plotText);
                pstmt.setString(2, posterimg);
                pstmt.setString(3, title);

                pstmt.executeUpdate();
                return true;
            } else {
                System.out.println("HTTPERROR : " + responseCode);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean GetAPI_MovieCD() {

        Connection conn = null;
        PreparedStatement delpstmt = null;
        PreparedStatement inspstmt = null;

        String key = "6fec5f3e4ac7862b9846d39babec533c";
        String result = "";

        LocalDate now = LocalDate.now();
        LocalDate yesterday = now.minusDays(1);

        String year = String.valueOf(yesterday.getYear());
        String monthValue = String.format("%02d", yesterday.getMonthValue());
        String dayOfMonth = String.valueOf(yesterday.getDayOfMonth());

        String targetDt = year + monthValue + dayOfMonth;

        try {
            conn = new DBConnect().getConn();

            String Delquery = "DELETE FROM movie";
            String Insertquery = "INSERT INTO movie(Movie_Num, Movie_Title) VALUES (?, ?)";

            delpstmt = conn.prepareStatement(Delquery);
            delpstmt.executeQuery();

            URL url = new URL("https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key="
                    + key + "&targetDt=" + targetDt);

            BufferedReader bf;

            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            JSONObject BoxOfficeResult = (JSONObject) jsonObject.get("boxOfficeResult");
            JSONArray BoxOfficeList = (JSONArray) BoxOfficeResult.get("dailyBoxOfficeList");


            inspstmt = conn.prepareStatement(Insertquery);

            for (Object movieObj : BoxOfficeList) {
                JSONObject movie = (JSONObject) movieObj;
                String movieCd = (String) movie.get("movieCd");
                String movieNm = (String) movie.get("movieNm");

                inspstmt.setString(1, movieCd);
                inspstmt.setString(2, movieNm);
                inspstmt.executeUpdate();
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (delpstmt != null) delpstmt.close();
                if (inspstmt != null) inspstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean GET_MOVIEDetail() {
        Connection conn = null;
        PreparedStatement delpstmt = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        PreparedStatement inspstmt = null;

        boolean r = false;

        String key = "6fec5f3e4ac7862b9846d39babec533c";
        String result = "";

        try {
            conn = new DBConnect().getConn();
            String Delquery = "DELETE FROM movie_detail";
            String selquery = "SELECT Movie_Num FROM movie";
            delpstmt = conn.prepareStatement(Delquery);
            delpstmt.executeQuery();
            pstmt = conn.prepareStatement(selquery);
            rs = pstmt.executeQuery();

            String Insquery = "INSERT INTO movie_detail(Movie_Num, Movie_Title, Genre, Director, Actor, Running_Time, Description)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";

            inspstmt = conn.prepareStatement(Insquery);

            while (rs.next()) {
                String movieNum = rs.getString("Movie_Num");
                URL url = new URL("https://kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key="
                        + key + "&movieCd=" + movieNum);

                BufferedReader bf;

                bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

                result = bf.readLine();

                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
                JSONObject movieInfoResult = (JSONObject) jsonObject.get("movieInfoResult");
                JSONObject movieInfo = (JSONObject) movieInfoResult.get("movieInfo");


                String Movie_Num = (String) movieInfo.get("movieCd"); // 영화 번호
                String Movie_Title = (String) movieInfo.get("movieNm"); // 영화 이름

                JSONArray Genre_Array = (JSONArray) movieInfo.get("genres");
                String Genre = "";
                for (int i = 0; i < Genre_Array.size(); i++) {
                    JSONObject genres_genreNm = (JSONObject) Genre_Array.get(i);
                    Genre += genres_genreNm.get("genreNm") + ","; // 장르
                }

                String Director_Name;

                JSONArray directors = (JSONArray) movieInfo.get("directors");
                if (directors.size() > 0) {
                    JSONObject directors_people = (JSONObject) directors.get(0);
                    Director_Name = (String) directors_people.get("peopleNm"); // 감독 이름
                } else {
                    Director_Name = "empty"; // 감독 이름이 없을 시
                }

                JSONArray actors = (JSONArray) movieInfo.get("actors");
                String Actor_Name = "";
                for (int i = 0; i < actors.size(); i++) {
                    JSONObject actors_info = (JSONObject) actors.get(i);
                    if (actors_info.get("peopleNm").equals("")) {
                        Actor_Name += (String) actors_info.get("peopleNmEn") + ",";
                    } else {
                        Actor_Name += (String) actors_info.get("peopleNm") + ","; // 배우이름
                    }
                }

                String Running_Time = (String) movieInfo.get("showTm");

                String Description = ""; // 줄거리 // API 추가 예정

                inspstmt.setString(1, Movie_Num);
                inspstmt.setString(2, Movie_Title);
                inspstmt.setString(3, Genre);
                inspstmt.setString(4, Director_Name);
                inspstmt.setString(5, Actor_Name);
                inspstmt.setString(6, Running_Time);
                inspstmt.setString(7, Description);

                inspstmt.executeUpdate();
                GETAPI_Poster(Movie_Title);

                boolean check = GETAPI_Poster(Movie_Title); // 나머지 줄거리랑 poster url도 받아오는 함수까지

                if (!check) {
                    r = false;
                } else {
                    r = true;
                }
            }
            return r;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (inspstmt != null) inspstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}