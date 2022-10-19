package Core.Database.Collections;

import Core.Database.Enums.DocumentType;
import Guild.Enums.Elements;
import Guild.Guild;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.List;
import java.util.Map;

/**
 * @author vince zydea
 */
public class GuildCollection extends Collection {
    public GuildCollection(MongoCollection collection, DocumentType type) {
        super(collection, type);
    }

    /**
     * Creates and returns created guild object.
     * @param id id of the guild.
     * @return new guild object.
     */
    public Guild load(Long id){
        return createGuild(find(new Document(Elements.ID.name(), id)), id);
    }

    public Guild createGuild(Document record, long id){
        Guild guild = new Guild(id);
        if(record != null) {
            List<String> words = (List<String>) record.get(Elements.BANNED_WORDS.name());
            if(words != null && !words.isEmpty()){
                words.forEach(word -> guild.BannedWordsManager.add(word));
            }


            guild.AutoResponseManager.add((List<String>) record.get(Elements.AUTO_REPONSES.name()));
            guild.SettingsManager.add((Map<String, Integer>) record.get(Elements.SETTINGS.name()));
        }

        return guild;
    }

    /**
     * Saves a single guild object by first deleting all existing records then inserting a single record. In case of accident record duplication.
     * @param guild single guild object being saved.
     */
    public void save(Guild guild){
        if(guild != null){
            replace(new Document(Elements.ID.name(), guild.getId()), guild.getRecord());
        }
    }
}
