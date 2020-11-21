package com.adam.repository;

import com.adam.model.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

public interface BookRepository extends CrudRepository<Book, Integer> {

    @Query(value = "SELECT MAX(CAST(SUBSTRING(book_id, 2, length(book_id)-1) AS UNSIGNED)) FROM book", nativeQuery = true)
    Integer getMaxBookId();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM book WHERE book_id = ? ", nativeQuery = true)
    void deleteByBookId(String bookId);

    @Query(value = "SELECT COUNT(*) FROM book", nativeQuery = true)
    Integer getMaxBookCount();

    //Check Book Amount
    @Query(value = "SELECT book_amount FROM book Where book_id = ? " , nativeQuery = true)
    Integer getBookAmount(String bookId);

    //get Book Date
    @Query(value = "SELECT book_date FROM book Where book_id = ? " , nativeQuery = true)
    Integer getBookDate(String bookId);

    @Query(value = "SELECT book_name FROM book Where book_id = ? " , nativeQuery = true)
    String getBookName(String bookId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE book SET book_amount = ? WHERE book_id = ? ", nativeQuery = true)
    void updateBookAmount(Integer bookAmount, String bookId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE book SET book_amount = ? WHERE book_id = ? ", nativeQuery = true)
    void updateBookS(Integer bookAmount, String bookId);

}
