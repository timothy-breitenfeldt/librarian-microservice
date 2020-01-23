package com.smoothstack.december.librarianService.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.smoothstack.december.librarianService.entity.Book;
import com.smoothstack.december.librarianService.entity.BookCopy;
import com.smoothstack.december.librarianService.entity.BookCopy.BookCopyId;
import com.smoothstack.december.librarianService.entity.LibraryBranch;
import com.smoothstack.december.librarianService.exception.ArgumentMissingException;
import com.smoothstack.december.librarianService.exception.IllegalRelationReferenceException;
import com.smoothstack.december.librarianService.service.LibrarianService;

@RestController
@RequestMapping("/lms/librarian")
public class LibrarianController {

    @Autowired
    private LibrarianService librarianService;

    @PostMapping("/book-copies")
    public ResponseEntity<BookCopy> createBookCopy(@RequestBody @Valid BookCopy bookCopy) {
        try {
            return new ResponseEntity<BookCopy>(this.librarianService.createBookCopy(bookCopy), HttpStatus.CREATED);
        } catch (ArgumentMissingException ame) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ame.getMessage(), ame);
        } catch (IllegalRelationReferenceException irre) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, irre.getMessage(), irre);
        }
    }

    @PostMapping(path = "/books")
    public ResponseEntity<Book> createBook(@RequestBody @Valid Book book) {
        try {
            return new ResponseEntity<Book>(this.librarianService.createBook(book), HttpStatus.CREATED);
        } catch (ArgumentMissingException ame) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ame.getMessage(), ame);
        } catch (IllegalRelationReferenceException irre) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, irre.getMessage(), irre);
        }
    }

    @GetMapping(value = "/books")
    public ResponseEntity<List<Book>> getBooks() {
        return new ResponseEntity<List<Book>>(this.librarianService.getBooks(), HttpStatus.OK);
    }

    @GetMapping("/branches")
    public ResponseEntity<List<LibraryBranch>> getLibraryBranches() {
        return new ResponseEntity<List<LibraryBranch>>(this.librarianService.getLibraryBranches(), HttpStatus.OK);
    }

    @GetMapping("/book-copies/branches/{branchId}")
    public ResponseEntity<List<BookCopy>> getBookCopies(@PathVariable @Min(1) Long branchId) {
        try {
            return new ResponseEntity<List<BookCopy>>(this.librarianService.getBookCopiesById(branchId), HttpStatus.OK);
        } catch (IllegalRelationReferenceException irre) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, irre.getMessage(), irre);
        }
    }

    @PutMapping("/book-copies/books/{bookId}/branches/{branchId}")
    public ResponseEntity<BookCopy> updateBookCopy(@PathVariable @Min(1) Long bookId,
            @PathVariable @Min(1) Long branchId, @RequestBody @Valid BookCopy bookCopy) {
        try {
            bookCopy.setId(new BookCopyId(bookId, branchId));
            return new ResponseEntity<BookCopy>(this.librarianService.updateBookCopy(bookCopy), HttpStatus.OK);
        } catch (ArgumentMissingException ame) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ame.getMessage(), ame);
        } catch (IllegalRelationReferenceException irre) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, irre.getMessage(), irre);
        }
    }

    @PutMapping("/branches/{branchId}")
    public ResponseEntity<LibraryBranch> updateLibraryBranch(@PathVariable @Min(1) Long branchId,
            @RequestBody @Valid LibraryBranch branch) {
        try {
            branch.setId(branchId);
            return new ResponseEntity<LibraryBranch>(this.librarianService.updateLibraryBranch(branch), HttpStatus.OK);
        } catch (ArgumentMissingException ame) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ame.getMessage(), ame);
        }
    }

}
