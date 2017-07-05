package actions;

import com.google.gson.Gson;
import models.Call;
import org.apache.struts2.ServletActionContext;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;
import services.CallService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SaveCSVAction {
    private CallService callService;
    private List<Call> listAll;
    private InputStream inputStream;
    private String filename;

    public String execute() throws IOException {

        listAll = callService.getAllSave (ShowAllAction.getSortCol (), ShowAllAction.getSortDir (), ShowAllAction.getWhere ());

        Gson gson = new Gson ();
        String jsonString = gson.toJson (listAll);

        String jsonFormatted = "{\"csv\": " + jsonString + "}";
        JSONObject objectResult = new JSONObject (jsonFormatted);
        JSONArray arrayResultData = objectResult.getJSONArray ("csv");

        String csvFile = CDL.toString (arrayResultData);

        Date date = new Date ();
        SimpleDateFormat myFormat = new SimpleDateFormat ("yyyyMMdd");

        filename = "all_records_" + myFormat.format (date) + ".csv";
    //    inputStream = new BufferedInputStream (new ByteArrayInputStream (csvFile.getBytes ("UTF-8")));

        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/csv");
        response.setHeader("Content-disposition", "attachment;filename="+filename);
        PrintWriter out = response.getWriter();
        out.write(csvFile);
        out.flush();

        return "SUCCESS";
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public String getFilename() {
        return filename;
    }

    public void setCallService(CallService callService) {
        this.callService = callService;
    }

}
