package mapperInterfaces;

import models.Call;

import java.util.List;

public interface CallMapper {

    void insertCall(Call call);
    List<Call> getCallById(int callId);
    List<Call> getAll(String sortCol, String sortDir, int start, int length, String where);
    List<Call> getAllSave(String sortCol, String sortDir, String where);

    int countAll();
    int countAllFiltered(String where);
    int getCallId(String date);
    int getTypeByCallId(int callId);


}

