package com.adam.repository;

import com.adam.model.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface BookRepository extends CrudRepository<Book, Integer> {

    @Query(value = "SELECT COUNT(*) FROM book", nativeQuery = true)
    Integer getMaxBookCount();

    @Query(value = "SELECT MAX(CAST(SUBSTRING(book_id, 2, length(book_id)-1) AS UNSIGNED)) FROM book", nativeQuery = true)
    Integer getMaxBookId();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM book WHERE book_id = ? ", nativeQuery = true)
    void deleteByBookId(String bookId);

}
