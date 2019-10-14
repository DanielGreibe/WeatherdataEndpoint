import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.rmi.ServerException;
import java.util.Calendar;
import java.util.Date;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

public class MongoDBDAO implements WeatherstationDAO {



    @Override
    public String setWeatherstationData(String jsonMessage, String weatherstationName) throws ServerException {
        MongoCollection<Document> collection = MongoConnection.getInstance("AgricircleDB").getDatabase().getCollection(weatherstationName);
        try {
            collection.insertOne(Document.parse(jsonMessage));
            return "Database updated";
        } catch (MongoWriteException | MongoWriteConcernException e) {
            throw new ServerException(e.getMessage());
        }
    }

    @Override
    public String getWeatherstationData(String weatherstationName , String contentType)
    {
        MongoCollection<Document> collection = MongoConnection.getInstance("AgricircleDB").getDatabase().getCollection(weatherstationName);
        StringBuilder returnString = new StringBuilder();
        FindIterable<Document> iterable = collection.find().projection(fields(include(
                "payload_fields.avg_wind_speed",
                "payload_fields.solar_radiation",
                "payload_fields.outside_temperature",
                "payload_fields.outside_humidity",
                "payload_fields.barometer_data",
                "payload_fields.rain_rate"),
                excludeId()));
        if(contentType.equals("netcdf"))
        {
            //TODO Convert JSON to netcdf and return it.
            return "Not Yet Implemented. Use contenttype = json";
        }
        if(iterable != null) {
            returnString.append("[");
            for (Document document : iterable) {
                returnString.append(document.toJson()).append(",");
            }
            returnString.delete(returnString.length()-1 , returnString.length());
            returnString.append("]");
        }
        if(returnString.length() == 0)
        {
            returnString.append("We couldn't find any data with the given criteria");
        }
        return returnString.toString();
    }



    public String getWeatherstationData(String stringDate , String weatherstationName , String contentType){
        MongoCollection<Document> collection = MongoConnection.getInstance("AgricircleDB").getDatabase().getCollection(weatherstationName);

        StringBuilder returnString = new StringBuilder();
        ObjectId date;
        try{
             date = DateToObjectId(stringDate);
        }
        catch(WrongDateFormatException wdfe)
        {
            return wdfe.getMessage();
        }

        FindIterable<Document> iterable = collection.find(gte("_id" , date)).
                projection(fields(include(
                        "payload_fields.avg_wind_speed",
                        "payload_fields.solar_radiation",
                        "payload_fields.outside_temperature",
                        "payload_fields.outside_humidity",
                        "payload_fields.barometer_data",
                        "payload_fields.rain_rate"),
                        excludeId()));

        if(contentType!= null && contentType.equals("netcdf"))
        {
            //TODO Convert JSON to netcdf and return it.
            return "Not Yet Implemented. Use contenttype = json";
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
            throw new WrongDateFormatException("Date should be earlier than current date");
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

    public static class WrongDateFormatException extends Exception
    {
        WrongDateFormatException(String message)
        {
            super(message);
        }
    }
}
