package com.adam.service.Impl;

import com.adam.api.request.DeleteByBookIdRequest;
import com.adam.model.Book;
import com.adam.repository.BookRepository;
import com.adam.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger LOG = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    @Override
    public String getNewBookIdInsertTable() {
        int getMaxBookId = 0 ;
        try {
            getMaxBookId = bookRepository.getMaxBookId();
        } catch (Exception exception) {

        }
        getMaxBookId ++;
        String getNewBookIdInsertTable = "B" + String.valueOf(getMaxBookId);
        return getNewBookIdInsertTable;
    }

    @Override
    public boolean addBook(Book book) {
        boolean isSucceeded = false;
        try {
            bookRepository.save(book);
            isSucceeded = true;
        } catch (Exception e) {
            LOG.error(" save book failed : ", e);
        }
        return isSucceeded;
    }

    @Override
    public boolean deleteByBookId(DeleteByBookIdRequest deleteByBookIdRequest) {
        boolean isSucceeded = false;
        try {
            bookRepository.deleteByBookId(deleteByBookIdRequest.getBookId());
            isSucceeded = true;
        } catch (Exception e) {
            LOG.info(" deleteByBookId failed : ", e);
        }
        return isSucceeded;
    }
}

