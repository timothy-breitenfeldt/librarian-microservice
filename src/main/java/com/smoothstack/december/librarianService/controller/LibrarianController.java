package com.smoothstack.december.librarianService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public BookCopy addBookCopy(@RequestBody BookCopy bookCopy) {
        return this.librarianService.addBookCopy(bookCopy);
    }

    @PutMapping("/branches")
    public LibraryBranch updateLibraryBranch(@RequestBody LibraryBranch branch) {
        return this.librarianService.updateLibraryBranch(branch);
    }

    @GetMapping("/books")
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
