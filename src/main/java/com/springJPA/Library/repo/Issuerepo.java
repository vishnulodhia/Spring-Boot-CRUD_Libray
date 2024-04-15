package com.springJPA.Library.repo;


import com.springJPA.Library.Modal.IssueBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Issuerepo extends JpaRepository<IssueBook, Long> {
}
