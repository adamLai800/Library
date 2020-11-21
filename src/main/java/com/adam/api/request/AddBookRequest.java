package com.adam.api.request;

public class AddBookRequest {

    private String bookName;

    private Integer bookDate;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getBookDate() {
        return bookDate;
    }

    public void setBookDate(Integer bookDate) {
        this.bookDate = bookDate;
    }

}
