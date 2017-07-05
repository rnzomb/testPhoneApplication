package mapperInterfaces;

import models.CallType;
import models.Event;

import java.util.List;

public interface EventMapper {

    void insertEvent(Event event);

    List<Event> getDataByCaller (int caller);
    List<CallType> getTypesByCaller(int callId);
}

