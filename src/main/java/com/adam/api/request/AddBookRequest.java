package com.adam.api.request;

//傳入書名與可借天數
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
