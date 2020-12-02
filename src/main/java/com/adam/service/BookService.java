package com.adam.service;

import com.adam.api.request.DeleteByBookIdRequest;
import com.adam.model.Book;

public interface BookService {

    //取最大流水序號
    String getNewBookIdInsertTable();

    //回傳布林值 成功為true 失敗為false
    boolean addBook(Book book);

    boolean deleteByBookId(DeleteByBookIdRequest deleteByBookIdRequest);

    Book getBookAll(String bookId);

    void updateBookAmount(int bookAmount, String bookId);

}
