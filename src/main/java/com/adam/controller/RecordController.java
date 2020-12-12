package com.adam.controller;

import com.adam.api.request.AddRecordRequest;
import com.adam.api.request.UpdateRecordRequest;
import com.adam.api.response.AddRecordResponse;
import com.adam.api.response.UpdateRecordResponse;
import com.adam.model.Book;
import com.adam.model.Record;
import com.adam.service.BookService;
import com.adam.service.RecordService;
import com.adam.util.LibraryConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Calendar;

@Controller
@RequestMapping(path="/api/2.0/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private BookService bookService;

    //AddRecord
    @PostMapping(path = "/addRecord")
    public @ResponseBody
    AddRecordResponse addRecord(@RequestBody AddRecordRequest addRecordRequest) {
        boolean isSucceeded = false;
        Calendar borrowDate = Calendar.getInstance();
        Record record = new Record();
        record.setUserId(addRecordRequest.getUserId());
        record.setBookId(addRecordRequest.getBookId());
        record.setBorrowDate(new Timestamp(borrowDate.getTimeInMillis()));
        Book book = bookService.getBookAll(addRecordRequest.getBookId());
        borrowDate.add(Calendar.DATE, book.getBookDate());
        record.setReturnDate(new Timestamp(borrowDate.getTimeInMillis()));
        record.setBookstatus(LibraryConstant.RECORD_STATUS_ADD);
        bookService.updateBookAmount(LibraryConstant.BOOK_AMOUNT_LESS, book.getBookId());
        AddRecordResponse addRecordResponse = new AddRecordResponse();
        isSucceeded = recordService.addRecord(record);
        try {
            if (isSucceeded) {
                addRecordResponse.setRecord(record);
                addRecordResponse.setCode(LibraryConstant.OK);
                addRecordResponse.setMsg(LibraryConstant.ADD_RECORD_MSG);
            } else {
                addRecordResponse.setCode(LibraryConstant.NO);
                addRecordResponse.setErrorMsg(LibraryConstant.ADD_RECORD_ERROR);
            }
        } catch (Exception ex) {
            addRecordResponse.setCode(LibraryConstant.OTHER);
            addRecordResponse.setErrorMsg(LibraryConstant.OTHERERROR);
        }
        return addRecordResponse;
    }

    //UpdateRecord
    @PutMapping(path = "/updateRecord")
    public @ResponseBody
    UpdateRecordResponse updateRecord(@RequestBody UpdateRecordRequest updateRecordRequest) {
        boolean isSucceeded = false;
        UpdateRecordResponse updateRecordResponse = new UpdateRecordResponse();
        Calendar actualReturnDate = Calendar.getInstance();
        Book book = bookService.getBookAll(updateRecordRequest.getBookId());
        bookService.updateBookAmount(LibraryConstant.BOOK_AMOUNT_ADD, book.getBookId());
        isSucceeded = recordService.updateActualReturnDate(
                new Timestamp(actualReturnDate.getTimeInMillis()), updateRecordRequest);
        try {
            if (isSucceeded) {
                updateRecordResponse.setCode(LibraryConstant.OK);
                updateRecordResponse.setMsg(LibraryConstant.UPDATE_RECORD_MSG);
            } else {
                updateRecordResponse.setCode(LibraryConstant.NO);
                updateRecordResponse.setErrorMsg(LibraryConstant.UPDATE_RECORD_ERROR);
            }
        } catch (Exception ex) {
            updateRecordResponse.setCode(LibraryConstant.OTHER);
            updateRecordResponse.setErrorMsg(LibraryConstant.OTHERERROR);
        }
        return updateRecordResponse;

    }

}
