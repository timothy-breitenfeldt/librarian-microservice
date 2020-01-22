package com.smoothstack.december.librarianService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.december.librarianService.entity.Book;
import com.smoothstack.december.librarianService.entity.BookCopy;
import com.smoothstack.december.librarianService.entity.LibraryBranch;
import com.smoothstack.december.librarianService.service.LibrarianService;

@RestController
@RequestMapping("/lms/librarian")
public class LibrarianController {

    @Autowired
    private LibrarianService librarianService;

    @PutMapping("/bookCopies")
    public BookCopy updateBookCopy(@RequestBody BookCopy bookCopy) {
        return this.librarianService.updateBookCopy(bookCopy);
    }

    @PostMapping("/bookCopies")
    public BookCopy createBookCopy(@RequestBody BookCopy bookCopy) {
        return this.librarianService.createBookCopy(bookCopy);
    }

    @PostMapping(path = "/books", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public Book createBook(@RequestBody Book book) {
        return this.librarianService.createBook(book);
    }

    @PutMapping("/branches")
    public LibraryBranch updateLibraryBranch(@RequestBody LibraryBranch branch) {
        return this.librarianService.updateLibraryBranch(branch);
    }

    @GetMapping(value = "/books")
    public List<Book> getBooks() {
        return this.librarianService.getBooks();
    }

    @GetMapping("/branches")
    public List<LibraryBranch> getLibraryBranches() {
        return this.librarianService.getLibraryBranches();
    }

    @GetMapping("/bookCopies/{branchId}")
    public List<BookCopy> getBookCopies(@PathVariable Long branchId) {
        return this.librarianService.getBookCopies(branchId);
    }

}
