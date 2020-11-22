package com.adam.controller;

import com.adam.api.request.AddBookRequest;
import com.adam.api.request.AddRecordRequest;
import com.adam.api.request.UpdateRecordRequest;
import com.adam.api.response.AddBookResponse;
import com.adam.api.response.AddRecordResponse;
import com.adam.api.response.UpdateRecordResponse;
import com.adam.model.Book;
import com.adam.model.Record;
import com.adam.model.UserRecordHistory;
import com.adam.service.BookService;
import com.adam.service.RecordService;
import com.adam.util.LibraryConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

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
        } catch (Exception e) {
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
        } catch (Exception e) {
            updateRecordResponse.setCode(LibraryConstant.OTHER);
            updateRecordResponse.setErrorMsg(LibraryConstant.OTHERERROR);
        }
        return updateRecordResponse;

    }
//
//    @GetMapping(path = "/getBookWhere")
//    public @ResponseBody
//    HashMap<String,Timestamp> getBookWhered(
//            @RequestParam String bookId) {
//        HashMap<String,Timestamp> getBookWheredMessage = new HashMap<String,Timestamp>();
//        int bookAmount = bookRepository.getBookAmount(bookId);
//        Calendar currentlyDate = Calendar.getInstance();
//        if (bookAmount == 0) {
//            getBookWheredMessage.put(userService.getUserName(recordService.getUserId(bookId))
//                    , recordService.getReturnDate(bookId));
//        }else{
//            getBookWheredMessage.put("此書還在圖書館"
//                    , new Timestamp(currentlyDate.getTimeInMillis()));
//
//        }
//        return getBookWheredMessage;
//    }
//
//    @GetMapping(path = "/getUserHistory")
//    public @ResponseBody
//    HashMap<String, ArrayList<UserRecordHistory>> getUserHistory(
//            @RequestParam String userId) {
//        HashMap<String, ArrayList<UserRecordHistory>> getUserHistory =
//                new HashMap<String, ArrayList<UserRecordHistory>>();
//
//        ArrayList<UserRecordHistory> userRecordHistory = recordServiceImpl.getUserRecordHistory(userId);
//        getUserHistory.put(userService.getUserName(userId), userRecordHistory);
//        return getUserHistory;
//    }
//
//    @GetMapping(path = "/getBookHistory")
//    public @ResponseBody ArrayList<UserRecordHistory> getBookRecordHistory(
//            @RequestParam String booId) {
//        HashMap<String, ArrayList<UserRecordHistory>> getUserHistory =
//                new HashMap<String, ArrayList<UserRecordHistory>>();
//
//        ArrayList<UserRecordHistory> userRecordHistory = recordServiceImpl.getBookRecordHistory(booId);
//        return userRecordHistory;
//    }

}
