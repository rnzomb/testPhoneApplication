package actions;

import com.google.gson.Gson;
import models.CallType;
import models.Event;
import services.EventService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class ShowEventAction {
    private InputStream inputStream;
    private EventService eventService;
    private int caller;

    public String execute() throws Exception {
        List<Event> listEvent = eventService.getDataByCaller(this.caller);
        List<CallType> listCallType = eventService.getTypesByCaller(this.caller);
        List listNew = new ArrayList();
        for (int i = 0; i < listEvent.size(); i++) {
            listNew.add(listEvent.get(i));
            listNew.add(listCallType.get(i));
        }
        Gson gson = new Gson();
        String jsonResult = gson.toJson(listNew);
        jsonResult = jsonResult.replaceAll("},\\{\"eventCount", ",\"eventCount");
        inputStream = new ByteArrayInputStream(jsonResult.getBytes("UTF-8"));
        return "SUCCESS";

    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public int getCaller() {
        return caller;
    }

    public void setCaller(int caller) {
        this.caller = caller;
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }
}
