package com.adam.service;

import com.adam.api.request.DeleteByBookIdRequest;
import com.adam.model.Book;

public interface BookService {

    String getNewBookIdInsertTable();

    boolean addBook(Book book);

    boolean deleteByBookId(DeleteByBookIdRequest deleteByBookIdRequest);

}
