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
    public String getMessage()
    {
        //Returns all content in the database in JSON format.
        String databaseString =  database.getAllFromDatabase();
        JSONObject jsonObject = new JSONObject(databaseString);
        return jsonObject.toString(4);
    }
/*
    @GET
    @Path("/{id}")
    public String message(@PathParam("id") int id)
    {

        //Should return the weatherstation data with an ID of {id}




        return "HAVEN'T IMPLEMENTED ACCESS TO DATABASE YET, SHOULD RETURN DATA WITH THE SPECIFIC ID " + id;
    }
*/


}