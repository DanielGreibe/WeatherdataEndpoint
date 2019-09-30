import com.mongodb.ConnectionString;
import com.mongodb.MongoException;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Calendar;
import java.util.Date;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

public class MongoDBDAO implements WeatherstationDAO {
    @Override
    public String InsertToDatabase(String jsonMessage) {
        MongoCollection<Document> collection = Connect("AgricircleDB" , "Weatherstation1");

        try {
            collection.insertOne(Document.parse(jsonMessage));
            return "Database updated";
        } catch (MongoException mwe) {
            //  Block of code to handle errors
            return mwe.getMessage();
        }
    }

    @Override
    public String findAllFromDatabase() {
        MongoCollection<Document> collection = Connect("AgricircleDB" , "Weatherstation1");

        try {
            String returnString = "";
            FindIterable<Document> iterable = collection.find();
            for (Document document : iterable) {
                returnString = returnString + document.toJson();
            }
            return returnString;
        } catch (MongoException mwe) {
            //  Block of code to handle errors
            return mwe.getMessage();
        }
    }

    @Override
    public String findOneFromDatabase(String key) {
        MongoCollection<Document> collection = Connect("AgricircleDB" , "Weatherstation1");

        try {
            Document doc = collection.find(eq("dev_id", key)).first();
            if (doc != null) {
                return doc.toJson();
            } else {
                return "Could not find any data with the given criterias";
            }
        } catch (MongoException mwe) {
            //  Block of code to handle errors
            return mwe.getMessage();
        }
    }

    @Override
    public String findSpecFieldsFromDatabase(String key) {
                //Alternative connection to Christian Budtz Mongodb Atlas Project
                //new ConnectionString("mongodb+srv://dtumongo:" + System.getenv("DTU_MONGO_PASS") + "@cluster0-5aegy.mongodb.net/AgricircleDB?retryWrites=true&w=majority");
                MongoCollection<Document> collection = Connect("AgricircleDB" , "Weatherstation1");
        try {
            String returnString = "";
            FindIterable<Document> iterable = collection.find(eq("dev_id", key))
                    .projection(fields(include( "payload_fields.avg_wind_speed",
                                                            "payload_fields.solar_radiation",
                                                            "payload_fields.outside_temperature",
                                                            "payload_fields.outside_humidity",
                                                            "payload_fields.barometer_data",
                                                            "payload_fields.rain_rate"),
                                                            excludeId()));
            if (iterable != null) {
                for (Document document : iterable) {
                    returnString = returnString + document.toJson();
                }
                return returnString;
            } else {
                return "Could not find any data with the given criterias";
            }
        } catch (MongoException mwe) {
            //  Block of code to handle errors
            return mwe.getMessage();
        }
    }

    public String findSpecFieldsFromDatabaseDATE(String stringDate) {
        MongoCollection<Document> collection = Connect("AgricircleDB" , "Weatherstation1");

        try {
            String returnString = "";
            Date tempDate = new Date(Calendar.DATE, 9 , 31);
            ObjectId date = new ObjectId(tempDate);
            System.out.println(date.toHexString());
            System.out.println("5f75bb130000000000000000");
            FindIterable<Document> iterable = collection.find(gte("_id" , new ObjectId("5f75bb130000000000000000")));
            //FindIterable<Document> iterable = collection.find(gte("_id" , date));
            if (iterable != null) {
                for (Document document : iterable) {
                    returnString = returnString + document.toJson();
                }
                return returnString;
            } else {
                return "Could not find any data with the given criterias";
            }
        } catch (MongoException mwe) {
            //  Block of code to handle errors
            return mwe.getMessage();
        }
    }

    private static MongoCollection<Document> Connect(String databaseName, String collectionName)
    {
        ConnectionString connectionString =
                new ConnectionString("mongodb+srv://testuser:" + System.getenv("DTU_MONGO_PASS_PERSONAL") + "@nitrogensensortest-c94pp.azure.mongodb.net/AgricircleDB");
        MongoClient mongoClient = MongoClients.create(connectionString);

        MongoDatabase database = mongoClient.getDatabase(databaseName);

        return database.getCollection(collectionName);
    }
    private static String DateToHexConverter(String date)
    {
        return date;
    }
}
