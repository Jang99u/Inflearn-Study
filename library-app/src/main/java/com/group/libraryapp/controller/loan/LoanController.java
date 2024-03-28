package com.group.libraryapp.controller.loan;

import com.group.libraryapp.dto.loan.request.LoanBookRequest;
import com.group.libraryapp.service.loan.LoanService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
