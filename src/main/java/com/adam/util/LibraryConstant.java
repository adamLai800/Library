package com.adam.util;

public class LibraryConstant {

    //Code
    public static final Integer OK = 20000;
    public static final Integer NO = 90001;
    public static final Integer OTHER = 99999;

    //Message
    public static final String ADD_USER_MSG = " User insert successfully!! ";
    public static final String DELETE_USER_MSG = " User delete successfully!!";
    public static final String ADD_BOOK_MSG = " Book insert successfully!! ";
    public static final String DELETE_BOOK_MSG = " Book delete successfully!!";
    public static final String ADD_RECORD_MSG = " 已完成借書手續!! ";
    public static final String UPDATE_RECORD_MSG = " 已完成歸還手續!! ";
    public static final String GET_BOOK_IN_USER_MSG = " 此書已被借!! ";
    public static final String GET_BOOK_IN_LIBRARY_MSG = " 此書在圖書館內!! ";
    public static final String GET_BOOK_HISTORY = " Book History!! ";
    public static final String GET_USER_HISTORY = " User History!! ";


    //Error Message
    public static final String ADD_USER_ERROR = " userService.addUser failure !! ";
    public static final String DELETE_USER_ERROR = " userService.deleteByUserId failure !! ";
    public static final String ADD_BOOK_ERROR = " bookService.addBook failure !! ";
    public static final String DELETE_BOOK_ERROR = " bookService.deleteByUserId failure !! ";
    public static final String ADD_RECORD_ERROR = " 借書手續錯誤 !! ";
    public static final String UPDATE_RECORD_ERROR = " 歸還手續錯誤!! ";

    public static final String OTHERERROR = " Other error !! ";

    //Book
    public static final int BOOK_AMOUNT_ONLY_ONE = 1;
    public static final int BOOK_AMOUNT_ADD = 1;
    public static final int BOOK_AMOUNT_LESS = 0;

    //Record
    public static final int RECORD_STATUS_ADD = 1;
    public static final int RECORD_STATUS_UPDATE = 2;

}
