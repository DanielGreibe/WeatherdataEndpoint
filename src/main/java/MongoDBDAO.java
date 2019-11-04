import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

public class MongoDBDAO implements WeatherstationDAO {


    private static final String PAYLOAD_FIELDS = "payload_fields";
    private static final String AVG_WIND_SPEED = "avg_wind_speed";
    private static final String BAROMETER_DATA = "barometer_data";
    private static final String OUTSIDE_HUMIDITY = "outside_humidity";
    private static final String OUTSIDE_TEMPERATURE = "outside_temperature";
    private static final String RAIN_RATE = "rain_rate";
    private static final String SOLAR_RADIATION = "solar_radiation";

    @Override
    public void setWeatherstationData(String jsonMessage, String weatherstationName) throws ServerException {
        MongoCollection<Document> collection = MongoConnection.getInstance("AgricircleDB").getDatabase().getCollection("Weatherstation1");
        try {
            collection.insertOne(Document.parse(jsonMessage));
        } catch (MongoWriteException | MongoWriteConcernException e) {
            throw new ServerException(e.getMessage());
        }
    }

    @Override
    public List<WeatherData> getWeatherstationData(String weatherstationName , ContentType contentType)
    {
        MongoCollection<Document> collection = MongoConnection.getInstance("AgricircleDB").getDatabase().getCollection("Weatherstation1");
        FindIterable<Document> iterable = collection.find().projection(fields(include(
                "payload_fields.avg_wind_speed",
                "payload_fields.solar_radiation",
                "payload_fields.outside_temperature",
                "payload_fields.outside_humidity",
                "payload_fields.barometer_data",
                "payload_fields.rain_rate",
                "metadata.time"),
                excludeId()));
        if(contentType == ContentType.NETCDF)
        {
            //TODO Convert JSON to netcdf and return it.
            throw new NotImplementedException();
        }
        else {
            return DocumentToWeatherData(iterable);
        }
    }


    public List<WeatherData> getWeatherstationData(String stringDate , String weatherstationName , ContentType contentType) throws WrongDateFormatException {
        // Currently The weatherstation sends a POST message to the url /rest/weatherstation which would result in data being sent to a collection named
        // weatherstation. The current data is saved in the collection Weatherstation1. This is why .getCollection takes the hardcoded string Weatherstation1
        // instead of the weatherstationName field.
        MongoCollection<Document> collection = MongoConnection.getInstance("AgricircleDB").getDatabase().getCollection("Weatherstation1");

        ObjectId date;
        date = DateToObjectId(stringDate);

        FindIterable<Document> iterable = collection.find(gte("_id" , date)).
                projection(fields(include(
                        "payload_fields.avg_wind_speed",
                        "payload_fields.solar_radiation",
                        "payload_fields.outside_temperature",
                        "payload_fields.outside_humidity",
                        "payload_fields.barometer_data",
                        "payload_fields.rain_rate",
                        "metadata.time"),
                        excludeId()));

        if(contentType == ContentType.NETCDF)
        {
            //TODO Convert JSON to netcdf and return it.
            throw new NotImplementedException();
        }
        else
        {
            return DocumentToWeatherData(iterable);
        }
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

    @NotNull
    private List<WeatherData> DocumentToWeatherData(FindIterable<Document> iterable) {
        List<Document> into = iterable.into(new ArrayList<>());
        List<WeatherData> weatherData = new ArrayList<>();
        for(Document doc: into){
            Document document = doc.get(PAYLOAD_FIELDS, Document.class);
            Document document2 = doc.get("metadata", Document.class);

            double avg_wind_speed;
            double outside_temperature;

            if(document.get(AVG_WIND_SPEED).toString().contains("."))
            {
                avg_wind_speed = document.getDouble(AVG_WIND_SPEED);
            }
            else
            {
                //Doesn't contain a . but should since its a double so i add it explicitly.
                 avg_wind_speed = Double.parseDouble(document.getInteger(AVG_WIND_SPEED).toString() + ".0");
            }
            if(document.get(OUTSIDE_TEMPERATURE).toString().contains("."))
            {
                outside_temperature = document.getDouble(OUTSIDE_TEMPERATURE);
            }
            else
            {
                outside_temperature = Double.parseDouble(document.getInteger(OUTSIDE_TEMPERATURE).toString() + ".0");
            }

                WeatherData temp = WeatherData.builder()
                        .average_wind_speed(avg_wind_speed)
                        .outside_temperature(outside_temperature)
                        .barometer_data(document.getDouble(BAROMETER_DATA))
                        .outside_humidity(document.getInteger(OUTSIDE_HUMIDITY))
                        .rain_rate(document.getInteger(RAIN_RATE))
                        .solar_radiation(document.getInteger(SOLAR_RADIATION))
                        .time(document2.getString("time")).build();

                weatherData.add(temp);

        }
        return weatherData;
    }
}
