package com.adam.repository;

import com.adam.model.Record;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;
import java.sql.Timestamp;

public interface RecordRepository extends CrudRepository<Record, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE record SET actual_return_date = ? ,book_status = 2 " +
            "WHERE user_id = ? AND book_id = ? "
            , nativeQuery = true)
    void updateActualReturnDate(Timestamp actualReturnDate, String userId, String bookId);

}
