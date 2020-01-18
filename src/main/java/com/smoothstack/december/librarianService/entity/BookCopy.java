package com.smoothstack.december.librarianService.entity;

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

@Entity
@Table(name = "tbl_book_copies")
@AssociationOverrides({ @AssociationOverride(name = "bookCopyId.book", joinColumns = @JoinColumn(name = "bookId")),
        @AssociationOverride(name = "primaryKey.branch", joinColumns = @JoinColumn(name = "branchId")) })
public class BookCopy {

    @Embeddable
    private class BookCopyId {

        @ManyToOne(cascade = CascadeType.ALL)
        private Book book;

        @ManyToOne(cascade = CascadeType.ALL)
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

    }

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

}
