package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
    private int recordId;
    private int callId;
    private int caller;
    private int receiver;
    private String recordEventId;
    private String talkDuration;
    private String recordDate;

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getCallId() {
        return callId;
    }

    public void setCallId(int callId) {
        this.callId = callId;
    }

    public int getCaller() {
        return caller;
    }

    public void setCaller(int caller) {
        this.caller = caller;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public String getRecordEventId() {
        return recordEventId;
    }

    public void setRecordEventId(String recordEventId) {
        this.recordEventId = recordEventId;
    }

    public String getTalkDuration() {
        return talkDuration;
    }

    public void setTalkDuration(String talkDuration) {
        this.talkDuration = talkDuration;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) throws ParseException {
        Date actual = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(recordDate);
        this.recordDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(actual);
    }

    public void setRecordDateSqlFormat(String recordDate) {
        this.recordDate = recordDate;
    }
}