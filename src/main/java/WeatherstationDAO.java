import DataObjects.Gateway;
import DataObjects.Metadata;
import DataObjects.PayloadFields;
import DataObjects.Weatherstation;

import java.util.List;

public interface WeatherstationDAO
{
    void InsertToDatabase(String json);
}
