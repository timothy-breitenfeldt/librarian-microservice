package com.smoothstack.lms.librarianservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smoothstack.lms.librarianservice.dao.AuthorDAO;
import com.smoothstack.lms.librarianservice.dao.BookCopyDAO;
import com.smoothstack.lms.librarianservice.dao.BookDAO;
import com.smoothstack.lms.librarianservice.dao.GenreDAO;
import com.smoothstack.lms.librarianservice.dao.LibraryBranchDAO;
import com.smoothstack.lms.librarianservice.entity.Author;
import com.smoothstack.lms.librarianservice.entity.Book;
import com.smoothstack.lms.librarianservice.entity.BookCopy;
import com.smoothstack.lms.librarianservice.entity.BookCopy.BookCopyId;
import com.smoothstack.lms.librarianservice.entity.Genre;
import com.smoothstack.lms.librarianservice.entity.LibraryBranch;
import com.smoothstack.lms.librarianservice.exception.ArgumentMissingException;
import com.smoothstack.lms.librarianservice.exception.IllegalRelationReferenceException;
import com.smoothstack.lms.librarianservice.exception.ResourceAlreadyExistsException;

@Service
@Transactional
public class LibrarianService {

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private AuthorDAO authorDAO;

    @Autowired
    private GenreDAO genreDAO;

    @Autowired
    private BookCopyDAO bookCopyDAO;

    @Autowired
    private LibraryBranchDAO libraryBranchDAO;

    public BookCopy createBookCopy(BookCopy bookCopy) {
        if (bookCopy.getId() == null) {
            throw new ArgumentMissingException("Missing 'id'");
        }
        if (bookCopy.getId().getBook() == null || bookCopy.getId().getBook().getId() == null) {
            throw new ArgumentMissingException("Missing 'book: {id}'");
        }
        if (bookCopy.getId().getBranch() == null || bookCopy.getId().getBranch().getId() == null) {
            throw new ArgumentMissingException("Missing 'branch: {id}'");
        }
        if (bookCopy.getAmount() == null) {
            throw new ArgumentMissingException("Missing 'amount'");
        }
        if (!this.bookDAO.existsById(bookCopy.getId().getBook().getId())) {
            throw new IllegalRelationReferenceException("Book does not exist");
        }
        if (!this.libraryBranchDAO.existsById(bookCopy.getId().getBranch().getId())) {
            throw new IllegalRelationReferenceException("Library branch does not exist");
        }
        if (this.bookCopyDAO.existsById(bookCopy.getId())) {
            throw new ResourceAlreadyExistsException("A book copy with this id already exists");
        }

        return this.bookCopyDAO.save(bookCopy);
    }

    public Book createBook(Book book) {
        if (book.getTitle() == null) {
            throw new ArgumentMissingException("Missing 'title'");
        }
        if (book.getPublisher() == null || book.getPublisher().getId() == null) {
            throw new ArgumentMissingException("Missing 'publisher: {id}'");
        }
        if (book.getAuthors().size() == 0) {
            throw new ArgumentMissingException("Missing 'authors: [{id}]'");
        }
        if (book.getGenres().size() == 0) {
            throw new ArgumentMissingException("Missing 'genres: [{id}]'");
        }
        for (Author author : book.getAuthors()) {
            if (author.getId() == null) {
                throw new ArgumentMissingException("Missing 'authors [{id}]");
            }
            if (!this.authorDAO.existsById(author.getId())) {
                throw new IllegalRelationReferenceException(
                        "The author with id of " + author.getId() + " does not exist");
            }
        }
        for (Genre genre : book.getGenres()) {
            if (genre.getId() == null) {
                throw new ArgumentMissingException("Missing 'genres [{id}]");
            }
            if (!this.genreDAO.existsById(genre.getId())) {
                throw new IllegalRelationReferenceException(
                        "The genre with id of " + genre.getId() + " does not exist");
            }
        }
        if (book.getId() != null && this.bookDAO.existsById(book.getId())) {
            throw new ResourceAlreadyExistsException("A book with this id already exists");
        }

        return this.bookDAO.save(book);
    }

    public List<Book> getBooksNotInBookCopies(Long branchId) {
        if (!this.libraryBranchDAO.existsById(branchId)) {
            throw new IllegalRelationReferenceException("Library branch does not exist");
        }

        List<BookCopy> bookCopies = this.bookCopyDAO.findBookCopiesById(branchId);
        List<Book> books = this.bookDAO.findAll();
        List<Book> result = books.stream().filter(b -> {
            for (BookCopy bc : bookCopies) {
                if (b.getId() == bc.getId().getBook().getId()) {
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList());

        return result;
    }

    public List<LibraryBranch> getLibraryBranches() {
        return this.libraryBranchDAO.findAll();
    }

    public LibraryBranch getLibraryBranchById(Long branchId) {
        if (!this.libraryBranchDAO.existsById(branchId)) {
            throw new IllegalRelationReferenceException("Library branch does not exist");
        }

        return this.libraryBranchDAO.findById(branchId).get();
    }

    public List<BookCopy> getBookCopiesById(Long branchId) {
        if (!this.libraryBranchDAO.existsById(branchId)) {
            throw new IllegalRelationReferenceException("Library branch does not exist");
        }

        return this.bookCopyDAO.findBookCopiesById(branchId);
    }

    public BookCopy updateBookCopy(BookCopy bookCopy) {
        if (bookCopy.getId() == null) {
            throw new ArgumentMissingException("Missing book copy 'id'");
        }
        if (bookCopy.getId().getBook().getId() == null) {
            throw new ArgumentMissingException("Missing 'book: {id}'");
        }
        if (bookCopy.getId().getBranch().getId() == null) {
            throw new ArgumentMissingException("Missing 'branch: {id}'");
        }
        if (bookCopy.getAmount() == null) {
            throw new ArgumentMissingException("Missing 'amount'");
        }
        if (!this.bookDAO.existsById(bookCopy.getId().getBook().getId())) {
            throw new IllegalRelationReferenceException("Book does not exist");
        }
        if (!this.libraryBranchDAO.existsById(bookCopy.getId().getBranch().getId())) {
            throw new IllegalRelationReferenceException("Library branch does not exist");
        }

        return this.bookCopyDAO.save(bookCopy);
    }

    public LibraryBranch updateLibraryBranch(LibraryBranch branch) {
        if (branch.getId() == null) {
            throw new ArgumentMissingException("Missing 'id'");
        }
        if (branch.getName() == null) {
            throw new ArgumentMissingException("Missing 'name'");
        }
        if (branch.getAddress() == null) {
            throw new ArgumentMissingException("Missing 'address'");
        }

        return this.libraryBranchDAO.save(branch);
    }

    public void removeBookCopy(BookCopyId bookCopyId) {
        if (bookCopyId.getBook().getId() == null) {
            throw new ArgumentMissingException("Missing book 'id'");
        }
        if (bookCopyId.getBranch().getId() == null) {
            throw new ArgumentMissingException("Missing library branch  'id'");
        }
        if (!this.bookCopyDAO.existsById(bookCopyId)) {
            throw new IllegalRelationReferenceException("Book copy does not exist");
        }

        this.bookCopyDAO.deleteById(bookCopyId);
    }

}
