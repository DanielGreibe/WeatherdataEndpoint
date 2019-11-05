import com.google.gson.Gson;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Provides an endpoint for weather data from multiple weather-stations located at various locations.
 * Possible input formats and outputs formats are described in the corresponding methods documentation.
 */
@Path("{weatherstation}")

public class WeatherstationEndpoint {
    private WeatherstationDAO database = new MongoDBDAO();

    /**
     * Inserts a text/JSON formatted String provided in the 'message' parameter to the database in the collection / table with the name provided in the 'weatherstationName' parameter.
     * @param message contains the text/JSON formatted String to be inserted in the database.
     * @param weatherstationName the name of the weatherstation that provided the data (Not Yet Implemented!)
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void setWeatherstationData(String message, @PathParam("weatherstation") String weatherstationName) throws ServerException {
        System.out.println("Kald setWeatherstationData(message , weatherstationName) med " + message + " og " + weatherstationName);
        try
        {
           database.setWeatherstationData(message , weatherstationName);
        }
        catch(ServerException se)
        {
            System.out.println(se.getMessage());
            throw se;
        }
    }

    /**
     * Provides all data stored in the database from the weather station stated in the weatherstationName parameter.
     * @param weatherstationName the name of the weather station to get data from. (Not Yet Implemented!)
     * @param contentType the type of content the API produces. Can be NETCDF or JSON. Only JSON is possible atm.
     * @return a JSON string with all data in the database.
     */
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public String getWeatherstationData(@PathParam("weatherstation") String weatherstationName , @QueryParam("contenttype") ContentType contentType)
    {
        System.out.println(
                "Kald getWeatherstationData(date,weatherstationName, contentType): \n" +
                        "Weatherstation Name: " + weatherstationName + "\n" +
                        "ContentType: " + contentType);
        //Returns all content in the database in JSON format.
        return new Gson().toJson(database.getWeatherstationData(weatherstationName , contentType));
    }

    /**
     * Provides all data elements that was posted between the provided date and current day.
     * @param dateStart A yyyy-mm-dd formatted string.
     * @param dateEnd A yyyy-mm-dd formatted string.
     * @param weatherstationName the name of the weather station to get data from. (Not Yet Implemented!)
     * @param contentType the type of content the API produces. Can be netcdf or json. Only json is possible atm.
     * @return A list of JSON elements that matches the filter criteria provided or 'We couldn't find any data with the given criteria' if no such data exists.
     */
    @GET
    @Path("date")
    @Produces(MediaType.APPLICATION_JSON)
    public String getWeatherstationData(@QueryParam("dateStart") String dateStart , @QueryParam("dateEnd") String dateEnd, @PathParam("weatherstation") String weatherstationName , @QueryParam("contenttype") ContentType contentType) throws WrongDateFormatException {
        System.out.println(
                "Kald getWeatherstationData(date,weatherstationName, contentType): " +
                "\n Date: " + dateStart +
                "\n Weatherstation Name: " + weatherstationName +
                "\n ContentType: " + contentType);
        return new Gson().toJson(database.getWeatherstationData(dateStart, dateEnd, weatherstationName , contentType));
    }
}