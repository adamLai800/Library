package com.adam.service.Impl;

import com.adam.model.Record;
import com.adam.model.UserRecordHistory;
import com.adam.repository.BookRepository;
import com.adam.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class RecordServiceImpl{

    @Autowired
    private RecordService recordService;

    @Autowired
    private BookRepository bookRepository;

    public ArrayList<UserRecordHistory> getUserRecordHistory(String userId){
        ArrayList<UserRecordHistory> userRecordHistorys = new ArrayList<UserRecordHistory>();
        ArrayList<Record> getRecordHistory  = recordService.getUserRecord(userId);
        for(Record recordHistory  : getRecordHistory) {
            UserRecordHistory userRecordHistory = new UserRecordHistory();
            String bookName = bookRepository.getBookName(recordHistory.getBookId());
            userRecordHistory.setBookName(bookName);
            userRecordHistory.setBorrowDate(recordHistory.getBorrowDate());
            userRecordHistory.setActualReturnDate(recordHistory.getActualReturnDate());
            if (recordHistory.getBookStatus() == 1){
                userRecordHistory.setBookStatus("尚未歸還");
            }else if (recordHistory.getBookStatus() == 2) {
                userRecordHistory.setBookStatus("已歸還");
            }
            userRecordHistorys.add(userRecordHistory);
        }
        return userRecordHistorys;
    }

    public ArrayList<UserRecordHistory> getBookRecordHistory(String bookId){
        ArrayList<UserRecordHistory> bookRecordHistorys = new ArrayList<UserRecordHistory>();
        ArrayList<Record> getRecordHistory  = recordService.getBookRecord(bookId);
        for(Record recordHistory  : getRecordHistory) {
            UserRecordHistory bookRecordHistory = new UserRecordHistory();
            String bookName = bookRepository.getBookName(recordHistory.getBookId());
            bookRecordHistory.setBookName(bookName);
            bookRecordHistory.setBorrowDate(recordHistory.getBorrowDate());
            bookRecordHistory.setActualReturnDate(recordHistory.getActualReturnDate());
            if (recordHistory.getBookStatus() == 1){
                bookRecordHistory.setBookStatus("尚未歸還");
            }else if (recordHistory.getBookStatus() == 2) {
                bookRecordHistory.setBookStatus("已歸還");
            }
            bookRecordHistorys.add(bookRecordHistory);
        }
        return bookRecordHistorys;
    }
}
