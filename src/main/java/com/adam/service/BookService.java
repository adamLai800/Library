package com.adam.service;

import com.adam.api.request.DeleteByUserIdRequest;
import com.adam.model.Book;
import com.adam.model.User;

public interface BookService {

    String getNewBookIdInsertTable();

    void addBook(Book book);

//    void deleteByBookId(DeleteByBookIdRequest deleteByBookIdRequest);

}
