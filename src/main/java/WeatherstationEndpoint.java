import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Provides an endpoint for weather data from multiple weather-stations located at various locations.
 */
@Path("{weatherstation}")
public class WeatherstationEndpoint {
    private WeatherstationDAO database = new MongoDBDAO();

    /**
     * Inserts a JSON data provided in the message to the database in the collection / table with the provided weather stationName.
     * @param message contains the JSON element to be inserted in the database.
     * @param weatherstationName the name of the weather station that provided the data.
     * @return returns 'Database updated' if the procedure succeeded.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String postMessage(String message, @PathParam("weatherstation") String weatherstationName)
    {
        System.out.println("Fik POST med " + message);
        return database.InsertToDatabase(message , weatherstationName);
    }


    /**
     * Provides all data stored in the database from the weather station stated in the weatherstationName parameter.
     * @param weatherstationName the name of the weather station to get data from.
     * @return a JSON string with all data in the database.
     */
    @GET
    public String getAll(@PathParam("weatherstation") String weatherstationName)
    {
        System.out.println("getALL() blev kaldt");
        //Returns all content in the database in JSON format.
        return database.findAllFromDatabase(weatherstationName);
    }

    /**
     * Provides all data elements that was posted between the provided date and current day.
     * @param date A yyyy-mm-dd formatted string.
     * @param weatherstationName the name of the weather station to get data from.
     * @return A list of JSON elements that matches the filter criteria provided or 'We couldn't find any data with the given criteria' if no such data exists.
     */
    @GET
    @Path("{date}")
    public String getOne(@PathParam("date") String date , @PathParam("weatherstation") String weatherstationName)
    {
        System.out.println("Kald getOne med " + date);
        return database.getWeatherstationData(date , weatherstationName);
    }
}