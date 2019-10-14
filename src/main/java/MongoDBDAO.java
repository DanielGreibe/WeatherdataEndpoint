import com.mongodb.MongoException;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

public class MongoDBDAO implements WeatherstationDAO {



    @Override
    public String InsertToDatabase(String jsonMessage, String weatherstationName) {
        MongoCollection<Document> collection = MongoConnection.getInstance("AgricircleDB").getDatabase().getCollection(weatherstationName);


        try {
            collection.insertOne(Document.parse(jsonMessage));
            return "Database updated";
        } catch (MongoException mwe) {
            //  Block of code to handle error
            return mwe.getMessage();
            //throw new DBException("Soe")
        }
    }

    @Override
    public String findAllFromDatabase(String weatherstationName) {

        MongoCollection<Document> collection = MongoConnection.getInstance("AgricircleDB").getDatabase().getCollection(weatherstationName);

        try {
            StringBuilder returnString = new StringBuilder();
            FindIterable<Document> iterable = collection.find();
            for (Document document : iterable) {
                returnString.append(document.toJson());
            }
            return returnString.toString();
        } catch (MongoException mwe) {
            //  Block of code to handle errors
            return mwe.getMessage();
        }
    }

    @Override
    public String findOneFromDatabase(String key) {
        MongoCollection<Document> collection = MongoConnection.getInstance("AgricircleDB").getDatabase().getCollection("Weatherstation1");

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
        MongoCollection<Document> collection = MongoConnection.getInstance("AgricircleDB").getDatabase().getCollection("Weatherstation1");
        try {
            StringBuilder returnString = new StringBuilder();
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
                    returnString.append(document.toJson());
                }
                return returnString.toString();
            } else {
                return "Could not find any data with the given criterias";
            }
        } catch (MongoException mwe) {
            //  Block of code to handle errors
            return mwe.getMessage();
        }
    }

    public String getWeatherstationData(String stringDate , String weatherstationName){
        MongoCollection<Document> collection = MongoConnection.getInstance("AgricircleDB").getDatabase().getCollection(weatherstationName);

        StringBuilder returnString = new StringBuilder();
        ObjectId date = DateToObjectId(stringDate);
        FindIterable<Document> iterable = collection.find(gte("_id" , date));
        System.out.println(iterable);
        if (iterable != null) {
            for (Document document : iterable) {
                returnString.append(document.toJson());
            }
        }
        if(returnString.length() == 0)
        {
            return "We couldn't find any data with the given criteria";
        }
        return returnString.toString();
    }

    private static String DateToHexString(int year, int month, int day)
    {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, day , 0 , 0 , 0);

        calendar.set(Calendar.MILLISECOND, 0);

        if(calendar.getTime().after(new Date()))
        {
            throw new InputMismatchException("Date should be earlier than current date");
        }
        long time = (calendar.getTimeInMillis() / 1000);
        return Long.toHexString(time) + "0000000000000000";
    }

    private ObjectId DateToObjectId(String stringDate)
    {
        if(stringDate == null)
        {
            throw new NullPointerException("Please provide a date");
        }
        String[] splittedDate = stringDate.split("-");
        if(splittedDate.length != 3 || splittedDate[0].length() > 4 || splittedDate[1].length() > 2 || splittedDate[2].length() > 2)
        {
            throw new InputMismatchException("Date format should be yyyy-mm-dd");
        }

        int Year = Integer.parseInt(splittedDate[0]);
        int Month = Integer.parseInt(splittedDate[1]);
        int Day = Integer.parseInt(splittedDate[2]);
        String hexString = DateToHexString(Year , Month , Day);
        return new ObjectId(hexString);
    }
}
