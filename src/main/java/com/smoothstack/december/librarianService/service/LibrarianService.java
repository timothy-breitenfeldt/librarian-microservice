package com.smoothstack.december.librarianService.service;

import java.util.List;

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
    public BookCopy updateBookCopy(BookCopy bookCopy) {
        return this.bookCopyDAO.save(bookCopy);
    }

    @Transactional
    public BookCopy addBookCopy(BookCopy bookCopy) {
        return this.bookCopyDAO.save(bookCopy);
    }

    @Transactional
    public LibraryBranch updateLibraryBranch(LibraryBranch branch) {
        return this.libraryBranchDAO.save(branch);
    }

    public List<Book> getBooks() {
        return this.bookDAO.findAll();
    }

    public List<LibraryBranch> getLibraryBranches() {
        return this.libraryBranchDAO.findAll();
    }

    public List<BookCopy> getBookCopies(Long branchId) {
        return this.bookCopyDAO.findBookCopiesById(branchId);
    }

}
