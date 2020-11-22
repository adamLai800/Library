package com.adam.api.request;

import java.sql.Timestamp;

public class GetBookWhereRequest {

    private String bookId;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
