package Core.Storage;

import Core.Database.Mongo;
import Core.Logging;
import Guild.User.User;
import Tools.TaskExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author vince zydea
 */
public class UserStorage extends Logging {
    private static final Logger logger = LogManager.getLogger(UserStorage.class);
    private static Map<Long, User> users = new HashMap<>();
    private static final long saveDelay = 10 * 60 * 1000;


    public UserStorage(){
        TaskExecutor.executeRepeating(() -> save(), saveDelay, saveDelay);
    }

    /**
     * Returns a user.
     * If the user exists in the storage, return it.
     * Else, load the user from the database then add it to {@link #users} and return it.
     * @param id
     * @return
     */
    public User get(long id, long guildId){
        try {
            User user = users.get(id);
            if (user == null) {
                user = Mongo.userCollection.load(id, guildId);
                users.put(id, user);
                logger.info("Loaded User(id: {}) to storage.", id);
            }

            return user;
        } catch (Exception exception){
            exception.printStackTrace();
            exceptionCaught(exception);
            return null;
        }
    }

    public void save(){
        Mongo.userCollection.save(users.values().stream().collect(Collectors.toList()));
        users.clear();
    }
}
