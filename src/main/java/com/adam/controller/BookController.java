package com.adam.controller;

import com.adam.api.request.AddBookRequest;
import com.adam.api.request.DeleteByBookIdRequest;
import com.adam.api.request.GetBookHistoryRequest;
import com.adam.api.request.GetBookWhereRequest;
import com.adam.api.response.*;
import com.adam.model.Book;
import com.adam.model.Record;
import com.adam.model.User;
import com.adam.service.BookService;
import com.adam.service.RecordService;
import com.adam.service.UserService;
import com.adam.util.LibraryConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

//@Controller是告訴Spring這是Controller
@Controller
//這裡先設定專案部分URL /api/2.0/book區塊
@RequestMapping(path="/api/2.0/book")
public class BookController {

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    //這裡引用Autowired 不需要自己透過new方式產生instance
    private BookService bookService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private UserService userService;

    /*新增書的API 方式是用Post
    前端會先request 物件給我 我就會接收到並回傳AddBookRequest內容
     */
    @PostMapping(path = "/addBook") //
    public @ResponseBody
    AddBookResponse addBook(@RequestBody AddBookRequest addBookRequest) {
        boolean isSucceeded = false;
        //記得講
        Book book = new Book();

        //bookId的產生是去取目前bookId欄位目前最大流水序號並加一產生的
        book.setBookId(bookService.getNewBookIdInsertTable());

        book.setBookName(addBookRequest.getBookName());
        book.setBookDate(addBookRequest.getBookDate());

        //bookAmount 設定為定值 數量為一本
        book.setBookAmount(LibraryConstant.BOOK_AMOUNT_ONLY_ONE);
        //透過bookService.addBook方法將book object 寫進資料庫
        isSucceeded = bookService.addBook(book);
        AddBookResponse addBookResponse = new AddBookResponse();
        try {
            //如果true
            if (isSucceeded) {
                //就會addBookRespons會塞入Book物件
                addBookResponse.setBook(book);
                //狀態值 客製化編碼 20000 為OK
                addBookResponse.setCode(LibraryConstant.OK);
                //資訊內容 Book insert successfully!!
                addBookResponse.setMsg(LibraryConstant.ADD_BOOK_MSG);
            } else {
                //狀態值 客製化會參數 90001 為NO
                addBookResponse.setCode(LibraryConstant.NO);
                //資訊內容 bookService.addBook failure
                addBookResponse.setErrorMsg(LibraryConstant.ADD_BOOK_ERROR);
            }
        } catch (Exception ex) {
            //狀態值 客製化會參數 99999 為其他錯誤
            //目前錯誤機制尚未建立完善 之後會繼續調整 重構
            addBookResponse.setCode(LibraryConstant.OTHER);
            addBookResponse.setErrorMsg(LibraryConstant.OTHERERROR);
        }
        //回傳Response
        return addBookResponse;
    }

    @DeleteMapping(path = "/deleteByBookId")
    public @ResponseBody
    DeleteByBookIdResponse
    deleteByBookId(@RequestBody DeleteByBookIdRequest deleteByBookIdRequest) {
        boolean isSucceeded = false;
        isSucceeded = bookService.deleteByBookId(deleteByBookIdRequest);
        DeleteByBookIdResponse deleteByBookIdResponse = new DeleteByBookIdResponse();
        try {
            if (isSucceeded) {
                deleteByBookIdResponse.setCode(LibraryConstant.OK);
                deleteByBookIdResponse.setMsg(LibraryConstant.DELETE_BOOK_MSG);
            } else {
                deleteByBookIdResponse.setCode(LibraryConstant.NO);
                deleteByBookIdResponse.setErrorMsg(LibraryConstant.DELETE_BOOK_ERROR);
            }
        } catch (Exception ex) {
            deleteByBookIdResponse.setCode(LibraryConstant.OTHER);
            deleteByBookIdResponse.setErrorMsg(LibraryConstant.OTHERERROR);
        }
        return deleteByBookIdResponse;
    }

    @GetMapping(path = "/getBookWhere")
    public @ResponseBody
    GetBookWhereResponse getBookWhere
            (@RequestBody GetBookWhereRequest getBookWhereRequest) {
        GetBookWhereResponse getBookWhereResponse = new GetBookWhereResponse();
        Book book = bookService.getBookAll(getBookWhereRequest.getBookId());
        if (book.getBookAmount() == 0) {
            Record record = recordService.getRecordByBookIdByBookStatus
                    (getBookWhereRequest.getBookId(), LibraryConstant.RECORD_STATUS_ADD);
            User user = userService.getUserAll(record.getUserId());
            getBookWhereResponse.setUserName(user.getUserName());
            getBookWhereResponse.setBookName(book.getBookName());
            getBookWhereResponse.setReturnDate(record.getReturnDate());
            getBookWhereResponse.setCode(LibraryConstant.OK);
            getBookWhereResponse.setMsg(LibraryConstant.GET_BOOK_IN_USER_MSG);
        }else{
            getBookWhereResponse.setCode(LibraryConstant.OK);
            getBookWhereResponse.setMsg(LibraryConstant.GET_BOOK_IN_LIBRARY_MSG);
        }
        return getBookWhereResponse;
    }

    @GetMapping(path = "/getBookHistory")
    public @ResponseBody
    GetBookHistoryResponse getBookRecordHistory
            (@RequestBody GetBookHistoryRequest getBookHistoryRequest) {
        GetBookHistoryResponse getBookHistoryResponse = new GetBookHistoryResponse();
        try {
            Book book = bookService.getBookAll(getBookHistoryRequest.getBookId());
            ArrayList<Record> getRecordByBookId = recordService.getRecordByBookId(getBookHistoryRequest.getBookId());
            getBookHistoryResponse.setBookName(book.getBookName());
            getBookHistoryResponse.setRecord(getRecordByBookId);
            getBookHistoryResponse.setCode(LibraryConstant.OK);
            getBookHistoryResponse.setMsg(LibraryConstant.GET_BOOK_HISTORY);
        } catch (Exception ex) {
            getBookHistoryResponse.setCode(LibraryConstant.OTHER);
            getBookHistoryResponse.setErrorMsg(LibraryConstant.OTHERERROR);
        }
        return getBookHistoryResponse;
    }

}
