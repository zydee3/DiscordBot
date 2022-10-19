package Core.Database.Collections;

import Guild.User.Enums.Elements;
import Core.Database.Enums.DocumentType;
import Guild.User.Enums.Status;
import Guild.User.User;
import org.bson.Document;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author vince zydea
 */
public class UserCollection extends Collection {
    public UserCollection(com.mongodb.client.MongoCollection collection, DocumentType type) {
        super(collection, type);
    }



    /**
     * Attempts to create the user by finding the record from the database.
     * If the user does not exist, find(record) will pass in null to User.Create(...);
     * If User.Create receives null, the user doesn't exist in the database and creates a new user.
     * @param discordId
     * @param guildId
     * @return
     */
    public User load(long discordId, long guildId){
        Document record = new Document();
        record.append(Elements.ID.name(), discordId).append(Elements.GUILD_ID.name(), guildId);

        return createUser(find(record), discordId, guildId);
    }

    @NotNull
    @Contract("null, _, _ -> new")
    private static User createUser(Document record, long discordId, long guildId){
        if(record != null){
            List<String> statusNames = (List<String>) record.get(Elements.STATUSES.name());
            List<Status> statuses = statusNames.stream().distinct().map(status -> Status.valueOf(status)).collect(Collectors.toList());
            return new User(record.getLong(Elements.ID.name()), guildId, record.getInteger(Elements.LEVEL.name()), record.getInteger(Elements.EXP.name()), record.getInteger(Elements.REWARDS.name()), statuses);
        } else {
            return new User(discordId, guildId, Elements.LEVEL.getDefaultValue(), Elements.EXP.getDefaultValue(), Elements.REWARDS.getDefaultValue(), null);
        }
    }

    /**
     * Saves a list of users.
     * A null check is unnecessary as {@link #save(User)} already performs one.
     * @param users List of users.
     */
    public void save(List<User> users){
        if(users != null && !users.isEmpty()){
            users.forEach(user -> save(user));
        }
    }

    /**
     * Saves a user into the database by invoking replace.
     * Replace replaces a user's record if it exists and inserts it if it doesn't.
     * @param user
     */
    public void save(User user){
        if(user != null){
            replace(new Document(Elements.ID.name(), user.getId()), user.getRecord());
        }
    }


}
