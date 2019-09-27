import com.mongodb.ConnectionString;
import com.mongodb.MongoException;
import com.mongodb.MongoWriteConcernException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;

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
    public String findAllFromDatabase()
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

    public String findOneFromDatabase(String key)
    {
        ConnectionString connectionString =
                new ConnectionString("mongodb+srv://testuser:testuser@nitrogensensortest-c94pp.azure.mongodb.net/AgricircleDB");
        MongoClient mongoClient = MongoClients.create(connectionString);

        MongoDatabase database = mongoClient.getDatabase("AgricircleDB");

        MongoCollection<Document> collection = database.getCollection("Weatherstation1");

        try {
            Document doc =  collection.find(eq("dev_id", key)).first();
            if(doc != null)
            {
                return doc.toJson();
            }
            else
            {
                return "Could not find any data with the given criterias";
            }
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
