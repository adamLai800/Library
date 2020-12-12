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
    @Query(value = "UPDATE record SET actual_return_date = ? ,book_status = ? " +
            "WHERE user_id = ? AND book_id = ? ", nativeQuery = true)
    void updateActualReturnDate(Timestamp actualReturnDate, int bookStatus, String userId, String bookId);

    @Query(value = "SELECT * FROM record " +
            "WHERE book_id = ? AND book_status = ? " , nativeQuery = true)
    Record getRecordByBookIdByBookStatus(String bookId, int bookStatus);

    @Query(value = "SELECT * FROM record WHERE book_id = ? ", nativeQuery = true)
    ArrayList<Record> getRecordByBookId(String bookId);

    @Query(value = "SELECT * FROM record WHERE user_id = ? ", nativeQuery = true)
    ArrayList<Record> getRecordByUserId(String userId);

}
