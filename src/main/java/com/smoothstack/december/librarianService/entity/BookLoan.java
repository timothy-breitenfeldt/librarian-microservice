package com.smoothstack.december.librarianService.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

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
import javax.persistence.Transient;;

@Entity
@Table(name = "tbl_book_loans")
@AssociationOverrides({ @AssociationOverride(name = "bookLoanId.book", joinColumns = @JoinColumn(name = "bookId")),
        @AssociationOverride(name = "bookLoanId.branch", joinColumns = @JoinColumn(name = "branchId")),
        @AssociationOverride(name = "bookLoanId.borrower", joinColumns = @JoinColumn(name = "cardNo")) })
public class BookLoan implements Serializable {

    @Embeddable
    private class BookLoanId implements Serializable {

        private static final long serialVersionUID = -8848058513226751763L;

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

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + this.getEnclosingInstance().hashCode();
            result = prime * result + Objects.hash(this.book, this.borrower, this.branch);
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BookLoanId)) {
                return false;
            }
            BookLoanId other = (BookLoanId) obj;
            if (!this.getEnclosingInstance().equals(other.getEnclosingInstance())) {
                return false;
            }
            return Objects.equals(this.book, other.book) && Objects.equals(this.borrower, other.borrower)
                    && Objects.equals(this.branch, other.branch);
        }

        private BookLoan getEnclosingInstance() {
            return BookLoan.this;
        }

    }

    private static final long serialVersionUID = 6976480817248770465L;

    @EmbeddedId
    private BookLoanId bookLoanId = new BookLoanId();

    @Column(name = "dateOut")
    private LocalDate dateOut;

    @Column(name = "dueDate")
    private LocalDate dueDate;

    @Column(name = "dateIn")
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

    @Override
    public int hashCode() {
        return Objects.hash(this.bookLoanId, this.dateIn, this.dateOut, this.dueDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BookLoan)) {
            return false;
        }
        BookLoan other = (BookLoan) obj;
        return Objects.equals(this.bookLoanId, other.bookLoanId) && Objects.equals(this.dateIn, other.dateIn)
                && Objects.equals(this.dateOut, other.dateOut) && Objects.equals(this.dueDate, other.dueDate);
    }

}