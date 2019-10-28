import java.util.List;

public interface WeatherstationDAO
{
    void setWeatherstationData(String json , String weatherstationName) throws ServerException;

    List<WeatherData> getWeatherstationData(String weatherstationName, ContentType contentType);

    List<WeatherData> getWeatherstationData(String date , String weatherstationName, ContentType contentType) throws WrongDateFormatException;
}
