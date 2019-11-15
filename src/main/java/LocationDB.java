import com.mongodb.MongoWriteConcernException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class LocationDB implements LocationDAO
{

    @Override
    public void setLocation(String JSONLocation) throws ServerException {
        MongoCollection<Document> collection = MongoConnection.getInstance("AgricircleDB").getDatabase().getCollection("Locations");
        try
        {
            collection.insertOne(Document.parse(JSONLocation));
        }
        catch (MongoWriteException | MongoWriteConcernException e)
        {
            throw new ServerException(e.getMessage());
        }
    }

    @Override
    public List<Location> getDeviceLocation(String station_id)
    {
        System.out.println("Called getDeviceLocation");
        MongoCollection<Document> collection = MongoConnection.getInstance("AgricircleDB").getDatabase().getCollection("Locations");
        FindIterable<Document> iterable = collection.find();
        return DocumentToLocationList(iterable);
    }

    public static List<Location> DocumentToLocationList(FindIterable<Document> iterable) {
        List<Document> payload = iterable.into(new ArrayList<>());
        List<Location> locationList = new ArrayList<>();

        for (Document document : payload)
        {
            Location location = Location.builder()
            .latitude(document.getDouble("latitude"))
            .longitude(document.getDouble("longitude"))
            .station_id(document.getString("station_id"))
            .start_time(document.getString("start_time"))
            .end_time(document.getString("end_time"))
            .build();
            locationList.add(location);
        }
        return locationList;
    }
}
