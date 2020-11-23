package com.adam.api.response;

import com.adam.model.Record;
import java.util.ArrayList;

public class GetBookHistoryResponse extends APIResponse {
    private String bookName;

    private ArrayList<Record> Record;

    public ArrayList<com.adam.model.Record> getRecord() {
        return Record;
    }

    public void setRecord(ArrayList<com.adam.model.Record> record) {
        Record = record;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

}
