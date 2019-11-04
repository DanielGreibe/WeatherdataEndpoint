import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WeatherData {

    private double average_wind_speed;
    private double barometer_data;
    private int outside_humidity;
    private double outside_temperature;
    private int rain_rate;
    private int solar_radiation;
    private String time;
    private String dev_id;

}
