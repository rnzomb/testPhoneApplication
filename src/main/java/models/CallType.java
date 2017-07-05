package models;

public class CallType {
    private int eventCount;
    private int callId;
    private String callType;

    public int getEventCount() {
        return eventCount;
    }

    public void setEventCount(int eventCount) {
        if (eventCount == 5) {
            this.callType = "Regular call";
        }
        if (eventCount == 4) {
            this.callType = "Cancelled call";
        }
        if (eventCount == 2) {
            this.callType = "Non-dialed call";
        }
        this.eventCount = eventCount;
    }

    public int getCallId() {
        return callId;
    }

    public void setCallId(int callId) {

        this.callId = callId;
    }

}
