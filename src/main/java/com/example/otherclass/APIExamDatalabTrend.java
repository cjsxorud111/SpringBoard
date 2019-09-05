package com.example.otherclass;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class APIExamDatalabTrend {
	final static Logger logger = LoggerFactory.getLogger(APIExamDatalabTrend.class);
	public static void main(String[] args) {
        String clientId = "EDX32wQH35PNQEQMLZoi";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "2tYxBjJsLb";//애플리케이션 클라이언트 시크릿값";
        try {
            String apiURL = "https://openapi.naver.com/v1/datalab/search";
            String body = 
            		"{\"startDate\":\"2017-01-01\""
            		+ ",\"endDate\":\"2017-04-30\""
            		+ ",\"timeUnit\":\"month\""
            		+ ",\"keywordGroups\":[{\"groupName\":\"test\""
            		+ ",\"keywords\":[\"test\""
            		+ ",\"english\""
            		+ ",\"wind\""
            		+ ",\"toy\""
            		+ ",\"juice\"]}]"
            		+ ",\"device\":\"pc\""
            		+ ",\"ages\":[\"1\""
            		+ ",\"2\"]"
            		+ ",\"gender\":\"f\"}";
			logger.error("test","에러발생");

            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            con.setRequestProperty("Content-Type", "application/json");

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(body);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
            }
			logger.error("test","에러발생");

            String inputLine;
            logger.error("test","에러발생");
            StringBuffer response = new StringBuffer();
            logger.error("test","에러발생");
            while ((inputLine = br.readLine()) != null) {
            	logger.error("test","에러발생");
                response.append(inputLine);
            }
            logger.error("test","에러발생");
            br.close();
            logger.error("test","에러발생");
            System.out.println(response.toString());

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
