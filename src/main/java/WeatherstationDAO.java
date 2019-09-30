public interface WeatherstationDAO
{
    String InsertToDatabase(String json);

    String findAllFromDatabase();

    String findOneFromDatabase(String key);

    String findSpecFieldsFromDatabase(String key);

    String findSpecFieldsFromDatabaseDATE(String date);
}
