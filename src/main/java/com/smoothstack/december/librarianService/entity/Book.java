package com.smoothstack.december.librarianService.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_book")
public class Book implements Serializable {

    private static final long serialVersionUID = 2276143279792957149L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookId")
    private Long bookId;

    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pubId")
    private Publisher publisher;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tbl_book_authors", joinColumns = { @JoinColumn(name = "bookId") }, inverseJoinColumns = {
            @JoinColumn(name = "authorId") })
    private Set<Author> authors = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tbl_book_genres", joinColumns = { @JoinColumn(name = "bookId") }, inverseJoinColumns = {
            @JoinColumn(name = "genreId") })
    private Set<Genre> genres = new HashSet<>();

    @OneToMany(mappedBy = "bookCopyId.book", cascade = CascadeType.ALL)
    private Set<BookCopy> bookCopies = new HashSet<>();

    @OneToMany(mappedBy = "bookLoanId.book", cascade = CascadeType.ALL)
    Set<BookLoan> bookLoans = new HashSet<BookLoan>();

    public Long getBookId() {
        return this.bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthors() {
        return this.authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Publisher getPublisher() {
        return this.publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Set<Genre> getGenres() {
        return this.genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<BookCopy> getBookCopies() {
        return this.bookCopies;
    }

    public void setBookCopies(Set<BookCopy> bookCopies) {
        this.bookCopies = bookCopies;
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    public void removeAuthor(Author author) {
        this.authors.remove(author);
    }

    public void addGenre(Genre genre) {
        this.genres.add(genre);
    }

    public void removeGenre(Genre genre) {
        this.genres.remove(genre);
    }

    public void addBookCopy(BookCopy bookCopy) {
        this.bookCopies.add(bookCopy);
    }

    public void removeBookCopy(BookCopy bookCopy) {
        this.bookCopies.remove(bookCopy);
    }

    public void addBookLoan(BookLoan bookLoan) {
        this.bookLoans.add(bookLoan);
    }

    public void removeBookLoan(BookLoan bookLoan) {
        this.bookLoans.remove(bookLoan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.bookId, this.title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Book)) {
            return false;
        }
        Book other = (Book) obj;
        return Objects.equals(this.bookId, other.bookId) && Objects.equals(this.title, other.title);
    }

}