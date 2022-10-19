package Core.Storage;

import Core.Bot;
import Core.Database.Mongo;
import Guild.Guild;
import Tools.TaskExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vince zydea
 */
public class GuildStorage {
    private static final Logger logger = LogManager.getLogger(GuildStorage.class);
    private static final long saveDelay = 10 * 1000;
    /**
     * Guild Storage
     * @key guild id (long)
     * @value guild object (Guild.Guild)
     */
    private static Map<Long, Guild> guilds = new HashMap<>();

    static {
        TaskExecutor.executeRepeating(() -> save(false), saveDelay, saveDelay);
    }


    /**
     * Retrieves a guild from the guild storage.
     * @param id Id of guild to be retrieved.
     * @return guild.
     */
    @NotNull
    public static Guild get(long id){
        Guild guild = guilds.get(id);
        if(guild == null){
            guild = Mongo.guildCollection.load(id);
            guilds.put(id, guild);
            logger.info("Loaded guild(id: {}) to storage.", id);
        }

        return guild;
    }

    /**
     * See {@link #guilds}
     * @return Number of guilds in guild collections.
     */
    public static int count(){
        return guilds.size();
    }

    /**
     * Saves all the guilds to the database.
     * See saved objects {@link #guilds}
     */
    public static void save(boolean removeFromStorage){
        guilds.forEach((id, guild) -> {
           Mongo.guildCollection.save(guild);
           if(removeFromStorage){
               guilds.remove(id);
           }
        });
    }
}
