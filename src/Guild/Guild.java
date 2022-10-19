package Guild;

import Guild.Enums.Elements;
import Core.Storage.UserStorage;
import Guild.Manager.AutoResponseManager;
import Guild.Manager.BannedWordsManager;
import Guild.Manager.SettingsManager;
import org.bson.Document;

/**
 * @author vince zydea
 */
public class Guild {
    private Long id;
    private UserStorage users = new UserStorage();;

    public final AutoResponseManager AutoResponseManager = new AutoResponseManager();
    public final BannedWordsManager BannedWordsManager = new BannedWordsManager();
    public final SettingsManager SettingsManager = new SettingsManager();


    public Guild(Long id){
        this.id = id;
    }

    /**
     * Getter for Guild.id.
     * @return id.
     */
    public Long getId(){
        return id;
    }

    /**
     * Retrieves user storage.
     * @return User storage.
     */
    public UserStorage getUserStorage(){
        return users;
    }

    public Document getRecord(){
        Document record = new Document(Guild.User.Enums.Elements.ID.name(), id);
        record.append(Elements.ID.name(), id)
                .append(Elements.BANNED_WORDS.name(), BannedWordsManager.getList())
                .append(Elements.AUTO_REPONSES.name(), AutoResponseManager.getRecord())
                .append(Elements.SETTINGS.name(), SettingsManager.getSettings());

        return record;
    }
}
