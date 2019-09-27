import com.mongodb.ConnectionString;
import com.mongodb.MongoException;
import com.mongodb.MongoWriteConcernException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.*;
import org.bson.Document;

public class MongoDBDAO implements WeatherstationDAO
{
    @Override
    public String InsertToDatabase(String jsonMessage)
    {
        ConnectionString connectionString =
                new ConnectionString("mongodb+srv://testuser:testuser@nitrogensensortest-c94pp.azure.mongodb.net/AgricircleDB");
        MongoClient mongoClient = MongoClients.create(connectionString);

        MongoDatabase database = mongoClient.getDatabase("AgricircleDB");

        MongoCollection<Document> collection = database.getCollection("Weatherstation1");

        try {
            collection.insertOne(Document.parse(jsonMessage));
            return "Database updated";
        }
        catch(MongoException mwe) {
            //  Block of code to handle errors
            return mwe.getMessage();
        }
        finally
        {
            mongoClient.close();
        }
    }

    @Override
    public String getAllFromDatabase()
    {
        ConnectionString connectionString =
                new ConnectionString("mongodb+srv://testuser:testuser@nitrogensensortest-c94pp.azure.mongodb.net/AgricircleDB");
        MongoClient mongoClient = MongoClients.create(connectionString);

        MongoDatabase database = mongoClient.getDatabase("AgricircleDB");

        MongoCollection<Document> collection = database.getCollection("Weatherstation1");

        try {
            String returnString = "";
            FindIterable<Document> iterable = collection.find();
            for (Document document : iterable) {
                returnString = returnString + document.toJson();
            }
            return returnString;
        }
        catch(MongoException mwe ) {
            //  Block of code to handle errors
            return mwe.getMessage();
        }
        finally
        {
            mongoClient.close();
        }
    }
}
