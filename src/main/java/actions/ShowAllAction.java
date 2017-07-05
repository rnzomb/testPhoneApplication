package actions;

import com.google.gson.Gson;
import models.Call;
import services.CallService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

public class ShowAllAction {
    private InputStream inputStream;
    private CallService callService;
    private List<Call> listAll;
    private int count;
    private int countFiltered;
    private int start;
    private int length;
    private static String sortCol;
    private static String sortDir;
    private String callerSearch;
    private String receiverSearch;
    private String eventNameSearch;
    private static String where;


    public String execute() throws Exception {
        where = null;                          // reset static variable to reset filters
        if (callerSearch != null) {
            where = "WHERE " + callerSearch;
        }
        if (eventNameSearch != null) {
            if (where != null) {
                where += " AND " + eventNameSearch;
            } else {
                where = "WHERE " + eventNameSearch;
            }
        }
        if (receiverSearch != null) {
            if (where != null) {
                where += " AND " + receiverSearch;
            } else {
                where = "WHERE " + receiverSearch;
            }
        }
        count = callService.countAll ();
        countFiltered = count;

        if (where != null) {
            countFiltered = callService.countAllFiltered (where);
        }
        listAll = callService.getAll (sortCol, sortDir, start, length, where);
        Gson gson = new Gson ();
        String jsonString = gson.toJson (listAll);
        String jsonResult = "{\n" +
                "  \"iTotalRecords\": " + count + ",\n" +
                "  \"iTotalDisplayRecords\": " + countFiltered + ",\n" +
                "  \"aaData\": " + jsonString + "}";
        jsonResult = jsonResult.replaceAll ("callId", "DT_RowId");
        inputStream = new ByteArrayInputStream (jsonResult.getBytes ("UTF-8"));
        return "SUCCESS";
    }

    public static String getSortCol() {
        return sortCol;
    }

    public static String getSortDir() {
        return sortDir;
    }

    public static String getWhere() {
        return where;
    }

    public void setiDisplayStart(int iDisplayStart) {
        this.start = iDisplayStart;
    }

    public void setiDisplayLength(int iDisplayLength) {
        this.length = iDisplayLength;
    }

    public void setiSortCol_0(int iSortCol_0) {
        if (iSortCol_0 == 0) {
            this.sortCol = "caller";
        }
        if (iSortCol_0 == 2) {
            this.sortCol = "receiver";
        }

    }

    public void setsSortDir_0(String sSortDir_0) {
        this.sortDir = sSortDir_0;
    }

    public void setsSearch_0(String sSearch_0) {
        if (!sSearch_0.equals ("")) {
            this.callerSearch = "caller LIKE \"" + sSearch_0 + "%\"";
        }
    }

    public void setsSearch_1(String sSearch_1) {
        sSearch_1 = sSearch_1.substring (1, (sSearch_1.length () - 1));
        this.eventNameSearch = "event_name = \"" + sSearch_1 + "\"";
    }

    public void setsSearch_2(String sSearch_2) {
        if (!sSearch_2.equals ("")) {
            this.receiverSearch = "receiver LIKE \"" + sSearch_2 + "%\"";
        }
    }

    public void setCallService(CallService callService) {
        this.callService = callService;
    }

    public InputStream getInputStream() {
        return inputStream;
    }


}
