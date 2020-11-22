package com.adam.repository;

import com.adam.model.Record;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;

public interface RecordRepository extends CrudRepository<Record, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE record SET actual_return_date = ? ,book_status = 2 " +
            "WHERE user_id = ? AND book_id = ? "
            , nativeQuery = true)
    void updateActualReturnDate(Timestamp actualReturnDate, String userId, String bookId);

//    @Query(value = "SELECT return_date FROM record " +
//            "WHERE book_id = ? AND book_status = 1 " , nativeQuery = true)
//    Timestamp getReturnDate(String bookId);
//
//    @Query(value = "SELECT * FROM record " +
//            "WHERE user_id = ? " , nativeQuery = true)
//    ArrayList<Record> getUserRecord(String userId);
//
//    @Query(value = "SELECT * FROM record " +
//            "WHERE book_id = ? " , nativeQuery = true)
//    ArrayList<Record> getBookRecord(String bookId);
//
//    @Query(value = "SELECT user_id FROM record " +
//            "WHERE book_id = ? AND book_status = 1 " , nativeQuery = true)
//    String getUserId(String bookId);

}
