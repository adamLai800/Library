package com.adam.service;

import com.adam.model.Record;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface RecordService extends CrudRepository<Record, Integer> {

    @Query(value = "SELECT return_date FROM record " +
            "WHERE book_id = ? AND book_status = 1 " , nativeQuery = true)
    Timestamp getReturnDate(String bookId);

    @Query(value = "SELECT * FROM record " +
            "WHERE user_id = ? " , nativeQuery = true)
    ArrayList<Record> getUserRecord(String userId);

    @Query(value = "SELECT * FROM record " +
            "WHERE book_id = ? " , nativeQuery = true)
    ArrayList<Record> getBookRecord(String bookId);

    @Query(value = "SELECT user_id FROM record " +
            "WHERE book_id = ? AND book_status = 1 " , nativeQuery = true)
    String getUserId(String bookId);

}
