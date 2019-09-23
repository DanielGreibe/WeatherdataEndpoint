import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("weatherstation")
public class WeatherstationEndpoint {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postMessage(String message)
    {
        /*
        //Returns the JSON message back
        return message;
        */

        //Returns a succes message
        return "POST call success!";
    }


    @GET
    public String getMessage()
    {
        /*
        Should return all the values in the Database
         */
        return "Du prøver at hente al data fra vejrstationen";
    }
    @GET
    @Path("/{id}")
    public String message(@PathParam("id") int id)
    {
        /*
        Should return the weatherstation data with an ID of {id}
         */
        return "Du prøver at hente data fra en vejrstationsrapport med ID " + id;
    }
}