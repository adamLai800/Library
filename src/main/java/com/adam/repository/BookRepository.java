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

    @Query(value = "SELECT * FROM book Where book_id = ? " , nativeQuery = true)
    Book getBookAll(String bookId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE book SET book_amount = ? WHERE book_id = ? ", nativeQuery = true)
    void updateBookAmount(int bookAmount, String bookId);


//    //Borrow book
//    public String borrowBook(String bookId) {
//        String borrowMessage = null;
//        int bookAmount = bookRepository.getBookAmount(bookId);
//        if(bookAmount == 0){
//            borrowMessage = "此書已借出";
//        }else{
//            borrowMessage = "ok";
//        }
//        return borrowMessage;
//    }
//
//    //Return book
//    public String returnBook(String bookId) {
//        String returnMessage = null;
//        int bookAmount = bookRepository.getBookAmount(bookId);
//
//        if(bookAmount == 0){
//            returnMessage = "ok";
//        }else{
//            returnMessage = "此書已歸還";
//        }
//        return returnMessage;
//    }
}
