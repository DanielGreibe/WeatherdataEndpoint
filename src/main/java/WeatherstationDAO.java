import DataObjects.Gateway;
import DataObjects.Metadata;
import DataObjects.PayloadFields;
import DataObjects.Weatherstation;

import java.util.List;

public interface WeatherstationDAO
{
    boolean InsertWeatherstation(Gateway gateway, Metadata metadata, PayloadFields payloadFields, WeatherstationEndpoint weatherstation);

    //List<Weatherstation>

}
