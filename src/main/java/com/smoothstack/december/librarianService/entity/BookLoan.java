package com.smoothstack.december.librarianService.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;;

@Entity
@Table
public class BookLoan {

    @Embeddable
    public static class BookLoanId implements Serializable {

        private static final long serialVersionUID = -8848058513226751763L;

        @Column
        private Long bookId;

        @Column
        private Long borrowerId;

        @Column
        private Long branchId;

        public BookLoanId() {
        }

        public BookLoanId(long bookId, long borrowerId, long branchId) {
            this.bookId = bookId;
            this.borrowerId = borrowerId;
            this.branchId = branchId;
        }

        public Long getBookId() {
            return this.bookId;
        }

        public void setBookId(Long bookId) {
            this.bookId = bookId;
        }

        public Long getBorrowerId() {
            return this.borrowerId;
        }

        public void setBorrowerId(Long borrowerId) {
            this.borrowerId = borrowerId;
        }

        public Long getBranchId() {
            return this.branchId;
        }

        public void setBranchId(Long branchId) {
            this.branchId = branchId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.bookId, this.borrowerId, this.branchId);
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
            return Objects.equals(this.bookId, other.bookId) && Objects.equals(this.borrowerId, other.borrowerId)
                    && Objects.equals(this.branchId, other.branchId);
        }

    }

    @EmbeddedId
    private BookLoanId id;;

    @Column
    private LocalDate dateOut;

    @Column
    private LocalDate dueDate;

    @Column
    private LocalDate dateIn;

    public BookLoanId getId() {
        return this.id;
    }

    public void setId(BookLoanId id) {
        this.id = id;
    }

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

}