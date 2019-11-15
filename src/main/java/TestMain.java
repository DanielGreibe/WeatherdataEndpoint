import vejr.Location;
import vejr.LocationDB;
import vejr.MongoDBDAO;

import java.util.ArrayList;

public class TestMain
{
    public static void main(String[] args) {
        MongoDBDAO mongoDBDAO = new MongoDBDAO();
        LocationDB locationDB = new LocationDB();
        Location location = mongoDBDAO.getLocation("mcf_lww_2" , "2019-11-16T20:53:43", locationDB.getDeviceLocation("mcf_lww_2"));
        System.out.println(location.latitude);
        System.out.println(location.longitude);
        System.out.println(location.station_id);
        System.out.println(location.start_time);
        System.out.println(location.end_time);
    }
}
