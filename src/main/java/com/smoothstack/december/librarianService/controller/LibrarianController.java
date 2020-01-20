package com.smoothstack.december.librarianService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.december.librarianService.entity.Author;
import com.smoothstack.december.librarianService.entity.Book;
import com.smoothstack.december.librarianService.entity.BookCopy;
import com.smoothstack.december.librarianService.entity.BookCopy.BookCopyId;
import com.smoothstack.december.librarianService.entity.Genre;
import com.smoothstack.december.librarianService.entity.LibraryBranch;
import com.smoothstack.december.librarianService.entity.Publisher;
import com.smoothstack.december.librarianService.service.LibrarianService;

@RestController
@RequestMapping("/lms/librarian")
public class LibrarianController {

    @Autowired
    private LibrarianService librarianService;

    @PutMapping("/bookCopies")
    public BookCopy updateBookCopy(@RequestParam Long bookId, @RequestParam Long branchId, @RequestParam Long amount) {
        BookCopy bookCopy = new BookCopy();
        bookCopy.setId(new BookCopyId(bookId, branchId));
        bookCopy.setAmount(amount);
        return this.librarianService.updateBookCopy(bookCopy);
    }

    @PostMapping("/bookCopies")
    public BookCopy createBookCopy(@RequestParam Long bookId, @RequestParam Long branchId, @RequestParam Long amount) {
        BookCopy bookCopy = new BookCopy();
        bookCopy.setId(new BookCopyId(bookId, branchId));
        bookCopy.setAmount(amount);
        return this.librarianService.createBookCopy(bookCopy);
    }

    @PostMapping("/books")
    public Book createBook(@RequestParam String title, @RequestParam Long publisherId, @RequestParam Long authorId,
            @RequestParam Long genreId) {
        Publisher publisher = new Publisher();
        Book book = new Book();
        Author author = new Author();
        Genre genre = new Genre();

        publisher.setId(publisherId);
        author.setId(authorId);
        genre.setId(genreId);
        book.setPublisher(publisher);
        book.addAuthor(author);
        book.addGenre(genre);
        return this.librarianService.createBook(book);
    }

    @PutMapping("/branches")
    public LibraryBranch updateLibraryBranch(@RequestParam Long branchId, @RequestParam String name,
            @RequestParam String address) {
        LibraryBranch branch = new LibraryBranch();
        branch.setId(branchId);
        branch.setName(name);
        branch.setAddress(address);
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
