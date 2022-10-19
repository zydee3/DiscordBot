package Core.Database.Collections;

import Core.Database.Enums.DocumentType;
import Core.Logging;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vince zydea
 */
public abstract class Collection extends Logging {
    private com.mongodb.client.MongoCollection collection;
    private DocumentType type;

    public Collection(com.mongodb.client.MongoCollection collection, DocumentType type){
        this.collection = collection;
        this.type = type;
    }

    /**
     * Finds a record of the referenced document in the collection.
     * @param document Document reference to be found.
     * @return First found document in the collection.
     */
    public Document find(Document document){
        try {
            com.mongodb.client.MongoCursor cursor = collection.find(document).cursor();
            if(cursor.hasNext()){
                return (Document) cursor.next();
            }
        } catch (Exception exception){
            exceptionCaught(exception);
        }

        return null;
    }

    /**
     * Returns a list of every document in the collection.
     * @return List of documents in collection.
     */
    public List<Document> findAll(){
        try {
            List<Document> records = new ArrayList<>();
            com.mongodb.client.MongoCursor cursor = collection.find().cursor();
            while(cursor.hasNext()){
                records.add((Document) cursor.next());
            }
            return records;
        } catch (Exception exception){
            exceptionCaught(exception);
        }

        return null;
    }

    /**
     * Ensures only a single document of object exists.
     * If there is zero record, insert the update document.
     * If there is a single record, replace it with the update document.
     * If more than one exists, which shouldn't, delete all records and insert the update.
     * @param reference Document being found
     * @param update
     */
    public void replace(Document reference, Document update){
        try {
            long total = collection.countDocuments(reference);

            if(total == 1){
                collection.updateOne(
                        Filters.eq("_id", find(reference).get("_id")),
                        new Document("$set", update),
                        new UpdateOptions().upsert(true)
                );
            } else if(total > 1){
                collection.deleteMany(reference);
                collection.insertOne(update);
            } else {
                collection.insertOne(update);
            }
        } catch (Exception exception){
            exceptionCaught(exception);
        }
    }
}
