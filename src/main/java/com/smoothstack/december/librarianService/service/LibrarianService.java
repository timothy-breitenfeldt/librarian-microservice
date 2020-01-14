package com.smoothstack.december.librarianService.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.smoothstack.december.librarianService.dao.BookCopyDAO;
import com.smoothstack.december.librarianService.dao.BookDAO;
import com.smoothstack.december.librarianService.dao.LibraryBranchDAO;
import com.smoothstack.december.librarianService.entity.Book;
import com.smoothstack.december.librarianService.entity.BookCopy;
import com.smoothstack.december.librarianService.entity.LibraryBranch;

public class LibrarianService {
    
    @Autowired
    private BookDAO bookDAO;
    
    @Autowired
    private BookCopyDAO bookCopyDAO;
    
    @Autowired
    private LibraryBranchDAO libraryBranchDAO;
    
    public String updateBookCopy(int bookId, int branchId, int amount) {
        try {
            this.bookCopyDAO.updateBookCopy(bookId, branchId, amount);
            return "successfully updated.";
        } catch(SQLException | ClassNotFoundException e) {
            return "failed to update.";
        }
    }
    
    public Map<String, Object> addBookCopy(int bookId, int branchId, int amount) {
        try {
            return this.bookCopyDAO.createBookCopy(bookId, branchId, amount);
        } catch(SQLException | ClassNotFoundException e) {
            return null;
        }
    }
    
    public String updateLibraryBranch(int branchId, String branchName, String branchAddress) {
        try {
            this.libraryBranchDAO.updateBranch(branchId, branchName, branchAddress);
            return "successfully updated.";
        } catch(SQLException | ClassNotFoundException e) {
            return "failed to update.";
        }
    }
    
    public List<Book> getBooks() {
        try {
            return this.bookDAO.getBooks();
        } catch(SQLException | ClassNotFoundException e) {
            return new ArrayList<Book>();
        }
    }
    
    public List<LibraryBranch> getLibraryBranches() {
        try {
            return this.libraryBranchDAO.getBranchs();
        } catch(SQLException | ClassNotFoundException e) {
            return new ArrayList<LibraryBranch>();
        }
    }
    
    public List<BookCopy> getBookCopies(int branchId) {
        try {
            return this.bookCopyDAO.getBookCopys(branchId);
        } catch(SQLException | ClassNotFoundException e) {
            return new ArrayList<BookCopy>();
        }
    }
    
}
