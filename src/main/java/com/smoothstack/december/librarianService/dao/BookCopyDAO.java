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
import org.springframework.stereotype.Component;

import com.smoothstack.december.librarianService.entity.BookCopy;

@Component
public class BookCopyDAO extends BaseDAO<BookCopy> implements ResultSetExtractor<List<BookCopy>> {
    
    public Map<String, Object> createBookCopy(int bookId, int branchId, int amount) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO tbl_book_copies (bookId, branchId, noOfCopies) VALUES (?, ?, ?);";
        Object[] parameters = new Object[] {bookId, branchId, amount};
        PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(sql);
        PreparedStatementCreator psc = pscFactory.newPreparedStatementCreator(parameters);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        super.mySQLTemplate.update(psc, keyHolder);
        return keyHolder.getKeys();
    }
    
    public List<BookCopy> getBookCopys() throws ClassNotFoundException, SQLException {
        return super.mySQLTemplate.query("SELECT * FROM tbl_book_copies;", this);
    }
    
    public List<BookCopy> getBookCopys(int branchId) throws ClassNotFoundException, SQLException {
        return super.mySQLTemplate.query("SELECT * FROM tbl_book_copies WHERE branchId = ?;",
                new Object[] {branchId},
                this);
    }
    
    public BookCopy getBookCopy(int bookId, int branchId) throws ClassNotFoundException, SQLException {
        List<BookCopy> bookCopies = super.mySQLTemplate.query("SELECT * FROM tbl_book_copies WHERE bookId = ? AND branchId = ?",
                new Object[] {bookId, branchId}, this);
        BookCopy bookCopy = null;
        
        if (bookCopies.size() != 0) {
            bookCopy = bookCopies.get(0);
        }
        
        return bookCopy;
    }
    
    public void updateBookCopy(int bookId, int branchId, int amount) throws ClassNotFoundException, SQLException {
        super.mySQLTemplate.update("UPDATE tbl_book_copies SET noOfCopies=? WHERE bookId = ? AND branchId = ?;",
                new Object[] {amount, bookId, branchId});
    }
    
    public void deleteBookCopy(int bookId, int branchId) throws ClassNotFoundException, SQLException {
        super.mySQLTemplate.update("DELETE FROM tbl_book_copies WHERE bookId = ? AND branchId = ?;",
                new Object[] {bookId, branchId});
    }
    
    @Override
    public List<BookCopy> extractData(ResultSet rs) throws SQLException {
        List<BookCopy> bookCopies = new ArrayList<>();
        
        while (rs.next()) {
            BookCopy bookCopy = new BookCopy();
            bookCopy.setBookId(rs.getInt("bookId"));
            bookCopy.setBranchId(rs.getInt("branchId"));
            bookCopy.setAmount(rs.getInt("noOfCopies"));
            bookCopies.add(bookCopy);
        }
        
        return bookCopies;
    }

}
