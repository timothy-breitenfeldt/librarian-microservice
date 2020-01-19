package com.smoothstack.december.librarianService.entity;

import java.io.Serializable;
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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "tbl_book_copies")
@AssociationOverrides({ @AssociationOverride(name = "bookCopyId.book", joinColumns = @JoinColumn(name = "bookId")),
        @AssociationOverride(name = "bookCopyId.branch", joinColumns = @JoinColumn(name = "branchId")) })
public class BookCopy implements Serializable {

    @Embeddable
    private class BookCopyId implements Serializable {

        private static final long serialVersionUID = 22619397635869180L;

        @ManyToOne(cascade = CascadeType.ALL)
        @JsonBackReference
        private Book book;

        @ManyToOne(cascade = CascadeType.ALL)
        @JsonBackReference
        private LibraryBranch branch;

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

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + this.getEnclosingInstance().hashCode();
            result = prime * result + Objects.hash(this.book, this.branch);
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BookCopyId)) {
                return false;
            }
            BookCopyId other = (BookCopyId) obj;
            if (!this.getEnclosingInstance().equals(other.getEnclosingInstance())) {
                return false;
            }
            return Objects.equals(this.book, other.book) && Objects.equals(this.branch, other.branch);
        }

        private BookCopy getEnclosingInstance() {
            return BookCopy.this;
        }

    }

    private static final long serialVersionUID = -8766588377489959146L;

    @EmbeddedId
    private BookCopyId bookCopyId = new BookCopyId();

    @Column(name = "noOfCopies")
    private Integer amount;

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Transient
    public Book getBook() {
        return this.bookCopyId.getBook();
    }

    public void setBook(Book book) {
        this.bookCopyId.setBook(book);
    }

    @Transient
    public LibraryBranch getBranch() {
        return this.bookCopyId.getBranch();
    }

    public void setBranch(LibraryBranch branch) {
        this.bookCopyId.setBranch(branch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.amount, this.bookCopyId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BookCopy)) {
            return false;
        }
        BookCopy other = (BookCopy) obj;
        return Objects.equals(this.amount, other.amount) && Objects.equals(this.bookCopyId, other.bookCopyId);
    }

}
