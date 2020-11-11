package com.adam.controller;

import com.adam.model.Book;
import com.adam.repository.BookRepository;
import com.adam.repository.Impl.BookRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/libray")
public class Bookcontroller {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookRepositoryImpl bookRepositoryImpl;

    //Add
    @PostMapping(path = "/addbook") // Map ONLY POST Requests
    public @ResponseBody
    String addNew(
            @RequestParam String bookName,
            @RequestParam Integer bookDate ) {

        Book book = new Book();
        book.setBookId(bookRepositoryImpl.getNewBookIdInsertTable());
        book.setBookName(bookName);
        book.setBookDate(bookDate);
        book.setBookAmount(1);
        bookRepository.save(book);
        return "BookSaved";
    }

    @DeleteMapping(path = "/deleteByBookId") // Map ONLY Delete All Requests
    public @ResponseBody String deleteByBookId(@RequestParam String bookId) {
        bookRepository.deleteByBookId(bookId);
        return "Book Delete OK";
    }

}
