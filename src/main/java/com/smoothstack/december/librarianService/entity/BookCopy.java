package com.smoothstack.december.librarianService.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class BookCopy {

    @Embeddable
    public static class BookCopyId implements Serializable {

        private static final long serialVersionUID = 22619397635869180L;

        @Column
        private Long bookId;

        @Column
        private Long branchId;

        public BookCopyId() {
        }

        public BookCopyId(Long bookId, Long branchId) {
            this.bookId = bookId;
            this.branchId = branchId;
        }

        public Long getBookId() {
            return this.bookId;
        }

        public void setBookId(Long bookId) {
            this.bookId = bookId;
        }

        public Long getBranchId() {
            return this.branchId;
        }

        public void setBranchId(Long branchId) {
            this.branchId = branchId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.bookId, this.branchId);
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
            return Objects.equals(this.bookId, other.bookId) && Objects.equals(this.branchId, other.branchId);
        }

    }

    @EmbeddedId
    private BookCopyId id;

    @Column
    private Long amount;

    public BookCopyId getId() {
        return this.id;
    }

    public void setId(BookCopyId id) {
        this.id = id;
    }

    public Long getAmount() {
        return this.amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

}
