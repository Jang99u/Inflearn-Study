package com.group.libraryapp.domain.loan;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {
    boolean existsByBookNameAndIsReturn(String bookName, boolean isReturn);

    Optional<UserLoanHistory> findByUserIdAndBookNameAndIsReturn(Long userId, String bookName, boolean isReturn);
}
