package com.ujjwal.job_service.repository;

import com.ujjwal.job_service.entity.UserAccount;   // ✅ add this import
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByUsername(String username);
}
