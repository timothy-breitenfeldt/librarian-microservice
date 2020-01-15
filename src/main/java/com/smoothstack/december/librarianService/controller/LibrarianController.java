package com.smoothstack.december.librarianService.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<String> updateBookCopy(@RequestBody BookCopy bookCopy) {
        try {
        this.librarianService.updateBookCopy(bookCopy.getBookId(), bookCopy.getBranchId(), bookCopy.getAmount());
        return new ResponseEntity<String>("success", HttpStatus.ACCEPTED);
        } catch(SQLException | ClassNotFoundException e) {
            return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/bookCopies")
    public ResponseEntity<Map<String, Object>> addBookCopy(@RequestBody BookCopy bookCopy) {
        try {
        Map<String, Object> response = this.librarianService.addBookCopy(bookCopy.getBookId(), bookCopy.getBranchId(), bookCopy.getAmount());
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        } catch(SQLException | ClassNotFoundException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", e.toString());
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/branches")
    public ResponseEntity<String> updateLibraryBranch(@RequestBody LibraryBranch branch) {
        try {
        this.librarianService.updateLibraryBranch(branch.getBranchId(), branch.getName(), branch.getAddress());
        return new ResponseEntity<String>("success", HttpStatus.ACCEPTED);
        } catch(SQLException | ClassNotFoundException e) {
            return new ResponseEntity<String>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        try {
        List<Book> response = this.librarianService.getBooks();
        return new ResponseEntity<List<Book>>(response, HttpStatus.ACCEPTED);
        } catch(SQLException | ClassNotFoundException e) {
            return new ResponseEntity<List<Book>>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/branches")
    public ResponseEntity<List<LibraryBranch>> getLibraryBranches() {
        try {
        List<LibraryBranch> response = this.librarianService.getLibraryBranches();
        return new ResponseEntity<List<LibraryBranch>>(response, HttpStatus.ACCEPTED);
        } catch(SQLException | ClassNotFoundException e) {
            return new ResponseEntity<List<LibraryBranch>>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/bookCopies/{branchId}")
    public ResponseEntity<List<BookCopy>> getBookCopies(@PathVariable int branchId) {
        try {
        List<BookCopy> response = this.librarianService.getBookCopies(branchId);
        return new ResponseEntity<List<BookCopy>>(response, HttpStatus.ACCEPTED);
        } catch(SQLException | ClassNotFoundException e) {
            return new ResponseEntity<List<BookCopy>>(HttpStatus.BAD_REQUEST);
        }
    }

}
