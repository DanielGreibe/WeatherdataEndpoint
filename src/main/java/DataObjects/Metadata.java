package DataObjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Metadata {

    private String time;
    private Double frequency;
    private String modulation;
    private String dataRate;
    private String codingRate;
    private List<Gateway> gateways = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getFrequency() {
        return frequency;
    }

    public void setFrequency(Double frequency) {
        this.frequency = frequency;
    }

    public String getModulation() {
        return modulation;
    }

    public void setModulation(String modulation) {
        this.modulation = modulation;
    }

    public String getDataRate() {
        return dataRate;
    }

    public void setDataRate(String dataRate) {
        this.dataRate = dataRate;
    }

    public String getCodingRate() {
        return codingRate;
    }

    public void setCodingRate(String codingRate) {
        this.codingRate = codingRate;
    }

    public List<Gateway> getGateways() {
        return gateways;
    }

    public void setGateways(List<Gateway> gateways) {
        this.gateways = gateways;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}