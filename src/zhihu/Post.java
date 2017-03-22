package zhihu;

/**
 * Created by IceWhite on 17/3/20.
 */
public class Post {
    String title;
    String postUrl;
    String content;

    public Post(){
        this.title = "";
        this.postUrl = "";
        this.content = "";
    }

    public Post(String title, String postUrl, String content) {
        this.title = title;
        this.postUrl = postUrl;
        this.content = content;
    }

    @Override
    public String toString() {
        return "title = " + title + "\n" +
                "postUrl = " + postUrl + "\n" +
                "content = " + content + "\n";
    }
}
