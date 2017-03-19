package baidu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IceWhite on 17/3/17.
 */
public class BaiduCrawler {
    static String sendGet(String url){
        String result = "";

        BufferedReader in = null;

        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();

            connection.connect();

            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null){
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (in != null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    static ArrayList<String> RegexString(String targetStr, String patternStr){
        ArrayList<String> resultList = new ArrayList<>();
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(targetStr);
        boolean isFind = matcher.find();
        while (isFind){
            resultList.add(matcher.group(1));
            isFind = matcher.find();
        }
        return resultList;
    }
    
    public static void main(String[] args){
        String url  = "https://www.zhihu.com/explore/recommendations";

        String result = sendGet(url);
        System.out.println(result);
        ArrayList<String> resultList = RegexString(result, "post-link.+?>(.+?)<");
        for(String str: resultList){
            System.out.println(str);
        }
    }


}
