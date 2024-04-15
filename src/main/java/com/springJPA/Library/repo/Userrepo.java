package com.springJPA.Library.repo;

import com.springJPA.Library.Modal.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Userrepo extends JpaRepository<UserDetail, Long> {
    Optional<UserDetail> findByuserName(String username );
}