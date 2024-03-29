package com.group.libraryapp.controller.loan;

import com.group.libraryapp.dto.loan.request.LoanBookRequest;
import com.group.libraryapp.dto.loan.request.ReturnBookRequest;
import com.group.libraryapp.service.loan.LoanService;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/book/loan")
    public void loanBook(@RequestBody LoanBookRequest loanBookRequest) {
        loanService.loanBook(loanBookRequest);
    }

    @PutMapping("/book/return")
    public void returnBook(@RequestBody ReturnBookRequest returnBookRequest) {
        loanService.returnBook(returnBookRequest);
    }
}
