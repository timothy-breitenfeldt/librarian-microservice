package com.smoothstack.december.librarianService.entity;

import java.time.LocalDate;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;;

@Entity
@Table(name = "tbl_book_loans")
@AssociationOverrides({ @AssociationOverride(name = "bookCopyId.book", joinColumns = @JoinColumn(name = "bookId")),
        @AssociationOverride(name = "primaryKey.branch", joinColumns = @JoinColumn(name = "branchId")) })
public class BookLoan {

    @Embeddable
    private class BookLoanId {

        @ManyToOne(cascade = CascadeType.ALL)
        private Book book;

        @ManyToOne(cascade = CascadeType.ALL)
        private LibraryBranch branch;

        @ManyToOne(cascade = CascadeType.ALL)
        private Borrower borrower;

        public Book getBook() {
            return this.book;
        }

        public void setBook(Book book) {
            this.book = book;
        }

        public LibraryBranch getBranch() {
            return this.branch;
        }

        public void setBranch(LibraryBranch branch) {
            this.branch = branch;
        }

        public Borrower getBorrower() {
            return this.borrower;
        }

        public void setBorrower(Borrower borrower) {
            this.borrower = borrower;
        }

    }

    @EmbeddedId
    private BookLoanId bookLoanId = new BookLoanId();

    @Column(name = "dateOut")
    @Temporal(TemporalType.DATE)
    private LocalDate dateOut;

    @Column(name = "dueDate")
    @Temporal(TemporalType.DATE)
    private LocalDate dueDate;

    @Column(name = "dateIn")
    @Temporal(TemporalType.DATE)
    private LocalDate dateIn;

    public LocalDate getDateOut() {
        return this.dateOut;
    }

    public void setDateOut(LocalDate dateOut) {
        this.dateOut = dateOut;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getDateIn() {
        return this.dateIn;
    }

    public void setDateIn(LocalDate dateIn) {
        this.dateIn = dateIn;
    }

    @Transient
    public Book getBook() {
        return this.bookLoanId.getBook();
    }

    public void setBook(Book book) {
        this.bookLoanId.setBook(book);
    }

    @Transient
    public LibraryBranch getBranch() {
        return this.bookLoanId.getBranch();
    }

    public void setBranch(LibraryBranch branch) {
        this.bookLoanId.setBranch(branch);
    }

    @Transient
    public Borrower getBorrower() {
        return this.bookLoanId.getBorrower();
    }

    public void setBorrower(Borrower borrower) {
        this.bookLoanId.setBorrower(borrower);
    }

}