package com.smoothstack.december.librarianService.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.smoothstack.december.librarianService.entity.Borrower;

public class BorrowerDAO extends BaseDAO<Borrower> implements ResultSetExtractor<List<Borrower>> {

    public Integer createBorrower(String name, String address, String phone) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO tbl_borrower (name, address, phone) VALUES (?, ?, ?);";
        Object[] parameters = new Object[] {name, address, phone};
        PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(sql);
        PreparedStatementCreator psc = pscFactory.newPreparedStatementCreator(parameters);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        super.mySQLTemplate.update(psc, keyHolder);
        return (Integer)keyHolder.getKey();
    }
    
    public List<Borrower> getBorrowers() throws ClassNotFoundException, SQLException {
        return super.mySQLTemplate.query("SELECT * FROM tbl_borrower;", this);
    }
    
    public Borrower getBorrower(int cardNo) throws ClassNotFoundException, SQLException {
        List<Borrower> borrowers = super.mySQLTemplate.query("SELECT * FROM tbl_borrower WHERE cardNo = ?;",
                new Object[] {cardNo}, this);
        Borrower borrower = null;
        
        if (borrowers.size() != 0) {
            borrower = borrowers.get(0);
        }
        
        return borrower;
    }
    
    public List<Borrower> getBorrowerBooks(int cardNumber, int branchId) throws ClassNotFoundException, SQLException {
        return super.mySQLTemplate.query("SELECT * FROM tbl_borrower "
                + "NATURAL JOIN TBL_BOOK_LOANS NATURAL JOIN tbl_book NATURAL JOIN tbl_library_branch "
                + "WHERE cardNo = ? AND branchId = ?;",
                new Object[] {cardNumber, branchId}, this);
    }
    
    public void updateBorrower(int cardNo, String name, String address, String phone) throws ClassNotFoundException, SQLException {
        super.mySQLTemplate.update("UPDATE tbl_borrower SET name=?, address=?, phone=? WHERE cardNo = ?;",
                new Object[] {cardNo, name, address, phone});
    }
    
    public void deleteBorrower(int cardNo) throws ClassNotFoundException, SQLException {
        super.mySQLTemplate.update("DELETE FROM tbl_borrower WHERE cardNo = ?;",
                new Object[] {cardNo});
    }
    
    public boolean borrowerExists(int cardNumber) throws ClassNotFoundException, SQLException {
        List<Borrower> borrowers = super.mySQLTemplate.query("SELECT * FROM tbl_borrower "
                + "WHERE cardNo = ?;",
                new Object[] {cardNumber}, this);
        return borrowers.size() == 1;         
    }
    
    @Override
    public List<Borrower> extractData(ResultSet rs) throws SQLException {
        List<Borrower> borrowers = new ArrayList<>();
        
        while (rs.next()) {
            Borrower borrower = new Borrower();
            borrower.setCardNumber(rs.getInt("borrowerId"));
            borrower.setName(rs.getString("name"));
            borrower.setAddress(rs.getString("address"));
            borrower.setPhoneNumber(rs.getString("phone"));
            borrowers.add(borrower);
        }
        
        return borrowers;
    }
    
}
