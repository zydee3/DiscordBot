package Guild.Manager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vince zydea
 */
public class BannedWordsManager {
    private List<String> bannedWords = new ArrayList<>();

    public void add(@NotNull String word){
        if(!word.isEmpty() && !bannedWords.contains(word.toLowerCase())){
            bannedWords.add(word);
        }
    }

    /**
     * Adds a list of banned words to the banned words list.
     * @param words Word to be added.
     */
    public void remove(@NotNull List<String> words){
        words.forEach(word -> {
            if(bannedWords.contains(word)) {
                bannedWords.remove(word);
            }
        });
    }

    public boolean contains(String word){
        word = word.toLowerCase();
        return bannedWords.contains(word);
    }

    public boolean contains(String ... words){
        for(String word : words){
            if(contains(word)){
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the list of banned words.
     * @return List of banned words.
     */
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        int size = bannedWords.size();
        for(int i = 0; i < size; i++){
            builder.append("**").append(i + 1).append("**. ").append(bannedWords.get(i));
            if(i < (size - 1)){
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public List<String> getList(){
        return bannedWords;
    }

    /**
     * Check if a sentence contains a banned word.
     * @param sentence Sentence to be checked.
     * @return True or false.
     */
    public boolean containsBannedWord(@NotNull String sentence){
        for(String word : sentence.split(" ")){
            if(bannedWords.contains(word)){
                return true;
            }
        }
        return false;
    }
}
