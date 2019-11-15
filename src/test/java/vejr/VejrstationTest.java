package vejr;

import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import vejr.LocationDB;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VejrstationTest {

    @BeforeAll
    private static void initialiseGson(){

    }

    @Test
    void testNoget(){
        LocationDB locationDB = new LocationDB();


        Date tidspunkt = MongoDBDAO.timeStrToDate("2019-11-16T20:53:43");
        assertEquals(119, tidspunkt.getYear());
    }
}
