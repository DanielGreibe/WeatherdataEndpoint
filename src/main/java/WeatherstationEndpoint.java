import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("weatherstation")
public class WeatherstationEndpoint {
    private WeatherstationDAO database = new MongoDBDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postMessage(String message)
    {
        return database.InsertToDatabase(message);
    }


    @GET
    public String getAll()
    {
        //Returns all content in the database in JSON format.
        String databaseString =  database.findAllFromDatabase();
        JSONObject jsonObject = new JSONObject(databaseString);
        return jsonObject.toString(4);
    }

    @GET
    @Path("{key}")
    public String getOne(@PathParam("key") String key)
    {
        return database.findOneFromDatabase(key);




        //return "HAVEN'T IMPLEMENTED ACCESS TO DATABASE YET, SHOULD RETURN DATA WITH THE SPECIFIC ID " + id;
    }


}