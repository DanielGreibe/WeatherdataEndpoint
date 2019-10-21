public class Weatherdata {

    private int averageWindSpeed;
    private double barometerData;
    private int outsideHumidity;
    private double outsideTemperature;
    private int rainRate;
    private int solarRadiation;


    public Weatherdata build()
    {
        Weatherdata weatherdata = new Weatherdata();
        weatherdata.averageWindSpeed = this.averageWindSpeed;
        weatherdata.barometerData = this.barometerData;
        weatherdata.outsideHumidity = this.outsideHumidity;
        weatherdata.outsideTemperature = this.outsideTemperature;
        weatherdata.rainRate = this.rainRate;
        weatherdata.solarRadiation = this.solarRadiation;

        return weatherdata;
    }

    public Weatherdata averageWindSpeed(int averageWindSpeed)
    {
        this.averageWindSpeed = averageWindSpeed;

        return this;
    }

    public Weatherdata barometerData(double barometerData)
    {
        this.barometerData = barometerData;
        return this;
    }

    public Weatherdata outsideHumidity(int outsideHumidity)
    {
        this.outsideHumidity = outsideHumidity;
        return this;
    }

    public Weatherdata outsideTemperature(double outsideTemperature)
    {
        this.outsideTemperature = outsideTemperature;
        return this;
    }

    public Weatherdata rainRate(int rainRate)
    {
        this.rainRate = rainRate;
        return this;
    }

    public Weatherdata solarRadiation(int solarRadiation)
    {
        this.solarRadiation = solarRadiation;
        return this;
    }

    public int getAverageWindSpeed() {
        return averageWindSpeed;
    }

    public void setAverageWindSpeed(int averageWindSpeed) {
        this.averageWindSpeed = averageWindSpeed;
    }

    public double getBarometerData() {
        return barometerData;
    }

    public void setBarometerData(double barometerData) {
        this.barometerData = barometerData;
    }

    public int getOutsideHumidity() {
        return outsideHumidity;
    }

    public void setOutsideHumidity(int outsideHumidity) {
        this.outsideHumidity = outsideHumidity;
    }

    public double getOutsideTemperature() {
        return outsideTemperature;
    }

    public void setOutsideTemperature(double outsideTemperature) {
        this.outsideTemperature = outsideTemperature;
    }

    public int getRainRate() {
        return rainRate;
    }

    public void setRainRate(int rainRate) {
        this.rainRate = rainRate;
    }

    public int getSolarRadiation() {
        return solarRadiation;
    }

    public void setSolarRadiation(int solarRadiation) {
        this.solarRadiation = solarRadiation;
    }

}
