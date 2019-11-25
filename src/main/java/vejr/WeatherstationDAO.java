package vejr;

import java.util.List;

public interface WeatherstationDAO
{
    void setWeatherstationData(String json , String weatherstationName) throws ServerException;

    List<WeatherData> getWeatherstationData(String weatherstationName);

    List<WeatherData> getWeatherstationData(String dateStart , String dateEnd, String weatherstationName) throws WrongDateFormatException;
}
