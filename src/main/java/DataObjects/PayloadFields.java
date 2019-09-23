package DataObjects;

import java.util.HashMap;
import java.util.Map;

public class PayloadFields {

    private Double avgWindSpeed;
    private Integer barTrend;
    private Double barometerData;
    private Integer dataVersion;
    private Integer davisType;
    private Double dayEt;
    private Integer dayRain;
    private Integer forecastIcons;
    private Integer frameId;
    private Integer leafWetness;
    private Integer outsideHumidity;
    private Double outsideTemperature;
    private Integer rainRate;
    private Integer reportId;
    private Integer soilMoisture;
    private Integer solarRadiation;
    private Integer uplinkId;
    private Integer uv;
    private Integer windDirection;
    private Double windSpeed;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Double getAvgWindSpeed() {
        return avgWindSpeed;
    }

    public void setAvgWindSpeed(Double avgWindSpeed) {
        this.avgWindSpeed = avgWindSpeed;
    }

    public Integer getBarTrend() {
        return barTrend;
    }

    public void setBarTrend(Integer barTrend) {
        this.barTrend = barTrend;
    }

    public Double getBarometerData() {
        return barometerData;
    }

    public void setBarometerData(Double barometerData) {
        this.barometerData = barometerData;
    }

    public Integer getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(Integer dataVersion) {
        this.dataVersion = dataVersion;
    }

    public Integer getDavisType() {
        return davisType;
    }

    public void setDavisType(Integer davisType) {
        this.davisType = davisType;
    }

    public Double getDayEt() {
        return dayEt;
    }

    public void setDayEt(Double dayEt) {
        this.dayEt = dayEt;
    }

    public Integer getDayRain() {
        return dayRain;
    }

    public void setDayRain(Integer dayRain) {
        this.dayRain = dayRain;
    }

    public Integer getForecastIcons() {
        return forecastIcons;
    }

    public void setForecastIcons(Integer forecastIcons) {
        this.forecastIcons = forecastIcons;
    }

    public Integer getFrameId() {
        return frameId;
    }

    public void setFrameId(Integer frameId) {
        this.frameId = frameId;
    }

    public Integer getLeafWetness() {
        return leafWetness;
    }

    public void setLeafWetness(Integer leafWetness) {
        this.leafWetness = leafWetness;
    }

    public Integer getOutsideHumidity() {
        return outsideHumidity;
    }

    public void setOutsideHumidity(Integer outsideHumidity) {
        this.outsideHumidity = outsideHumidity;
    }

    public Double getOutsideTemperature() {
        return outsideTemperature;
    }

    public void setOutsideTemperature(Double outsideTemperature) {
        this.outsideTemperature = outsideTemperature;
    }

    public Integer getRainRate() {
        return rainRate;
    }

    public void setRainRate(Integer rainRate) {
        this.rainRate = rainRate;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getSoilMoisture() {
        return soilMoisture;
    }

    public void setSoilMoisture(Integer soilMoisture) {
        this.soilMoisture = soilMoisture;
    }

    public Integer getSolarRadiation() {
        return solarRadiation;
    }

    public void setSolarRadiation(Integer solarRadiation) {
        this.solarRadiation = solarRadiation;
    }

    public Integer getUplinkId() {
        return uplinkId;
    }

    public void setUplinkId(Integer uplinkId) {
        this.uplinkId = uplinkId;
    }

    public Integer getUv() {
        return uv;
    }

    public void setUv(Integer uv) {
        this.uv = uv;
    }

    public Integer getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(Integer windDirection) {
        this.windDirection = windDirection;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}