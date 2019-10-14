public interface WeatherstationDAO
{
    String InsertToDatabase(String json , String weatherstationName);

    String findAllFromDatabase(String weatherstationName);

    String findOneFromDatabase(String key);

    String findSpecFieldsFromDatabase(String key);

    String getWeatherstationData(String date , String weatherstationName);
}
