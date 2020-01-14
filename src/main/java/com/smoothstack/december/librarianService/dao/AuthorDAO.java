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

import com.smoothstack.december.librarianService.entity.Author;

public class AuthorDAO extends BaseDAO<Author> implements ResultSetExtractor<List<Author>> {
    
    public Integer createAuthor(String authorName) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO tbl_author (authorName) VALUES (?);";
        Object[] parameters = new Object[] {authorName};
        PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(sql);
        PreparedStatementCreator psc = pscFactory.newPreparedStatementCreator(parameters);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        super.mySQLTemplate.update(psc, keyHolder);
        return (Integer)keyHolder.getKey();
    }
    
    public List<Author> getAuthors() throws ClassNotFoundException, SQLException {
        return super.mySQLTemplate.query("SELECT * FROM tbl_author;", this);
    }
    
    public Author getAuthor(int authorId) throws ClassNotFoundException, SQLException {
        Author author = null;
        List<Author> authors = super.mySQLTemplate.query("SELECT * FROM tbl_author WHERE authorId = ?",
                new Object[] {authorId}, this);
        
        if (authors.size() == 1) {
            author = authors.get(0);
        }
        
        return author;
    }
    
    public void updateAuthor(int authorId, String authorName) throws SQLException {
        super.mySQLTemplate.update("UPDATE tbl_author SET authorName=? WHERE authorId = ?;",
                new Object[] {authorId, authorName});
    }
    
    public void deleteAuthor(int authorId) throws ClassNotFoundException, SQLException {
        super.mySQLTemplate.update("DELETE FROM tbl_author WHERE authorId = ?;",
                new Object[] {authorId});
    }
    
    @Override
    public List<Author> extractData(ResultSet rs) throws SQLException {
        List<Author> authors = new ArrayList<>();
        
        while (rs.next()) {
            Author author = new Author();
            author.setAuthorId(rs.getInt("authorId"));
            author.setName(rs.getString("authorName"));
            authors.add(author);
        }
        
        return authors;
    }
    
}
