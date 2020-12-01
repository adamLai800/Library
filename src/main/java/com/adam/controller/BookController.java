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


@Controller
@RequestMapping(path="/api/2.0/book")
public class BookController {

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private RecordService recordService;

    @Autowired
    private UserService userService;


    @GetMapping(path = "/getOK")
    public @ResponseBody String getOK() {
        return "OK";
    }

    //addBook
    @PostMapping(path = "/addBook") //
    public @ResponseBody
    AddBookResponse addBook(@RequestBody AddBookRequest addBookRequest) {
        boolean isSucceeded = false;

        Book book = new Book();
        book.setBookId(bookService.getNewBookIdInsertTable());
        book.setBookName(addBookRequest.getBookName());
        book.setBookDate(addBookRequest.getBookDate());
        book.setBookAmount(LibraryConstant.BOOK_AMOUNT_ONLY_ONE);

        isSucceeded = bookService.addBook(book);
        AddBookResponse addBookResponse = new AddBookResponse();
        try {
            if (isSucceeded) {
                addBookResponse.setBook(book);
                addBookResponse.setCode(LibraryConstant.OK);
                addBookResponse.setMsg(LibraryConstant.ADD_BOOK_MSG);
            } else {
                addBookResponse.setCode(LibraryConstant.NO);
                addBookResponse.setErrorMsg(LibraryConstant.ADD_BOOK_ERROR);
            }
        } catch (Exception e) {
            addBookResponse.setCode(LibraryConstant.OTHER);
            addBookResponse.setErrorMsg(LibraryConstant.OTHERERROR);
        }

        return addBookResponse;
    }

    //deleteByBookId
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
        } catch (Exception e) {
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
        } catch (Exception e) {
            getBookHistoryResponse.setCode(LibraryConstant.OTHER);
            getBookHistoryResponse.setErrorMsg(LibraryConstant.OTHERERROR);
        }
        return getBookHistoryResponse;
    }

}
