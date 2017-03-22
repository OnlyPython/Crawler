package zhihu;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Question {
    String question;
    String questionUrl;
    String questionDescription;
    List<String> answers;

    public Question() {
        this.question = "";
        this.questionUrl = "";
        this.questionDescription = "";
        answers = new ArrayList<String>();
    }

    public Question(String questionUrl) {
        String questionID = getQuestionID(questionUrl);
        this.questionUrl = "https://www.zhihu.com/question/" + questionID;
        answers = new ArrayList<>();
        System.out.println("正在抓取：" + this.questionUrl);
        String questionContent = Spider.SendGet(this.questionUrl);
        // 匹配问题标题
        Pattern questionPattern = Pattern.compile("QuestionHeader-title\">(.+?)<");
        Matcher questionMatcher = questionPattern.matcher(questionContent);
        if(questionMatcher.find()){
            this.question = questionMatcher.group(1);
        }else{
            // 旧版知乎问题页面正则
            questionPattern = Pattern.compile("h2.+?zm-editable-content\">(.+?)<");
            questionMatcher = questionPattern.matcher(questionContent);
            questionMatcher.find();
            this.question = questionMatcher.group(1);
        }
        // 匹配问题描述
        Pattern questionDescriptionPattern = Pattern.compile("RichText\">(.+?)<");
        Matcher questionDescriptionMatcher = questionDescriptionPattern.matcher(questionContent);
        if(questionDescriptionMatcher.find()){
            this.questionDescription = questionDescriptionMatcher.group(1);
        }else {
            questionDescriptionPattern = Pattern.compile("h2.+?zm-editable-content\">(.+?)<");
            questionDescriptionMatcher = questionDescriptionPattern.matcher(questionContent);
            questionDescriptionMatcher.find();
            this.questionDescription = questionDescriptionMatcher.group(1);
        }
        this.answers = new ArrayList<>();
        // 获取答案
        Pattern answerPattern = Pattern.compile("RichText CopyrightRichText-richText\">(.+?)<");
        Matcher answerMatcher = answerPattern.matcher(questionContent);
        while (answerMatcher.find()){
            answers.add(answerMatcher.group(1));
        }

    }

    String getQuestionID(String url){
        Pattern questionIDPattern = Pattern.compile("question/(.+?)/");
        Matcher questionIDMatcher = questionIDPattern.matcher(url);
        if(questionIDMatcher.find()){
            String questionID = questionIDMatcher.group(1);
            return questionID;
        }else{
            Pattern questionIDPattern2 = Pattern.compile("question/(.+)");
            Matcher questionIDMatcher2 = questionIDPattern2.matcher(url);
            if(questionIDMatcher2.find()){
                String questionID = questionIDMatcher2.group(1);
                return questionID;
            }
        }
        return "";
    }

    @Override
    public String toString() {
        return  "question = " + question + '\n' +
                "questionDescription = " +questionDescription + '\n' +
                "questionUrl = " + questionUrl + '\n' +
                "answers = " + answers +
                "\n";
    }
}
