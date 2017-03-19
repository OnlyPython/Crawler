package zhihu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IceWhite on 17/3/18.
 */
public class Zhihu {
    String question;
    String zhihuUrl;
    List<String> answers;

    public Zhihu() {
        this.question = "";
        this.zhihuUrl = "";
        answers = new ArrayList<String>();
    }

    @Override
    public String toString() {
        return  "question = " + question + '\n' +
                "zhihuUrl = " + zhihuUrl + '\n' +
                "answers = " + answers +
                "\n";
    }
}
