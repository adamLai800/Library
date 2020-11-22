package com.adam.api.response;

import com.adam.model.Record;

public class AddRecordResponse extends APIResponse {
    private Record record;

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }
}
