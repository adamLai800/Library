package com.adam.controller;

import com.adam.api.request.AddBookRequest;
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

    //AddBook
    @PostMapping(path = "/addBook")
    public @ResponseBody
    String addBook(
            @RequestBody AddBookRequest addBookRequest ) {

        Book book = new Book();
        book.setBookId(bookService.getNewBookIdInsertTable());
        book.setBookName(addBookRequest.getBookName());
        book.setBookDate(addBookRequest.getBookDate());
        book.setBookAmount(LibraryConstant.BOOK_AMOUNT_ONLY_ONE);
        bookService.addBook(book);
        return "Book Saved";
    }

//    //deleteByBookId
//    @DeleteMapping(path = "/deleteByBookId")
//    public @ResponseBody String deleteByBookId(@RequestParam String bookId) {
//        bookRepository.deleteByBookId(bookId);
//        return "Book Delete OK";
//    }

}
