package com.springJPA.Library.repo;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springJPA.Library.Modal.Book;

@Repository
public interface Bookrepo extends JpaRepository<Book, Long> {

}