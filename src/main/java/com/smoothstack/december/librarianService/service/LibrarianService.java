package com.smoothstack.december.librarianService.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smoothstack.december.librarianService.dao.BookCopyDAO;
import com.smoothstack.december.librarianService.dao.BookDAO;
import com.smoothstack.december.librarianService.dao.LibraryBranchDAO;
import com.smoothstack.december.librarianService.entity.Book;
import com.smoothstack.december.librarianService.entity.BookCopy;
import com.smoothstack.december.librarianService.entity.LibraryBranch;

@Service
public class LibrarianService {
    
    @Autowired
    private BookDAO bookDAO;
    
    @Autowired
    private BookCopyDAO bookCopyDAO;
    
    @Autowired
    private LibraryBranchDAO libraryBranchDAO;

    @Transactional
    public void updateBookCopy(int bookId, int branchId, int amount) throws SQLException, ClassNotFoundException{
            this.bookCopyDAO.updateBookCopy(bookId, branchId, amount);
    }
    
    @Transactional
    public Map<String, Object> addBookCopy(int bookId, int branchId, int amount) throws SQLException, ClassNotFoundException {
            return this.bookCopyDAO.createBookCopy(bookId, branchId, amount);
    }
    
    @Transactional
    public void updateLibraryBranch(int branchId, String branchName, String branchAddress) throws SQLException, ClassNotFoundException {
            this.libraryBranchDAO.updateBranch(branchId, branchName, branchAddress);
    }
    
    public List<Book> getBooks() throws SQLException, ClassNotFoundException {
            return this.bookDAO.getBooks();
    }
    
    public List<LibraryBranch> getLibraryBranches() throws SQLException, ClassNotFoundException {
            return this.libraryBranchDAO.getBranchs();
    }
    
    public List<BookCopy> getBookCopies(int branchId) throws SQLException, ClassNotFoundException {
            return this.bookCopyDAO.getBookCopys(branchId);
    }
    
}
