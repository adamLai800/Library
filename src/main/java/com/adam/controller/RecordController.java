package com.adam.controller;

import com.adam.model.Record;
import com.adam.repository.BookRepository;
import com.adam.repository.Impl.RecordRepositoryImpl;
import com.adam.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Calendar;

@Controller
@RequestMapping(path="/libray")
public class RecordController {

    @Autowired
    private RecordRepositoryImpl recordRepositoryImpl;

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private BookRepository bookRepository;

    //AddRecord
    @PostMapping(path = "/addRecord")
    public @ResponseBody
    String addRecord(
            @RequestParam String userId,
            @RequestParam String bookId) {
        String addRecordMessage = null;
        addRecordMessage = recordRepositoryImpl.borrowBook(bookId);
        if(addRecordMessage == "ok") {
            Calendar borrowDate = Calendar.getInstance();
            Record record = new Record();
            record.setUserId(userId);
            record.setBookId(bookId);
            record.setBorrowDate(new Timestamp(borrowDate.getTimeInMillis()));
            System.out.print(record.getBorrowDate());
            borrowDate.add(Calendar.DATE, bookRepository.getBookDate(bookId));
            record.setReturnDate(new Timestamp(borrowDate.getTimeInMillis()));
            System.out.print(record.getReturnDate());
            record.setBookstatus(1);
            bookRepository.updateBookAmount(0, bookId);
            recordRepository.save(record);
            addRecordMessage = "已完成借書手續";
        }
        return addRecordMessage;
    }

    //updateRecord
    @GetMapping(path = "/updateRecord")
    public @ResponseBody
    String updateRecord(
            @RequestParam String userId,
            @RequestParam String bookId) {
        String updateRecorMessage = null;
        updateRecorMessage = recordRepositoryImpl.returnBook(bookId);
        if (updateRecorMessage == "ok") {
            Calendar actualReturnDate = Calendar.getInstance();
            bookRepository.updateBookAmount(1, bookId);
            recordRepository.updateActualReturnDate(
                    new Timestamp(actualReturnDate.getTimeInMillis()), userId, bookId);
            updateRecorMessage = "已完成歸還手續";
        }
        return updateRecorMessage;
    }

}
