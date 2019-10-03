import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("weatherstation")
public class WeatherstationEndpoint {
    private WeatherstationDAO database = new MongoDBDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String postMessage(String message)
    {
        return database.InsertToDatabase(message);
    }


    @GET
    public String getAll()
    {
        System.out.println("getALL() blev kaldt");
        //Returns all content in the database in JSON format.
        String databaseString =  database.findAllFromDatabase();
        return databaseString;
    }

    @GET
    @Path("{key}")
    public String getOne(@PathParam("key") String key)
    {
        return database.findSpecFieldsFromDatabaseDATE(key);
    }



    //Link to implement QueryParameters
    //https://www.mscharhag.com/java-ee-mvc/query-parameters
}