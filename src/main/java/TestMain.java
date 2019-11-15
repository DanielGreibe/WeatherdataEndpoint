public class TestMain
{
    public static void main(String[] args) {
        MongoDBDAO mongoDBDAO = new MongoDBDAO();
        Location location = mongoDBDAO.getLocation("mcf_lww_2" , "2019-11-16T20:53:43");
        System.out.println(location.latitude);
        System.out.println(location.longitude);
        System.out.println(location.station_id);
        System.out.println(location.start_time);
        System.out.println(location.end_time);
    }
}
