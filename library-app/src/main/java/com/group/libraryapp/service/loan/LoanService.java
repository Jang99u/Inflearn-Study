package com.group.libraryapp.service.loan;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.loan.UserLoanHistory;
import com.group.libraryapp.domain.loan.UserLoanHistoryRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.loan.request.LoanBookRequest;
import com.group.libraryapp.dto.loan.request.ReturnBookRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoanService {
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public LoanService(UserLoanHistoryRepository userLoanHistoryRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void loanBook(LoanBookRequest loanBookRequest) {
        Book book = bookRepository.findByName(loanBookRequest.getBookName())
                .orElseThrow(IllegalAccessError::new);

        if(userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
            throw new IllegalArgumentException();
        }

        User user =  userRepository.findByName(loanBookRequest.getUserName())
                .orElseThrow(IllegalAccessError::new);

        userLoanHistoryRepository.save(new UserLoanHistory(user.getId(), book.getName()));
    }

    @Transactional
    public void returnBook(ReturnBookRequest returnBookRequest) {
        User user = userRepository.findByName(returnBookRequest.getUserName())
                .orElseThrow(IllegalAccessError::new);

        Book book = bookRepository.findByName(returnBookRequest.getBookName())
                .orElseThrow(IllegalAccessError::new);

        UserLoanHistory userLoanHistory = userLoanHistoryRepository
                .findByUserIdAndBookNameAndIsReturn(user.getId(), book.getName(), false)
                .orElseThrow(IllegalAccessError::new);

        userLoanHistory.doReturn();
    }
}
