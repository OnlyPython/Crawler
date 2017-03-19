package zhihu;

import java.util.ArrayList;

/**
 * Created by IceWhite on 17/3/18.
 */
public class Main {
    public static void main(String[] args) {
        String url = "https://www.zhihu.com/explore/recommendations";
        String content = Spider.sendGet(url);
        ArrayList<Zhihu> zhihu = Spider.getZhihu(content);
        System.out.println(zhihu);
    }
}
