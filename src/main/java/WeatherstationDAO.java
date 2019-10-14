import java.rmi.ServerException;

public interface WeatherstationDAO
{
    String setWeatherstationData(String json , String weatherstationName) throws ServerException;

    String getWeatherstationData(String weatherstationName, String contentType);

    String getWeatherstationData(String date , String weatherstationName, String contentType);
}
