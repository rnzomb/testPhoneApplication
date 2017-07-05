package services;

import models.CallType;
import models.Event;
import mapperInterfaces.EventMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;


public class EventService {
    private EventMapper eventMapper;
    private SqlSession sqlSession;

    public EventService() throws Exception {
    }

    public void setEventMapper(EventMapper eventMapper) {
        this.eventMapper = eventMapper;
    }

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }


    public List<Event> getDataByCaller(int caller) {
        eventMapper = sqlSession.getMapper (EventMapper.class);
        return eventMapper.getDataByCaller (caller);
    }

    public List<CallType> getTypesByCaller(int caller) {
        eventMapper = sqlSession.getMapper (EventMapper.class);
        return eventMapper.getTypesByCaller (caller);
    }

    public void insertEvent(Event event) {
        eventMapper = sqlSession.getMapper (EventMapper.class);
        eventMapper.insertEvent (event);
    }
}
