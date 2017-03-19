package zhihu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IceWhite on 17/3/18.
 */
public class Spider {
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

    static ArrayList<Zhihu> getZhihu(String content){
        ArrayList<Zhihu> results = new ArrayList<>();
        // 获取问题的正则
        Pattern questionPattern = Pattern.compile("post-link.+?>(.+?)<");
        Matcher questionMatcher = questionPattern.matcher(content);
        // 获取链接的正则
        Pattern urlPattern = Pattern.compile("post-link.+?href=\"(.+?)\"");
        Matcher urlMatcher = urlPattern.matcher(content);

        boolean isFind = questionMatcher.find() && urlMatcher.find();

        while (isFind){
            Zhihu zhihuTmp = new Zhihu();
            zhihuTmp.question = questionMatcher.group(1);
            zhihuTmp.zhihuUrl = urlMatcher.group(1);
            results.add(zhihuTmp);
            isFind = questionMatcher.find() && urlMatcher.find();
        }
        return results;

    }
}
