package Guild.Manager;

import Core.Logging;
import Guild.Channel.Message.AutoResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vince zydea
 */
public class AutoResponseManager extends Logging {
    private final String separator = "&";
    private List<AutoResponse> responses = new ArrayList<>();

    public void add(String question, String pattern, String answer) {
        if (indexOf(question) > -1) {
            responses.add(new AutoResponse(question, pattern, answer));
        }
    }

    public void add(List<String> record){
        if(record == null || record.isEmpty()){
            return;
        }

        record.forEach(response -> {
            String[] elements = response.split(separator);
            if(elements.length == 3){
                add(elements[0], elements[1], elements[2]);
            }
        });
    }

    public String get(String question){
        int index = indexOf(question);
        return(index > -1 ? responses.get(index).getAnswer() : "");
    }

    public void remove(String question){
        int index = indexOf(question);
        if(index > -1) {
            responses.remove(index);
        }
    }

    public int indexOf(String question){
        for(int i = 0; i < responses.size(); i++){
            if(responses.get(i).matches(question)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString(){
        int size = responses.size();
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < size; i++){
            builder.append("**Question**: ").append(responses.get(i).getQuestion()).append("\n**Answer**: ").append(responses.get(i).getAnswer()).append((i < (size - 1)) ? "\n" : "");
        }
        return builder.toString();
    }

    public List<String> getRecord(){
        List<String> responseStrings = new ArrayList<>();
        for(AutoResponse response : responses){
            StringBuilder builder = new StringBuilder();
            builder.append(response.getQuestion()).append(separator).append(response.getPattern()).append(separator).append(response.getAnswer());
            responseStrings.add(builder.toString());
        }
        return responseStrings;
    }

    public List<AutoResponse> getList(){
        return responses;
    }
}
