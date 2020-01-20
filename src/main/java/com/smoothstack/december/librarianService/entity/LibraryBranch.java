package com.smoothstack.december.librarianService.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class LibraryBranch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    // @OneToMany(mappedBy = "bookCopyId.branch", cascade = CascadeType.ALL, fetch =
    // FetchType.LAZY)
    // @JoinTable
    // private Set<BookCopy> bookCopies = new HashSet<>();

    // @OneToMany(mappedBy = "bookLoanId.branch", cascade = CascadeType.ALL, fetch =
    // FetchType.LAZY)
    // @JsonManagedReference
    // private Set<BookLoan> bookLoans = new HashSet<>();

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

}