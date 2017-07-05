package actions;

import com.google.gson.Gson;
import models.Call;
import services.CallService;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ShowCallIdAction {
    private InputStream inputStream;
    private CallService callService;
    private String key;
    private int callId;
    private String callType;

    public String execute() throws Exception {

        //key - is Date, that must be converted back to get our call ID from DB
        Date actual = new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss").parse (key);
        String date = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss").format (actual);
        callId = callService.getCallId (date);

        List<Call> listCallId = callService.getCallById (this.callId);
        callType= getCallType ();
        Gson gson = new Gson ();
        String jsonResult = gson.toJson (listCallId) + " callType=" + callType;

        inputStream = new ByteArrayInputStream (jsonResult.getBytes ("UTF-8"));
        return "SUCCESS";

    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setCallService(CallService callService) {
        this.callService = callService;
    }

    public String getCallType () {
        int eventCount = callService.getTypeByCallId (callId);
        if (eventCount == 5) {
            return "Regular call";
        }
        if (eventCount == 4) {
            return  "Cancelled call";
        }
        if (eventCount == 2) {
            return "Non-dialed call";
        }
        return "obscure";
    }
}


