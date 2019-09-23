import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;

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
        MongoClient mongoClient = DatabaseConnection.connect();
        MongoDatabase database = mongoClient.getDatabase("AgricircleDB");

        MongoCollection<Document> collection = database.getCollection("Weatherstation1");

        String json = "{\"app_id\":\"mcf88_test_1\",\"dev_id\":\"mcf_lww_1\",\"hardware_serial\":\"70B3D58FF100D6FF\",\"port\":2,\"counter\":124,\"confirmed\":true,\"payload_raw\":\"CyUAABDcdDICCAu4AFAAAP9aAAAAIAD/////////AAYA\",\"payload_fields\":{\"avg_wind_speed\":17.698999999999998,\"bar_trend\":0,\"barometer_data\":29.916,\"data_version\":0,\"davis_type\":16,\"day_et\":0.032,\"day_rain\":0,\"forecast_icons\":6,\"frame_id\":0,\"leaf_wetness\":16777215,\"outside_humidity\":80,\"outside_temperature\":13.444444444444446,\"rain_rate\":0,\"report_id\":37,\"soil_moisture\":-1,\"solar_radiation\":90,\"uplink_id\":11,\"uv\":255,\"wind_direction\":184,\"wind_speed\":12.872},\"metadata\":{\"time\":\"2019-09-23T12:40:58.822403683Z\",\"frequency\":868.5,\"modulation\":\"LORA\",\"data_rate\":\"SF7BW125\",\"coding_rate\":\"4/5\",\"gateways\":[{\"gtw_id\":\"eui-7276ff00390301da\",\"timestamp\":1689752084,\"time\":\"2019-09-23T12:40:58Z\",\"channel\":7,\"rssi\":-84,\"snr\":6.8,\"rf_chain\":1},{\"gtw_id\":\"eui-1234567997654321\",\"timestamp\":1471069155,\"time\":\"\",\"channel\":2,\"rssi\":-91,\"snr\":9.3,\"rf_chain\":0},{\"gtw_id\":\"eui-000080029c10db15\",\"timestamp\":355055196,\"time\":\"2019-09-23T12:40:58.798582Z\",\"channel\":7,\"rssi\":-86,\"snr\":9.5,\"rf_chain\":1,\"latitude\":55.73246,\"longitude\":12.39422,\"altitude\":31}]},\"downlink_url\":\"https://integrations.thethingsnetwork.org/ttn-eu/api/v2/down/mcf88_test_1/ws1dk?key=ttn-account-v2.0zspI9Ggh4YjdCgMWrljs_acBeSZ08e-T0e1ZYj1K0Y\"}\n";
        Document doc1 = Document.parse(json);

        Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
                .append("info", new Document("x", 203).append("y", 102));

        collection.insertOne(Document.parse(message));
        /*
         */
        mongoClient.close();

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