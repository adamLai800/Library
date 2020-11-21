package com.adam.api.response;

import com.adam.model.Book;

public class AddBookResponse extends APIResponse {
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

}

