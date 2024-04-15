package com.springJPA.Library.repo;

import com.springJPA.Library.Modal.ReturnBook;
import com.springJPA.Library.Modal.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Returnrepo extends JpaRepository<ReturnBook, Long> {

}
