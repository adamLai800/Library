package com.adam.service.Impl;

import com.adam.model.Book;
import com.adam.repository.BookRepository;
import com.adam.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public String getNewBookIdInsertTable() {
        int getMaxBookId = 0 ;
        try {
            getMaxBookId = bookRepository.getMaxBookId();
        }catch (Exception exception){

        }
        getMaxBookId ++;
        String getNewBookIdInsertTable = "B" + String.valueOf(getMaxBookId);
        return getNewBookIdInsertTable;
    }

    @Override
    public void addBook(Book book) {
        bookRepository.save(book);
    }
}

