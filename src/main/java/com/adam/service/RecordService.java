package com.adam.service;

import com.adam.api.request.UpdateRecordRequest;
import com.adam.model.Record;

import java.sql.Timestamp;
import java.util.ArrayList;

public interface RecordService {

    boolean addRecord(Record record);

    boolean updateActualReturnDate(Timestamp actualReturnDate, UpdateRecordRequest updateRecordRequest);

    Record getRecordByBookIdByBookStatus(String bookId, int bookStatus);

    ArrayList<Record> getRecordByBookId(String bookId);

}
