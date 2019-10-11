import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection
{
    private static MongoConnection mongoConnection = null;

    public static MongoConnection getInstance(String database)
    {
        if (mongoConnection == null)
        {
            mongoConnection = new MongoConnection(database);
        }
        return mongoConnection;
    }

    private MongoClient mongoClient = null;
    private MongoDatabase mongoDatabase = null;

    private MongoConnection(String database)
    {
        //Alternative connection to Christian Budtz Mongodb Atlas Project
        //new ConnectionString("mongodb+srv://dtumongo:" + System.getenv("DTU_MONGO_PASS") + "@cluster0-5aegy.mongodb.net/AgricircleDB?retryWrites=true&w=majority");
        ConnectionString connectionString = new ConnectionString("mongodb+srv://testuser:" + System.getenv("DTU_MONGO_PASS_PERSONAL") + "@nitrogensensortest-c94pp.azure.mongodb.net/" + database);
        mongoClient = MongoClients.create(connectionString);
        mongoDatabase = mongoClient.getDatabase(database);
    }

    public MongoDatabase getDatabase()
    {
        return mongoDatabase;
    }
}
