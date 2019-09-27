import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBDAO implements WeatherstationDAO
{
    @Override
    public void InsertToDatabase(String jsonMessage)
    {
        //ConnectionString connectionString =
               // new ConnectionString("mongodb+srv://testuser:testuser@nitrogensensortest-c94pp.azure.mongodb.net/AgricircleDB");
        //MongoClient client = MongoClients.create(connectionString);

        MongoClient mongoClient = DatabaseConnection.connect();
        MongoDatabase database = mongoClient.getDatabase("AgricircleDB");

        MongoCollection<Document> collection = database.getCollection("Weatherstation1");

        collection.insertOne(Document.parse(jsonMessage));
        mongoClient.close();
    }
}
