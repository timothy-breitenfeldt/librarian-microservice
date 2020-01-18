package com.smoothstack.december.librarianService.entity;

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

@Entity
@Table(name = "tbl_borrower")
public class Borrower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cardNo")
    private Long cardNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phoneNumber;

    @OneToMany(mappedBy = "bookLoanId.borrower", cascade = CascadeType.ALL)
    private Set<BookLoan> bookLoans = new HashSet<>();

    public Long getCardNumber() {
        return this.cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
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

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void addBookLoan(BookLoan bookLoan) {
        this.bookLoans.add(bookLoan);
    }

    public void removeBookLoan(BookLoan bookLoan) {
        this.bookLoans.remove(bookLoan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.address, this.cardNumber, this.name, this.phoneNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Borrower)) {
            return false;
        }
        Borrower other = (Borrower) obj;
        return Objects.equals(this.address, other.address) && Objects.equals(this.cardNumber, other.cardNumber)
                && Objects.equals(this.name, other.name) && Objects.equals(this.phoneNumber, other.phoneNumber);
    }

}