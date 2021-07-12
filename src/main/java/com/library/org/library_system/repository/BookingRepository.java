package com.library.org.library_system.repository;

import com.library.org.library_system.model.Book;
import com.library.org.library_system.model.BookUserDto;
import com.library.org.library_system.model.Booking;
import com.library.org.library_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

/*  @Query("SELECT new com.roytuts.spring.data.jpa.left.right.inner.cross.join.dto.DeptEmpDto(d.name, e.name, e.email, e.address) "
          + "FROM Department d INNER JOIN d.employees e")
  List<DeptEmpDto> fetchEmpDeptDataInnerJoin();*/

  @Query("select new com.library.org.library_system.model.BookUserDto(bk.id,bk.title,bk.author,us.id,us.first_name,us.last_name,us.phone_number) " +
          "from Booking b " +
          " inner join b.book bk inner join b.user us where bk.availability = false")
  List <BookUserDto> findTakenBooks();

}
