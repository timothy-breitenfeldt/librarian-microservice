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

import com.smoothstack.december.librarianService.entity.Genre;

@Component
public class GenreDAO extends BaseDAO<Genre> implements ResultSetExtractor<List<Genre>> {

    public Integer createGenre(String genreName) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO tbl_genre (genreName) VALUES (?);";
        Object[] parameters = new Object[] {genreName};
        PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(sql);
        PreparedStatementCreator psc = pscFactory.newPreparedStatementCreator(parameters);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        super.mySQLTemplate.update(psc, keyHolder);
        return (Integer)keyHolder.getKey();
    }
    
    public List<Genre> getGenres() throws ClassNotFoundException, SQLException {
        return super.mySQLTemplate.query("SELECT * FROM tbl_genre;", this);
    }
    
    public Genre getGenre(int genreId) throws ClassNotFoundException, SQLException {
        List<Genre> genres = super.mySQLTemplate.query("SELECT * FROM tbl_genre WHERE genreId = ?",
                new Object[] {genreId}, this);
        Genre genre = null;
        
        if (genres.size() != 0) {
            genre = genres.get(0);
        }
        
        return genre;
    }
    
    public void updateGenre(int genreId, String genreName) throws ClassNotFoundException, SQLException {
        super.mySQLTemplate.update("UPDATE tbl_genre SET genreName=? WHERE genreId = ?;",
                new Object[] {genreId, genreName}, this);
    }
    
    public void deleteGenre(int genreId) throws ClassNotFoundException, SQLException {
        super.mySQLTemplate.update("DELETE FROM tbl_genre WHERE genreId = ?;",
                new Object[] {genreId});
    }
    
    @Override
    public List<Genre> extractData(ResultSet rs) throws SQLException {
        List<Genre> genres = new ArrayList<>();
        
        while (rs.next()) {
            Genre genre = new Genre();
            genre.setGenreId(rs.getInt("genreId"));
            genre.setName(rs.getString("genreName"));
            genres.add(genre);
        }
        
        return genres;
    }

}
