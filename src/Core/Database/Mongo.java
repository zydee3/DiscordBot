package Core.Database;

import Core.Constants.Constants;
import Core.Database.Collections.GuildCollection;
import Core.Database.Collections.UserCollection;
import Core.Database.Enums.DocumentType;
import Core.Logging;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * @author vince zydea
 */
public class Mongo extends Logging {
    private static final MongoClient client = new MongoClient();
    private static final MongoDatabase database = client.getDatabase(Constants.Mongo.SCHEMA);
    public static final UserCollection userCollection = new UserCollection(database.getCollection(Constants.Mongo.COLLECTION_USER), DocumentType.USER);
    public static final GuildCollection guildCollection = new GuildCollection(database.getCollection(Constants.Mongo.COLLECTION_GUILD), DocumentType.GUILD);
}
