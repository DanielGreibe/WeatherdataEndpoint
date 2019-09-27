import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;

@Path("weatherstation")
public class WeatherstationEndpoint {
    private WeatherstationDAO database = new MongoDBDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postMessage(String message)
    {
        database.InsertToDatabase(message);
        //Returns a succes message
        return "POST call success!";
    }


    @GET
    public String getMessage()
    {
        /*
        Should return all the values in the Database
         */
        return "HAVEN'T IMPLEMENTED ACCESS TO DATABASE YET, SHOULD RETURN ALL DATA.";
    }
    @GET
    @Path("/{id}")
    public String message(@PathParam("id") int id)
    {
        /*
        Should return the weatherstation data with an ID of {id}
         */
        return "HAVEN'T IMPLEMENTED ACCESS TO DATABASE YET, SHOULD RETURN DATA WITH THE SPECIFIC ID " + id;
    }
}