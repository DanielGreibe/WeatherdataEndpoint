import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("weatherstation")
public class WeatherstationEndpoint {
    private WeatherstationDAO database = new MongoDBDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String postMessage(String message)
    {

        System.out.println("Fik POST med "+message);
        return database.InsertToDatabase(message);
    }


    @GET
    public String getAll()
    {
        System.out.println("getALL() blev kaldt");
        //Returns all content in the database in JSON format.
        return database.findAllFromDatabase();
    }

    @GET
    @Path("{date}")
    public String getOne(@PathParam("date") String date)
    {
        System.out.println("Kald getOne med "+date);
        return database.findSpecFieldsFromDatabaseDATE(date);
    }



    //Link to implement QueryParameters
    //https://www.mscharhag.com/java-ee-mvc/query-parameters
}