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

@Entity
@Table(name = "tbl=publisher")
public class Publisher implements Serializable {

    private static final long serialVersionUID = 7377441833253328339L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisherId")
    private Long publisherId;

    @Column(name = "publisherName")
    private String name;

    @Column(name = "publisherAddress")
    private String address;

    @Column(name = "publisherPhone")
    private String phoneNumber;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Book> books = new HashSet<>();

    public Long getPublisherId() {
        return this.publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
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

    public Set<Book> getBooks() {
        return this.books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        this.books.add(book);
        book.setPublisher(this);
    }

    public void removeBook(Book book) {
        this.books.remove(book);
        book.setPublisher(null);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.address, this.name, this.phoneNumber, this.publisherId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Publisher)) {
            return false;
        }
        Publisher other = (Publisher) obj;
        return Objects.equals(this.address, other.address) && Objects.equals(this.name, other.name)
                && Objects.equals(this.phoneNumber, other.phoneNumber)
                && Objects.equals(this.publisherId, other.publisherId);
    }

}