
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import vejr.Location;
import vejr.LocationDAO;
import vejr.LocationDB;
import vejr.ServerException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("location")
public class LocationEndpoint
{
    private LocationDAO database = new LocationDB();
    private ObjectMapper objectMapper = new ObjectMapper();

    @GET
    @Path("{station_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDeviceLocation(@PathParam("station_id") String station_id) throws JsonProcessingException {
        List<Location> locationList = database.getDeviceLocation(station_id);
        return objectMapper.writeValueAsString(locationList);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void setLocation(String JSONLocation) throws ServerException {
        database.setLocation(JSONLocation);
    }

}
