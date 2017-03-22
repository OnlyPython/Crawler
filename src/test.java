import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IceWhite on 17/3/20.
 */
public class test {
    public static void main(String[] args) {
        String url = "<h2 class=\"zm-item-title\"><span class=\"zm-editable-content\">怎样从零基础变成懂车达人？</span></h2>";
        Pattern questionIDPattern2 = Pattern.compile("h2.+?zm-editable-content\">(.+?)<");
        Matcher questionIDMatcher2 = questionIDPattern2.matcher(url);
        String questionID = null;
        if(questionIDMatcher2.find()){
            try {
                questionID = questionIDMatcher2.group(1);
            } catch (Exception e) {
                System.out.println(url);
                e.printStackTrace();
            }
        }

        System.out.println(questionID);
    }
}
