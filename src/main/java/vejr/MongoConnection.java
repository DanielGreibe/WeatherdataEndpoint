package vejr;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection
{

    private static boolean TestEnvironment = Boolean.parseBoolean(System.getenv("NITRO_TESTENVIRONMENT"));
    private static MongoConnection mongoConnection = null;

    public static MongoConnection getInstance(String database)
    {
        if(TestEnvironment)
        {
            if (mongoConnection == null)
            {
                mongoConnection = new MongoConnection("test");
            }
            return mongoConnection;
        }
        else
        {
            if (mongoConnection == null)
            {
                mongoConnection = new MongoConnection(database);

            }
            return mongoConnection;
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
