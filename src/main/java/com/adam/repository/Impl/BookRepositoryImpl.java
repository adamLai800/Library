package com.adam.repository.Impl;

import com.adam.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BookRepositoryImpl {

    @Autowired
    private BookRepository bookRepository;

    public String getNewBookIdInsertTable() {
        int getMaxBookId = bookRepository.getMaxBookCount();
        if(getMaxBookId != 0 ){
            getMaxBookId = bookRepository.getMaxBookId();
            getMaxBookId = getMaxBookId + 1;
        }else{
            getMaxBookId = 1;
        }
        String getNewBookIdInsertTable = "B" + String.valueOf(getMaxBookId);
        return getNewBookIdInsertTable;
    }

}
