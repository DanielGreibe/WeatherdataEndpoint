public class Weatherdata {

    private int average_wind_speed;
    private double barometer_data;
    private int outside_humidity;
    private double outside_temperature;
    private int rain_rate;
    private int solar_radiation;


    public Weatherdata build()
    {
        Weatherdata weatherdata = new Weatherdata();
        weatherdata.average_wind_speed = this.average_wind_speed;
        weatherdata.barometer_data = this.barometer_data;
        weatherdata.outside_humidity = this.outside_humidity;
        weatherdata.outside_temperature = this.outside_temperature;
        weatherdata.rain_rate = this.rain_rate;
        weatherdata.solar_radiation = this.solar_radiation;

        return weatherdata;
    }

    public Weatherdata averageWindSpeed(int averageWindSpeed)
    {
        this.average_wind_speed = averageWindSpeed;

        return this;
    }

    public Weatherdata barometerData(double barometerData)
    {
        this.barometer_data = barometerData;
        return this;
    }

    public Weatherdata outsideHumidity(int outsideHumidity)
    {
        this.outside_humidity = outsideHumidity;
        return this;
    }

    public Weatherdata outsideTemperature(double outsideTemperature)
    {
        this.outside_temperature = outsideTemperature;
        return this;
    }

    public Weatherdata rainRate(int rainRate)
    {
        this.rain_rate = rainRate;
        return this;
    }

    public Weatherdata solarRadiation(int solarRadiation)
    {
        this.solar_radiation = solarRadiation;
        return this;
    }

    public int getAverage_wind_speed() {
        return average_wind_speed;
    }

    public void setAverage_wind_speed(int average_wind_speed) {
        this.average_wind_speed = average_wind_speed;
    }

    public double getBarometer_data() {
        return barometer_data;
    }

    public void setBarometer_data(double barometer_data) {
        this.barometer_data = barometer_data;
    }

    public int getOutside_humidity() {
        return outside_humidity;
    }

    public void setOutside_humidity(int outside_humidity) {
        this.outside_humidity = outside_humidity;
    }

    public double getOutside_temperature() {
        return outside_temperature;
    }

    public void setOutside_temperature(double outside_temperature) {
        this.outside_temperature = outside_temperature;
    }

    public int getRain_rate() {
        return rain_rate;
    }

    public void setRain_rate(int rain_rate) {
        this.rain_rate = rain_rate;
    }

    public int getSolar_radiation() {
        return solar_radiation;
    }

    public void setSolar_radiation(int solar_radiation) {
        this.solar_radiation = solar_radiation;
    }

}
