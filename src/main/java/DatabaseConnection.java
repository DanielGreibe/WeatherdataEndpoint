import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.ConnectionString;


public class DatabaseConnection
{
    public static void main(String[] args) {
        connect();
    }
    public static MongoClient connect()
    {
        ConnectionString connectionString =
                new ConnectionString("mongodb+srv://testuser:testuser@nitrogensensortest-c94pp.azure.mongodb.net/AgricircleDB");
        return MongoClients.create(connectionString);

    }
}
