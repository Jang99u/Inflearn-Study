package com.group.libraryapp.domain.loan;

import javax.persistence.*;

@Entity
public class userLoanHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "user_id")
    private Long userId;
    @Column(nullable = false, name = "book_name")
    private String bookName;
    @Column(nullable = false, name = "is_return")
    private boolean isReturn;

    protected userLoanHistory() {}

    public userLoanHistory(Long userId, String name) {
        this.userId = userId;
        this.bookName = name;
        this.isReturn = false;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return bookName;
    }

    public boolean isReturn() {
        return isReturn;
    }
}
