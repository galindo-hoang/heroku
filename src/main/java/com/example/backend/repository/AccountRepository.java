package com.example.backend.repository;

import com.example.backend.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findAccountEntityByEmail(String email);

    Optional<AccountEntity> findAccountEntityByEmailAndPassword(String email, String password);
}
