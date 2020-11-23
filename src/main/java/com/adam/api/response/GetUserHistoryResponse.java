package com.adam.api.response;

import com.adam.model.Record;
import java.util.ArrayList;

public class GetUserHistoryResponse extends APIResponse {

    private String UserName;

    private ArrayList<Record> Record;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public ArrayList<com.adam.model.Record> getRecord() {
        return Record;
    }

    public void setRecord(ArrayList<com.adam.model.Record> record) {
        Record = record;
    }

}
