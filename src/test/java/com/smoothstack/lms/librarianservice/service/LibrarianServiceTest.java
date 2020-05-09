package com.smoothstack.lms.librarianservice.service;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.smoothstack.lms.librarianservice.dao.AuthorDAO;
import com.smoothstack.lms.librarianservice.dao.BookCopyDAO;
import com.smoothstack.lms.librarianservice.dao.BookDAO;
import com.smoothstack.lms.librarianservice.dao.GenreDAO;
import com.smoothstack.lms.librarianservice.dao.LibraryBranchDAO;
import com.smoothstack.lms.librarianservice.entity.Book;
import com.smoothstack.lms.librarianservice.entity.BookCopy;
import com.smoothstack.lms.librarianservice.entity.BookCopy.BookCopyId;
import com.smoothstack.lms.librarianservice.entity.LibraryBranch;
import com.smoothstack.lms.librarianservice.exception.ArgumentMissingException;
import com.smoothstack.lms.librarianservice.exception.IllegalRelationReferenceException;
import com.smoothstack.lms.librarianservice.exception.ResourceAlreadyExistsException;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LibrarianServiceTest {

    @Mock
    private BookDAO bookDAO;

    @Mock
    private AuthorDAO authorDAO;

    @Mock
    private GenreDAO genreDAO;

    @Mock
    private BookCopyDAO bookCopyDAO;

    @Mock
    private LibraryBranchDAO libraryBranchDAO;

    @InjectMocks
    private LibrarianService librarianService;

    @Test
    public void testNonZeroSizeOfGetBranches() {
        List<LibraryBranch> branches = new ArrayList<>();
        branches.add(createLibraryBranch(1l, "name_1", "address_1"));
        branches.add(createLibraryBranch(2l, "name_2", "address_2"));
        Mockito.when(this.libraryBranchDAO.findAll()).thenReturn(branches);
        int expected = 2;
        int actual = this.librarianService.getLibraryBranches().size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testZeroSizeOfGetBranches() {
        List<LibraryBranch> branches = new ArrayList<>();
        Mockito.when(this.libraryBranchDAO.findAll()).thenReturn(branches);
        int expected = 0;
        int actual = this.librarianService.getLibraryBranches().size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSuccessfulCreateBookCopy() {
        Long bookId = 1l;
        Long branchId = 1l;
        Long amount = 1l;
        BookCopy bookCopy = createBookCopy(bookId, branchId, amount);
        Mockito.when(this.bookCopyDAO.save(bookCopy)).thenReturn(bookCopy);
        Mockito.when(this.bookDAO.existsById(bookCopy.getId().getBook().getId())).thenReturn(true);
        Mockito.when(this.libraryBranchDAO.existsById(bookCopy.getId().getBranch().getId())).thenReturn(true);
        Mockito.when(this.bookCopyDAO.existsById(bookCopy.getId())).thenReturn(false);
        BookCopy result = this.librarianService.createBookCopy(bookCopy);
        Long[] expecteds = new Long[] { bookId, branchId };
        Long[] actuals = new Long[] { result.getId().getBook().getId(), result.getId().getBranch().getId() };
        Assert.assertArrayEquals(expecteds, actuals);
    }

    @Test
    public void testNullBookCopyIdCreateBookCopy() {
        Long amount = 1l;
        BookCopy bookCopy = new BookCopy();
        bookCopy.setAmount(amount);

        try {
            this.librarianService.createBookCopy(bookCopy);
            Assert.fail("Expected ArgumentMissingException to be thrown");
        } catch (ArgumentMissingException e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Missing 'id'"));
        }
    }

    @Test
    public void testNullBookIdCreateBookCopy() {
        Long bookId = null;
        Long branchId = 1l;
        Long amount = 1l;
        BookCopy bookCopy = createBookCopy(bookId, branchId, amount);

        try {
            this.librarianService.createBookCopy(bookCopy);
            Assert.fail("Expected ArgumentMissingException to be thrown");
        } catch (ArgumentMissingException e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Missing 'book: {id}'"));
        }
    }

    @Test
    public void testNullBranchIdCreateBookCopy() {
        Long bookId = 1l;
        Long branchId = null;
        Long amount = 1l;
        BookCopy bookCopy = createBookCopy(bookId, branchId, amount);

        try {
            this.librarianService.createBookCopy(bookCopy);
            Assert.fail("Expected ArgumentMissingException to be thrown");
        } catch (ArgumentMissingException e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Missing 'branch: {id}'"));
        }
    }

    @Test
    public void testNullAmountCreateBookCopy() {
        Long bookId = 1l;
        Long branchId = 1l;
        Long amount = null;
        BookCopy bookCopy = createBookCopy(bookId, branchId, amount);

        try {
            this.librarianService.createBookCopy(bookCopy);
            Assert.fail("Expected ArgumentMissingException to be thrown");
        } catch (ArgumentMissingException e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Missing 'amount'"));
        }
    }

    @Test
    public void testInvalidBookIdCreateBookCopy() {
        Long bookId = 1l;
        Long branchId = 1l;
        Long amount = 1l;
        BookCopy bookCopy = createBookCopy(bookId, branchId, amount);
        Mockito.when(this.bookDAO.existsById(bookCopy.getId().getBook().getId())).thenReturn(false);

        try {
            this.librarianService.createBookCopy(bookCopy);
            Assert.fail("Expected IllegalRelationReferenceException to be thrown");
        } catch (IllegalRelationReferenceException e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Book does not exist"));
        }
    }

    @Test
    public void testInvalidBranchIdCreateBookCopy() {
        Long bookId = 1l;
        Long branchId = 1l;
        Long amount = 1l;
        BookCopy bookCopy = createBookCopy(bookId, branchId, amount);
        Mockito.when(this.bookDAO.existsById(bookCopy.getId().getBook().getId())).thenReturn(true);
        Mockito.when(this.libraryBranchDAO.existsById(bookCopy.getId().getBranch().getId())).thenReturn(false);

        try {
            this.librarianService.createBookCopy(bookCopy);
            Assert.fail("Expected IllegalRelationReferenceException to be thrown");
        } catch (IllegalRelationReferenceException e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("Library branch does not exist"));
        }
    }

    @Test
    public void testInvalidBookCopyIdCreateBookCopy() {
        Long bookId = 1l;
        Long branchId = 1l;
        Long amount = 1l;
        BookCopy bookCopy = createBookCopy(bookId, branchId, amount);
        Mockito.when(this.bookDAO.existsById(bookCopy.getId().getBook().getId())).thenReturn(true);
        Mockito.when(this.libraryBranchDAO.existsById(bookCopy.getId().getBranch().getId())).thenReturn(true);
        Mockito.when(this.bookCopyDAO.existsById(bookCopy.getId())).thenReturn(true);

        try {
            this.librarianService.createBookCopy(bookCopy);
            Assert.fail("Expected ResourceAlreadyExistsException to be thrown");
        } catch (ResourceAlreadyExistsException e) {
            Assert.assertThat(e.getMessage(), CoreMatchers.is("A book copy with this id already exists"));
        }
    }

    private LibraryBranch createLibraryBranch(Long id, String name, String address) {
        LibraryBranch branch = new LibraryBranch();
        branch.setId(id);
        branch.setName(name);
        branch.setAddress(address);
        return branch;
    }

    private BookCopy createBookCopy(Long bookId, Long branchId, Long amount) {
        BookCopy bookCopy = new BookCopy();
        Book book = new Book();
        LibraryBranch branch = new LibraryBranch();
        BookCopyId bookCopyId = new BookCopyId();
        book.setId(bookId);
        branch.setId(branchId);
        bookCopyId.setBook(book);
        bookCopyId.setBranch(branch);
        bookCopy.setId(bookCopyId);
        bookCopy.setAmount(amount);
        return bookCopy;
    }

}
