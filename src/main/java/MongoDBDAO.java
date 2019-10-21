import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.Calendar;
import java.util.Date;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

public class MongoDBDAO implements WeatherstationDAO {



    @Override
    public String setWeatherstationData(String jsonMessage, String weatherstationName) throws ServerException {
        MongoCollection<Document> collection = MongoConnection.getInstance("AgricircleDB").getDatabase().getCollection("Weatherstation1");
        try {
            collection.insertOne(Document.parse(jsonMessage));
            return "Database updated";
        } catch (MongoWriteException | MongoWriteConcernException e) {
            throw new ServerException(e.getMessage());
        }
    }

    @Override
    public String getWeatherstationData(String weatherstationName , ContentType contentType)
    {
        MongoCollection<Document> collection = MongoConnection.getInstance("AgricircleDB").getDatabase().getCollection("Weatherstation1");
        StringBuilder returnString = new StringBuilder();
        FindIterable<Document> iterable = collection.find().projection(fields(include(
                "payload_fields.avg_wind_speed",
                "payload_fields.solar_radiation",
                "payload_fields.outside_temperature",
                "payload_fields.outside_humidity",
                "payload_fields.barometer_data",
                "payload_fields.rain_rate"),
                excludeId()));
        if(contentType == ContentType.NETCDF)
        {
            //TODO Convert JSON to netcdf and return it.
            throw new NotImplementedException();
        }
        else {
            if (iterable != null) {
                returnString.append("[");
                for (Document document : iterable) {
                    returnString.append(document.toJson()).append(",");
                }
                returnString.delete(returnString.length() - 1, returnString.length());
                returnString.append("]");
            }
        }
        return returnString.toString();
    }



    public String getWeatherstationData(String stringDate , String weatherstationName , ContentType contentType) throws WrongDateFormatException {
        // Currently The weatherstation sends a POST message to the url /rest/weatherstation which would result in data being sent to a collection named
        // weatherstation. The current data is saved in the collection Weatherstation1. This is why .getCollection takes the hardcoded string Weatherstation1
        // instead of the weatherstationName field.
        MongoCollection<Document> collection = MongoConnection.getInstance("AgricircleDB").getDatabase().getCollection("Weatherstation1");

        StringBuilder returnString = new StringBuilder();
        ObjectId date;
        date = DateToObjectId(stringDate);

        FindIterable<Document> iterable = collection.find(gte("_id" , date)).
                projection(fields(include(
                        "payload_fields.avg_wind_speed",
                        "payload_fields.solar_radiation",
                        "payload_fields.outside_temperature",
                        "payload_fields.outside_humidity",
                        "payload_fields.barometer_data",
                        "payload_fields.rain_rate"),
                        excludeId()));

        if(contentType == ContentType.NETCDF)
        {
            //TODO Convert JSON to netcdf and return it.
            throw new NotImplementedException();
        }
        else
        {
            if (iterable != null) {
                returnString.append("{\"dataobjects\":[");
                for (Document document : iterable) {
                    returnString.append(document.toJson()).append(",");
                }
                returnString.delete(returnString.length() - 1, returnString.length());
                returnString.append("]}");
            }
        }
        return returnString.toString();
    }

    private static String DateToHexString(int year, int month, int day) throws WrongDateFormatException {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, day , 0 , 0 , 0);

        calendar.set(Calendar.MILLISECOND, 0);

        if(calendar.getTime().after(new Date()))
        {
            throw new WrongDateFormatException("Provided date should be earlier than current date");
        }
        long time = (calendar.getTimeInMillis() / 1000);
        return Long.toHexString(time) + "0000000000000000";
    }

    private static ObjectId DateToObjectId(String stringDate) throws WrongDateFormatException {
        if(stringDate == null)
        {
            throw new WrongDateFormatException("Please provide a date");
        }
        String[] splittedDate = stringDate.split("-");
        if(splittedDate.length != 3 || splittedDate[0].length() > 4 || splittedDate[1].length() > 2 || splittedDate[2].length() > 2)
        {
            throw new WrongDateFormatException("Date format should be yyyy-mm-dd");
        }

        int Year = Integer.parseInt(splittedDate[0]);
        int Month = Integer.parseInt(splittedDate[1]);
        int Day = Integer.parseInt(splittedDate[2]);
        String hexString = DateToHexString(Year , Month , Day);
        return new ObjectId(hexString);
    }
}
