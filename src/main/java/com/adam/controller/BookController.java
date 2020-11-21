package com.adam.controller;

import com.adam.api.request.AddBookRequest;
import com.adam.api.request.DeleteByBookIdRequest;
import com.adam.api.response.AddBookResponse;
import com.adam.api.response.DeleteByBookIdResponse;
import com.adam.model.Book;
import com.adam.service.BookService;
import com.adam.util.LibraryConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/api/2.0/book")
public class BookController {

    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

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

}
