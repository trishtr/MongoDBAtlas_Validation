package models;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
public class parsedHL7 {

    private ObjectId _id;
    private String eventType;
    private String eventSubType;
    private String clientId;
    private String eventSource;
    private String timestamp;
    private Map payload;
    private List<LogContainer> log;

    public ObjectId getId() {
        return _id;
    }

    public parsedHL7 setId(ObjectId id) {
        this._id = id;
        return this;
    }

    public String getEventType() {
        return eventType;
    }

    public parsedHL7 setEventType(String eventType) {
        this.eventType = eventType;
        return this;
    }

    public String getEventSubType() {
        return eventSubType;
    }

    public parsedHL7 setEventSubType(String eventSubType) {
        this.eventSubType = eventSubType;
        return this;
    }

    public String getClientId() {
        return clientId;
    }

    public parsedHL7 setClientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String getEventSource() {
        return eventSource;
    }

    public parsedHL7 setEventSource(String eventSource) {
        this.eventSource = eventSource;
        return this;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public parsedHL7 setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Map getPayload() {
        return payload;
    }

    public parsedHL7 setPayload(Map payload) {
        this.payload = payload;
        return this;
    }



    public List<Map> getLog() {
        return log;
    }

    public parsedHL7 setLog(List<Map> log) {
        this.log = log;
        return this;
    }

    public parsedHL7(ObjectId id, String eventType, String eventSubType, String clientId, String eventSource, String timestamp, Map payload) {
        this._id = id;
        this.eventType = eventType;
        this.eventSubType = eventSubType;
        this.clientId = clientId;
        this.eventSource = eventSource;
        this.timestamp = timestamp;
        this.payload = payload;
    }

    public String toString() {
        return super.toString();
    }

}
