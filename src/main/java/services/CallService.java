package services;

import models.Call;
import mapperInterfaces.CallMapper;
import org.apache.ibatis.session.SqlSession;


import java.util.List;


public class CallService {
    private CallMapper callMapper;
    private SqlSession sqlSession;

    public CallService() throws Exception {
    }

    public void setCallMapper(CallMapper callMapper) {
        this.callMapper = callMapper;
    }

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public int countAll() {
        return callMapper.countAll ();
    }

    public int countAllFiltered(String where){
        callMapper = sqlSession.getMapper (CallMapper.class);
        return callMapper.countAllFiltered (where);
    }

    public List<Call> getAll(String sortCol, String sortDir, int start, int length, String eventName) {
        callMapper = sqlSession.getMapper (CallMapper.class);
        return callMapper.getAll (sortCol, sortDir, start, length, eventName);
    }

    public List<Call> getAllSave(String sortCol, String sortDir, String where) {
        callMapper = sqlSession.getMapper (CallMapper.class);
        return callMapper.getAllSave (sortCol, sortDir, where);
    }

    public int getCallId(String date) {
        callMapper = sqlSession.getMapper (CallMapper.class);
        return callMapper.getCallId (date);
    }

    public List<Call> getCallById(int callId) {
        callMapper = sqlSession.getMapper (CallMapper.class);
        return callMapper.getCallById (callId);
    }

    public int getTypeByCallId(int callId) {
        callMapper = sqlSession.getMapper (CallMapper.class);
        return callMapper.getTypeByCallId (callId);
    }

    public void insertCall(Call call) {
        callMapper = sqlSession.getMapper (CallMapper.class);
        callMapper.insertCall (call);
    }

}
