package com.smoothstack.december.librarianService.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smoothstack.december.librarianService.entity.Book;
import com.smoothstack.december.librarianService.entity.BookCopy;
import com.smoothstack.december.librarianService.entity.LibraryBranch;
import com.smoothstack.december.librarianService.service.LibrarianService;

@RestController
@RequestMapping("/librarian")
public class LibrarianController {
    
    @Autowired
    private LibrarianService librarianService;
    
    @PutMapping("/bookCopies")
    public ResponseEntity<String> updateBookCopy(int bookId, int branchId, int amount) {
        String response = this.librarianService.updateBookCopy(bookId, branchId, amount);
        return new ResponseEntity<String>(response, HttpStatus.ACCEPTED);
    }
    
    @PostMapping("/bookCopies")
    public ResponseEntity<Map<String, Object>>  addBookCopy(int bookId, int branchId, int amount) {
        Map<String, Object> response = this.librarianService.addBookCopy(bookId, branchId, amount);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    
    @PutMapping("/branches")
    public ResponseEntity<String> updateLibraryBranch(int branchId, String branchName, String branchAddress) {
        String response = this.librarianService.updateLibraryBranch(branchId, branchName, branchAddress);
        return new ResponseEntity<String>(response, HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> response = this.librarianService.getBooks();
        return new ResponseEntity<List<Book>>(response, HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/branches")
    public ResponseEntity<List<LibraryBranch>> getLibraryBranches() {
        List<LibraryBranch> response = this.librarianService.getLibraryBranches();
        return new ResponseEntity<List<LibraryBranch>>(response, HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/bookCopies")
    public ResponseEntity<List<BookCopy>> getBookCopies(int branchId) {
        List<BookCopy> response = this.librarianService.getBookCopies(branchId);
        return new ResponseEntity<List<BookCopy>>(response, HttpStatus.ACCEPTED);
    }

}
