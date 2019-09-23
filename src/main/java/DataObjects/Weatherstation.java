package DataObjects;

import java.util.HashMap;
import java.util.Map;

public class Weatherstation {

    private String appId;
    private String devId;
    private String hardwareSerial;
    private Integer port;
    private Integer counter;
    private Boolean confirmed;
    private Boolean isRetry;
    private String payloadRaw;
    private PayloadFields payloadFields;
    private Metadata metadata;
    private String downlinkUrl;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getHardwareSerial() {
        return hardwareSerial;
    }

    public void setHardwareSerial(String hardwareSerial) {
        this.hardwareSerial = hardwareSerial;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Boolean getIsRetry() {
        return isRetry;
    }

    public void setIsRetry(Boolean isRetry) {
        this.isRetry = isRetry;
    }

    public String getPayloadRaw() {
        return payloadRaw;
    }

    public void setPayloadRaw(String payloadRaw) {
        this.payloadRaw = payloadRaw;
    }

    public PayloadFields getPayloadFields() {
        return payloadFields;
    }

    public void setPayloadFields(PayloadFields payloadFields) {
        this.payloadFields = payloadFields;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public String getDownlinkUrl() {
        return downlinkUrl;
    }

    public void setDownlinkUrl(String downlinkUrl) {
        this.downlinkUrl = downlinkUrl;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}