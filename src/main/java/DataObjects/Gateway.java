package DataObjects;

import java.util.HashMap;
import java.util.Map;

public class Gateway {

    private String gtwId;
    private Integer timestamp;
    private String time;
    private Integer channel;
    private Integer rssi;
    private Double snr;
    private Integer rfChain;
    private Double latitude;
    private Double longitude;
    private String locationSource;
    private Integer altitude;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getGtwId() {
        return gtwId;
    }

    public void setGtwId(String gtwId) {
        this.gtwId = gtwId;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getRssi() {
        return rssi;
    }

    public void setRssi(Integer rssi) {
        this.rssi = rssi;
    }

    public Double getSnr() {
        return snr;
    }

    public void setSnr(Double snr) {
        this.snr = snr;
    }

    public Integer getRfChain() {
        return rfChain;
    }

    public void setRfChain(Integer rfChain) {
        this.rfChain = rfChain;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getLocationSource() {
        return locationSource;
    }

    public void setLocationSource(String locationSource) {
        this.locationSource = locationSource;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}