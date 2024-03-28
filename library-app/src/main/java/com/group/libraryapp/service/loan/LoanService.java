package com.group.libraryapp.service.loan;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.loan.userLoanHistory;
import com.group.libraryapp.domain.loan.userLoanHistoryRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.loan.request.LoanBookRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoanService {
    private final userLoanHistoryRepository userLoanHistoryRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public LoanService(userLoanHistoryRepository userLoanHistoryRepository, BookRepository bookRepository, UserRepository userRepository) {
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

        userLoanHistoryRepository.save(new userLoanHistory(user.getId(), book.getName()));
    }
}
