import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Provides an endpoint for weather data from multiple weather-stations located at various locations.
 * Possible input formats and outputs formats are described in the corresponding methods documentation.
 */
@Path("weatherstation")
public class WeatherstationEndpoint {
    private WeatherstationDAO database = new MongoDBDAO();

    /**
     * Inserts a text/JSON formatted String provided in the 'message' parameter to the database.
     * @param message contains the application/json formatted String to be inserted in the database.
     * @return returns 'Database updated' if the procedure succeeded.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String setWeatherstationData(String message)
    {

        System.out.println("Fik POST med "+message);
        return database.InsertToDatabase(message);
    }


    /**
     * Provides all data stored in the database.
     * @return a JSON string with all data in the database.
     */
    @GET
    public String getWeatherstationData()
    {
        System.out.println("getALL() blev kaldt");
        //Returns all content in the database in JSON format.
        return database.findAllFromDatabase();
    }

    /**
     * Provides all data elements that was posted between the provided date and current day.
     * @param date A date in the yyyy-mm-dd format.
     * @return A list of JSON data objects that matches the filter criteria provided or 'We couldn't find any data with the given criteria' if no such data exists.
     */
    @GET
    @Path("{date}")
    public String getWeatherstationData(@PathParam("date") String date)
    {
        System.out.println("Kald getOne med "+date);
        return database.findSpecFieldsFromDatabaseDATE(date);
    }

}