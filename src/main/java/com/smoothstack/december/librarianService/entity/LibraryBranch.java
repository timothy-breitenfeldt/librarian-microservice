package com.smoothstack.december.librarianService.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tbl_library_branch")
public class LibraryBranch implements Serializable {

    private static final long serialVersionUID = 5712621590319520638L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branchId")
    private Long branchId;

    @Column(name = "branchName")
    private String name;

    @Column(name = "branchAddress")
    private String address;

    @OneToMany(mappedBy = "bookCopyId.branch", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<BookCopy> bookCopies = new HashSet<>();

    @OneToMany(mappedBy = "bookLoanId.branch", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<BookLoan> bookLoans = new HashSet<>();

    public Long getBranchId() {
        return this.branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<BookCopy> getBookCopies() {
        return this.bookCopies;
    }

    public void setBookCopies(Set<BookCopy> bookCopies) {
        this.bookCopies = bookCopies;
    }

    public void addBookCopy(BookCopy bookCopy) {
        this.bookCopies.add(bookCopy);
    }

    public void removeBookCopy(BookCopy bookCopy) {
        this.bookCopies.add(bookCopy);
    }

    public void addBookLoan(BookLoan bookLoan) {
        this.bookLoans.add(bookLoan);
    }

    public void removeBookLoan(BookLoan bookLoan) {
        this.bookLoans.remove(bookLoan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.address, this.branchId, this.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LibraryBranch)) {
            return false;
        }
        LibraryBranch other = (LibraryBranch) obj;
        return Objects.equals(this.address, other.address) && Objects.equals(this.branchId, other.branchId)
                && Objects.equals(this.name, other.name);
    }

}