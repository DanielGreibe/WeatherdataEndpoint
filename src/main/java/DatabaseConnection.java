import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.ConnectionString;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

public class DatabaseConnection
{
    public static void main(String[] args) {
        connect();
    }
    public static MongoClient connect()
    {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://testuser:testuser@nitrogensensortest-c94pp.azure.mongodb.net/AgricircleDB");

        MongoClient mongoClient = MongoClients.create(connectionString);

        return mongoClient;

    }
}
