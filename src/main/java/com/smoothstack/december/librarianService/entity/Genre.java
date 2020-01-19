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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_genre")
public class Genre implements Serializable {

    private static final long serialVersionUID = -7871622461454589536L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genreId")
    private Long genreId;

    @Column(name = "genreName")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tbl_book_genres", joinColumns = { @JoinColumn(name = "genreId") }, inverseJoinColumns = {
            @JoinColumn(name = "bookId") })
    private Set<Book> books = new HashSet<>();

    public Long getGenreId() {
        return this.genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return this.books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public void removeBook(Book book) {
        this.books.remove(book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.genreId, this.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Genre)) {
            return false;
        }
        Genre other = (Genre) obj;
        return Objects.equals(this.genreId, other.genreId) && Objects.equals(this.name, other.name);
    }

}
