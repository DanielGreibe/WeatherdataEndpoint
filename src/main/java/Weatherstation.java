import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("weatherstation")
public class Weatherstation {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postMessage(String message)
    {
        return message;
    }
}