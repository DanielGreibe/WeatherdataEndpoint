public interface WeatherstationDAO
{
    String InsertToDatabase(String json);

    String findAllFromDatabase();

    String findOneFromDatabase(String key);
}
