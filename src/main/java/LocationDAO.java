import java.util.List;

public interface LocationDAO
{
    void setLocation(String JSONLocation) throws ServerException;

    List<Location> getDeviceLocation(String station_id);
}
