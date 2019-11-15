package vejr;

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
    public double latitude;
    public double longitude;
    public String station_id;
    public String start_time;
    public String end_time;
}
