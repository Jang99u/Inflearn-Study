package com.group.libraryapp.domain.loan;

import org.springframework.data.jpa.repository.JpaRepository;

public interface userLoanHistoryRepository extends JpaRepository<userLoanHistory, Long> {
    boolean existsByBookNameAndIsReturn(String name, boolean isReturn);
}
