package com.library.org.library_system.repository;

import com.library.org.library_system.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface BooksRepository extends JpaRepository<Book,Long> {

    @Modifying
    @Query(value = "UPDATE books SET availability=:availability WHERE id=:id_book ", nativeQuery = true)
    void updateBook(@Param("id_book") Long id_book,@Param("availability") boolean availability);

    @Query(value = "select  * from books where books.availability=true", nativeQuery = true)
    Iterable<Book> freeBoks();

}
