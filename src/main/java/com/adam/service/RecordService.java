package com.adam.service;

import com.adam.api.request.UpdateRecordRequest;
import com.adam.model.Record;

import java.sql.Timestamp;

public interface RecordService {

    boolean addRecord(Record record);

    boolean updateActualReturnDate(Timestamp actualReturnDate, UpdateRecordRequest updateRecordRequest);

    Record getRecordByBookId(String bookId);

}
