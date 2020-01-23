package com.smoothstack.december.librarianService.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

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
import com.smoothstack.december.librarianService.entity.BookCopy.BookCopyId;
import com.smoothstack.december.librarianService.entity.LibraryBranch;
import com.smoothstack.december.librarianService.service.LibrarianService;

@RestController
@RequestMapping("/lms/librarian")
public class LibrarianController {

    @Autowired
    private LibrarianService librarianService;

    @PutMapping("/book-copies/books/{bookId}/branches/{branchId}")
    public BookCopy updateBookCopy(@PathVariable @Min(1) Long bookId, @PathVariable @Min(1) Long branchId,
            @RequestBody @Valid BookCopy bookCopy) {
        bookCopy.setId(new BookCopyId(bookId, branchId));
        return this.librarianService.updateBookCopy(bookCopy);
    }

    @PostMapping("/book-copies")
    public BookCopy createBookCopy(@RequestBody @Valid BookCopy bookCopy) {
        return this.librarianService.createBookCopy(bookCopy);
    }

    @PostMapping(path = "/books")
    public Book createBook(@RequestBody @Valid Book book) {
        return this.librarianService.createBook(book);
    }

    @PutMapping("/branches/{branchId}")
    public LibraryBranch updateLibraryBranch(@PathVariable @Min(1) Long branchId,
            @RequestBody @Valid LibraryBranch branch) {
        branch.setId(branchId);
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

    @GetMapping("/book-copies/branches/{branchId}")
    public List<BookCopy> getBookCopies(@PathVariable @Min(1) Long branchId) {
        return this.librarianService.getBookCopies(branchId);
    }

}
