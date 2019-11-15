import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

@Slf4j
public class MongoDBDAO implements WeatherstationDAO {


    private static final String PAYLOAD_FIELDS = "payload_fields";
    private static final String AVG_WIND_SPEED = "avg_wind_speed";
    private static final String BAROMETER_DATA = "barometer_data";
    private static final String OUTSIDE_HUMIDITY = "outside_humidity";
    private static final String OUTSIDE_TEMPERATURE = "outside_temperature";
    private static final String RAIN_RATE = "rain_rate";
    private static final String SOLAR_RADIATION = "solar_radiation";
    public static final String STATION_ID = "dev_id";
    public static final String TIME = "time";

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
    public List<WeatherData> getWeatherstationData(String weatherstationName, ContentType contentType) {
        MongoCollection<Document> collection = MongoConnection.getInstance("AgricircleDB").getDatabase().getCollection("Weatherstation1");
        FindIterable<Document> iterable = collection.find().projection(fields(include(
                "payload_fields.avg_wind_speed",
                "payload_fields.solar_radiation",
                "payload_fields.outside_temperature",
                "payload_fields.outside_humidity",
                "payload_fields.barometer_data",
                "payload_fields.rain_rate",
                "dev_id",
                "metadata.time"),
                excludeId()));

        if (contentType == contentType.NETCDF) {
            //TODO Convert JSON to netcdf and return it.
            throw new UnsupportedOperationException();
        } else {
            return DocumentToWeatherData(iterable);
        }
    }


    public List<WeatherData> getWeatherstationData(String stringDateStart, String stringDateEnd, String weatherstationName, ContentType contentType) throws WrongDateFormatException {
        // Currently The weatherstation sends a POST message to the url /rest/weatherstation which would result in data being sent to a collection named
        // weatherstation. The current data is saved in the collection Weatherstation1. This is why .getCollection takes the hardcoded string Weatherstation1
        // instead of the weatherstationName field.
        MongoCollection<Document> collection = MongoConnection.getInstance("AgricircleDB").getDatabase().getCollection("Weatherstation1");

        ObjectId dateStart;
        ObjectId dateEnd;
        dateStart = DateToObjectId(stringDateStart);
        dateEnd = DateToObjectId(stringDateEnd);

        FindIterable<Document> iterable = collection.find(and(
                        gte("_id", dateStart),
                        lte("_id", dateEnd))).
                projection(fields(include(
                        "payload_fields.avg_wind_speed",
                        "payload_fields.solar_radiation",
                        "payload_fields.outside_temperature",
                        "payload_fields.outside_humidity",
                        "payload_fields.barometer_data",
                        "payload_fields.rain_rate",
                        "dev_id",
                        "metadata.time"
                        ),
                        excludeId()));

        if (contentType == ContentType.NETCDF) {
            //TODO Convert JSON to netcdf and return it.
            throw new UnsupportedOperationException();
        } else {
            return DocumentToWeatherData(iterable);
        }
    }

    private static String DateToHexString(int year, int month, int day) throws WrongDateFormatException {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, 1, 0, 0);

        calendar.set(Calendar.MILLISECOND, 0);

        if (calendar.getTime().after(new Date())) {
            throw new WrongDateFormatException("Provided date should be earlier than current date");
        }
        long time = (calendar.getTimeInMillis() / 1000);
        return Long.toHexString(time) + "0000000000000000";
    }

    private static ObjectId DateToObjectId(String stringDate) throws WrongDateFormatException {
        if (stringDate == null) {
            throw new WrongDateFormatException("Please provide a date");
        }
        String[] splittedDate = stringDate.split("-");
        if (splittedDate.length != 3 || splittedDate[0].length() > 4 || splittedDate[1].length() > 2 || splittedDate[2].length() > 2) {
            throw new WrongDateFormatException("Date format should be yyyy-mm-dd");
        }

        int Year = Integer.parseInt(splittedDate[0]);
        int Month = Integer.parseInt(splittedDate[1]);
        int Day = Integer.parseInt(splittedDate[2]);
        String hexString = DateToHexString(Year, Month, Day);
        return new ObjectId(hexString);
    }

    @NotNull
    private List<WeatherData> DocumentToWeatherData(FindIterable<Document> iterable) {
        List<Document> into = iterable.into(new ArrayList<>());
        List<WeatherData> weatherData = new ArrayList<>();
        List<Location> LocationList = getDeviceLocations();
        for (Document doc : into) {
            Document payload_fields = doc.get(PAYLOAD_FIELDS, Document.class);
            Document metadata = doc.get("metadata", Document.class);
            Location location = getLocation(doc.getString(STATION_ID) , metadata.getString(TIME), LocationList);


            if (payload_fields == null || metadata == null) {
                log.error("Document is null, skipped payload_fields");
                log.error(doc.toString());

            } else {
                WeatherData temp = WeatherData.builder()
                        .average_wind_speed(new Double(payload_fields.get(AVG_WIND_SPEED) + ""))
                        .outside_temperature(new Double(payload_fields.get(OUTSIDE_TEMPERATURE) + ""))
                        .barometer_data(new Double(payload_fields.get(BAROMETER_DATA) + ""))
                        .outside_humidity(payload_fields.getInteger(OUTSIDE_HUMIDITY))
                        .rain_rate(payload_fields.getInteger(RAIN_RATE))
                        .solar_radiation(payload_fields.getInteger(SOLAR_RADIATION))
                        .station_id(doc.getString(STATION_ID))
                        .time(metadata.getString(TIME))
                        .latitude(location.latitude)
                        .longitude(location.longitude)
                        .build();

                weatherData.add(temp);

            }
        }
        return weatherData;
    }

    public Location getLocation(String station_id, String time, List<Location> LocationList) {
        Location newLocation = new Location();

        for (Location location : LocationList)
        {
            String[] start_time = location.start_time.split("[-:T.]");
            String[] end_time = location.end_time.split("[-:T.]");
            String[] time_array = time.split("[-:T.]");

            Calendar start_time_calendar = Calendar.getInstance();
            start_time_calendar.set(Calendar.YEAR, Integer.parseInt(start_time[0]));
            start_time_calendar.set(Calendar.MONTH, Integer.parseInt(start_time[1])-1);
            start_time_calendar.set(Calendar.DATE, Integer.parseInt(start_time[2]));
            start_time_calendar.set(Calendar.HOUR, Integer.parseInt(start_time[3])-12);
            start_time_calendar.set(Calendar.MINUTE, Integer.parseInt(start_time[4]));
            start_time_calendar.set(Calendar.SECOND, Integer.parseInt(start_time[5]));
            Date start_time_date = start_time_calendar.getTime();

            Calendar end_time_calendar = Calendar.getInstance();
            end_time_calendar.set(Calendar.YEAR, Integer.parseInt(end_time[0]));
            end_time_calendar.set(Calendar.MONTH, Integer.parseInt(end_time[1])-1);
            end_time_calendar.set(Calendar.DATE, Integer.parseInt(end_time[2]));
            end_time_calendar.set(Calendar.HOUR, Integer.parseInt(end_time[3])-12);
            end_time_calendar.set(Calendar.MINUTE, Integer.parseInt(end_time[4]));
            end_time_calendar.set(Calendar.SECOND, Integer.parseInt(end_time[5]));
            Date end_time_date = end_time_calendar.getTime();


            Calendar time_calendar = Calendar.getInstance();
            time_calendar.set(Calendar.YEAR, Integer.parseInt(time_array[0]));
            time_calendar.set(Calendar.MONTH, Integer.parseInt(time_array[1])-1);
            time_calendar.set(Calendar.DATE, Integer.parseInt(time_array[2]));
            time_calendar.set(Calendar.HOUR, Integer.parseInt(time_array[3])-12);
            time_calendar.set(Calendar.MINUTE, Integer.parseInt(time_array[4]));
            time_calendar.set(Calendar.SECOND, Integer.parseInt(time_array[5]));
            Date time_date = time_calendar.getTime();

            if(time_date.before(end_time_date) && time_date.after(start_time_date))
            {
                newLocation = Location.builder()
                        .latitude(location.latitude)
                        .longitude(location.longitude)
                        .station_id(location.station_id)
                        .start_time(location.start_time)
                        .end_time(location.end_time)
                        .build();

                return newLocation;
            }

        }
        return newLocation;
    }

    private List<Location> getDeviceLocations()
    {
        MongoCollection<Document> collection = MongoConnection.getInstance("AgricircleDB").getDatabase().getCollection("Locations");
        FindIterable<Document> iterable = collection.find();
        return LocationDB.DocumentToLocationList(iterable);

    }
}
