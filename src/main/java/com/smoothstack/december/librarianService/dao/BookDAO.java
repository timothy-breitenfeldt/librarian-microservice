package com.smoothstack.december.librarianService.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.smoothstack.december.librarianService.entity.Book;

public class BookDAO extends BaseDAO<Book> implements ResultSetExtractor<List<Book>> {
    
    public Integer createBook(String title, int publisherId) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO tbl_book (title, pubId) VALUES (?, ?);";
        Object[] parameters = new Object[] {title, publisherId};
        PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(sql);
        PreparedStatementCreator psc = pscFactory.newPreparedStatementCreator(parameters);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        super.mySQLTemplate.update(psc, keyHolder);
        return (Integer)keyHolder.getKey();
    }
    
    public Map<String, Object> createBookAuthor(int bookId, int authorId) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO tbl_book_authors (bookId, authorId) VALUES (?, ?);";
        Object[] parameters = new Object[] {bookId, authorId};
        PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(sql);
        PreparedStatementCreator psc = pscFactory.newPreparedStatementCreator(parameters);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        super.mySQLTemplate.update(psc, keyHolder);
        return keyHolder.getKeys();
    }

    public Map<String, Object> createBookGenre(int bookId, int genreId) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO tbl_book_genres (bookId, genreId) VALUES (?, ?);";
        Object[] parameters = new Object[] {bookId, genreId};
        PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(sql);
        PreparedStatementCreator psc = pscFactory.newPreparedStatementCreator(parameters);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        super.mySQLTemplate.update(psc, keyHolder);
        return keyHolder.getKeys();
    }
    
    public List<Book> getBooks() throws ClassNotFoundException, SQLException {
        return super.mySQLTemplate.query("SELECT * FROM tbl_book;", this);
    }
    
    public List<Book> getBooksByAuthorId(int authorId) throws ClassNotFoundException, SQLException {
        return super.mySQLTemplate.query("SELECT * FROM tbl_book NATURAL JOIN tbl_book_authors WHERE authorId = ?;",
                new Object[] {authorId}, this);
    }
    
    public List<Book> getAvailableBooksNotCheckedOut(int branchId, int cardNumber) throws SQLException {
        return super.mySQLTemplate.query("SELECT bookId, title, pubId FROM tbl_book NATURAL JOIN tbl_book_copies WHERE branchId = ? AND cardNumber = ? AND noOfCopies > 0 AND dateIn != NULL;",
                new Object[] {branchId, cardNumber}, this);
    }

    public Book getBook(int bookId) throws ClassNotFoundException, SQLException {
        Book book = null;
        List<Book> books = super.mySQLTemplate.query("SELECT * FROM tbl_book WHERE bookId = ?;",
                new Object[] {bookId}, this);
        
        if (books.size() == 1) {
            book = books.get(0);
        }
        
        return book;
    }
    
    public void updateBook(int bookId, String title, int publisherId) throws ClassNotFoundException, SQLException {
        super.mySQLTemplate.update("UPDATE tbl_book SET title=?, pubId=? WHERE bookId = ?",
                new Object[] {title, publisherId, bookId});
    }

    public void updateBookAuthor(int bookId, int authorId) throws ClassNotFoundException, SQLException {
        super.mySQLTemplate.update("UPDATE tbl_book_authors SET authorId=? WHERE bookId = ?",
                new Object[] {authorId, bookId});
    }

    public void updateBookGenre(int bookId, int genreId) throws ClassNotFoundException, SQLException {
        super.mySQLTemplate.update("UPDATE tbl_book_genres SET genreId=? WHERE bookId = ?",
                new Object[] {genreId, bookId});
    }
    
    public void deleteBook(int bookId) throws ClassNotFoundException, SQLException {
        super.mySQLTemplate.update("DELETE FROM tbl_book WHERE bookId = ?;",
                new Object[] {bookId});
    }

    @Override
    public List<Book> extractData(ResultSet rs) throws SQLException {
        List<Book> books = new ArrayList<>();
        
        while (rs.next()) {
            Book book = new Book();
            book.setBookId(rs.getInt("bookId"));
            book.setTitle(rs.getString("title"));
                    books.add(book);
        }
        
        return books;
    }

}
