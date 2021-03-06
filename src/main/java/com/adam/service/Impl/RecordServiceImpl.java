package com.adam.service.Impl;

import com.adam.api.request.UpdateRecordRequest;
import com.adam.model.Record;
import com.adam.repository.RecordRepository;
import com.adam.service.BookService;
import com.adam.service.RecordService;
import com.adam.util.LibraryConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.ArrayList;

@Service
public class RecordServiceImpl implements RecordService{

    private static final Logger LOG = LoggerFactory.getLogger(RecordServiceImpl.class);

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private RecordService recordService;

    @Autowired
    private BookService bookService;

    @Override
    public boolean addRecord(Record record) {
        boolean isSucceeded = false;
        try {
            recordRepository.save(record);
            isSucceeded = true;
        } catch (Exception ex) {
            LOG.error(" save record failed : ", ex);
        }
        return isSucceeded;
    }

    @Override
    public boolean updateActualReturnDate(Timestamp actualReturnDate, UpdateRecordRequest updateRecordRequest) {
        boolean isSucceeded = false;
        try {
            recordRepository.updateActualReturnDate(actualReturnDate,
                    LibraryConstant.RECORD_STATUS_UPDATE,
                    updateRecordRequest.getUserId(),
                    updateRecordRequest.getBookId());
            isSucceeded = true;
        } catch (Exception ex) {
            LOG.error(" update record failed : ", ex);
        }
        return isSucceeded;
    }

    @Override
    public Record getRecordByBookIdByBookStatus(String bookId, int bookStatus) {
        Record getRecordByBookIdByBookStatus
                = recordRepository.getRecordByBookIdByBookStatus(bookId, bookStatus);
        return getRecordByBookIdByBookStatus;
    }

    @Override
    public ArrayList<Record> getRecordByBookId(String bookId) {
        ArrayList<Record> getRecordByBookId = recordRepository.getRecordByBookId(bookId);
        return getRecordByBookId;
    }

    @Override
    public ArrayList<Record> getRecordByUserId(String userId) {
        ArrayList<Record> getRecordByUserId = recordRepository.getRecordByUserId(userId);
        return getRecordByUserId;
    }

}
