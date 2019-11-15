import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Location
{
    double latitude;
    double longitude;
    String station_id;
    String start_time;
    String end_time;
}
