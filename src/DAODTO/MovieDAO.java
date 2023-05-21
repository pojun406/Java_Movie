package DAODTO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class MovieDAO {
    //영화제목, 장르, 감독, 출연배우, 상영시간, 줄거리등
    public void GetAPI_MovieCD(){

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String key = "6fec5f3e4ac7862b9846d39babec533c";
        String result = "";

        LocalDate now = LocalDate.now();

        String year = String.valueOf(now.getYear());
        String monthValue = String.valueOf(now.getMonthValue());
        String dayOfMonth = String.valueOf(now.getDayOfMonth());

        String targetDt = year+monthValue+dayOfMonth;

        try{
            URL url = new URL("https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key="
                    + key + "&targetDt=" + targetDt);

            BufferedReader bf;

            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONObject BoxOfficeResult = (JSONObject)jsonObject.get("boxOfficeResult");
            JSONArray BoxOfficeList = (JSONArray)BoxOfficeResult.get("dailyBoxOfficeList");

            for (Object movieObj : BoxOfficeList) {
                JSONObject movie = (JSONObject) movieObj;
                String movieCd = (String) movie.get("movieCd");
                System.out.println(movieCd);

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        String key = "6fec5f3e4ac7862b9846d39babec533c";
        String result = "";

        LocalDate now = LocalDate.now();

        String year = String.valueOf(now.getYear());
        String monthValue = String.valueOf(now.getMonthValue());
        String dayOfMonth = String.valueOf(now.getDayOfMonth());

        String targetDt = year+monthValue+dayOfMonth;

        try{
            URL url = new URL("https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=6fec5f3e4ac7862b9846d39babec533c&targetDt=20230521");

            BufferedReader bf;

            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            result = bf.readLine();


// dailyBoxOfficeList 배열의 각 영화 정보에 접근


                // movieCd 값을 데이터베이스에 저장하는 작업 수행
                // ...
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }


}
