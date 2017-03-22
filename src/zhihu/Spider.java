package zhihu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Spider {
    static String SendGet(String url){
        String result = "";

        BufferedReader in = null;

        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4)");
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

    // 获取知乎(推荐)文章
    static ArrayList<Post> GetZhihuPosts(String content){
        // 保存文章的列表
        ArrayList<Post> postList = new ArrayList<>();

        // 获取文章标题的正则
        Pattern postPattern = Pattern.compile("post-link.+?>(.+?)<");
        Matcher postMatcher = postPattern.matcher(content);
        // 获取文章链接的正则
        Pattern postUrlPattern = Pattern.compile("post-link.+?href=\"(.+?)\"");
        Matcher postUrlMatcher = postUrlPattern.matcher(content);

        boolean isFind = postMatcher.find() && postUrlMatcher.find();

        while (isFind){
            Post postTmp = new Post();
            postTmp.title = postMatcher.group(1);
            postTmp.postUrl = postUrlMatcher.group(1);
            postList.add(postTmp);
            isFind = postMatcher.find() && postUrlMatcher.find();
        }
        return postList;
    }

    // 获取知乎(推荐)问题
    static ArrayList<Question> GetZhihuQuestions(String content){
        // 保存问题的列表
        ArrayList<Question> questionList = new ArrayList<>();

        // 获取问题的正则
//        Pattern questionPattern = Pattern.compile("question_link.+?>(.+?)<");
//        Matcher questionMatcher = questionPattern.matcher(content);
        // 获取问题链接的正则
        Pattern questionUrlPattern = Pattern.compile("question_link.+?href=\"(.+?)\"");
        Matcher questionUrlMatcher = questionUrlPattern.matcher(content);

        boolean isFind = questionUrlMatcher.find();

        while (isFind){
            Question questionTmp = new Question(questionUrlMatcher.group(1));
            questionList.add(questionTmp);
            isFind = questionUrlMatcher.find();
        }
        return questionList;
    }
}
