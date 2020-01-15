package com.smoothstack.december.librarianService.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.smoothstack.december.librarianService.entity.BookLoan;

@Component
public class BookLoanDAO extends BaseDAO<BookLoan> implements ResultSetExtractor<List<BookLoan>> {
    
    public Map<String, Object> checkoutBook(int bookId, int branchId, int cardNumber) throws ClassNotFoundException, SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateOutObject = LocalDate.now();
        LocalDate dueDateObject = dateOutObject.plusWeeks(1);
        String dateOut = dateOutObject.format(formatter);
        String dueDate = dueDateObject.format(formatter);
        String sql = "INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) VALUES (?, ?, ?, ?, ?);";
        Object[] parameters = new Object[] {bookId, branchId, cardNumber, dateOut, dueDate};
        PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(sql);
        PreparedStatementCreator psc = pscFactory.newPreparedStatementCreator(parameters);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        super.mySQLTemplate.update(psc, keyHolder);
        super.mySQLTemplate.update("UPDATE  tbl_book_copies SET noOfCopies=noOfCopies-1 WHERE bookId = ? AND branchId = ?",
                new Object[] {bookId, branchId});
        return keyHolder.getKeys();
    }
    
    public List<BookLoan> getBookLoans() throws ClassNotFoundException, SQLException {
        return super.mySQLTemplate.query("SELECT * FROM tbl_book_loans;", this);
    }
    
    public List<BookLoan> getBookLoansForBorrower(int branchId, int cardNumber) throws ClassNotFoundException, SQLException {
        return super.mySQLTemplate.query("SELECT * FROM tbl_book_loans WHERE branchId = ? AND cardNo = ?;",
                new Object[] {branchId, cardNumber}, this);
    }
    
    public BookLoan getBookLoan(int bookId, int branchId, int cardNumber) throws ClassNotFoundException, SQLException {
        List<BookLoan> bookLoans = super.mySQLTemplate.query("SELECT * FROM tbl_book_loans WHERE bookId = ? AND branchId = ? AND cardNo = ?;",
                new Object[] {bookId, branchId, cardNumber}, this);
        BookLoan bookLoan = null;
        
        if (bookLoans.size() != 0) {
            bookLoan = bookLoans.get(0);
        }
        
        return bookLoan;
    }
    
    public void updateBookLoan(int bookId, int branchId, int amount) throws ClassNotFoundException, SQLException {
        super.mySQLTemplate.update("UPDATE tbl_book_loans SET noOfCopies=? WHERE bookId = ? AND branchId = ?;",
                new Object[] {amount, bookId, branchId});
    }
    
    public void checkinBook(int bookId, int branchId, int cardNumber) throws ClassNotFoundException, SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateInObject = LocalDate.now();
        String dateIn = dateInObject.format(formatter);
        
        super.mySQLTemplate.update("UPDATE  tbl_book_copies SET noOfCopies=noOfCopies+1 WHERE bookId = ? AND branchId = ?",
                new Object[] {bookId, branchId});
        super.mySQLTemplate.update("UPDATE tbl_book_loans SET dateIn=? WHERE bookId = ? AND branchId = ? AND cardNo = ?;",
                new Object[] {dateIn, bookId, branchId, cardNumber});
    }
    
    public void overrideDueDate(int bookId, int branchId, int cardNumber, LocalDate dueDateObject) throws ClassNotFoundException, SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dueDate = dueDateObject.format(formatter);
        super.mySQLTemplate.update("UPDATE  tbl_book_loans SET dueDate=? WHERE bookId = ? AND branchId = ? AND cardNo = ?",
                new Object[] {dueDate, bookId, branchId, cardNumber});
    }
    
    @Override
    public List<BookLoan> extractData(ResultSet rs) throws SQLException {
        List<BookLoan> bookLoans = new ArrayList<>();
        
        while (rs.next()) {
            BookLoan bookLoan = new BookLoan();
            bookLoan.setBookId(rs.getInt("bookId"));
            bookLoan.setBranchId(rs.getInt("branchId"));
            bookLoan.setCardNumber(rs.getInt("cardNo"));
            bookLoan.setDateOut(LocalDate.parse(rs.getString("dateOut")));
            bookLoan.setDueDate(LocalDate.parse(rs.getString("dueDate")));
            bookLoan.setDateIn(LocalDate.parse(rs.getString("dateIn")));
            bookLoans.add(bookLoan);
        }
        
        return bookLoans;
    }

}
