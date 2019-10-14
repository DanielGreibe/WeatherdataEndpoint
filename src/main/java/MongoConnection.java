import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * jdoc description
 * */
public class MongoConnection
{
    private static boolean TestEnvironment = true;
    private static MongoConnection mongoTestConnection = null;
    private static MongoConnection mongoProductionConnection = null;

    public static MongoConnection getInstance(String database)
    {
        if(TestEnvironment)
        {
            if (mongoTestConnection == null)
            {
                mongoTestConnection = new MongoConnection("test");

            }
            return mongoTestConnection;
        }
        else
        {
            if (mongoProductionConnection == null)
            {
                mongoProductionConnection = new MongoConnection(database);

            }
            return mongoProductionConnection;
        }
    }

    private MongoClient mongoClient = null;
    private MongoDatabase mongoDatabase = null;

    private MongoConnection(String database)
    {
        ConnectionString connectionString;
        if(TestEnvironment)
        {
            connectionString = new ConnectionString("mongodb+srv://testuser:" + System.getenv("DTU_MONGO_PASS_PERSONAL") + "@nitrogensensortest-c94pp.azure.mongodb.net/test");
        }
        else
        {
            connectionString = new ConnectionString("mongodb+srv://testuser:" + System.getenv("DTU_MONGO_PASS_PERSONAL") + "@nitrogensensortest-c94pp.azure.mongodb.net/" + database);
        }
        mongoClient = MongoClients.create(connectionString);
        mongoDatabase = mongoClient.getDatabase(database);
    }

    public MongoDatabase getDatabase()
    {
        return mongoDatabase;
    }
}
