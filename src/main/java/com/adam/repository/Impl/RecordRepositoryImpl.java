package com.adam.repository.Impl;

import com.adam.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RecordRepositoryImpl {

    @Autowired
    private BookRepository bookRepository;

    //Borrow book
    public String borrowBook(String bookId) {
        String borrowMessage = null;
        int bookAmount = bookRepository.getBookAmount(bookId);
        if(bookAmount == 0){
            borrowMessage = "此書已借出";
        }else{
            borrowMessage = "ok";
        }
        return borrowMessage;
    }

    //Return book
    public String returnBook(String bookId) {
        String returnMessage = null;
        int bookAmount = bookRepository.getBookAmount(bookId);

        if(bookAmount == 0){
            returnMessage = "ok";
        }else{
            returnMessage = "此書已歸還";
        }
        return returnMessage;
    }

}
