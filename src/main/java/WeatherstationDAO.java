import DataObjects.Gateway;
import DataObjects.Metadata;
import DataObjects.PayloadFields;

public interface WeatherstationDAO
{
    boolean InsertWeatherstation(Gateway gateway, Metadata metadata, PayloadFields payloadFields, WeatherstationEndpoint weatherstation);

}
