package com.group.libraryapp.controller.book;

import com.group.libraryapp.dto.book.request.BookCreateRequest;
import com.group.libraryapp.service.book.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookContorller {
    private final BookService bookService;

    public BookContorller(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    public void saveUser(@RequestBody BookCreateRequest bookCreateRequest) {
        bookService.saveBook(bookCreateRequest);
    }
}
