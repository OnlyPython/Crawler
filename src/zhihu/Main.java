package zhihu;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        String url = "https://www.zhihu.com/explore/recommendations";
        String content = Spider.SendGet(url);
        ArrayList<Question> question = Spider.GetZhihuQuestions(content);
        System.out.println(question);
//        ArrayList<Post> posts = Spider.GetZhihuPosts(content);
//        System.out.println(posts);
    }
}
