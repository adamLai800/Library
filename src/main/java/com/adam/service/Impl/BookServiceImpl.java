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
        int getMaxBookId = 0;
        try {
            getMaxBookId = bookRepository.getMaxBookId();
        } catch (Exception ex) {
            LOG.error(" insert into book failed : ", ex);
        }
        getMaxBookId ++;
        String getNewBookIdInsertTable = "B" + String.valueOf(getMaxBookId);
        return getNewBookIdInsertTable;
    }

    //Book物件傳給bookRepository用Override方式
    @Override
    public boolean addBook(Book book) {
        boolean isSucceeded = false;
        try {
            //用Autowired方式產生bookRepository的instance
            //再用bookRepository.save的方法 然後透過此方法將Book物件寫進資料庫
            bookRepository.save(book);
            isSucceeded = true;
        } catch (Exception ex) {
            LOG.error(" save book failed : ", ex);
        }
        return isSucceeded;
    }

    @Override
    public boolean deleteByBookId(DeleteByBookIdRequest deleteByBookIdRequest) {
        boolean isSucceeded = false;
        try {
            bookRepository.deleteByBookId(deleteByBookIdRequest.getBookId());
            isSucceeded = true;
        } catch (Exception ex) {
            LOG.info(" deleteByBookId failed : ", ex);
        }
        return isSucceeded;
    }

    @Override
    public Book getBookAll(String bookId) {
        Book book = bookRepository.getBookAll(bookId);
        return book;
    }

    @Override
    public void updateBookAmount(int bookAmount, String bookId) {
        bookRepository.updateBookAmount(bookAmount, bookId);
    }

}

