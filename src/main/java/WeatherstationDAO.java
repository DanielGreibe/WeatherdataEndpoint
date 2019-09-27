public interface WeatherstationDAO
{
    String InsertToDatabase(String json);

    String getAllFromDatabase();
}
