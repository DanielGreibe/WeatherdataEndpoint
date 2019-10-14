import java.rmi.ServerException;

public interface WeatherstationDAO
{
    String setWeatherstationData(String json , String weatherstationName) throws ServerException;

    String getWeatherstationData(String weatherstationName);

    String getWeatherstationData(String date , String weatherstationName);
}
