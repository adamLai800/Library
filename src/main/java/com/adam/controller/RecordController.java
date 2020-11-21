//package com.adam.controller;
//
//import com.adam.model.Record;
//import com.adam.model.UserRecordHistory;
//import com.adam.repository.BookRepository;
//import com.adam.repository.Impl.RecordRepositoryImpl;
//import com.adam.repository.RecordRepository;
//import com.adam.service.Impl.RecordServiceImpl;
//import com.adam.service.RecordService;
//import com.adam.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.HashMap;
//
//@Controller
//@RequestMapping(path="/record")
//public class RecordController {
//
//    @Autowired
//    private RecordRepositoryImpl recordRepositoryImpl;
//
//    @Autowired
//    private RecordRepository recordRepository;
//
//    @Autowired
//    private RecordService recordService;
//
//    @Autowired
//    private BookRepository bookRepository;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private RecordServiceImpl recordServiceImpl;
//
//    //AddRecord
//    @PostMapping(path = "/addRecord")
//    public @ResponseBody
//    String addRecord(
//            @RequestParam String userId,
//            @RequestParam String bookId) {
//        String addRecordMessage = null;
//        addRecordMessage = recordRepositoryImpl.borrowBook(bookId);
//        if(addRecordMessage == "ok") {
//            Calendar borrowDate = Calendar.getInstance();
//            Record record = new Record();
//            record.setUserId(userId);
//            record.setBookId(bookId);
//            record.setBorrowDate(new Timestamp(borrowDate.getTimeInMillis()));
//            borrowDate.add(Calendar.DATE, bookRepository.getBookDate(bookId));
//            record.setReturnDate(new Timestamp(borrowDate.getTimeInMillis()));
//            record.setBookstatus(1);
//            bookRepository.updateBookAmount(0, bookId);
//            recordRepository.save(record);
//            addRecordMessage = "已完成借書手續";
//        }
//        return addRecordMessage;
//    }
//
//    //updateRecord
//    @GetMapping(path = "/updateRecord")
//    public @ResponseBody
//    String updateRecord(
//            @RequestParam String userId,
//            @RequestParam String bookId) {
//        String updateRecorMessage = null;
//        updateRecorMessage = recordRepositoryImpl.returnBook(bookId);
//        if (updateRecorMessage == "ok") {
//            Calendar actualReturnDate = Calendar.getInstance();
//            bookRepository.updateBookAmount(1, bookId);
//            recordRepository.updateActualReturnDate(
//                    new Timestamp(actualReturnDate.getTimeInMillis()), userId, bookId);
//            updateRecorMessage = "已完成歸還手續";
//        }
//        return updateRecorMessage;
//    }
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
//}
