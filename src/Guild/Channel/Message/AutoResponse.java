package Guild.Channel.Message;

import java.util.regex.Pattern;

/**
 * @author vince zydea
 */
public class AutoResponse {
    private String question;
    private String pattern;
    private String answer;
    private Pattern compiledPattern;

    public AutoResponse(String question, String pattern, String answer){
        this.question = question;
        this.pattern = pattern;
        this.answer = answer;
        compiledPattern = Pattern.compile(pattern);
    }

    public boolean matches(String question){
        return compiledPattern.matcher(question).find();
    }

    public String getQuestion(){
        return question;
    }

    public String getAnswer(){
        return answer;
    }

    public String getPattern(){
        return pattern;
    }
}
