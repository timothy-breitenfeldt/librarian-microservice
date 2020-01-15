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
import org.springframework.stereotype.Component;

import com.smoothstack.december.librarianService.entity.LibraryBranch;

@Component
public class LibraryBranchDAO extends BaseDAO<LibraryBranch> implements ResultSetExtractor<List<LibraryBranch>> {
    
    public Integer createBranch(String branchName, String branchAddress) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO tbl_library_branch (branchName, branchAddress) VALUES (?, ?);";
        Object[] parameters = new Object[] {branchName, branchAddress};
        PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(sql);
        PreparedStatementCreator psc = pscFactory.newPreparedStatementCreator(parameters);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        super.mySQLTemplate.update(psc, keyHolder);
        return (Integer)keyHolder.getKey();
    }
    
    public List<LibraryBranch> getBranchs() throws ClassNotFoundException, SQLException {
        return super.mySQLTemplate.query("SELECT * FROM tbl_library_branch;", this);
    }
    
    public LibraryBranch getBranch(int branchId) throws ClassNotFoundException, SQLException {
        List<LibraryBranch> branches = super.mySQLTemplate.query("SELECT * FROM tbl_library_branch WHERE branchId = ?;",
                new Object[] {branchId}, this);
        LibraryBranch branch = null;
        
        if (branches.size() != 0) {
            branch = branches.get(0);
        }
        
        return branch;
    }
    
    public void updateBranch(int branchId, String branchName, String branchAddress) throws ClassNotFoundException, SQLException {
        super.mySQLTemplate.update("UPDATE tbl_library_branch SET branchName=?, branchAddress=? WHERE branchId = ?;",
                new Object[] {branchName, branchAddress, branchId});
    }
    
    public void deleteBranch(int branchId) throws ClassNotFoundException, SQLException {
        super.mySQLTemplate.update("DELETE FROM tbl_library_branch WHERE branchId = ?;",
                new Object[] {branchId});
    }

    @Override
    public List<LibraryBranch> extractData(ResultSet rs) throws SQLException {
        List<LibraryBranch> branches = new ArrayList<>();
        
        while (rs.next()) {
            LibraryBranch branch = new LibraryBranch();
            branch.setBranchId(rs.getInt("branchId"));
            branch.setName(rs.getString("branchName"));
            branch.setAddress(rs.getString("branchAddress"));
            branches.add(branch);
        }
        
        return branches;
    }

}
