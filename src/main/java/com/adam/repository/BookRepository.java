package com.adam.repository;

import com.adam.model.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;


public interface BookRepository extends CrudRepository<Book, Integer> {

    //此sql語句抓取book_id欄位去掉第一碼取最大流水序號
    @Query(value = "SELECT MAX(CAST(SUBSTRING(book_id, 2, length(book_id)-1) AS UNSIGNED)) FROM book", nativeQuery = true)
    Integer getMaxBookId();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM book WHERE book_id = ? ", nativeQuery = true)
    void deleteByBookId(String bookId);

    @Query(value = "SELECT * FROM book Where book_id = ? " , nativeQuery = true)
    Book getBookAll(String bookId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE book SET book_amount = ? WHERE book_id = ? ", nativeQuery = true)
    void updateBookAmount(int bookAmount, String bookId);

}
