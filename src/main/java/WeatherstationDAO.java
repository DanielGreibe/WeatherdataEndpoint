public interface WeatherstationDAO
{
    String setWeatherstationData(String json , String weatherstationName) throws ServerException;

    String getWeatherstationData(String weatherstationName, ContentType contentType);

    String getWeatherstationData(String date , String weatherstationName, ContentType contentType) throws WrongDateFormatException;
}
